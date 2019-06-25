package com.yizhitong.userclient.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lgh.huanglib.util.CheckNetwork;
import com.lgh.huanglib.util.L;
import com.lgh.huanglib.util.base.ActivityStack;
import com.lgh.huanglib.util.config.GlideUtil;
import com.lgh.huanglib.util.cusview.richtxtview.XRichText;
import com.lgh.huanglib.util.data.IsFastClick;
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
import com.yizhitong.userclient.utils.data.MySp;
import com.zzhoujay.richtext.RichText;
import com.zzhoujay.richtext.callback.OnUrlClickListener;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * description ： 新闻详情页
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
    //    @BindView(R.id.xrichtext)
//    XRichText mXrichtext;
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
    @BindView(R.id.textview)
    TextView textview;

    String doctorId;

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

    /**
     * 获取新闻详情
     */
    @Override
    public void getNewsDetail() {
        if (CheckNetwork.checkNetwork2(mContext)) {
            loadDialog();
            baseAction.getNewsDetail(iuid);
        }
    }

    /**
     * 获取新闻详情 成功
     *
     * @param newsDetailDto
     */
    @Override
    public void getNewsDetailSuccessful(NewsDetailDto newsDetailDto) {
        loadDiss();
        NewsDetailDto.DataBean dataBean = newsDetailDto.getData();
        mTvNewsTitle.setText(dataBean.getThe_title());
        mTvNewsMan.setText(dataBean.getThe_man());
        mTvNewsTime.setText(DynamicTimeFormat.LongToString(dataBean.getCreate_time_stamp()));
//        mXrichtext.text(Util.toUtf8(dataBean.getThe_note()));
        L.e("lgh_note", Util.toUtf8(dataBean.getThe_note()));
        RichText.initCacheDir(this);
        RichText.debugMode = true;
        RichText.from(Util.toUtf8(dataBean.getThe_note()))
                .urlClick(new OnUrlClickListener() {
                    @Override
                    public boolean urlClicked(String url) {
                        if (url.startsWith("code://")) {
//                            Toast.makeText(MainActivity.this, url.replaceFirst("code://", ""), Toast.LENGTH_SHORT).show();
                            return true;
                        }
                        return false;
                    }
                })
                .into(textview);
        NewsDetailDto.DataBean.DoctorMVBean doctorMVBean = dataBean.getDoctorMV();
        doctorId = doctorMVBean.getIUID();
        GlideUtil.setImage(mContext, WebUrlUtil.IMG_URL + doctorMVBean.getThe_img(), mIvCard, R.drawable.icon_placeholder);
        mTvDoctorName.setText(doctorMVBean.getName());
        mTvDoctorLevel.setText(doctorMVBean.getThe_level());
        mTvDoctorHospital.setText(doctorMVBean.getHospital());
        mTvDoctorNote.setText(ResUtil.getFormatString(R.string.news_detail_tip_2, doctorMVBean.getThe_spec()));
//        GlideUtil.setImage(mContext,WebUrlUtil.IMG_URL+dataBean.getThe_img(),newsImgIv,0);
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
                if (!MySp.iSLoginLive(mContext)) {
                    //todo 判断是否登录
                    jumpActivityNotFinish(mContext, LoginActivity.class);
                    return;
                }
                Intent intent = new Intent(mContext, DoctorDetailActivity.class);
                intent.putExtra("iuid", doctorId);
                mContext.startActivity(intent);
                break;
        }
    }

    @Override
    public void finish() {
        super.finish();
        IsFastClick.lastClickTime = 0;
    }
}
