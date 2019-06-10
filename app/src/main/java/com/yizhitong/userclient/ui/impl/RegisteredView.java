package com.yizhitong.userclient.ui.impl;

import com.lgh.huanglib.util.base.BaseView;
import com.yizhitong.userclient.event.GeneralDto;
import com.yizhitong.userclient.event.RegisteredCodeDto;
import com.yizhitong.userclient.event.post.RegisteredPost;

/**
* 注册
* @author lgh
* created at 2019/5/14 0014 10:24
*/
public interface RegisteredView extends BaseView {

    /**
     * 注册
     */
    void registered(RegisteredPost registeredPost);

    /**
     * 注册成功
     */
    void registeredSuccessful(GeneralDto generalDto);


    /**
     * 获取验证码
     */
    void registeredCode(String userName);


    /**
     * 获取验证码成功
     */
    void CodeSuccessful(RegisteredCodeDto registeredCodeDto);

}
