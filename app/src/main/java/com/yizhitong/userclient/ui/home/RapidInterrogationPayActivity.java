package com.yizhitong.userclient.ui.home;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.lgh.huanglib.util.base.ActivityStack;
import com.lgh.huanglib.util.data.ResUtil;
import com.yizhitong.userclient.R;
import com.yizhitong.userclient.actions.RapidInterrogationPayAction;
import com.yizhitong.userclient.adapters.ImageItemAdapter;
import com.yizhitong.userclient.event.InquiryInfoDto;
import com.yizhitong.userclient.ui.impl.RapidInterrogationPayView;
import com.yizhitong.userclient.utils.base.UserBaseActivity;

import java.lang.ref.WeakReference;

import butterknife.BindView;

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


    @Override
    public int intiLayout() {
        return R.layout.activity_rapid_interrogation_pay;
    }

    @Override
    protected RapidInterrogationPayAction initAction() {
        return new RapidInterrogationPayAction(this,this);
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

    }


    @Override
    public void getAskHeadById() {

    }

    @Override
    public void getAskHeadByIdSuccessful(InquiryInfoDto inquiryInfoDto) {

    }

    @Override
    public void onError(String message, int code) {

    }

    @Override
    public void onLigonError() {

    }
}
