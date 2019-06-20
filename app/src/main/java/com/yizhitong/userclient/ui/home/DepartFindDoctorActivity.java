package com.yizhitong.userclient.ui.home;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.lgh.huanglib.util.CheckNetwork;
import com.lgh.huanglib.util.base.ActivityStack;
import com.lgh.huanglib.util.data.ResUtil;
import com.yizhitong.userclient.R;
import com.yizhitong.userclient.actions.DepartFindDoctorAction;
import com.yizhitong.userclient.adapters.DepartListAdapter;
import com.yizhitong.userclient.event.DepartListDto;
import com.yizhitong.userclient.ui.impl.DepartFindDoctorView;
import com.yizhitong.userclient.ui.login.LoginActivity;
import com.yizhitong.userclient.utils.base.UserBaseActivity;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * description ： 按项目找医生
 * author : lgh
 * email : 1045105946@qq.com
 * date : 2019/6/18
 */
public class DepartFindDoctorActivity extends UserBaseActivity<DepartFindDoctorAction> implements DepartFindDoctorView {

    @BindView(R.id.top_view)
    View topView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.f_title_tv)
    TextView titleTv;

    DepartListAdapter departListAdapter;
    @BindView(R.id.et_search)
    EditText mEtSearch;
    @BindView(R.id.iv_search)
    ImageView mIvSearch;
    @BindView(R.id.rv_depart)
    RecyclerView mRvDepart;

    @Override
    public int intiLayout() {
        return R.layout.activity_depart_find_doctor;
    }

    @Override
    protected DepartFindDoctorAction initAction() {
        return new DepartFindDoctorAction(this, this);
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
                .addTag("DepartFindDoctorActivity")  //给上面参数打标记，以后可以通过标记恢复
                .navigationBarWithKitkatEnable(false)
                .init();
        toolbar.setNavigationOnClickListener(view -> finish());
        titleTv.setText(ResUtil.getString(R.string.find_doctor_tip_title));
    }

    @Override
    protected void init() {
        super.init();
        mContext = this;
        mActicity = this;

        departListAdapter = new DepartListAdapter();
        mRvDepart.setLayoutManager(new GridLayoutManager(mContext,2));
        mRvDepart.setAdapter(departListAdapter);

        findDepartByAll();
    }

    @Override
    public void findDepartByAll() {
        if (CheckNetwork.checkNetwork2(mContext)) {
            loadDialog();
            baseAction.findDepartByAll();
        }
    }

    @Override
    public void findDepartByAllSuccessful(DepartListDto departListDto) {
        loadDiss();
        departListAdapter.refresh(departListDto.getData());
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
        if (baseAction != null) {
            baseAction.toUnregister();
        }
    }

    @OnClick({R.id.iv_search, R.id.rv_depart})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.iv_search:
                break;
            case R.id.rv_depart:
                break;
        }
    }
}
