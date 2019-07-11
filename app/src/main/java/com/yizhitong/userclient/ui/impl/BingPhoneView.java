package com.yizhitong.userclient.ui.impl;

import com.lgh.huanglib.util.base.BaseView;
import com.yizhitong.userclient.event.GeneralDto;
import com.yizhitong.userclient.event.WeiLoginDto;
import com.yizhitong.userclient.event.post.WeiXinLoginPost;

/**
* description ： 绑定手机
* author : lgh
* email : 1045105946@qq.com
* date : 2019/7/10
*/
public interface BingPhoneView extends BaseView {

    void weiXinChecks(String phone);

    void weiXinChecksSuccessful(GeneralDto generalDto);

    void weiXinLogin(WeiXinLoginPost weiXinLoginPost);

    void weiXinLoginSuccessful(WeiLoginDto generalDto);
}
