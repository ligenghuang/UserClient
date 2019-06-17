package com.yizhitong.userclient.ui.physicianvisits;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lgh.huanglib.util.CheckNetwork;
import com.lgh.huanglib.util.base.ActivityStack;
import com.lgh.huanglib.util.config.GlideUtil;
import com.lgh.huanglib.util.data.ResUtil;
import com.yizhitong.userclient.R;
import com.yizhitong.userclient.actions.InquiryInfoPayAction;
import com.yizhitong.userclient.event.InquiryInfoPayDto;
import com.yizhitong.userclient.net.WebUrlUtil;
import com.yizhitong.userclient.ui.impl.InquiryInfoPayView;
import com.yizhitong.userclient.ui.login.LoginActivity;
import com.yizhitong.userclient.utils.base.UserBaseActivity;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * description ： 问诊单支付页面
 * author : lgh
 * email : 1045105946@qq.com
 * date : 2019/6/14
 */
public class InquiryInfoPayActivity extends UserBaseActivity<InquiryInfoPayAction> implements InquiryInfoPayView {
    @BindView(R.id.top_view)
    View topView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.f_title_tv)
    TextView titleTv;


    String iuid;
    @BindView(R.id.iv_inquiry)
    ImageView mIvInquiry;
    @BindView(R.id.tv_inquiry_doctor_name)
    TextView mTvInquiryDoctorName;
    @BindView(R.id.tv_inquiry_doctor_level)
    TextView mTvInquiryDoctorLevel;
    @BindView(R.id.tv_inquiry_doctor_hospital)
    TextView mTvInquiryDoctorHospital;
    @BindView(R.id.tv_inquiry_note)
    TextView mTvInquiryNote;
    @BindView(R.id.tv_money)
    TextView mTvMoney;
    @BindView(R.id.tv_pay)
    TextView mTvPay;
    @BindView(R.id.ll_inquiry_money)
    LinearLayout mLlInquiryMoney;

    @BindView(R.id.checkbox)
    CheckBox mCheckbox;
    @BindView(R.id.ll_cb)
    LinearLayout mLlCb;

    boolean isRead = false;

    @Override
    public int intiLayout() {
        return R.layout.activity_inquiry_info_pay;
    }

    @Override
    protected InquiryInfoPayAction initAction() {
        return new InquiryInfoPayAction(this, this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityStack.getInstance().addActivity(new WeakReference<>(this));
        binding();
        initView();
    }

    @Override
    protected void initTitlebar() {
        super.initTitlebar();
        mImmersionBar
                .statusBarView(R.id.top_view)
                .keyboardEnable(true)
                .statusBarDarkFont(true, 0.2f)
                .addTag("InquiryInfoPayActivity")  //给上面参数打标记，以后可以通过标记恢复
                .navigationBarWithKitkatEnable(false)
                .init();
        toolbar.setNavigationOnClickListener(view -> finish());
        titleTv.setText(ResUtil.getString(R.string.inquity_tip_14));
    }

    @Override
    protected void init() {
        super.init();
        mContext = this;
        mActicity = this;
        iuid = getIntent().getStringExtra("iuid");
        getAskHeadById(iuid);
    }

    @Override
    public void getAskHeadById(String iuid) {
        if (CheckNetwork.checkNetwork2(mContext)) {
            loadDialog();
            baseAction.getAskHeadById(iuid);
        }
    }

    @Override
    public void getAskHeadByIdSuccessful(InquiryInfoPayDto inquiryInfoPayDto) {
        loadDiss();
        InquiryInfoPayDto.DataBean dataBean = inquiryInfoPayDto.getData();
        GlideUtil.setImage(mContext, WebUrlUtil.IMG_URL + dataBean.getThe_img(), mIvInquiry, R.drawable.icon_placeholder);
        mTvInquiryDoctorName.setText(dataBean.getDoctorName());
        mTvInquiryDoctorLevel.setText("(" + dataBean.getThe_level() + ")");
        mTvInquiryDoctorHospital.setText(dataBean.getHospital());
        mTvInquiryNote.setText(dataBean.getIll_note());
        mTvMoney.setText("￥" + dataBean.getDoctor_money());
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


    @OnClick({R.id.ll_cb, R.id.checkbox})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_cb:
            case R.id.checkbox:
                isRead = !isRead;
                mCheckbox.setChecked(isRead);
                mTvPay.setSelected(isRead);
                break;
        }
    }
}
