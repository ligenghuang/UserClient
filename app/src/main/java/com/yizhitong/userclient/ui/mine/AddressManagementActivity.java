package com.yizhitong.userclient.ui.mine;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.lgh.huanglib.util.CheckNetwork;
import com.lgh.huanglib.util.base.ActivityStack;
import com.lgh.huanglib.util.data.ResUtil;
import com.yizhitong.userclient.R;
import com.yizhitong.userclient.actions.AddressManagementAction;
import com.yizhitong.userclient.adapters.AddressManagementAdapter;
import com.yizhitong.userclient.event.AddressListDto;
import com.yizhitong.userclient.event.GeneralDto;
import com.yizhitong.userclient.ui.impl.AddressManagementView;
import com.yizhitong.userclient.ui.login.LoginActivity;
import com.yizhitong.userclient.utils.base.UserBaseActivity;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.OnClick;
/**
* description ： 收货地址
* author : lgh
* email : 1045105946@qq.com
* date : 2019/6/17
*/
public class AddressManagementActivity extends UserBaseActivity<AddressManagementAction> implements AddressManagementView {

    @BindView(R.id.top_view)
    View topView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.f_title_tv)
    TextView titleTv;
    @BindView(R.id.f_right_tv)
    TextView rightTv;

    @BindView(R.id.rv_address)
    RecyclerView addressRv;
    @BindView(R.id.tv_btn)
    TextView btnTv;

    AddressManagementAdapter addressManagementAdapter;
    boolean isEditor = false;
    int type = 0;

    @Override
    public int intiLayout() {
        return R.layout.activity_address_management;
    }

    @Override
    protected AddressManagementAction initAction() {
        return new AddressManagementAction(this, this);
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
                .addTag("AddressManagementActivity")  //给上面参数打标记，以后可以通过标记恢复
                .navigationBarWithKitkatEnable(false)
                .init();
        toolbar.setNavigationOnClickListener(view -> finish());
        titleTv.setText(ResUtil.getString(R.string.address_tip_title));
        rightTv.setText(ResUtil.getString(R.string.address_tip_1));
    }

    @Override
    protected void init() {
        super.init();
        mContext = this;
        mActicity = this;
        type = getIntent().getIntExtra("type",0);

        addressManagementAdapter = new AddressManagementAdapter(this,type);
        addressRv.setLayoutManager(new LinearLayoutManager(this));
        addressRv.setAdapter(addressManagementAdapter);
        loadView();
    }

    @OnClick({R.id.f_right_tv,R.id.tv_btn})
    void OnClick(View view) {
        switch (view.getId()) {
            case R.id.f_right_tv:
                isEditor = !isEditor;
                setEditor();
                break;
            case R.id.tv_btn:
                if (isEditor){
                    //TODO 删除
                   deteleUserAddress(getDeteleData());
                }else {
                    //todo 新增
                    jumpActivityNotFinish(mContext,AddAddressActivity.class);
                }
                break;
        }
    }

    @Override
    protected void loadView() {
        super.loadView();
        addressManagementAdapter.setOnClickListenter(new AddressManagementAdapter.OnClickListenter() {
            @Override
            public void OnClick(AddressListDto.DataBean m) {
                Intent intent = new Intent();
                intent.putExtra("name",m.getName());
                intent.putExtra("phone",m.getPhone());
                intent.putExtra("address",m.getUserAddress());
                intent.putExtra("iuid",m.getIUID());
                setResult(200,intent);
                finish();
            }
        });
    }

    private String getDeteleData() {
        String text = "";
        int num = 0;
        for (int i = 0; i < addressManagementAdapter.getAllData().size(); i++) {
            if (num > 0){
                text = text+",";
            }
            AddressListDto.DataBean dataBean = addressManagementAdapter.getAllData().get(i);
            if (dataBean.isClick()){
                text = text + dataBean.getIUID();
                num++;
            }
        }
        return text;
    }

    private void setEditor() {
        rightTv.setText(ResUtil.getString(isEditor ? R.string.address_tip_2 : R.string.address_tip_1));
        btnTv.setText(ResUtil.getString(isEditor ? R.string.address_tip_5:R.string.address_tip_4));
        btnTv.setBackgroundResource(isEditor?R.drawable.shape_logout_btn_bg:R.drawable.shape_login_btn_bg);
        addressManagementAdapter.setEditor(isEditor);
    }

    /**
     * 获取收货地址
     */
    @Override
    public void getAddressList() {
        if (CheckNetwork.checkNetwork2(mContext)) {
            loadDialog();
            baseAction.getAddressList();
        }
    }

    /**
     *获取收货地址成功
     * @param addressListDto
     */
    @Override
    public void getAddressListSuccessful(AddressListDto addressListDto) {
        loadDiss();
        isEditor = false;
        setEditor();
        addressManagementAdapter.refresh(addressListDto.getData());
    }

    /**
     * 删除地址
     * @param text
     */
    @Override
    public void deteleUserAddress(String text) {
        if (CheckNetwork.checkNetwork2(mContext)){
            loadDialog();
            baseAction.deteleUserAddress(text);
        }
    }

    /**
     * 删除地址成功
     * @param generalDto
     */
    @Override
    public void deteleUserAddressSuccessful(GeneralDto generalDto) {
        loadDiss();
        getAddressList();
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
            getAddressList();
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
