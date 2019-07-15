package com.yizhitong.userclient.ui.impl;

import com.lgh.huanglib.util.base.BaseView;
import com.yizhitong.userclient.event.InquiryInfoDto;
import com.yizhitong.userclient.event.WeiXinPayDto;

/**
* description ： 问诊单支付
* author : lgh
* email : 1045105946@qq.com
* date : 2019/6/19
*/
public interface RapidInterrogationPayView extends BaseView {

    void getAskHeadById();

    void getAskHeadByIdSuccessful(InquiryInfoDto inquiryInfoDto);


    void OrderResultPay();
    void OrderResultPaySuccess(WeiXinPayDto weiXinPayDto);

    void defrayPaySuccess();
    void defrayPaySuccessSuccessful();
}
