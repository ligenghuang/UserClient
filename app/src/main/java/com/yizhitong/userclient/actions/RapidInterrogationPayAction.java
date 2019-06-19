package com.yizhitong.userclient.actions;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lgh.huanglib.actions.Action;
import com.lgh.huanglib.net.CollectionsUtils;
import com.lgh.huanglib.util.L;
import com.lgh.huanglib.util.config.MyApplication;
import com.lgh.huanglib.util.data.MySharedPreferencesUtil;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.yizhitong.userclient.event.InquiryInfoDto;
import com.yizhitong.userclient.net.WebUrlUtil;
import com.yizhitong.userclient.ui.impl.RapidInterrogationPayView;
import com.yizhitong.userclient.utils.base.UserBaseActivity;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;

/**
 * description ： 问诊单支付
 * author : lgh
 * email : 1045105946@qq.com
 * date : 2019/6/19
 */
public class RapidInterrogationPayAction extends BaseAction<RapidInterrogationPayView>  {
    public RapidInterrogationPayAction(RxAppCompatActivity _rxAppCompatActivity,RapidInterrogationPayView view) {
        super(_rxAppCompatActivity);
        attachView(view);
    }

    public void getAskHeadById(String id){
        post(WebUrlUtil.POST_ASKHEAD_BY_ID, false, service -> manager.runHttp(
                service.PostData_1(MySharedPreferencesUtil.getSessionId(MyApplication.getContext()),
                        CollectionsUtils.generateMap("IUID",id
                        ),
                        WebUrlUtil.POST_ASKHEAD_BY_ID)
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
                    case WebUrlUtil.POST_ASKHEAD_BY_ID:
                        if (aBoolean){
                            L.e("xx", "输出返回结果 " + action.getUserData().toString());
                            Gson gson = new Gson();
                            InquiryInfoDto inquiryInfoDto = gson.fromJson(action.getUserData().toString(), new TypeToken<InquiryInfoDto>() {
                            }.getType());
                            if (inquiryInfoDto.getCode() == 1) {
                                view.getAskHeadByIdSuccessful(inquiryInfoDto);
                            } else {
                                view.onError(inquiryInfoDto.getMsg(), inquiryInfoDto.getCode());
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
