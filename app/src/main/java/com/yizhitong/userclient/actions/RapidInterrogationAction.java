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
import com.yizhitong.userclient.event.AddAskHesdDto;
import com.yizhitong.userclient.event.AmountDto;
import com.yizhitong.userclient.event.GeneralDto;
import com.yizhitong.userclient.event.NewsDetailDto;
import com.yizhitong.userclient.event.post.AddAskHeadPost;
import com.yizhitong.userclient.net.WebUrlUtil;
import com.yizhitong.userclient.ui.impl.RapidInterrogationView;
import com.yizhitong.userclient.utils.Util;
import com.yizhitong.userclient.utils.config.MyApp;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * description ： 快速问诊
 * author : lgh
 * email : 1045105946@qq.com
 * date : 2019/6/19
 */
public class RapidInterrogationAction extends BaseAction<RapidInterrogationView> {
    public RapidInterrogationAction(RxAppCompatActivity _rxAppCompatActivity,RapidInterrogationView view) {
        super(_rxAppCompatActivity);
        attachView(view);
    }

    /**
     * 获取快速问诊费用
     */
    public void getRegisteredAmount(){
        post(WebUrlUtil.POST_DOCTOR_AMOUNT, false, service -> manager.runHttp(
                service.PostData_1(CollectionsUtils.generateMap("docIuid", "undefined"), WebUrlUtil.POST_DOCTOR_AMOUNT)));
    }

    /**
     * 上传图片
     * @param path
     */
    public void fileName(String path){
        File file = new File(path);
        //构建body
        MultipartBody.Builder build = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("file", file.getName(), RequestBody.create(MediaType.parse("image/jpg"), file));
        RequestBody requestBody = build.build();
        post(WebUrlUtil.POST_ASK_FILENAME, false, service -> manager.runHttp(
                service.PostData_String(MySharedPreferencesUtil.getSessionId(MyApplication.getContext()),requestBody, WebUrlUtil.POST_ASK_FILENAME)));
    }

    /**
     * 提交问诊单
     * @param imgs
     * @param post
     */
    public void addAskHead(List<String> imgs, AddAskHeadPost post){
        String theImg = "[]";
        for (int i = 0; i < imgs.size(); i++) {
            if (i == 0) {
                theImg = "[";
            }else {
                theImg = theImg+",";
            }
            theImg = theImg + "\""+imgs.get(i)+ "\"";
            if (i == imgs.size() - 1) {
                theImg = theImg + "]";
            }
        }
        RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("type", "0")
                .addFormDataPart("theImg" ,theImg)
                .addFormDataPart("mycars",post.toString())
                .build();
        post(WebUrlUtil.POST_ADD_ASKHEAD,false,service -> manager.runHttp(
                service.PostData_1(MySharedPreferencesUtil.getSessionId(MyApp.getContext()),requestBody,WebUrlUtil.POST_ADD_ASKHEAD)));
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
                    case WebUrlUtil.POST_DOCTOR_AMOUNT:
                        //TODO 获取问诊费用
                        if (aBoolean) {
                            L.e("xx", "输出返回结果 " + action.getUserData().toString());
                            Gson gson = new Gson();
                            try {
                                AmountDto amount = gson.fromJson(action.getUserData().toString(), new TypeToken<AmountDto>() {
                                }.getType());
                                view.getRegisteredAmountSuccessful(amount);
                                return;
                            }catch (JsonSyntaxException e){
                                GeneralDto generalDto = gson.fromJson(action.getUserData().toString(), new TypeToken<GeneralDto>() {
                                }.getType());
                                view.onError(generalDto.getMsg(),generalDto.getCode());
                                return;
                            }
                        }
//                        view.onError(msg, action.getErrorType());
                        break;
                    case WebUrlUtil.POST_ASK_FILENAME:
                        //todo 上传图片
                        if (aBoolean){
                            L.e("xx", "输出返回结果 " + action.getUserData().toString());
                            Gson gson = new Gson();
                            try {
                                String path = gson.fromJson(action.getUserData().toString(), new TypeToken<String>() {
                                }.getType());
                                view.fileNameSuccessful(path);
                                return;
                            }catch (JsonSyntaxException e){
                                GeneralDto generalDto = gson.fromJson(action.getUserData().toString(), new TypeToken<GeneralDto>() {
                                }.getType());
                                view.onError(generalDto.getMsg(),generalDto.getCode());
                                return;
                            }
                        }
                        break;
                    case WebUrlUtil.POST_ADD_ASKHEAD:
                        if (aBoolean){
                            L.e("xx", "输出返回结果 " + action.getUserData().toString());
                            Gson gson = new Gson();
                            try {
                                AddAskHesdDto path = gson.fromJson(action.getUserData().toString(), new TypeToken<AddAskHesdDto>() {
                                }.getType());
                                if (path.getCode() == 1){
                                    view.addAskHeadSuccessful(path);
                                }else {
                                    view.onError(path.getMsg(),path.getCode());
                                }
                                return;
                            }catch (JsonSyntaxException e){
                                GeneralDto generalDto = gson.fromJson(action.getUserData().toString(), new TypeToken<GeneralDto>() {
                                }.getType());
                                view.onError(generalDto.getMsg(),generalDto.getCode());
                                return;
                            }
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
