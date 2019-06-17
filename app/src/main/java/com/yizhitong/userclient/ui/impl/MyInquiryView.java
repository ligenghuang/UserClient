package com.yizhitong.userclient.ui.impl;

import com.lgh.huanglib.util.base.BaseView;
import com.yizhitong.userclient.event.MyInquiryDto;

/**
* description ： 我的问诊单
* author : lgh
* email : 1045105946@qq.com
* date : 2019/6/14
*/
public interface MyInquiryView extends BaseView {

    void getAskHead();

    void getAskHeadSuccessful(MyInquiryDto myInquiryDto);

}
