package com.yizhitong.userclient.ui.impl;

import com.lgh.huanglib.util.base.BaseView;
import com.yizhitong.userclient.event.AskDrugListDto;
import com.yizhitong.userclient.event.InquiryInfoDto;
import com.yizhitong.userclient.event.PreInfoDto;

/**
* description ： 问诊单详情
* author : lgh
* email : 1045105946@qq.com
* date : 2019/6/17
*/
public interface InquiryInfoView extends BaseView {
    void getAskHeadById();
    void getAskHeadByIdSuccessful(InquiryInfoDto inquiryInfoDto);

    void getAskDrugByAskId();
    void getAskDrugByAskIdSuccessful(AskDrugListDto askDrugListDto);

    void getPreInfo(String iuid);
    void getPreInfoSuccessful(PreInfoDto preInfoDto);
}
