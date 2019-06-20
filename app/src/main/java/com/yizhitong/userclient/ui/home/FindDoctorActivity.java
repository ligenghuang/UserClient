package com.yizhitong.userclient.ui.home;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lgh.huanglib.util.CheckNetwork;
import com.lgh.huanglib.util.L;
import com.lgh.huanglib.util.base.ActivityStack;
import com.lxj.xpopup.XPopup;
import com.yizhitong.userclient.R;
import com.yizhitong.userclient.actions.FindDoctorAction;
import com.yizhitong.userclient.adapters.FindDoctorAdapter;
import com.yizhitong.userclient.event.DepartListDto;
import com.yizhitong.userclient.event.FindDoctorDto;
import com.yizhitong.userclient.event.post.FindDoctorPost;
import com.yizhitong.userclient.ui.MainActivity;
import com.yizhitong.userclient.ui.impl.FindDoctorView;
import com.yizhitong.userclient.ui.login.LoginActivity;
import com.yizhitong.userclient.utils.base.UserBaseActivity;
import com.yizhitong.userclient.utils.popup.CustomCitytPopupView;
import com.yizhitong.userclient.utils.popup.CustomDepartPopupView;
import com.yizhitong.userclient.utils.popup.CustomScreenPopupView;
import com.yizhitong.userclient.utils.popup.CustomSortPopupView;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * description ： 搜索医生
 * author : lgh
 * email : 1045105946@qq.com
 * date : 2019/6/20
 */
public class FindDoctorActivity extends UserBaseActivity<FindDoctorAction> implements FindDoctorView {
    @BindView(R.id.top_view)
    View topView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.tv_tab_1)
    TextView mTvTab1;
    @BindView(R.id.ll_tab_1)
    LinearLayout mLlTab1;
    @BindView(R.id.tv_tab_2)
    TextView mTvTab2;
    @BindView(R.id.ll_tab_2)
    LinearLayout mLlTab2;
    @BindView(R.id.tv_tab_3)
    TextView mTvTab3;
    @BindView(R.id.ll_tab_3)
    LinearLayout mLlTab3;
    @BindView(R.id.tv_tab_4)
    TextView mTvTab4;
    @BindView(R.id.ll_tab_4)
    LinearLayout mLlTab4;
    @BindView(R.id.rv_doctor)
    RecyclerView mRvDoctor;

    CustomDepartPopupView customDepartPopupView;
    CustomCitytPopupView customCitytPopupView;
    CustomSortPopupView customSortPopupView;
    CustomScreenPopupView customScreenPopupView;
    FindDoctorPost post;
    FindDoctorAdapter findDoctorAdapter;

    @Override
    public int intiLayout() {
        return R.layout.activity_find_doctor;
    }

    @Override
    protected FindDoctorAction initAction() {
        return new FindDoctorAction(this, this);
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
                .addTag("FindDoctorActivity")  //给上面参数打标记，以后可以通过标记恢复
                .navigationBarWithKitkatEnable(false)
                .init();
        toolbar.setNavigationOnClickListener(view -> finish());

    }

    @Override
    protected void init() {
        super.init();
        mContext = this;
        mActicity = this;
        customSortPopupView = (CustomSortPopupView) new XPopup.Builder(mContext)
                .atView(mLlTab1)
                .asCustom(new CustomSortPopupView(mContext));
        customScreenPopupView = (CustomScreenPopupView) new XPopup.Builder(mContext)
                .atView(mLlTab1)
                .asCustom(new CustomScreenPopupView(mContext));
        post = new FindDoctorPost();
        post.setRegion("全国-全国");

        findDoctorAdapter = new FindDoctorAdapter(mContext);
        mRvDoctor.setLayoutManager(new LinearLayoutManager(mContext));
        mRvDoctor.setAdapter(findDoctorAdapter);

        findDoctor(post);
        loadView();
    }

    @Override
    protected void loadView() {
        super.loadView();
        //todo 排序
        customSortPopupView.setOnClickListener(new CustomSortPopupView.OnClickListener() {
            @Override
            public void onDepartPopupClick(String id) {
                L.e("lgh_id", "id  =  " + id);
                post.setSort(id);
                findDoctor(post);
                customSortPopupView.dismiss();
            }
        });
        //TODO 筛选
        customScreenPopupView.setOnClickListener(new CustomScreenPopupView.OnClickListener() {
            @Override
            public void onDepartPopupClick(String the_level, String money, int isPrescription) {
                post.setThe_level(the_level);
                post.setJIAGEQ(money);
                post.setChufang(isPrescription == 1 ? "1" : "");
                findDoctor(post);
                customScreenPopupView.dismiss();
            }
        });
    }

    @OnClick({R.id.tv_tab_1, R.id.ll_tab_2, R.id.ll_tab_3, R.id.ll_tab_4})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_tab_1:
                findDepartByAll();
                break;
            case R.id.ll_tab_2:
                showCustomCityPopupView();
                break;
            case R.id.ll_tab_3:
                //todo 显示排序弹窗
                customSortPopupView.show();
                break;
            case R.id.ll_tab_4:
                //todo 显示筛选弹窗
                customScreenPopupView.show();
                break;
        }
    }


    /**
     * 显示省市二级弹窗
     */
    private void showCustomCityPopupView() {
        customCitytPopupView = (CustomCitytPopupView) new XPopup.Builder(mContext)
                .atView(mLlTab1)
                .asCustom(new CustomCitytPopupView(mContext, mTvTab2.getText().toString()));
        customCitytPopupView.setOnClickListener(new CustomCitytPopupView.OnClickListener() {
            @Override
            public void onDepartPopupClick(String city, String name) {
                mTvTab2.setText(name);
                post.setRegion(city);
                findDoctor(post);
                customCitytPopupView.dismiss();
            }
        });
        customCitytPopupView.show();
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
        customDepartPopupView = (CustomDepartPopupView) new XPopup.Builder(mContext)
                .atView(mLlTab1)
                .asCustom(new CustomDepartPopupView(mContext, departListDto, mTvTab1.getText().toString()));
        customDepartPopupView.setOnClickListener(new CustomDepartPopupView.OnClickListener() {
            @Override
            public void onDepartPopupClick(String id, String name) {
                mTvTab1.setText(name);
                post.setDepartid(id.equals("0") ? "" : id);
                findDoctor(post);
                customDepartPopupView.dismiss();
            }
        });
        customDepartPopupView.show();
    }

    @Override
    public void findDoctor(FindDoctorPost post) {
        if (CheckNetwork.checkNetwork2(mContext)) {
            loadDialog();
            baseAction.findDoctor(post);
        }
    }

    @Override
    public void findDoctorSuccessful(FindDoctorDto findDoctorDto) {
        loadDiss();
        findDoctorAdapter.refresh(findDoctorDto.getData());
    }

    /**
     * 失败
     *
     * @param message
     * @param code
     */
    public void onError(String message, int code) {
        loadDiss();
        showNormalToast(message);
    }

    /**
     * 未登录
     */
    @Override
    public void onLigonError() {
        loadDiss();
        jumpActivity(mContext, LoginActivity.class);
        ActivityStack.getInstance().exitIsNotHaveMain(MainActivity.class, LoginActivity.class);
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
