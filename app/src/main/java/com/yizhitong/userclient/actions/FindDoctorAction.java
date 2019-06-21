package com.yizhitong.userclient.actions;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lgh.huanglib.actions.Action;
import com.lgh.huanglib.net.CollectionsUtils;
import com.lgh.huanglib.util.L;
import com.lgh.huanglib.util.config.MyApplication;
import com.lgh.huanglib.util.data.MySharedPreferencesUtil;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.yizhitong.userclient.event.DepartListDto;
import com.yizhitong.userclient.event.DepartidDto;
import com.yizhitong.userclient.event.FindDoctorDto;
import com.yizhitong.userclient.event.post.FindDoctorPost;
import com.yizhitong.userclient.net.WebUrlUtil;
import com.yizhitong.userclient.ui.impl.FindDoctorView;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;

/**
 * description ： 搜索医生
 * author : lgh
 * email : 1045105946@qq.com
 * date : 2019/6/20
 */
public class FindDoctorAction extends BaseAction<FindDoctorView> {
    public FindDoctorAction(RxAppCompatActivity _rxAppCompatActivity, FindDoctorView view) {
        super(_rxAppCompatActivity);
        attachView(view);
    }

    /**
     * 获取科室
     */
    public void findDepartByAll() {
        post(WebUrlUtil.POST_DEPART_ALL, false, service -> manager.runHttp(
                service.PostData_1(MySharedPreferencesUtil.getSessionId(MyApplication.getContext()), WebUrlUtil.POST_DEPART_ALL)));
    }

    /**
     * 搜索医生
     *
     * @param post
     */
    public void findDoctor(FindDoctorPost post) {
        post(WebUrlUtil.POST_FINDDOCTOR_LIST, false, service -> manager.runHttp(
                service.PostData_1(MySharedPreferencesUtil.getSessionId(MyApplication.getContext()),
                        CollectionsUtils.generateMap("departid", post.getDepartid(),
                                "region", post.getRegion(), "sort", post.getSort(), "chufang", post.getChufang()
                                , "the_level", post.getThe_level(), "JIAGEQ", post.getJIAGEQ()
                        ),
                        WebUrlUtil.POST_FINDDOCTOR_LIST)));
    }

    /**
     * 关键字搜索医生
     *
     * @param condition
     */
    public void findDoctorCondition(String condition) {
        post(WebUrlUtil.POST_FINDDOCTOR_CONDITION_LIST, false, service -> manager.runHttp(
                service.PostData_1(MySharedPreferencesUtil.getSessionId(MyApplication.getContext()),
                        CollectionsUtils.generateMap("condition", condition), WebUrlUtil.POST_FINDDOCTOR_CONDITION_LIST)));
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
                    case WebUrlUtil.POST_DEPART_ALL:
                        if (aBoolean) {
                            L.e("xx", "输出返回结果 " + action.getUserData().toString());
                            Gson gson = new Gson();
                            DepartListDto departListDto = gson.fromJson(action.getUserData().toString(), new TypeToken<DepartListDto>() {
                            }.getType());
                            if (departListDto.getCode() == 1) {
                                view.findDepartByAllSuccessful(departListDto);
                            } else {
                                view.onError(departListDto.getMsg(), departListDto.getCode());
                            }
                            return;
                        }
                        view.onError(msg, action.getErrorType());
                        break;
                    case WebUrlUtil.POST_FINDDOCTOR_LIST:
                    case WebUrlUtil.POST_FINDDOCTOR_CONDITION_LIST:
                        if (aBoolean) {
                            L.e("xx", "输出返回结果 " + action.getUserData().toString());
                            Gson gson = new Gson();
                            FindDoctorDto findDoctorDto = gson.fromJson(action.getUserData().toString(), new TypeToken<FindDoctorDto>() {
                            }.getType());
                            if (findDoctorDto.getCode() == 1) {
                                view.findDoctorSuccessful(findDoctorDto);
                            } else {
                                view.onError(findDoctorDto.getMsg(), findDoctorDto.getCode());
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
