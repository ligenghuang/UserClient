package com.yizhitong.userclient.ui.impl;

import com.lgh.huanglib.util.base.BaseView;
import com.yizhitong.userclient.event.DocByAskIdDto;
import com.yizhitong.userclient.event.GeneralDto;
import com.yizhitong.userclient.event.post.AddDoctorEvalPost;

/**
 * description ： 评价
 * author : lgh
 * email : 1045105946@qq.com
 * date : 2019/6/17
 */
public interface InquiryInfoEvaluateView extends BaseView {

    void getDocByAskId();

    void getDocByAskIdSuccessful(DocByAskIdDto docByAskIdDto);

    void addDoctorEval(AddDoctorEvalPost addDoctorEvalPost);
    void addDoctorEvalSuccessful(GeneralDto generalDto);
}
