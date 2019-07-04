package com.yizhitong.userclient.ui.impl;

import com.lgh.huanglib.util.base.BaseView;
import com.yizhitong.userclient.event.AddAskHesdDto;
import com.yizhitong.userclient.event.AmountDto;
import com.yizhitong.userclient.event.GeneralDto;
import com.yizhitong.userclient.event.post.AddAskHeadPost;

import java.util.List;

/**
* description ： 快速问诊
* author : lgh
* email : 1045105946@qq.com
* date : 2019/6/19
*/
public interface RapidInterrogationView extends BaseView {

    void getRegisteredAmount();

    void getRegisteredAmountSuccessful(AmountDto amount);

    void fileName(String path);

    void fileNameSuccessful(String path);

    void addAskHead(List<String> imgs, AddAskHeadPost post);

    void addAskHeadSuccessful(AddAskHesdDto generalDto);
}
