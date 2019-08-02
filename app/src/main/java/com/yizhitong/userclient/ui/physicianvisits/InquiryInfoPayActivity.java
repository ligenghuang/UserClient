package com.yizhitong.userclient.ui.physicianvisits;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
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
import com.yizhitong.userclient.event.WeiXinPayDto;
import com.yizhitong.userclient.net.WebUrlUtil;
import com.yizhitong.userclient.ui.home.OrderPaySuccessfulActivity;
import com.yizhitong.userclient.ui.impl.InquiryInfoPayView;
import com.yizhitong.userclient.ui.login.LoginActivity;
import com.yizhitong.userclient.ui.mine.InterrogationAgreementActivity;
import com.yizhitong.userclient.utils.base.UserBaseActivity;
import com.yizhitong.userclient.utils.data.DynamicTimeFormat;
import com.yizhitong.userclient.utils.wechat.PayUtil;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * description ： 问诊单支付页面（缺少支付）
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
    @BindView(R.id.tv_inquiry_money)
    TextView inquiryMoneyTv;

    @BindView(R.id.checkbox)
    CheckBox mCheckbox;
    @BindView(R.id.ll_cb)
    LinearLayout mLlCb;

    boolean isRead = false;
    PayUtil payUtil;
    String id;
    String pay_moeny;

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
        payUtil = new PayUtil(this);
        payUtil.register();
        loadView();
    }


    @Override
    protected void loadView() {
        super.loadView();
        payUtil.setListener(new PayUtil.OnResponseListener() {
            @Override
            public void onSuccess() {
                //todo 支付成功
                loadDiss();
                jumpActivity(mContext, OrderPaySuccessfulActivity.class);
            }

            @Override
            public void onCancel(String message) {
                //TODO  支付失败
                loadDiss();
                showNormalToast(message);
            }

            @Override
            public void onFail(String message) {
                //TODO  支付失败
                loadDiss();
                showNormalToast(message);
            }
        });
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
        id = dataBean.getAskIUID();
        GlideUtil.setImage(mContext, WebUrlUtil.IMG_URL + dataBean.getThe_img(), mIvInquiry, R.drawable.icon_placeholder);
        if (!TextUtils.isEmpty(dataBean.getDoctorName())) {
            //todo 已分配医生
            mTvInquiryDoctorName.setText(dataBean.getDoctorName());
            mTvInquiryDoctorLevel.setText("(" + dataBean.getThe_level() + ")");
            mTvInquiryDoctorHospital.setText(dataBean.getHospital());
        } else {
            //todo 未分配医生
            mTvInquiryDoctorName.setText(ResUtil.getString(R.string.inquity_tip_24));
        }
        mTvInquiryNote.setText(dataBean.getIll_note());
        pay_moeny = dataBean.getDoctor_money()+"";
        mTvMoney.setText("￥" + dataBean.getDoctor_money());
        inquiryMoneyTv.setText("￥" + dataBean.getDoctor_money());
    }

    @Override
    public void OrderResultPay() {
        if (CheckNetwork.checkNetwork2(mContext)){
            loadDialog("请稍等");
            baseAction.OrderResultPay(id);
        }
    }

    @Override
    public void OrderResultPaySuccess(WeiXinPayDto weiXinPayDto) {
        if (weiXinPayDto.getData().getReturn_code().equals("SUCCESS")){
            payUtil.pay(weiXinPayDto.getData().getMch_id(),weiXinPayDto.getData().getAppid(),weiXinPayDto.getData().getNonce_str(),
                    DynamicTimeFormat.getTimestamp(),weiXinPayDto.getData().getPrepay_id(),weiXinPayDto.getData().getSign());
        }else {
            loadDiss();
            showNormalToast(weiXinPayDto.getData().getReturn_msg());
        }
    }

    @Override
    public void defrayPaySuccess() {
        if (CheckNetwork.checkNetwork2(mContext)){
            baseAction.defrayPaySuccess(id,pay_moeny);
        }
    }

    @Override
    public void defrayPaySuccessSuccessful() {
        loadDiss();
        jumpActivity(mContext, OrderPaySuccessfulActivity.class);
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
        hideInput();
        if (baseAction != null) {
            baseAction.toRegister();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    public void finish() {
        super.finish();
        if (baseAction != null) {
            baseAction.toUnregister();
        }
        if (payUtil != null) {
            payUtil.unregister();
        }
    }

    @OnClick({R.id.ll_cb, R.id.checkbox, R.id.tv_interrogation_agreement,R.id.tv_pay})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_cb:
            case R.id.checkbox:
                isRead = !isRead;
                mCheckbox.setChecked(isRead);
                mTvPay.setSelected(isRead);
                break;
            case R.id.tv_interrogation_agreement:
                //todo 问诊协议
                jumpActivityNotFinish(mContext, InterrogationAgreementActivity.class);
                break;
            case R.id.tv_pay:
                //todo 立即支付
                if (isRead) {
                    OrderResultPay();
                }
                break;
        }
    }
}
