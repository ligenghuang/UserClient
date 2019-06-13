package com.yizhitong.userclient.ui.impl;

import com.lgh.huanglib.util.base.BaseView;
import com.yizhitong.userclient.event.GeneralDto;
import com.yizhitong.userclient.event.MyPrescriptionDto;

/**
 * 我的处方
 *
 * @author lgh
 * created at 2019/5/18 0018 10:20
 */
public interface MyPrescriptionView extends BaseView {

    void isLogin();

    void isLoginSuccessful();

    void isLoginError();


    void getPrescription();

    void getPrescriptionSuccessful(MyPrescriptionDto myPrescriptionDto);

    void deletePrescription(String iuid);

    void deletePrescriptionSuccessful(GeneralDto generalDto);
}
