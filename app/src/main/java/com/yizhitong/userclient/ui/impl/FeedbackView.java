package com.yizhitong.userclient.ui.impl;

import com.lgh.huanglib.util.base.BaseView;
import com.yizhitong.userclient.event.GeneralDto;

/**
 * description:意见反馈
 * autour: huang
 * date: 2019/5/22 14:17
 * update: 2019/5/22
 * version:
 */
public interface FeedbackView extends BaseView {

    void getFeedback(String note, String phone);

    void getFeedbackSuccessful(GeneralDto generalDto);
}
