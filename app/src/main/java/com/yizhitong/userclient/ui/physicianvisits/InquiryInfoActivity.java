package com.yizhitong.userclient.ui.physicianvisits;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.lgh.huanglib.util.CheckNetwork;
import com.lgh.huanglib.util.base.ActivityStack;
import com.lgh.huanglib.util.data.ResUtil;
import com.yizhitong.userclient.R;
import com.yizhitong.userclient.actions.InquiryInfoAction;
import com.yizhitong.userclient.adapters.IllessImgAdapter;
import com.yizhitong.userclient.event.InquiryInfoDto;
import com.yizhitong.userclient.ui.impl.InquiryInfoView;
import com.yizhitong.userclient.ui.login.LoginActivity;
import com.yizhitong.userclient.utils.base.UserBaseActivity;

import java.lang.ref.WeakReference;

import butterknife.BindView;

/**
 * description ： 问诊详情
 * author : lgh
 * email : 1045105946@qq.com
 * date : 2019/6/17
 */
public class InquiryInfoActivity extends UserBaseActivity<InquiryInfoAction> implements InquiryInfoView {

    @BindView(R.id.top_view)
    View topView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.f_title_tv)
    TextView titleTv;


    String iuid;
    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.tv_sex)
    TextView mTvSex;
    @BindView(R.id.tv_age)
    TextView mTvAge;
    @BindView(R.id.tv_weight)
    TextView mTvWeight;
    @BindView(R.id.tv_allergy)
    TextView mTvAllergy;
    @BindView(R.id.tv_family)
    TextView mTvFamily;
    @BindView(R.id.tv_medical_history)
    TextView mTvMedicalHistory;
    @BindView(R.id.tv_illness)
    TextView mTvIllness;
    @BindView(R.id.rv_img_illness)
    RecyclerView mRvImgIllness;

    IllessImgAdapter illessImgAdapter;

    @Override
    public int intiLayout() {
        return R.layout.activity_inquiry_info;
    }

    @Override
    protected InquiryInfoAction initAction() {
        return new InquiryInfoAction(this, this);
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
                .addTag("InquiryInfoActivity")  //给上面参数打标记，以后可以通过标记恢复
                .navigationBarWithKitkatEnable(false)
                .init();
        toolbar.setNavigationOnClickListener(view -> finish());
        titleTv.setText(ResUtil.getString(R.string.inquity_info_tip_1));
    }

    @Override
    protected void init() {
        super.init();
        mContext = this;
        mActicity = this;
        iuid = getIntent().getStringExtra("iuid");
        illessImgAdapter = new IllessImgAdapter(mContext);
        mRvImgIllness.setLayoutManager(new GridLayoutManager(mContext, 4));
        mRvImgIllness.setAdapter(illessImgAdapter);

        getAskHeadById();
    }


    @Override
    public void getAskHeadById() {
        if (CheckNetwork.checkNetwork2(mContext)) {
            loadDialog();
            baseAction.getAskHeadById(iuid);
        }
    }

    @Override
    public void getAskHeadByIdSuccessful(InquiryInfoDto inquiryInfoDto) {
        loadDiss();
        InquiryInfoDto.DataBean dataBean = inquiryInfoDto.getData();
        InquiryInfoDto.DataBean.PatientMVBean patientMVBean = dataBean.getPatientMV();
        mTvAge.setText(patientMVBean.getAge() + "岁");
        mTvName.setText(patientMVBean.getName());
        mTvSex.setText(patientMVBean.getSex());
        mTvWeight.setText(patientMVBean.getWeight() + "KG");
        mTvAllergy.setText(patientMVBean.getAllergy_note());
        mTvFamily.setText(patientMVBean.getMed_family());
        mTvMedicalHistory.setText(patientMVBean.getMed_history());
        mTvIllness.setText(dataBean.getIll_note());
        illessImgAdapter.refresh(dataBean.getIll_img());
    }

    /**
     * 失败
     *
     * @param message
     * @param code
     */
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

}
