package com.yizhitong.userclient.ui.impl;

import com.lgh.huanglib.util.base.BaseView;
import com.yizhitong.userclient.event.BannerDto;
import com.yizhitong.userclient.event.NewsBytheClassDto;
import com.yizhitong.userclient.event.NewsTypeDto;

/**
* description ： 首页
* author : lgh
* email : 1045105946@qq.com
* date : 2019/6/18
*/
public interface HomeView extends BaseView {

    void getNewsType();

    void getNewsTypeSuccessful(NewsTypeDto newsTypeDto);

    void getNewsBytheClass(String name);

    void getNewsBytheClassSuccessful(NewsBytheClassDto newsBytheClassDto);

    void getAllBanner();

    void getAllBannerSuccessful(BannerDto bannerDto);

    void isReadFlag();

    void isReadFlagSuccessful(String b);
}
