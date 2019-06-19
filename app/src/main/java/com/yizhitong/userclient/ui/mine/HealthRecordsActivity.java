package com.yizhitong.userclient.ui.mine;

import android.content.Intent;
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
import com.yizhitong.userclient.actions.HealthRecordsAction;
import com.yizhitong.userclient.adapters.HealthRecordAdapter;
import com.yizhitong.userclient.event.PatientListDto;
import com.yizhitong.userclient.ui.impl.HealthRecordsView;
import com.yizhitong.userclient.ui.login.LoginActivity;
import com.yizhitong.userclient.utils.base.UserBaseActivity;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.OnClick;

public class HealthRecordsActivity extends UserBaseActivity<HealthRecordsAction> implements HealthRecordsView {

    @BindView(R.id.top_view)
    View topView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.f_title_tv)
    TextView titleTv;

    @BindView(R.id.rv_patient)
    RecyclerView patientRv;

    HealthRecordAdapter healthRecordAdapter;
    boolean isSelect = false;

    @Override
    public int intiLayout() {
        return R.layout.activity_health_records;
    }

    @Override
    protected HealthRecordsAction initAction() {
        return new HealthRecordsAction(this,this);
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
                .addTag("HealthRecordsActivity")  //给上面参数打标记，以后可以通过标记恢复
                .navigationBarWithKitkatEnable(false)
                .init();
        toolbar.setNavigationOnClickListener(view -> finish());

    }

    @Override
    protected void init() {
        super.init();
        mActicity = this;
        mContext = this;

        isSelect = getIntent().getBooleanExtra("isSelect",false);
        titleTv.setText(ResUtil.getString(isSelect ?R.string.rapid_interrogation_tip_6:R.string.health_records_tip_title));

        healthRecordAdapter = new HealthRecordAdapter(this,isSelect);
        patientRv.setLayoutManager(new LinearLayoutManager(this));
        patientRv.setAdapter(healthRecordAdapter);

        loadView();
    }


    @Override
    protected void loadView() {
        super.loadView();
        healthRecordAdapter.setOnClickListener(new HealthRecordAdapter.OnClickListener() {
            @Override
            public void OnClick(String id, String name) {
                //todo 跳转至快速问诊页
                Intent intent = new Intent();
                intent.putExtra("id",id);
                intent.putExtra("name",name);
                setResult(200,intent);
                finish();
            }
        });
    }

    @OnClick(R.id.tv_submit)
    void OnClick(View view){
        switch (view.getId()){
            case R.id.tv_submit:
                //todo 新增问诊人
                jumpActivityNotFinish(mContext,AddPatientActivity.class);
                break;
        }
    }

    @Override
    public void getMyPatient() {
        if (CheckNetwork.checkNetwork2(mContext)){
            loadDialog();
            baseAction.getMyPatient();
        }
    }

    @Override
    public void getMyPatientSuccessful(PatientListDto patientListDto) {
        loadDiss();
        healthRecordAdapter.refresh(patientListDto.getData());
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
            getMyPatient();
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
