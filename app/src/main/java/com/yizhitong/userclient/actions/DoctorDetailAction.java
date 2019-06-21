package com.yizhitong.userclient.actions;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lgh.huanglib.actions.Action;
import com.lgh.huanglib.net.CollectionsUtils;
import com.lgh.huanglib.util.L;
import com.lgh.huanglib.util.config.MyApplication;
import com.lgh.huanglib.util.data.MySharedPreferencesUtil;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.yizhitong.userclient.event.DepartListDto;
import com.yizhitong.userclient.event.DoctorDetailDto;
import com.yizhitong.userclient.event.FavDoctorDto;
import com.yizhitong.userclient.event.GeneralDto;
import com.yizhitong.userclient.net.WebUrlUtil;
import com.yizhitong.userclient.ui.impl.DoctorDetailView;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;

/**
* description ： 医生详情
* author : lgh
* email : 1045105946@qq.com
* date : 2019/6/21
*/
public class DoctorDetailAction extends BaseAction<DoctorDetailView> {
    public DoctorDetailAction(RxAppCompatActivity _rxAppCompatActivity,DoctorDetailView view) {
        super(_rxAppCompatActivity);
        attachView(view);
    }

    /**
     * 获取医生详情
     * @param iuid
     */
    public void getDoctor(String iuid){
        post(WebUrlUtil.POST_DOCTOR_DETAIL, false, service -> manager.runHttp(
                service.PostData_1(MySharedPreferencesUtil.getSessionId(MyApplication.getContext()),
                        CollectionsUtils.generateMap("IUID",iuid),WebUrlUtil.POST_DOCTOR_DETAIL)));
    }

    /**
     * 是否关注
     * @param iuid
     */
    public void getFavDoctorByuser(String iuid){
        post(WebUrlUtil.POST_FAV_DOCTOR, false, service -> manager.runHttp(
                service.PostData_1(MySharedPreferencesUtil.getSessionId(MyApplication.getContext()),
                        CollectionsUtils.generateMap("IUID",iuid),WebUrlUtil.POST_FAV_DOCTOR)));
    }

    /**
     * 取消关注
     */
    public void removeDoctor(String iuid){
        post(WebUrlUtil.POST_REMOVE_DOCTOR, false, service -> manager.runHttp(
                service.PostData_1(MySharedPreferencesUtil.getSessionId(MyApplication.getContext()),
                        CollectionsUtils.generateMap("doctorid",iuid),WebUrlUtil.POST_REMOVE_DOCTOR)));
    }


    /**
     * 关注
     */
    public void concernsDoctor(String iuid){
        post(WebUrlUtil.POST_CONCERNS_DOCOTR, false, service -> manager.runHttp(
                service.PostData_1(MySharedPreferencesUtil.getSessionId(MyApplication.getContext()),
                        CollectionsUtils.generateMap("doctorid",iuid),WebUrlUtil.POST_CONCERNS_DOCOTR)));
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
                    case WebUrlUtil.POST_DOCTOR_DETAIL:
                        if (aBoolean){
                            L.e("xx", "输出返回结果 " + action.getUserData().toString());
                            Gson gson = new Gson();
                            DoctorDetailDto doctorDetailDto = gson.fromJson(action.getUserData().toString(), new TypeToken<DoctorDetailDto>() {
                            }.getType());
                            if (doctorDetailDto.getCode() == 1) {
                                view.getDoctorSuccessful(doctorDetailDto);
                            } else {
                                view.onError(doctorDetailDto.getMsg(), doctorDetailDto.getCode());
                            }
                            return;
                        }
                        view.onError(msg,action.getErrorType());
                        break;
                    case WebUrlUtil.POST_FAV_DOCTOR:
                        if (aBoolean){
                            L.e("xx", "输出返回结果 " + action.getUserData().toString());
                            Gson gson = new Gson();
                            FavDoctorDto favDoctorDto = gson.fromJson(action.getUserData().toString(), new TypeToken<FavDoctorDto>() {
                            }.getType());
                            if (favDoctorDto.getCode() == 1) {
                                view.getFavDoctorByuserSuccessful(favDoctorDto);
                            } else {
                                view.onError(favDoctorDto.getMsg(), favDoctorDto.getCode());
                            }
                            return;
                        }
                        view.onError(msg,action.getErrorType());
                        break;
                    case WebUrlUtil.POST_REMOVE_DOCTOR:
                        if (aBoolean){
                            L.e("xx", "输出返回结果 " + action.getUserData().toString());
                            Gson gson = new Gson();
                            GeneralDto generalDto = gson.fromJson(action.getUserData().toString(), new TypeToken<GeneralDto>() {
                            }.getType());
                            if (generalDto.getCode() == 1) {
                                view.removeoctorSuccessful(generalDto);
                            } else {
                                view.onError(generalDto.getMsg(), generalDto.getCode());
                            }
                            return;
                        }
                        view.onError(msg,action.getErrorType());
                        break;
                    case WebUrlUtil.POST_CONCERNS_DOCOTR:
                        if (aBoolean){
                            L.e("xx", "输出返回结果 " + action.getUserData().toString());
                            Gson gson = new Gson();
                            GeneralDto generalDto = gson.fromJson(action.getUserData().toString(), new TypeToken<GeneralDto>() {
                            }.getType());
                            if (generalDto.getCode() == 1) {
                                view.concernsDoctorSuccessful(generalDto);
                            } else {
                                view.onError(generalDto.getMsg(), generalDto.getCode());
                            }
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
