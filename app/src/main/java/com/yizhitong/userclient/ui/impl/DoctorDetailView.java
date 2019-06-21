package com.yizhitong.userclient.ui.impl;

import com.lgh.huanglib.util.base.BaseView;
import com.lgh.huanglib.util.config.GlideUtil;
import com.yizhitong.userclient.event.DoctorDetailDto;
import com.yizhitong.userclient.event.FavDoctorDto;
import com.yizhitong.userclient.event.GeneralDto;

public interface DoctorDetailView extends BaseView {

    void getDoctor();

    void getDoctorSuccessful(DoctorDetailDto doctorDetailDto);

    void getFavDoctorByuser();

    void getFavDoctorByuserSuccessful(FavDoctorDto favDoctorDto);

    void removeDoctor();

    void removeoctorSuccessful(GeneralDto generalDto);

    void concernsDoctor();

    void concernsDoctorSuccessful(GeneralDto generalDto);
}
