package com.yizhitong.userclient.ui.impl;

import com.lgh.huanglib.util.base.BaseView;
import com.yizhitong.userclient.event.DepartidDto;
import com.yizhitong.userclient.event.DoctorInfoDto;
import com.yizhitong.userclient.event.GeneralDto;
import com.yizhitong.userclient.event.HospitalListDto;

/**
* 个人信息
* @author lgh
* created at 2019/5/16 0016 10:54
*/
public interface DoctorCertifiedView extends BaseView {

    void getDoctorsInfo();

    void getDoctorsInfoSuccessful(DoctorInfoDto userInfoDto);

    void getFindDepartid();

    void getFindDepartidSuccessful(DepartidDto departidDto);

    void sevaDoctorsInfo();

    void sevaDoctorsInfoSuccessful(GeneralDto generalDto);

    void getHospitalName();
    void getHospitalNameSuccessful(HospitalListDto hospitalListDto);

}
