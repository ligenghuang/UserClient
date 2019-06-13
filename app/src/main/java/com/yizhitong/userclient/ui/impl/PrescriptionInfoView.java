package com.yizhitong.userclient.ui.impl;

import com.lgh.huanglib.util.base.BaseView;
import com.yizhitong.userclient.event.PreInfoDto;

public interface PrescriptionInfoView extends BaseView {

    void getPreInfo(String iuid);
    void getPreInfoSuccessful(PreInfoDto preInfoDto);
}
