package com.yizhitong.userclient.ui.impl;

import com.lgh.huanglib.util.base.BaseView;
import com.yizhitong.userclient.event.NewsDetailDto;

/**
* description ： 新闻详情页
* author : lgh
* email : 1045105946@qq.com
* date : 2019/6/18
*/
public interface NewsDetailView extends BaseView {

    void getNewsDetail();

    void getNewsDetailSuccessful(NewsDetailDto newsDetailDto);
}
