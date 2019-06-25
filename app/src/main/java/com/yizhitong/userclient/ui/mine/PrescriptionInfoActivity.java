package com.yizhitong.userclient.ui.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lgh.huanglib.util.CheckNetwork;
import com.lgh.huanglib.util.base.ActivityStack;
import com.lgh.huanglib.util.data.PriceUtils;
import com.lgh.huanglib.util.data.ResUtil;
import com.yizhitong.userclient.R;
import com.yizhitong.userclient.actions.PrescriptionInfoAction;
import com.yizhitong.userclient.adapters.PrescriptionInfoDruyAdapter;
import com.yizhitong.userclient.event.PreInfoDto;
import com.yizhitong.userclient.ui.impl.PrescriptionInfoView;
import com.yizhitong.userclient.ui.login.LoginActivity;
import com.yizhitong.userclient.utils.base.UserBaseActivity;
import com.yizhitong.userclient.utils.data.DynamicTimeFormat;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.OnClick;
/**
* description ： 处方详情 缺少支付方式 收货信息 跳转物流查询页
* author : lgh
* email : 1045105946@qq.com
* date : 2019/6/14
*/
public class PrescriptionInfoActivity extends UserBaseActivity<PrescriptionInfoAction> implements PrescriptionInfoView {

    @BindView(R.id.top_view)
    View topView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.f_title_tv)
    TextView titleTv;


    String iuid = "";

    @BindView(R.id.tv_prescription_no)
    TextView mTvPrescriptionNo;
    @BindView(R.id.tv_prescription_type)
    TextView mTvPrescriptionType;
    @BindView(R.id.tv_prescription_time)
    TextView mTvPrescriptionTime;
    @BindView(R.id.tv_prescription_name)
    TextView mTvPrescriptionName;
    @BindView(R.id.tv_prescription_sex)
    TextView mTvPrescriptionSex;
    @BindView(R.id.tv_prescription_age)
    TextView mTvPrescriptionAge;
    @BindView(R.id.tv_prescription_department)
    TextView mTvPrescriptionDepartment;
    @BindView(R.id.rv_drug)
    RecyclerView mRvDrug;
    @BindView(R.id.tv_prescription_note)
    TextView mTvPrescriptionNote;
    @BindView(R.id.tv_prescription_prescribers_are)
    TextView mTvPrescriptionPrescribersAre;
    @BindView(R.id.tv_money)
    TextView mTvMoney;
    @BindView(R.id.tv_pay)
    TextView mTvPay;

    PrescriptionInfoDruyAdapter prescriptionInfoDruyAdapter;

    PreInfoDto preInfoDto;
    @BindView(R.id.ll_prescription_money)
    LinearLayout mLlPrescriptionMoney;
    @BindView(R.id.tv_check_logistics)
    TextView mTvCheckLogistics;
    @BindView(R.id.tv_pay_type)
    TextView mTvPayType;
    @BindView(R.id.tv_pay_time)
    TextView mTvPayTime;
    @BindView(R.id.tv_druy_money)
    TextView mTvDruyMoney;
    @BindView(R.id.tv_pay_money)
    TextView mTvPayMoney;
    @BindView(R.id.ll_pay)
    LinearLayout mLlPay;

    @Override
    public int intiLayout() {
        return R.layout.activity_prescription_info;
    }

    @Override
    protected PrescriptionInfoAction initAction() {
        return new PrescriptionInfoAction(this, this);
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
                .addTag("PrescriptionInfoActivity")  //给上面参数打标记，以后可以通过标记恢复
                .navigationBarWithKitkatEnable(false)
                .init();
        toolbar.setNavigationOnClickListener(view -> finish());
        titleTv.setText(ResUtil.getString(R.string.prescription_tip_23));
    }

    @Override
    protected void init() {
        super.init();
        mContext = this;
        mActicity = this;
        iuid = getIntent().getStringExtra("iuid");

        prescriptionInfoDruyAdapter = new PrescriptionInfoDruyAdapter();
        mRvDrug.setLayoutManager(new LinearLayoutManager(this));
        mRvDrug.setAdapter(prescriptionInfoDruyAdapter);

        getPreInfo(iuid);
    }


    /**
     * 获取处方详情
     *
     * @param iuid
     */
    @Override
    public void getPreInfo(String iuid) {
        if (CheckNetwork.checkNetwork2(mContext)) {
            loadDialog();
            baseAction.getPreInfo(iuid);
        }
    }

    @Override
    public void getPreInfoSuccessful(PreInfoDto preInfoDto) {
        loadDiss();
        this.preInfoDto = preInfoDto;
        PreInfoDto.DataBean dataBean = preInfoDto.getData();
//        mTvPrescriptionNo.setText("NO:" + dataBean.getAskdrug_no());
        mTvPrescriptionTime.setText(DynamicTimeFormat.LongToString(dataBean.getCreate_time_stamp()));
        PreInfoDto.DataBean.PatientMVBean patientMVBean = preInfoDto.getData().getPatientMV();
        mTvPrescriptionName.setText(patientMVBean.getName());
        mTvPrescriptionAge.setText(patientMVBean.getAge() + "岁");
        mTvPrescriptionSex.setText(patientMVBean.getSex());
        mTvPrescriptionDepartment.setText(dataBean.getDepartName());
        mTvPrescriptionNote.setText(dataBean.getIll_note());
        mTvPrescriptionPrescribersAre.setText(dataBean.getDoctorName());
        mTvMoney.setText("￥" + PriceUtils.formatPrice(dataBean.getDrug_money()));
        prescriptionInfoDruyAdapter.refresh(dataBean.getDrugMV());

        //todo 2019/06/14 缺少支付方式 收货信息
        mTvPayTime.setText(DynamicTimeFormat.LongToString5(dataBean.getPay_time_stamp()));
        mTvDruyMoney.setText("￥"+PriceUtils.formatPrice(dataBean.getDrug_money()));
        mTvPayMoney.setText("￥"+PriceUtils.formatPrice(dataBean.getPay_money()));

        if (dataBean.getReback_flag() == 1){
            //todo 已取消
           mLlPrescriptionMoney.setVisibility(View.GONE);
           mTvCheckLogistics.setVisibility(View.GONE);
            mLlPay.setVisibility(View.VISIBLE);
        }else if (dataBean.getFinish_flag() == 1){
            //todo 已完成
            mLlPrescriptionMoney.setVisibility(View.GONE);
            mTvCheckLogistics.setVisibility(View.GONE);
            mLlPay.setVisibility(View.VISIBLE);
        }else if (dataBean.getPay_flag() == 1){
            //todo 待发货
            mLlPrescriptionMoney.setVisibility(View.GONE);
            mTvCheckLogistics.setVisibility(View.VISIBLE);
            mLlPay.setVisibility(View.VISIBLE);
        }else if (dataBean.getPay_flag() == 0){
            //todo 待付款
            mLlPrescriptionMoney.setVisibility(View.VISIBLE);
            mTvCheckLogistics.setVisibility(View.GONE);
            mLlPay.setVisibility(View.GONE);
        }



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
        if (code == -2) {
            showNormalToast(message);
            jumpActivity(mContext, LoginActivity.class);
        } else {
            showNormalToast(message);
        }
    }

    @Override
    public void onLigonError() {
        jumpActivity(mContext, LoginActivity.class);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (baseAction != null) {
            baseAction.toRegister();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (baseAction != null) {
            baseAction.toUnregister();
        }
    }

    @OnClick({R.id.tv_pay, R.id.tv_check_logistics})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_pay:
                Intent intent = new Intent(mContext, PrescriptionInfoPayActivity.class);
                intent.putExtra("preInfoDto", preInfoDto);
                startActivityForResult(intent, 200);
                break;
            case R.id.tv_check_logistics:
                break;
        }
    }
}
