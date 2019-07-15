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
import com.yizhitong.userclient.actions.BaseAction;
import com.yizhitong.userclient.actions.PrescriptionInfoPayAction;
import com.yizhitong.userclient.adapters.MyPrescriptionPayDruyAdapter;
import com.yizhitong.userclient.event.PreInfoDto;
import com.yizhitong.userclient.event.WeiXinPayDto;
import com.yizhitong.userclient.ui.home.OrderPaySuccessfulActivity;
import com.yizhitong.userclient.ui.impl.PrescriptionInfoPayView;
import com.yizhitong.userclient.utils.base.UserBaseActivity;
import com.yizhitong.userclient.utils.data.DynamicTimeFormat;
import com.yizhitong.userclient.utils.wechat.PayUtil;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * description ： 处方支付页面（缺少支付）
 * author : lgh
 * email : 1045105946@qq.com
 * date : 2019/7/12
 */
public class PrescriptionInfoPayActivity extends UserBaseActivity<PrescriptionInfoPayAction> implements PrescriptionInfoPayView {

    @BindView(R.id.top_view)
    View topView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.f_title_tv)
    TextView titleTv;
    @BindView(R.id.ll_address_null)
    LinearLayout mLlAddressNull;
    @BindView(R.id.tv_address_name)
    TextView mTvAddressName;
    @BindView(R.id.tv_address_phone)
    TextView mTvAddressPhone;
    @BindView(R.id.tv_address)
    TextView mTvAddress;
    @BindView(R.id.ll_address)
    LinearLayout mLlAddress;
    @BindView(R.id.rv_drug)
    RecyclerView mRvDrug;
    @BindView(R.id.tv_money)
    TextView mTvMoney;
    @BindView(R.id.tv_pay)
    TextView mTvPay;

    MyPrescriptionPayDruyAdapter myPrescriptionPayDruyAdapter;

    PreInfoDto preInfoDto;
    @BindView(R.id.ll_address_info)
    LinearLayout mLlAddressInfo;

    PayUtil payUtil;

    String id;
    String pay_moeny;
    String addressId;

    @Override
    public int intiLayout() {
        return R.layout.activity_prescription_info_pay;
    }

    @Override
    protected PrescriptionInfoPayAction initAction() {
        return new PrescriptionInfoPayAction(this, this);
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
                .addTag("PrescriptionInfoPayActivity")  //给上面参数打标记，以后可以通过标记恢复
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

        preInfoDto = (PreInfoDto) getIntent().getSerializableExtra("preInfoDto");

        myPrescriptionPayDruyAdapter = new MyPrescriptionPayDruyAdapter(mContext);
        mRvDrug.setLayoutManager(new LinearLayoutManager(mContext));
        mRvDrug.setAdapter(myPrescriptionPayDruyAdapter);
        myPrescriptionPayDruyAdapter.refresh(preInfoDto.getData().getDrugMV());
        mTvMoney.setText("￥" + PriceUtils.formatPrice(preInfoDto.getData().getDrug_money()));
        id = preInfoDto.getData().getAskdrugheadid();
        pay_moeny = preInfoDto.getData().getDrug_money()+"";


        if (preInfoDto.getData().getUserAddMV() != null) {
            mLlAddressNull.setVisibility(View.GONE);
            mLlAddress.setVisibility(View.VISIBLE);
            PreInfoDto.DataBean.UserAddMVBean userAddMVBean = preInfoDto.getData().getUserAddMV();
            addressId = userAddMVBean.getIUID();
            mTvAddressName.setText(userAddMVBean.getName());
            mTvAddressPhone.setText(userAddMVBean.getPhone());
            mTvAddress.setText(userAddMVBean.getUserAddress());
        } else {
            mLlAddressNull.setVisibility(View.VISIBLE);
            mLlAddress.setVisibility(View.GONE);
        }

        payUtil = new PayUtil(this);
        payUtil.register();
    }


    @OnClick({R.id.ll_address_info, R.id.tv_pay})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_address_info:
                //todo 选择地址
                Intent intent = new Intent(mContext, AddressManagementActivity.class);
                intent.putExtra("type", 1);
                startActivityForResult(intent, 200);
                break;
            case R.id.tv_pay:
                //todo 2019-06-24 立即支付
              if (preInfoDto.getData().getUserAddMV() != null){
                  OrderResultPay();
              }else {
                  showNormalToast("请选择地址！");
              }
                break;
        }
    }

    @Override
    protected void initView() {
        super.initView();
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 200) {
            if (data != null) {
                mLlAddressNull.setVisibility(View.GONE);
                mLlAddress.setVisibility(View.VISIBLE);
                mTvAddressName.setText(data.getStringExtra("name"));
                mTvAddressPhone.setText(data.getStringExtra("phone"));
                mTvAddress.setText(data.getStringExtra("address"));
                addressId = data.getStringExtra("iuid");
            }
        }
    }

    /**
     * type 1、处方  0、问诊
     */
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
            baseAction.defrayPaySuccess(id,pay_moeny,addressId);
        }
    }

    @Override
    public void defrayPaySuccessSuccessful() {
        loadDiss();
        jumpActivity(mContext, OrderPaySuccessfulActivity.class);
    }

    /**
     * 失败
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
