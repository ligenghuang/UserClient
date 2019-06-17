package com.yizhitong.userclient.ui.impl;

import com.lgh.huanglib.util.base.BaseView;
import com.yizhitong.userclient.event.CommonLanguageListDto;
import com.yizhitong.userclient.event.GeneralDto;
import com.yizhitong.userclient.event.MessageDetailInquiryDto;
import com.yizhitong.userclient.event.MessageDetailListDto;
import com.yizhitong.userclient.event.MessageDto;
import com.yizhitong.userclient.event.SendMessageDto;

/**
 * description:消息详情
 * autour: huang
 * date: 2019/5/23 13:36
 * update: 2019/5/23
 * version:
 */
public interface MessageDetailView extends BaseView {

    void isLogin();

    void isLoginSuccessful();

    void isLoginError();

    void getAskHeadByUserId();

    void getAskHeadByUserIdSuccessful(MessageDetailInquiryDto inquiryDetailDto);

    void getAskChat();

    void getAskChatMore();

    void getAskChatSuccessful(MessageDetailListDto messageDetailListDto);

    void sendMessage(String chat_note);

    void sendMessageSuccessful(SendMessageDto sendMessageDto);
    void sendPicturesaSuccessful(SendMessageDto sendMessageDto);

    void getCommonLanguage();
    void getCommonLanguageSuccessful(CommonLanguageListDto commonLanguageListDto);

    void sendCommonLanguage(String txt);
    void sendCommonLanguageSuccessful(GeneralDto generalDto);

    void deleteCommonLanguage(String iuid);
    void deleteCommonLanguageSuccessful(GeneralDto generalDto);

    void getMessage(MessageDto messageDto);
}
