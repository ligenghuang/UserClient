package com.yizhitong.userclient.ui.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lgh.huanglib.util.base.ActivityStack;
import com.lgh.huanglib.util.data.PriceUtils;
import com.lgh.huanglib.util.data.ResUtil;
import com.yizhitong.userclient.R;
import com.yizhitong.userclient.actions.BaseAction;
import com.yizhitong.userclient.adapters.MyPrescriptionPayDruyAdapter;
import com.yizhitong.userclient.event.PreInfoDto;
import com.yizhitong.userclient.utils.base.UserBaseActivity;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.OnClick;

public class PrescriptionInfoPayActivity extends UserBaseActivity {

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

    @Override
    public int intiLayout() {
        return R.layout.activity_prescription_info_pay;
    }

    @Override
    protected BaseAction initAction() {
        return null;
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
    }


    @OnClick({R.id.ll_address_info,R.id.tv_pay})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_address_info:
                //todo
                Intent intent = new Intent(mContext, AddressManagementActivity.class);
                intent.putExtra("type", 1);
                startActivityForResult(intent, 200);
                break;
            case R.id.tv_pay:
                //todo 2019-06-24 立即支付
                break;
        }
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
            }
        }
    }
}
