package com.yizhitong.userclient.ui.impl;

import com.lgh.huanglib.util.base.BaseView;
import com.yizhitong.userclient.event.MessageDto;
import com.yizhitong.userclient.event.MessageListDto;

public interface MessageView extends BaseView {

//    void isLogin();
//
//    void isLoginSuccessful();
//
//    void isLoginError();

    void getMessageList();

    void getMessageListSuccessful(MessageListDto messageListDto);

    void getMessage(MessageDto messageDto);
}
