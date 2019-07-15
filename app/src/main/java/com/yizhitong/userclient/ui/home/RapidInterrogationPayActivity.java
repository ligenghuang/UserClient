package com.yizhitong.userclient.ui.home;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lgh.huanglib.util.CheckNetwork;
import com.lgh.huanglib.util.base.ActivityStack;
import com.lgh.huanglib.util.config.GlideUtil;
import com.lgh.huanglib.util.data.PriceUtils;
import com.lgh.huanglib.util.data.ResUtil;
import com.yizhitong.userclient.R;
import com.yizhitong.userclient.actions.RapidInterrogationPayAction;
import com.yizhitong.userclient.adapters.ImageItemAdapter;
import com.yizhitong.userclient.event.InquiryInfoDto;
import com.yizhitong.userclient.event.WeiXinPayDto;
import com.yizhitong.userclient.net.WebUrlUtil;
import com.yizhitong.userclient.ui.impl.RapidInterrogationPayView;
import com.yizhitong.userclient.ui.login.LoginActivity;
import com.yizhitong.userclient.utils.base.UserBaseActivity;
import com.yizhitong.userclient.utils.data.DynamicTimeFormat;
import com.yizhitong.userclient.utils.wechat.PayUtil;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * description ：快速问诊 支付
 * author : lgh
 * email : 1045105946@qq.com
 * date : 2019/6/19
 */
public class RapidInterrogationPayActivity extends UserBaseActivity<RapidInterrogationPayAction> implements RapidInterrogationPayView {
    @BindView(R.id.top_view)
    View topView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.f_title_tv)
    TextView titleTv;
    @BindView(R.id.tv_inquiry_note)
    TextView mTvInquiryNote;
    @BindView(R.id.checkbox)
    CheckBox mCheckbox;
    @BindView(R.id.ll_cb)
    LinearLayout mLlCb;
    @BindView(R.id.tv_money)
    TextView mTvMoney;
    @BindView(R.id.tv_inquiry_money)
    TextView mTvInquiryMoney;
    @BindView(R.id.tv_pay)
    TextView mTvPay;
    @BindView(R.id.rv_img)
    RecyclerView mRvImg;

    @BindView(R.id.iv_inquiry)
    ImageView mIvInquiry;
    @BindView(R.id.tv_inquiry_doctor_name)
    TextView mTvInquiryDoctorName;
    @BindView(R.id.tv_inquiry_doctor_level)
    TextView mTvInquiryDoctorLevel;
    @BindView(R.id.tv_inquiry_doctor_hospital)
    TextView mTvInquiryDoctorHospital;
    @BindView(R.id.ll_inquiry_doctor_info)
    LinearLayout mLlInquiryDoctorInfo;
    @BindView(R.id.tv_inquiry_doctor)
    TextView mTvInquiryDoctor;

    String iuid;
    ImageItemAdapter imageItemAdapter;
    boolean isRead = false;

    PayUtil payUtil;
    String id;
    String pay_moeny;


    @Override
    public int intiLayout() {
        return R.layout.activity_rapid_interrogation_pay;
    }

    @Override
    protected RapidInterrogationPayAction initAction() {
        return new RapidInterrogationPayAction(this, this);
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
                .addTag("RapidInterrogationPayActivity")  //给上面参数打标记，以后可以通过标记恢复
                .navigationBarWithKitkatEnable(false)
                .init();
        toolbar.setNavigationOnClickListener(view -> finish());
        titleTv.setText(ResUtil.getString(R.string.prescription_tip_25));
    }

    @Override
    protected void init() {
        super.init();
        mContext = this;
        mActicity = this;

        iuid = getIntent().getStringExtra("id");

        imageItemAdapter = new ImageItemAdapter(mContext, false);
        mRvImg.setLayoutManager(new GridLayoutManager(mContext, 4));
        mRvImg.setAdapter(imageItemAdapter);


        getAskHeadById();
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
                defrayPaySuccess();
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

    @OnClick({R.id.checkbox, R.id.ll_cb, R.id.tv_pay})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_cb:
            case R.id.checkbox:
                isRead = !isRead;
                mCheckbox.setChecked(isRead);
                mTvPay.setSelected(isRead);
                break;
            case R.id.tv_pay:
                if (isRead) {
                    OrderResultPay();
                }
                break;
        }
    }

    /**
     * 获取问诊单信息
     */
    @Override
    public void getAskHeadById() {
        if (CheckNetwork.checkNetwork2(mContext)) {
            loadDialog();
            baseAction.getAskHeadById(iuid);
        }
    }

    /**
     * 获取问诊单信息 成功
     *
     * @param inquiryInfoDto
     */
    @Override
    public void getAskHeadByIdSuccessful(InquiryInfoDto inquiryInfoDto) {
        loadDiss();
        InquiryInfoDto.DataBean dataBean = inquiryInfoDto.getData();
        id = dataBean.getAskIUID();
        mTvInquiryNote.setText(dataBean.getIll_note());
        String money = "￥" + PriceUtils.formatPrice(dataBean.getAll_money());
        mTvInquiryMoney.setText(money);
        mTvMoney.setText(money);
        pay_moeny = dataBean.getDoctor_money()+"";
        imageItemAdapter.refresh(dataBean.getIll_img());
        if (!dataBean.getDocUserId().isEmpty()) {
            //todo 已分配医生
            GlideUtil.setImage(mContext, WebUrlUtil.IMG_URL+dataBean.getThe_img(),mIvInquiry,R.drawable.icon_placeholder);
            mLlInquiryDoctorInfo.setVisibility(View.VISIBLE);
            mTvInquiryDoctor.setVisibility(View.GONE);
            mTvInquiryDoctorHospital.setText(dataBean.getHospital());
            mTvInquiryDoctorName.setText(dataBean.getDoctorName());
            mTvInquiryDoctorLevel.setText("("+dataBean.getThe_level()+")");
        }else {
            //todo 未分配医生
            mLlInquiryDoctorInfo.setVisibility(View.GONE);
            mTvInquiryDoctor.setVisibility(View.VISIBLE);
        }
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
}
