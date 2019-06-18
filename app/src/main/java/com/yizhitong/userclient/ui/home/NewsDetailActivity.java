package com.yizhitong.userclient.ui.home;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lgh.huanglib.util.CheckNetwork;
import com.lgh.huanglib.util.base.ActivityStack;
import com.lgh.huanglib.util.config.GlideUtil;
import com.lgh.huanglib.util.cusview.richtxtview.XRichText;
import com.lgh.huanglib.util.data.ResUtil;
import com.yizhitong.userclient.R;
import com.yizhitong.userclient.actions.NewsDetailAction;
import com.yizhitong.userclient.event.NewsDetailDto;
import com.yizhitong.userclient.net.WebUrlUtil;
import com.yizhitong.userclient.ui.impl.NewsDetailView;
import com.yizhitong.userclient.ui.login.LoginActivity;
import com.yizhitong.userclient.utils.Util;
import com.yizhitong.userclient.utils.base.UserBaseActivity;
import com.yizhitong.userclient.utils.data.DynamicTimeFormat;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * description ： 新闻详情页(缺少咨询)
 * author : lgh
 * email : 1045105946@qq.com
 * date : 2019/6/18
 */
public class NewsDetailActivity extends UserBaseActivity<NewsDetailAction> implements NewsDetailView {
    @BindView(R.id.top_view)
    View topView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.f_title_tv)
    TextView titleTv;

    String iuid;
    @BindView(R.id.tv_news_title)
    TextView mTvNewsTitle;
    @BindView(R.id.tv_news_man)
    TextView mTvNewsMan;
    @BindView(R.id.tv_news_time)
    TextView mTvNewsTime;
    @BindView(R.id.xrichtext)
    XRichText mXrichtext;
    @BindView(R.id.iv_card)
    ImageView mIvCard;
    @BindView(R.id.tv_doctor_name)
    TextView mTvDoctorName;
    @BindView(R.id.tv_doctor_level)
    TextView mTvDoctorLevel;
    @BindView(R.id.tv_doctor_hospital)
    TextView mTvDoctorHospital;
    @BindView(R.id.tv_doctor_note)
    TextView mTvDoctorNote;
    @BindView(R.id.tv_consult)
    TextView mTvConsult;

    @Override
    public int intiLayout() {
        return R.layout.activity_news_detail;
    }

    @Override
    protected NewsDetailAction initAction() {
        return new NewsDetailAction(this, this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityStack.getInstance().addActivity(new WeakReference<>(this));
        binding();
    }

    @Override
    protected void initTitlebar() {
        super.initTitlebar();
        mImmersionBar
                .statusBarView(R.id.top_view)
                .keyboardEnable(true)
                .statusBarDarkFont(true, 0.2f)
                .addTag("NewsDetailActivity")  //给上面参数打标记，以后可以通过标记恢复
                .navigationBarWithKitkatEnable(false)
                .init();
        toolbar.setNavigationOnClickListener(view -> finish());
        titleTv.setText(ResUtil.getString(R.string.news_detail_tip_title));
    }

    @Override
    protected void init() {
        super.init();
        mContext = this;
        mActicity = this;
        iuid = getIntent().getStringExtra("iuid");

        getNewsDetail();
    }

    @Override
    public void getNewsDetail() {
        if (CheckNetwork.checkNetwork2(mContext)) {
            loadDialog();
            baseAction.getNewsDetail(iuid);
        }
    }

    @Override
    public void getNewsDetailSuccessful(NewsDetailDto newsDetailDto) {
        loadDiss();
        NewsDetailDto.DataBean dataBean = newsDetailDto.getData();
        mTvNewsTitle.setText(dataBean.getThe_title());
        mTvNewsMan.setText(dataBean.getThe_man());
        mTvNewsTime.setText(DynamicTimeFormat.LongToString(dataBean.getCreate_time_stamp()));
        mXrichtext.text(Util.toUtf8(dataBean.getThe_note()));

        NewsDetailDto.DataBean.DoctorMVBean doctorMVBean = dataBean.getDoctorMV();
        GlideUtil.setImage(mContext, WebUrlUtil.IMG_URL+doctorMVBean.getThe_img(),mIvCard,R.drawable.icon_placeholder);
        mTvDoctorName.setText(doctorMVBean.getName());
        mTvDoctorLevel.setText(doctorMVBean.getThe_level());
        mTvDoctorHospital.setText(doctorMVBean.getHospital());
        mTvDoctorNote.setText(ResUtil.getFormatString(R.string.news_detail_tip_2,doctorMVBean.getThe_spec()));
    }

    @Override
    public void onError(String message, int code) {
        loadDiss();
        showNormalToast(message);
    }

    @Override
    public void onLigonError() {
        loadDiss();
        jumpActivity(mContext, LoginActivity.class);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (baseAction != null) {
            baseAction.toRegister();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (baseAction != null) {
            baseAction.toUnregister();
        }
    }


    @OnClick(R.id.tv_consult)
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.tv_consult:
                break;
        }
    }
}
