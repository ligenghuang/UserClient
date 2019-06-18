package com.yizhitong.userclient.actions;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.lgh.huanglib.actions.Action;
import com.lgh.huanglib.net.CollectionsUtils;
import com.lgh.huanglib.util.L;
import com.lgh.huanglib.util.config.MyApplication;
import com.lgh.huanglib.util.data.MySharedPreferencesUtil;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.yizhitong.userclient.event.NewsBytheClassDto;
import com.yizhitong.userclient.event.NewsTypeDto;
import com.yizhitong.userclient.net.WebUrlUtil;
import com.yizhitong.userclient.ui.impl.HomeView;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;

/**
* description ： 首页
* author : lgh
* email : 1045105946@qq.com
* date : 2019/6/18
*/
public class HomeAction extends BaseAction<HomeView>{
    public HomeAction(RxAppCompatActivity _rxAppCompatActivity,HomeView view) {
        super(_rxAppCompatActivity);
        attachView(view);
    }

    /**
     * 获取新闻类别
     */
    public void getNewsType(){
        post(WebUrlUtil.POST_NEWS_TYPE, false, service -> manager.runHttp(
                service.PostData_1(MySharedPreferencesUtil.getSessionId(MyApplication.getContext()),WebUrlUtil.POST_NEWS_TYPE)
        ));
    }

    /**
     * 获取新闻列表
     * @param name
     */
    public void getNewsBytheClass(String name){
        post(WebUrlUtil.POST_NEWS_BYID, false, service -> manager.runHttp(
                service.PostData_1(MySharedPreferencesUtil.getSessionId(MyApplication.getContext()),
                        CollectionsUtils.generateMap("the_class",name),WebUrlUtil.POST_NEWS_BYID)
        ));
    }


    /**
     * sticky:表明优先接收最高级  threadMode = ThreadMode.MAIN：表明在主线程
     *
     * @param action
     */
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void MessageEvent(final Action action) {
        L.e("xx", "action   接收到数据更新....." + action.getIdentifying() + " action.getErrorType() : " + action.getErrorType());

        final String msg = action.getMsg(action);
        Observable.just(action.getErrorType())
                .all(new Predicate<Integer>() {
                    @Override
                    public boolean test(Integer integer) throws Exception {
                        return (integer == 200);
                    }
                }).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {
                // 输出返回结果
                L.e("xx", "输出返回结果 " + aBoolean);

                switch (action.getIdentifying()) {
                    case WebUrlUtil.POST_NEWS_TYPE:
                        if (aBoolean) {
                            L.e("xx", "输出返回结果 " + action.getUserData().toString());
                            Gson gson = new Gson();
                            try {
                                NewsTypeDto newsTypeDto = gson.fromJson(action.getUserData().toString(), new TypeToken<NewsTypeDto>() {
                                }.getType());
                                view.getNewsTypeSuccessful(newsTypeDto);
                            } catch (JsonSyntaxException e) {
                                return;
                            }
                            return;
                        }
                        view.onError(msg, action.getErrorType());
                        break;
                    case WebUrlUtil.POST_NEWS_BYID:
                        if (aBoolean) {
                            L.e("xx", "输出返回结果 " + action.getUserData().toString());
                            Gson gson = new Gson();
                            try {
                                NewsBytheClassDto newsBytheClassDto = gson.fromJson(action.getUserData().toString(), new TypeToken<NewsBytheClassDto>() {
                                }.getType());
                                view.getNewsBytheClassSuccessful(newsBytheClassDto);
                            } catch (JsonSyntaxException e) {
                                return;
                            }
                            return;
                        }
                        view.onError(msg, action.getErrorType());
                        break;
                }

            }

        });

    }

    public void toRegister() {

        register(this);
    }

    public void toUnregister() {

        unregister(this);
    }

}
