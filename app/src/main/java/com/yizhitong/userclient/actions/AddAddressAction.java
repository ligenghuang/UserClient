package com.yizhitong.userclient.actions;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lgh.huanglib.actions.Action;
import com.lgh.huanglib.net.CollectionsUtils;
import com.lgh.huanglib.util.L;
import com.lgh.huanglib.util.config.MyApplication;
import com.lgh.huanglib.util.data.MySharedPreferencesUtil;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.yizhitong.userclient.event.AddressInfoDto;
import com.yizhitong.userclient.event.GeneralDto;
import com.yizhitong.userclient.event.post.AddUserAddressPost;
import com.yizhitong.userclient.net.WebUrlUtil;
import com.yizhitong.userclient.ui.impl.AddAddressView;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class AddAddressAction extends BaseAction<AddAddressView> {
    public AddAddressAction(RxAppCompatActivity _rxAppCompatActivity, AddAddressView view) {
        super(_rxAppCompatActivity);
        attachView(view);
    }

    /**
     * 新增地址
     * @param addUserAddressPost
     */
    public void addUserAddress(AddUserAddressPost addUserAddressPost) {

        post(WebUrlUtil.POST_ADDRESS_ADD, false, service -> manager.runHttp(
                service.PostData_1(MySharedPreferencesUtil.getSessionId(MyApplication.getContext()),
                        CollectionsUtils.generateMap("type", addUserAddressPost.getType(), "iuid", addUserAddressPost.getIuid(), "name", addUserAddressPost.getName(), "phone", addUserAddressPost.getPhone(),
                                "cityPicker", addUserAddressPost.getCityPicker(), "theAdd", addUserAddressPost.getTheAdd(), "defaultFlag", addUserAddressPost.getDefaultFlag()
                        ),
                        WebUrlUtil.POST_ADDRESS_ADD)
        ));
    }

    /**
     * 获取地址详情
     * @param iuid
     */
    public void getUserAddByIuid(String iuid){
        post(WebUrlUtil.POST_ADDRESS_INFO, false, service -> manager.runHttp(
                service.PostData_1(MySharedPreferencesUtil.getSessionId(MyApplication.getContext()),
                        CollectionsUtils.generateMap("iuid",iuid
                        ),
                        WebUrlUtil.POST_ADDRESS_INFO)
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
                    case WebUrlUtil.POST_ADDRESS_ADD:
                        //todo 新增地址
                        if (aBoolean) {
                            L.e("xx", "输出返回结果 " + action.getUserData().toString());
                            Gson gson = new Gson();
                            GeneralDto generalDto = gson.fromJson(action.getUserData().toString(), new TypeToken<GeneralDto>() {
                            }.getType());
                            if (generalDto.getCode() == 1) {
                                view.addUserAddressSuccessful(generalDto);
                            } else {
                                view.onError(generalDto.getMsg(), generalDto.getCode());
                            }
                            return;
                        }
                        view.onError(msg, action.getErrorType());
                        break;
                    case WebUrlUtil.POST_ADDRESS_INFO:
                        //todo 获取地址详情
                        if (aBoolean) {
                            L.e("xx", "输出返回结果 " + action.getUserData().toString());
                            Gson gson = new Gson();
                            AddressInfoDto addressInfoDto = gson.fromJson(action.getUserData().toString(), new TypeToken<AddressInfoDto>() {
                            }.getType());
                            if (addressInfoDto.getCode() == 1) {
                                view.getUserAddByIuidSuccessful(addressInfoDto);
                            } else {
                                view.onError(addressInfoDto.getMsg(), addressInfoDto.getCode());
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
