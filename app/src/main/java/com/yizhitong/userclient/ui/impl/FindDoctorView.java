package com.yizhitong.userclient.ui.impl;

import com.lgh.huanglib.util.base.BaseView;
import com.yizhitong.userclient.event.DepartListDto;

/**
 * description ： 按项目找医生
 * author : lgh
 * email : 1045105946@qq.com
 * date : 2019/6/18
 */
public interface FindDoctorView extends BaseView {

    void findDepartByAll();

    void findDepartByAllSuccessful(DepartListDto departListDto);
}
