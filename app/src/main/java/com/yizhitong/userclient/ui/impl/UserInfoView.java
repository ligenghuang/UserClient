package com.yizhitong.userclient.ui.impl;

import com.lgh.huanglib.util.base.BaseView;
import com.yizhitong.userclient.event.PersonalDataDto;
import com.yizhitong.userclient.event.UpdataInfoDto;

public interface UserInfoView extends BaseView {

    void getUserInfo();
    void getUserInfoSuccessful(PersonalDataDto personalDataDto);

    void updataFile(String filePath);
    void updataNicename(String nicename);
    void updataIdNumber(String idNumber);
    void updataPhone(String phone);

    void updataSuccessful(UpdataInfoDto updataInfoDto);

    void logout();

    void logoutSuccessful();

}
