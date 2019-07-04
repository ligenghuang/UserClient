package com.yizhitong.userclient.actions;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.lgh.huanglib.actions.Action;
import com.lgh.huanglib.net.CollectionsUtils;
import com.lgh.huanglib.util.L;
import com.lgh.huanglib.util.data.MySharedPreferencesUtil;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.yizhitong.userclient.event.PersonalDataDto;
import com.yizhitong.userclient.event.UpdataInfoDto;
import com.yizhitong.userclient.event.post.ParamPost;
import com.yizhitong.userclient.net.WebUrlUtil;
import com.yizhitong.userclient.ui.impl.UserInfoView;
import com.yizhitong.userclient.utils.config.MyApp;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class UserInfoAction extends BaseAction<UserInfoView> {
    public UserInfoAction(RxAppCompatActivity _rxAppCompatActivity, UserInfoView view) {
        super(_rxAppCompatActivity);
        attachView(view);
    }

    /**
     * 获取个人资料
     */
    public void getUserInfo(){
        post(WebUrlUtil.POST_USER_INFO, false, service -> manager.runHttp(service.PostData_1(
                MySharedPreferencesUtil.getSessionId(MyApp.getContext()), WebUrlUtil.POST_USER_INFO)));
    }

    /**
     * 修改头像
     * @param filePath
     */
    public void updataFile(String filePath) {
        File file = new File(filePath);
        //构建body
        MultipartBody.Builder build = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("file", file.getName(), RequestBody.create(MediaType.parse("image/jpeg"), file));
        RequestBody requestBody = build.build();
        post(WebUrlUtil.POST_USER_UPDATE, false, service -> manager.runHttp(service.PostData_1(
                MySharedPreferencesUtil.getSessionId(MyApp.getContext()), requestBody, WebUrlUtil.POST_USER_UPDATE)));
    }

    /**
     * 修改昵称
     * @param nicename
     */
    public void updataNicename(String nicename){
        ParamPost paramPost = new ParamPost();
        paramPost.setNicename(nicename);
        MultipartBody.Builder build = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("param",paramPost.toNicename());
        RequestBody requestBody = build.build();
        post(WebUrlUtil.POST_USER_UPDATE, false, service -> manager.runHttp(service.PostData_1(
                MySharedPreferencesUtil.getSessionId(MyApp.getContext()), requestBody, WebUrlUtil.POST_USER_UPDATE)));
    }

    /**
     * 修改身份证
     * @param idNumber
     */
    public void updataIdNumber(String idNumber){
        ParamPost paramPost = new ParamPost();
        paramPost.setIdnumber(idNumber);
        MultipartBody.Builder build = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("param",paramPost.toIdNumber());
        RequestBody requestBody = build.build();
        post(WebUrlUtil.POST_USER_UPDATE, false, service -> manager.runHttp(service.PostData_1(
                MySharedPreferencesUtil.getSessionId(MyApp.getContext()), requestBody, WebUrlUtil.POST_USER_UPDATE)));
    }

    /**
     * 修改手机号码
     * @param phone
     */
    public void updataPhone(String phone){
        ParamPost paramPost = new ParamPost();
        paramPost.setPhome(phone);
        MultipartBody.Builder build = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("param",paramPost.toPhone());
        RequestBody requestBody = build.build();
        post(WebUrlUtil.POST_USER_UPDATE, false, service -> manager.runHttp(service.PostData_1(
                MySharedPreferencesUtil.getSessionId(MyApp.getContext()), requestBody, WebUrlUtil.POST_USER_UPDATE)));
    }

    /**
     * 退出登录
     */
    public void logout(){
        post(WebUrlUtil.POST_LOGOUT,false,service -> manager.runHttp(
                service.PostData_String(MySharedPreferencesUtil.getSessionId(MyApp.getContext()), CollectionsUtils.generateMap("H5ORDOC","0"),WebUrlUtil.POST_LOGOUT)
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
                    case WebUrlUtil.POST_USER_INFO:
                        //todo 获取个人资料
                        if (aBoolean) {
                            L.e("xx", "输出返回结果 " + action.getUserData().toString());
                            Gson gson = new Gson();
                            try{
                                PersonalDataDto personalDataDto = gson.fromJson(action.getUserData().toString(), new TypeToken<PersonalDataDto>() {
                                }.getType());
                                if (personalDataDto.getCode() == 1){
                                    view.getUserInfoSuccessful(personalDataDto);
                                }else {
                                    view.onError(personalDataDto.getMsg(),personalDataDto.getCode());
                                }
                            }catch (JsonSyntaxException e){

                            }
                            return;
                        }
                        view.onError(msg,action.getErrorType());
                        break;
                    case WebUrlUtil.POST_USER_UPDATE:
                        //TODO 修改资料
                        if (aBoolean) {
                            L.e("xx", "输出返回结果 " + action.getUserData().toString());
                            Gson gson = new Gson();
                           try{
                               UpdataInfoDto updataInfoDto = gson.fromJson(action.getUserData().toString(), new TypeToken<UpdataInfoDto>() {
                               }.getType());
                               if (updataInfoDto.getCode() == 1){
                                   view.updataSuccessful(updataInfoDto);
                               }else {
                                   view.onError(updataInfoDto.getMsg(),updataInfoDto.getCode());
                               }
                           }catch (JsonSyntaxException e){

                           }
                            return;
                        }
                        view.onError(msg,action.getErrorType());
                        break;
                    case WebUrlUtil.POST_LOGOUT:
                        if (aBoolean) {
                            L.e("xx", "输出返回结果 " + action.getUserData().toString());
                            Gson gson = new Gson();
                            String generalDto = gson.fromJson(action.getUserData().toString(), new TypeToken<String>() {
                            }.getType());
                            view.logoutSuccessful();
                            return;
                        }
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
