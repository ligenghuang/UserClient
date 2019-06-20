package com.yizhitong.userclient.ui.impl;

import com.lgh.huanglib.util.base.BaseView;
import com.yizhitong.userclient.event.DepartListDto;
import com.yizhitong.userclient.event.DepartidDto;
import com.yizhitong.userclient.event.FindDoctorDto;
import com.yizhitong.userclient.event.post.FindDoctorPost;

/**
* description ： 搜索医生
* author : lgh
* email : 1045105946@qq.com
* date : 2019/6/20
*/
public interface FindDoctorView extends BaseView {
    void findDepartByAll();

    void findDepartByAllSuccessful(DepartListDto departListDto);

    void findDoctor(FindDoctorPost post);

    void findDoctorSuccessful(FindDoctorDto findDoctorDto);
}
