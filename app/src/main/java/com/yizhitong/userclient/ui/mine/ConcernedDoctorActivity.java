package com.yizhitong.userclient.ui.mine;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.lgh.huanglib.util.CheckNetwork;
import com.lgh.huanglib.util.base.ActivityStack;
import com.lgh.huanglib.util.data.ResUtil;
import com.yizhitong.userclient.R;
import com.yizhitong.userclient.actions.ConcernedDoctorAction;
import com.yizhitong.userclient.adapters.ConcernedDoctorAdapter;
import com.yizhitong.userclient.event.ConcernedDoctorListDto;
import com.yizhitong.userclient.ui.impl.ConcernedDoctorView;
import com.yizhitong.userclient.ui.login.LoginActivity;
import com.yizhitong.userclient.utils.base.UserBaseActivity;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.OnClick;

public class ConcernedDoctorActivity extends UserBaseActivity<ConcernedDoctorAction> implements ConcernedDoctorView {

    @BindView(R.id.top_view)
    View topView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.f_title_tv)
    TextView titleTv;
    @BindView(R.id.f_right_tv)
    TextView rightTv;

    @BindView(R.id.rv_doctor)
    RecyclerView doctorRv;

    ConcernedDoctorAdapter concernedDoctorAdapter;

    @Override
    public int intiLayout() {
        return R.layout.activity_concerned_doctor;
    }

    @Override
    protected ConcernedDoctorAction initAction() {
        return new ConcernedDoctorAction(this, this);
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
                .addTag("ConcernedDoctorActivity")  //给上面参数打标记，以后可以通过标记恢复
                .navigationBarWithKitkatEnable(false)
                .init();
        toolbar.setNavigationOnClickListener(view -> finish());
        titleTv.setText(ResUtil.getString(R.string.concerned_doctor_tip_title));
        rightTv.setText(ResUtil.getString(R.string.concerned_doctor_tip_1));
        rightTv.setTextColor(ResUtil.getColor(R.color.color_289d23));
    }

    @Override
    protected void init() {
        super.init();
        mActicity = this;
        mContext = this;

        concernedDoctorAdapter = new ConcernedDoctorAdapter(this);
        doctorRv.setLayoutManager(new LinearLayoutManager(this));
        doctorRv.setAdapter(concernedDoctorAdapter);

        loadDialog();
        isLogin();
    }

    @OnClick(R.id.f_right_tv)
    void OnClick(View view){
        switch (view.getId()){
            case R.id.f_right_tv:
                jumpActivityNotFinish(mContext,DoctorCertifiedActivity.class);
                break;
        }
    }

    @Override
    public void isLogin() {
        if (CheckNetwork.checkNetwork2(mContext)) {
            baseAction.isLogin();
        }
    }

    @Override
    public void isLoginSuccessful() {
        findFavDoctors();
    }

    @Override
    public void isLoginError() {
        loadDiss();
        jumpActivityNotFinish(mContext, LoginActivity.class);
    }

    @Override
    public void findFavDoctors() {
        if (CheckNetwork.checkNetwork2(mContext)) {
            baseAction.findFavDoctors();
        } else {
            loadDiss();
        }
    }

    @Override
    public void findFavDoctorsSuccessful(ConcernedDoctorListDto concernedDoctorListDto) {
        loadDiss();
        concernedDoctorAdapter.refresh(concernedDoctorListDto.getData());
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

}
