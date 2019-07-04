package com.yizhitong.userclient.ui.impl;

import com.lgh.huanglib.util.base.BaseView;
import com.yizhitong.userclient.event.GeneralDto;
import com.yizhitong.userclient.event.PatientListDto;

public interface HealthRecordsView extends BaseView {

    void getMyPatient();

    void getMyPatientSuccessful(PatientListDto patientListDto);

    void  deletePatient(String id);
    void deletePatientSuccessful(GeneralDto generalDto);
}
