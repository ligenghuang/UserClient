package com.yizhitong.userclient.ui.impl;

import com.lgh.huanglib.util.base.BaseView;
import com.yizhitong.userclient.event.GeneralDto;
import com.yizhitong.userclient.event.PatientInfoDto;
import com.yizhitong.userclient.event.post.AddPatientPost;

public interface AddPatientView extends BaseView {

    void isLogin();

    void isLoginSuccessful();

    void isLoginError();

    void addPatient(AddPatientPost addPatientPost);

    void addPatientSuccessful(GeneralDto generalDto);

    void getPatient();

    void getPatientSuccessful(PatientInfoDto patientInfoDto);
}
