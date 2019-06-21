package com.yizhitong.userclient.ui.home;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.lgh.huanglib.util.base.ActivityStack;
import com.lgh.huanglib.util.data.ResUtil;
import com.yizhitong.userclient.R;
import com.yizhitong.userclient.actions.BaseAction;
import com.yizhitong.userclient.ui.MainActivity;
import com.yizhitong.userclient.utils.base.UserBaseActivity;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * description ： 支付成功
 * author : lgh
 * email : 1045105946@qq.com
 * date : 2019/6/21
 */
public class OrderPaySuccessfulActivity extends UserBaseActivity {
    @BindView(R.id.top_view)
    View topView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.f_title_tv)
    TextView titleTv;
    @BindView(R.id.tv_to_Inquiry)
    TextView mTvToInquiry;
    @BindView(R.id.tv_to_home)
    TextView mTvToHome;

    @Override
    public int intiLayout() {
        return R.layout.activity_order_pay_successful;
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
                .addTag("OrderPaySuccessfulActivity")  //给上面参数打标记，以后可以通过标记恢复
                .navigationBarWithKitkatEnable(false)
                .init();
        toolbar.setNavigationOnClickListener(view -> finish());
        titleTv.setText(ResUtil.getString(R.string.order_pay_successfil_tip_1));
    }

    @Override
    protected void init() {
        super.init();
        mContext = this;
        mActicity = this;
    }

    @OnClick({R.id.tv_to_Inquiry, R.id.tv_to_home})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_to_Inquiry:
                jumpMainActivity(2);
                break;
            case R.id.tv_to_home:
                jumpMainActivity(0);
                break;
        }
    }

    private void jumpMainActivity(int p){
        MainActivity.Position = p;
        ActivityStack.getInstance().exitIsNotHaveMain(MainActivity.class);
        finish();
    }
}
