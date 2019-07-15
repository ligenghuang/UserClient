package com.yizhitong.userclient.ui.impl;

import com.lgh.huanglib.util.base.BaseView;
import com.yizhitong.userclient.event.WeiXinPayDto;

/**
 * description ： 处方支付页面
 * author : lgh
 * email : 1045105946@qq.com
 * date : 2019/7/12
 */
public interface PrescriptionInfoPayView extends BaseView {

    void OrderResultPay();

    void OrderResultPaySuccess(WeiXinPayDto weiXinPayDto);
    void defrayPaySuccess();
    void defrayPaySuccessSuccessful();
}
