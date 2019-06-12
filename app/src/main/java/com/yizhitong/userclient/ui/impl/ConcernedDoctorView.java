package com.yizhitong.userclient.ui.impl;

import com.lgh.huanglib.util.base.BaseView;
import com.yizhitong.userclient.event.ConcernedDoctorListDto;

public interface ConcernedDoctorView extends BaseView {

    void isLogin();

    void isLoginSuccessful();

    void isLoginError();

    void findFavDoctors();

    void findFavDoctorsSuccessful(ConcernedDoctorListDto concernedDoctorListDto);
}
