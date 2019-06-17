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
import com.yizhitong.userclient.event.MyInquiryDto;
import com.yizhitong.userclient.net.WebUrlUtil;
import com.yizhitong.userclient.ui.impl.MyInquiryView;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;

/**
* description ： 我的问诊单
* author : lgh
* email : 1045105946@qq.com
* date : 2019/6/14
*/
public class MyInquiryAction extends BaseAction<MyInquiryView> {

    public MyInquiryAction(RxAppCompatActivity _rxAppCompatActivity, MyInquiryView view) {
        super(_rxAppCompatActivity);
        attachView(view);
    }

    /**
     * 获取问诊列表
     *
     * @param type
     */
    public void getAskHead(int type) {
        if (type == 0) {
            post(WebUrlUtil.POST_ASKHEAD, false, service -> manager.runHttp(
                    service.PostData_1(MySharedPreferencesUtil.getSessionId(MyApplication.getContext()), CollectionsUtils.generateMap("H5ORDOC", "0", "type", "null"), WebUrlUtil.POST_ASKHEAD)));
        } else {
            post(WebUrlUtil.POST_ASKHEAD, false, service -> manager.runHttp(
                    service.PostData_1(MySharedPreferencesUtil.getSessionId(MyApplication.getContext()), CollectionsUtils.generateMap("H5ORDOC", "0", "type", type + ""), WebUrlUtil.POST_ASKHEAD)));
        }
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
                    case WebUrlUtil.POST_ASKHEAD:
                        //todo 获取问诊列表
                        if (aBoolean) {
                            L.e("xx", "输出返回结果 " + action.getUserData().toString());
                            Gson gson = new Gson();
                            try {
                                MyInquiryDto myInquiryDto = gson.fromJson(action.getUserData().toString(), new TypeToken<MyInquiryDto>() {
                                }.getType());
                                view.getAskHeadSuccessful(myInquiryDto);
                            } catch (JsonSyntaxException e) {
                                GeneralDto generalDto = gson.fromJson(action.getUserData().toString(), new TypeToken<GeneralDto>() {
                                }.getType());
                                if (generalDto.getCode() == -2) {
                                    view.onLigonError();
                                } else {
                                    view.onError(generalDto.getMsg(), generalDto.getCode());
                                }
                                return;
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
