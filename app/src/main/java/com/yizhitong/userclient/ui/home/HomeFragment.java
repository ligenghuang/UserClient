package com.yizhitong.userclient.ui.home;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lgh.huanglib.util.CheckNetwork;
import com.lgh.huanglib.util.L;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.yizhitong.userclient.R;
import com.yizhitong.userclient.actions.HomeAction;
import com.yizhitong.userclient.adapters.Banner;
import com.yizhitong.userclient.adapters.NewsListAdapter;
import com.yizhitong.userclient.adapters.NewsTypeAdapter;
import com.yizhitong.userclient.event.NewsBytheClassDto;
import com.yizhitong.userclient.event.NewsTypeDto;
import com.yizhitong.userclient.ui.MainActivity;
import com.yizhitong.userclient.ui.impl.HomeView;
import com.yizhitong.userclient.ui.login.LoginActivity;
import com.yizhitong.userclient.ui.mine.MyPrescriptionActivity;
import com.yizhitong.userclient.utils.base.UserBaseFragment;
import com.yizhitong.userclient.utils.cusview.CustomLinearLayoutManager;
import com.yizhitong.userclient.utils.data.MySp;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bingoogolapple.bgabanner.BGABanner;

/**
 * description ： 首页
 * author : lgh
 * email : 1045105946@qq.com
 * date : 2019/6/18
 */
public class HomeFragment extends UserBaseFragment<HomeAction> implements HomeView {
    View view;
    @BindView(R.id.top_view)
    View topView;

    @BindView(R.id.rv_title)
    RecyclerView mRvTitle;
    @BindView(R.id.rv_content)
    RecyclerView mRvContent;

    @BindView(R.id.tv_bottom)
    TextView bottomTv;
    @BindView(R.id.et_search)
    EditText mEtSearch;

    @BindView(R.id.banner_main)
    BGABanner banner_main;

    @BindView(R.id.ll_news_list)
    LinearLayout newsListLl;
    @BindView(R.id.tv_null_data)
    TextView nullDataTv;

    NewsTypeAdapter newsTypeAdapter;
    NewsListAdapter newsListAdapter;
    CustomLinearLayoutManager customLinearLayoutManager;

    boolean isFirst = true;
    public static boolean isJumpNewsDetail = false;

    Banner banner;

    @Override
    protected HomeAction initAction() {
        return new HomeAction((RxAppCompatActivity) getActivity(), this);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mContext = getContext();
        mActivity = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        binding();
        mImmersionBar.setStatusBarView(getActivity(), topView);
        return view;
    }

    @Override
    protected void onFragmentVisibleChange(boolean isVisible) {
        super.onFragmentVisibleChange(isVisible);
        L.e("lgh_home", "isVisible  =  " + isVisible);
        if (isVisible) {
            ((MainActivity) getActivity()).changeStatusBar(false, R.color.transparent);
            if (isFirst) {
                loadDialog();
                isFirst = false;
            }
          if (!isJumpNewsDetail){
              getNewsType();
          }
            isJumpNewsDetail = false;
        }

    }


    @Override
    protected void initialize() {
        init();
        loadView();
    }


    @Override
    protected void init() {
        super.init();

        newsTypeAdapter = new NewsTypeAdapter();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRvTitle.setLayoutManager(linearLayoutManager);
        mRvTitle.setAdapter(newsTypeAdapter);

        newsListAdapter = new NewsListAdapter(mContext);
        customLinearLayoutManager = new CustomLinearLayoutManager(mContext);
        customLinearLayoutManager.setScrollEnabled(true);
        customLinearLayoutManager.setStackFromEnd(true);
        mRvContent.setLayoutManager(new LinearLayoutManager(mContext));
        mRvContent.setAdapter(newsListAdapter);

        //轮播图
        banner = new Banner();
        banner_main.setAdapter(banner);
        getBannerData();
    }

