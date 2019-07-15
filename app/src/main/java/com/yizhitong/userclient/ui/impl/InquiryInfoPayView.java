package com.yizhitong.userclient.ui.impl;

import com.lgh.huanglib.util.base.BaseView;
import com.yizhitong.userclient.event.InquiryInfoPayDto;
import com.yizhitong.userclient.event.WeiXinPayDto;

/**
* description :问诊单支付
* author : lgh
* email : 1045105946@qq.com
* date : 2019/6/17
*/
public interface InquiryInfoPayView extends BaseView {
    void getAskHeadById(String iuid);
    void getAskHeadByIdSuccessful(InquiryInfoPayDto inquiryInfoPayDto);

    void OrderResultPay();
    void OrderResultPaySuccess(WeiXinPayDto weiXinPayDto);

    void defrayPaySuccess();
    void defrayPaySuccessSuccessful();

}
