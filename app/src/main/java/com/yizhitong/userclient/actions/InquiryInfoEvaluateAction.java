package com.yizhitong.userclient.actions;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lgh.huanglib.actions.Action;
import com.lgh.huanglib.net.CollectionsUtils;
import com.lgh.huanglib.util.L;
import com.lgh.huanglib.util.config.MyApplication;
import com.lgh.huanglib.util.data.MySharedPreferencesUtil;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.yizhitong.userclient.event.DocByAskIdDto;
import com.yizhitong.userclient.event.GeneralDto;
import com.yizhitong.userclient.event.InquiryInfoPayDto;
import com.yizhitong.userclient.event.post.AddDoctorEvalPost;
import com.yizhitong.userclient.net.WebUrlUtil;
import com.yizhitong.userclient.ui.impl.InquiryInfoEvaluateView;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;

/**
* description ： 评价
* author : lgh
* email : 1045105946@qq.com
* date : 2019/6/17
*/
public class InquiryInfoEvaluateAction extends BaseAction<InquiryInfoEvaluateView> {
    public InquiryInfoEvaluateAction(RxAppCompatActivity _rxAppCompatActivity,InquiryInfoEvaluateView view) {
        super(_rxAppCompatActivity);
        attachView(view);
    }

    /**
     * 获取医生信息
     * @param iuid
     */
    public void getDocByAskId(String iuid){
        post(WebUrlUtil.POST_DOCTOR_BYID, false, service -> manager.runHttp(
                service.PostData_1(MySharedPreferencesUtil.getSessionId(MyApplication.getContext()),
                        CollectionsUtils.generateMap("askId",iuid
                        ),
                        WebUrlUtil.POST_DOCTOR_BYID)
        ));
    }

    /**
     * 提交评价
     * @param addDoctorEvalPost
     */
    public void addDoctorEval(AddDoctorEvalPost addDoctorEvalPost){
        post(WebUrlUtil.POST_ADD_DOCTOR_EVAL, false, service -> manager.runHttp(
                service.PostData_1(MySharedPreferencesUtil.getSessionId(MyApplication.getContext()),
                        CollectionsUtils.generateMap("askId",addDoctorEvalPost.getAskId(),"the_note",addDoctorEvalPost.getThe_note(),
                                "the_star",addDoctorEvalPost.getThe_star(),"anonymous_flag",addDoctorEvalPost.getAnonymous_flag()
                        ),
                        WebUrlUtil.POST_ADD_DOCTOR_EVAL)
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
                    case WebUrlUtil.POST_DOCTOR_BYID:
                        //获取问诊单信息
                        if (aBoolean){
                            L.e("xx", "输出返回结果 " + action.getUserData().toString());
                            Gson gson = new Gson();
                            DocByAskIdDto docByAskIdDto = gson.fromJson(action.getUserData().toString(), new TypeToken<DocByAskIdDto>() {
                            }.getType());
                            if (docByAskIdDto.getCode() == 1) {
                                view.getDocByAskIdSuccessful(docByAskIdDto);
                            } else {
                                view.onError(docByAskIdDto.getMsg(), docByAskIdDto.getCode());
                            }
                            return;
                        }
                        view.onError(msg, action.getErrorType());
                        break;
                    case WebUrlUtil.POST_ADD_DOCTOR_EVAL:
                        //todo 添加评价
                        if (aBoolean){
                            L.e("xx", "输出返回结果 " + action.getUserData().toString());
                            Gson gson = new Gson();
                            GeneralDto generalDto = gson.fromJson(action.getUserData().toString(), new TypeToken<GeneralDto>() {
                            }.getType());
                           if (generalDto.getCode() == 1){
                               view.addDoctorEvalSuccessful(generalDto);
                           }else {
                               view.onError(generalDto.getMsg(),generalDto.getCode());
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
