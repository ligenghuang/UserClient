package com.yizhitong.userclient.actions;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.lgh.huanglib.actions.Action;
import com.lgh.huanglib.net.CollectionsUtils;
import com.lgh.huanglib.util.L;
import com.lgh.huanglib.util.config.MyApplication;
import com.lgh.huanglib.util.data.MySharedPreferencesUtil;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.yizhitong.userclient.event.GeneralDto;
import com.yizhitong.userclient.event.InquiryInfoPayDto;
import com.yizhitong.userclient.event.WeiXinPayDto;
import com.yizhitong.userclient.event.WeiXinPaySuccessDto;
import com.yizhitong.userclient.net.WebUrlUtil;
import com.yizhitong.userclient.ui.impl.InquiryInfoPayView;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;

/**
* description ：问诊单支付
* author : lgh
* email : 1045105946@qq.com
* date : 2019/6/17
*/
public class InquiryInfoPayAction extends BaseAction<InquiryInfoPayView> {
    public InquiryInfoPayAction(RxAppCompatActivity _rxAppCompatActivity,InquiryInfoPayView view) {
        super(_rxAppCompatActivity);
        attachView(view);
    }

    /**
     * 获取问诊单详情
     * @param iuid
     */
    public void getAskHeadById(String iuid){
        post(WebUrlUtil.POST_ASKHEAD_BYID, false, service -> manager.runHttp(
                service.PostData_1(MySharedPreferencesUtil.getSessionId(MyApplication.getContext()),
                        CollectionsUtils.generateMap("iuid",iuid
                        ),
                        WebUrlUtil.POST_ASKHEAD_BYID)
        ));
    }

    /**
     * 支付
     * @param id
     */
    public void OrderResultPay(String id){
        post(WebUrlUtil.POST_WEIXIN_PAY, false, service -> manager.runHttp(
                service.PostData_1(MySharedPreferencesUtil.getSessionId(MyApplication.getContext()),
                        CollectionsUtils.generateMap("type",0,"id",id
                        ),
                        WebUrlUtil.POST_WEIXIN_PAY)
        ));
    }

    /**
     * 问诊单支付成功
     * @param id
     * @param money
     */
    public void defrayPaySuccess(String id,String money){
        post(WebUrlUtil.POST_DEFRAY, false, service -> manager.runHttp(
                service.PostData_1(MySharedPreferencesUtil.getSessionId(MyApplication.getContext()),
                        CollectionsUtils.generateMap("pay_moeny",money,"askId",id
                        ),
                        WebUrlUtil.POST_DEFRAY)
        ));
    }

    /**
     * sticky:表明优先接收最高级  threadMode = ThreadMode.MAIN：表明在主线程
     *
     * @param action
     */
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void MessageEvent(final Action action) {
        L.e("xx", "action   接收到数据更新....." + action.getIdentifying() + " action.getErrorType() : " + action.getErrorType());

        final String msg = action.getMsg(action);
        Observable.just(action.getErrorType())
                .all(new Predicate<Integer>() {
                    @Override
                    public boolean test(Integer integer) throws Exception {
                        return (integer == 200);
                    }
                }).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {
                // 输出返回结果
                L.e("xx", "输出返回结果 " + aBoolean);

                switch (action.getIdentifying()) {
                    case WebUrlUtil.POST_ASKHEAD_BYID:
                        //获取问诊单信息
                        if (aBoolean){
                            L.e("xx", "输出返回结果 " + action.getUserData().toString());
                            Gson gson = new Gson();
                            InquiryInfoPayDto inquiryInfoPayDto = gson.fromJson(action.getUserData().toString(), new TypeToken<InquiryInfoPayDto>() {
                            }.getType());
                            if (inquiryInfoPayDto.getCode() == 1) {
                                view.getAskHeadByIdSuccessful(inquiryInfoPayDto);
                            } else {
                                view.onError(inquiryInfoPayDto.getMsg(), inquiryInfoPayDto.getCode());
                            }
                            return;
                        }
                        view.onError(msg, action.getErrorType());
                        break;
                    case WebUrlUtil.POST_WEIXIN_PAY:
                        if (aBoolean){
                            L.e("xx", "输出返回结果 " + action.getUserData().toString());
                            Gson gson = new Gson();
                           try {
                               WeiXinPayDto weiXinPayDto = gson.fromJson(action.getUserData().toString(), new TypeToken<WeiXinPayDto>() {
                               }.getType());
                               L.e("lgh_json",weiXinPayDto.toString());
                               if (weiXinPayDto.getCode() == 1) {
                                   view.OrderResultPaySuccess(weiXinPayDto);
                               } else {
                                   view.onError(weiXinPayDto.getMsg(), weiXinPayDto.getCode());
                               }
                           }catch (JsonSyntaxException e){
                               GeneralDto generalDto =  gson.fromJson(action.getUserData().toString(), new TypeToken<GeneralDto>() {
                               }.getType());
                               view.onError(generalDto.getMsg(),generalDto.getCode());
                           }

                            return;
                        }
                        view.onError(msg, action.getErrorType());
                        break;
                    case WebUrlUtil.POST_DEFRAY:
                        if (aBoolean){
                            L.e("xx", "输出返回结果 " + action.getUserData().toString());
                            Gson gson = new Gson();
                            WeiXinPaySuccessDto generalDto = gson.fromJson(action.getUserData().toString(), new TypeToken<WeiXinPaySuccessDto>() {
                            }.getType());
                            if (generalDto.getCode() == 1) {
                                view.defrayPaySuccessSuccessful();
                            } else {
                                view.onError(generalDto.getMsg(), generalDto.getCode());
                            }
                            return;
                        }
                        view.onError(msg, action.getErrorType());
                        break;

                }

            }

        });

    }

    public void toRegister() {

        register(this);
    }

    public void toUnregister() {

        unregister(this);
    }
}
