package com.yizhitong.userclient.ui.impl;

import com.lgh.huanglib.util.base.BaseView;
import com.yizhitong.userclient.event.LoginDto;

/**
* 登录
* @author lgh
* created at 2019/5/13 0013 15:05
*/
public interface LoginView extends BaseView {

    /**
     * 登录
     */
    void Login(String username, String pwd);

    /**
     * 登录成功
     */
    void LoginSuccessful(LoginDto generalDto);

//    /**
//     * 授权登录 成功
//     */
//    void authorizationSuccessful();
//
//    /**
//     * 授权登录 失败
//     */
//    void authorizationError(String m, int type);
}