    private void getBannerData() {
        List<String> stringList = new ArrayList<>();
        List<String> tips = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            stringList.add("ddd");
            tips.add("");
        }
        banner_main.setData(stringList, tips);
    }


    @OnClick({R.id.tv_btn_1, R.id.tv_btn_2, R.id.tv_btn_3, R.id.tv_btn_4})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_btn_1:
                //todo 快速问诊
                jumpActivityNotFinish(mContext, RapidInterrogationActivity.class);
                break;
            case R.id.tv_btn_2:
                //TODO 找医生
                jumpActivityNotFinish(mContext, DepartFindDoctorActivity.class);
                break;
            case R.id.tv_btn_3:
                //TODO 名医专区
                jumpActivityNotFinish(mContext, FindDoctorActivity.class);
                break;
            case R.id.tv_btn_4:
                //TODO 我的处方
                if (!MySp.iSLoginLive(mContext)) {
                    //todo 判断是否登录
                    jumpActivityNotFinish(mContext, LoginActivity.class);
                    return;
                }
                jumpActivityNotFinish(mContext, MyPrescriptionActivity.class);
                break;
        }
    }

    @Override
    protected void loadView() {
        super.loadView();
        newsTypeAdapter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                for (int i = 0; i < newsTypeAdapter.getAllData().size(); i++) {
                    newsTypeAdapter.getAllData().get(i).setClick(i == position);


                }
                if (newsTypeAdapter.getAllData().get(position).getName().equals("全部")) {
                    getNewsBytheClass("");
                } else {
                    getNewsBytheClass(newsTypeAdapter.getAllData().get(position).getName());
                }

                L.e("lgh_news", newsTypeAdapter.getAllData().toString());
                newsTypeAdapter.notifyDataSetChanged();
            }
        });

        mEtSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {

                    InputMethodManager imm = (InputMethodManager) mActivity.getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(mActivity.getWindow().getDecorView().getWindowToken(), 0);
                    if (!TextUtils.isEmpty(mEtSearch.getText().toString())) {
                        Intent intent = new Intent(mContext, FindDoctorActivity.class);
                        intent.putExtra("condition", mEtSearch.getText().toString());
                        startActivity(intent);
                        mEtSearch.setText("");
                    } else {
                        Intent intent = new Intent(mContext, FindDoctorActivity.class);
                        startActivity(intent);
                    }
                    return true;
                }
                return false;
            }
        });


    }

    /**
     * 获取新闻类别
     */
    @Override
    public void getNewsType() {
        if (CheckNetwork.checkNetwork2(mContext)) {
            baseAction.getNewsType();
        }
    }

    /**
     * 获取新闻类别 成功
     *
     * @param newsTypeDto
     */
    @Override
    public void getNewsTypeSuccessful(NewsTypeDto newsTypeDto) {
        L.e("lgh_news", newsTypeDto.getData().toString());
        List<NewsTypeDto.DataBean> list = new ArrayList<>();
        NewsTypeDto.DataBean dataBean = new NewsTypeDto.DataBean();
        dataBean.setClick(true);
        dataBean.setName("全部");
        dataBean.setSort(-1);
        list.add(dataBean);
        list.addAll(newsTypeDto.getData());
        newsTypeAdapter.refresh(list);
        getNewsBytheClass("");
    }

    /**
     * 获取新闻列表
     *
     * @param name
     */
    @Override
    public void getNewsBytheClass(String name) {
        if (CheckNetwork.checkNetwork2(mContext)) {
            baseAction.getNewsBytheClass(name);
        }
    }

    /**
     * 获取新闻列表 成功
     *
     * @param newsBytheClassDto
     */
    @Override
    public void getNewsBytheClassSuccessful(NewsBytheClassDto newsBytheClassDto) {
        loadDiss();

        if (newsBytheClassDto.getData().size() == 0){
            newsListLl.setVisibility(View.GONE);
            nullDataTv.setVisibility(View.VISIBLE);
        }else {
            newsListLl.setVisibility(View.VISIBLE);
            nullDataTv.setVisibility(View.GONE);
            newsListAdapter.refresh(newsBytheClassDto.getData());
            bottomTv.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onError(String message, int code) {
        loadDiss();
    }

    @Override
    public void onLigonError() {
        loadDiss();
    }

    @Override
    public void onResume() {
        super.onResume();
        baseAction.toRegister();
        if (!isJumpNewsDetail){
            getNewsType();
        }
        isJumpNewsDetail = false;
    }

    @Override
    public void onPause() {
        super.onPause();
        baseAction.toUnregister();
    }


}
