package com.yizhitong.userclient.actions;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lgh.huanglib.actions.Action;
import com.lgh.huanglib.net.CollectionsUtils;
import com.lgh.huanglib.util.L;
import com.lgh.huanglib.util.config.MyApplication;
import com.lgh.huanglib.util.data.MySharedPreferencesUtil;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.yizhitong.userclient.event.GeneralDto;
import com.yizhitong.userclient.event.PatientListDto;
import com.yizhitong.userclient.net.WebUrlUtil;
import com.yizhitong.userclient.ui.impl.HealthRecordsView;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;

public class HealthRecordsAction extends BaseAction<HealthRecordsView> {
    public HealthRecordsAction(RxAppCompatActivity _rxAppCompatActivity,HealthRecordsView view) {
        super(_rxAppCompatActivity);
        attachView(view);
    }

    /**
     * 获取问诊人信息列表
     */
    public void getMyPatient(){
        post(WebUrlUtil.POST_MY_PATIENT, false, service -> manager.runHttp(
                service.PostData_1(MySharedPreferencesUtil.getSessionId(MyApplication.getContext()),
                        WebUrlUtil.POST_MY_PATIENT)
        ));
    }

    /**
     * 删除问诊人
     * @param id
     */
    public void deletePatient(String id){
        post(WebUrlUtil.POST_DELETE_PATIENT, false, service -> manager.runHttp(
                service.PostData_1(MySharedPreferencesUtil.getSessionId(MyApplication.getContext()),
                        CollectionsUtils.generateMap("patientId",id),
                        WebUrlUtil.POST_DELETE_PATIENT)
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
                    case WebUrlUtil.POST_MY_PATIENT:
                        //todo 获取问诊人信息列表
                        if (aBoolean){
                            L.e("xx", "输出返回结果 " + action.getUserData().toString());
                            Gson gson = new Gson();
                            PatientListDto patientListDto = gson.fromJson(action.getUserData().toString(), new TypeToken<PatientListDto>() {
                            }.getType());
                            if (patientListDto.getCode() == 1) {
                                view.getMyPatientSuccessful(patientListDto);
                            } else {
                                view.onError(patientListDto.getMsg(), patientListDto.getCode());
                            }
                            return;
                        }
                        view.onError(msg, action.getErrorType());
                        break;
                    case WebUrlUtil.POST_DELETE_PATIENT:
                        //todo 删除问诊人
                        if (aBoolean){
                            L.e("xx", "输出返回结果 " + action.getUserData().toString());
                            Gson gson = new Gson();
                            GeneralDto generalDto = gson.fromJson(action.getUserData().toString(), new TypeToken<GeneralDto>() {
                            }.getType());
                           view.deletePatientSuccessful(generalDto);
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
