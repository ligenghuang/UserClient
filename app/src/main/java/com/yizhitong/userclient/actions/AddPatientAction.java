package com.yizhitong.userclient.actions;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lgh.huanglib.actions.Action;
import com.lgh.huanglib.net.CollectionsUtils;
import com.lgh.huanglib.util.L;
import com.lgh.huanglib.util.config.MyApplication;
import com.lgh.huanglib.util.data.MySharedPreferencesUtil;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.yizhitong.userclient.event.PatientInfoDto;
import com.yizhitong.userclient.event.post.AddPatientPost;
import com.yizhitong.userclient.net.WebUrlUtil;
import com.yizhitong.userclient.ui.impl.AddPatientView;
import com.yizhitong.userclient.utils.data.MySp;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;

public class AddPatientAction extends BaseAction<AddPatientView> {
    public AddPatientAction(RxAppCompatActivity _rxAppCompatActivity, AddPatientView view) {
        super(_rxAppCompatActivity);
        attachView(view);
    }


    public void isLogin() {
        post(WebUrlUtil.POST_ISLOGIN, false, service -> manager.runHttp(
                service.PostData_String(MySharedPreferencesUtil.getSessionId(MyApplication.getContext()), CollectionsUtils.generateMap("userId", MySp.getToken(MyApplication.getContext())), WebUrlUtil.POST_ISLOGIN)));
    }

    /**
     * 获取问诊人详情
     * @param iuid
     */
    public void getPatient(String iuid){
        post(WebUrlUtil.POST_PATIENT_INFO, false, service -> manager.runHttp(
                service.PostData_1(MySharedPreferencesUtil.getSessionId(MyApplication.getContext()), CollectionsUtils.generateMap("iuid", iuid), WebUrlUtil.POST_PATIENT_INFO)));
    }

    public void addPatient(int type, AddPatientPost patientPost){
        post(WebUrlUtil.POST_ADD_PATIENT_INFO, false, service -> manager.runHttp(
                service.PostData_1(MySharedPreferencesUtil.getSessionId(MyApplication.getContext()), CollectionsUtils.generateMap("type", type,
                        "patient",patientPost.toString()), WebUrlUtil.POST_ADD_PATIENT_INFO)));

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
                    case WebUrlUtil.POST_ISLOGIN:
                        if (aBoolean) {
                            L.e("xx", "输出返回结果 " + action.getUserData().toString());
                            Gson gson = new Gson();
                            String generalDto = gson.fromJson(action.getUserData().toString(), new TypeToken<String>() {
                            }.getType());
                            if (generalDto.equals("1")) {
                                view.isLoginSuccessful();
                                return;
                            }
                            view.isLoginError();
                            return;
                        }
//                        view.isLoginError();
                        break;
                    case WebUrlUtil.POST_PATIENT_INFO:
                        if (aBoolean) {
                            L.e("xx", "输出返回结果 " + action.getUserData().toString());
                            Gson gson = new Gson();
                            PatientInfoDto patientInfoDto = gson.fromJson(action.getUserData().toString(), new TypeToken<PatientInfoDto>() {
                            }.getType());
                            view.getPatientSuccessful(patientInfoDto);
                            return;
                        }
                        view.onError(msg,action.getErrorType());
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
