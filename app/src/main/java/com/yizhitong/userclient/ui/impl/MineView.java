package com.yizhitong.userclient.ui.impl;

import com.lgh.huanglib.util.base.BaseView;
import com.yizhitong.userclient.event.GeneralDto;
import com.yizhitong.userclient.event.UserInfoDto;

/**
* 我的
* @author lgh
* created at 2019/5/15 0015 16:02
*/
public interface MineView extends BaseView {

    void isLogin();

    void isLoginSuccessful();

    void isLoginError();

    void getUserInfo();

    void getUserInfoSuccessful(UserInfoDto userInfoDto);

}
