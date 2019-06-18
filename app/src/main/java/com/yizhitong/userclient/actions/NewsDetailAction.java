package com.yizhitong.userclient.actions;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lgh.huanglib.actions.Action;
import com.lgh.huanglib.net.CollectionsUtils;
import com.lgh.huanglib.util.L;
import com.lgh.huanglib.util.config.MyApplication;
import com.lgh.huanglib.util.data.MySharedPreferencesUtil;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.yizhitong.userclient.event.NewsDetailDto;
import com.yizhitong.userclient.net.WebUrlUtil;
import com.yizhitong.userclient.ui.impl.NewsDetailView;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;

/**
 * description ： 新闻详情页
 * author : lgh
 * email : 1045105946@qq.com
 * date : 2019/6/18
 */
public class NewsDetailAction extends BaseAction<NewsDetailView>{
    public NewsDetailAction(RxAppCompatActivity _rxAppCompatActivity,NewsDetailView view) {
        super(_rxAppCompatActivity);
        attachView(view);
    }

    public void getNewsDetail(String iuid){
        post(WebUrlUtil.POST_NEWS_DETAIL, false, service -> manager.runHttp(
                service.PostData_1(MySharedPreferencesUtil.getSessionId(MyApplication.getContext()),
                        CollectionsUtils.generateMap("IUID",iuid
                        ),
                        WebUrlUtil.POST_NEWS_DETAIL)
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
                    case WebUrlUtil.POST_NEWS_DETAIL:
                        if (aBoolean) {
                            L.e("xx", "输出返回结果 " + action.getUserData().toString());
                            Gson gson = new Gson();
                            NewsDetailDto newsDetailDto = gson.fromJson(action.getUserData().toString(), new TypeToken<NewsDetailDto>() {
                            }.getType());
                            if (newsDetailDto.getCode() == 1) {
                                view.getNewsDetailSuccessful(newsDetailDto);
                            } else {
                                view.onError(newsDetailDto.getMsg(), newsDetailDto.getCode());
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