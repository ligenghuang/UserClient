package com.yizhitong.userclient.ui.mine;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lgh.huanglib.util.CheckNetwork;
import com.lgh.huanglib.util.L;
import com.lgh.huanglib.util.config.GlideUtil;
import com.lgh.huanglib.util.data.PriceUtils;
import com.lgh.huanglib.util.data.ResUtil;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.yizhitong.userclient.R;
import com.yizhitong.userclient.actions.MineAction;
import com.yizhitong.userclient.adapters.BaseRecyclerAdapter;
import com.yizhitong.userclient.event.UserInfoDto;
import com.yizhitong.userclient.net.WebUrlUtil;
import com.yizhitong.userclient.ui.MainActivity;
import com.yizhitong.userclient.ui.impl.MineView;
import com.yizhitong.userclient.ui.login.LoginActivity;
import com.yizhitong.userclient.utils.base.UserBaseFragment;
import com.yizhitong.userclient.utils.data.MySp;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 我的fragment
 */
public class MineFragment extends UserBaseFragment<MineAction> implements MineView {
    View view;
    @BindView(R.id.top_view)
    View topView;

    @BindView(R.id.iv_user_portrait)
    ImageView userPortraitIv;
    @BindView(R.id.tv_user_name)
    TextView userNmaeTv;
    @BindView(R.id.tv_user_health_value)
    TextView userHealthValueTv;

    @Override
    protected MineAction initAction() {
        return new MineAction((RxAppCompatActivity) getContext(), this);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mContext = getContext();
        mActivity = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_mine, container, false);
        ButterKnife.bind(this, view);
        binding();
        mImmersionBar.setStatusBarView(getActivity(), topView);
        return view;
    }

    @Override
    protected void initialize() {
        init();
    }

    @Override
    protected void init() {
        super.init();
        if (MySp.iSLoginLive(mContext)) {
            loadDialog();
        }
    }

    @Override
    protected void onFragmentVisibleChange(boolean isVisible) {
        super.onFragmentVisibleChange(isVisible);
        loadDiss();
        if (isVisible) {
            ((MainActivity) getActivity()).changeStatusBar(false, R.color.color_38a234);
        }
    }

    @OnClick({R.id.ll_user_info, R.id.ll_feedback, R.id.ll_address_management, R.id.ll_concerned_doctor,
            R.id.ll_health_records,R.id.ll_prescription_template})
    void OnClick(View view) {
        switch (view.getId()) {
            case R.id.ll_user_info:
                //todo 个人信息
                if (!MySp.iSLoginLive(mContext)) {
                    //todo 判断是否登录
                    jumpActivityNotFinish(mContext, LoginActivity.class);
                    return;
                }
                jumpActivityNotFinish(mContext, UserInfoActivity.class);
                break;
            case R.id.ll_concerned_doctor:
                //todo 我关注的医生
                if (!MySp.iSLoginLive(mContext)) {
                    //todo 判断是否登录
                    jumpActivityNotFinish(mContext, LoginActivity.class);
                    return;
                }
                jumpActivityNotFinish(mContext, ConcernedDoctorActivity.class);
                break;
            case R.id.ll_prescription_template:
                //todo 我的处方
                if (!MySp.iSLoginLive(mContext)) {
                    //todo 判断是否登录
                    jumpActivityNotFinish(mContext, LoginActivity.class);
                    return;
                }
                jumpActivityNotFinish(mContext, MyPrescriptionActivity.class);
                break;
            case R.id.ll_health_records:
                //todo 健康档案
                if (!MySp.iSLoginLive(mContext)) {
                    //todo 判断是否登录
                    jumpActivityNotFinish(mContext, LoginActivity.class);
                    return;
                }
                jumpActivityNotFinish(mContext,HealthRecordsActivity.class);
                break;
            case R.id.ll_address_management:
                //todo 地址管理
                if (!MySp.iSLoginLive(mContext)) {
                    //todo 判断是否登录
                    jumpActivityNotFinish(mContext, LoginActivity.class);
                    return;
                }
                jumpActivityNotFinish(mContext, AddressManagementActivity.class);
                break;
            case R.id.ll_feedback:
                //todo 意见反馈
                if (!MySp.iSLoginLive(mContext)) {
                    //todo 判断是否登录
                    jumpActivityNotFinish(mContext, LoginActivity.class);
                    return;
                }
                //TODO 意见反馈
                jumpActivityNotFinish(mContext, FeedbackActivity.class);
                break;
        }
    }


    @Override
    public void isLogin() {
        if (CheckNetwork.checkNetwork2(mContext)) {
//            loadDialog(ResUtil.getString(R.string.registered_tip_17));
            baseAction.isLogin();
        }
    }

    @Override
    public void isLoginSuccessful() {
        loadDiss();
        getUserInfo();
    }

    @Override
    public void getUserInfo() {
        if (CheckNetwork.checkNetwork2(mContext)) {
            baseAction.getUserInfo();
        }
    }

    @Override
    public void getUserInfoSuccessful(UserInfoDto userInfoDto) {
        loadDiss();
        if (userInfoDto.getCode() == 1) {
            loadDiss();
            UserInfoDto.DataBean userInfoBean = userInfoDto.getData();
            userNmaeTv.setText(userInfoBean.getNicename());
            String portrait = userInfoBean.getNiceImg();
            userHealthValueTv.setVisibility(View.VISIBLE);
            userHealthValueTv.setText(ResUtil.getFormatString(R.string.mine_tip_8, userInfoBean.getHealthCoin()));
            if (portrait.indexOf("H5/Uimg") != -1) {
                GlideUtil.setImage(mContext, WebUrlUtil.IMG_URL + portrait, userPortraitIv, R.drawable.icon_placeholder);
                L.e("lgh", WebUrlUtil.IMG_URL + portrait);
            } else {
                GlideUtil.setImage(mContext, WebUrlUtil.IMG_URL + "H5/Uimg" + portrait, userPortraitIv, R.drawable.icon_placeholder);
                L.e("lgh", WebUrlUtil.IMG_URL + "H5/Uimg" + portrait);
            }
        } else {
            loadDiss();
            MySp.setToken(mContext, null);
        }
    }

    @Override
    public void onError(String message, int code) {
        loadDiss();
        if (!TextUtils.isEmpty(message)) {
            showToast(message);
        }
    }

    @Override
    public void isLoginError() {
        loadDiss();
        jumpActivityNotFinish(mContext, LoginActivity.class);
    }

    @Override
    public void onLigonError() {

    }

    @Override
    public void onResume() {
        super.onResume();
        baseAction.toRegister();
        if (MySp.getToken(mContext) != null) {
            isLogin();
        } else {
            userPortraitIv.setImageResource(R.drawable.icon_placeholder);
            userNmaeTv.setText("未登录");
            userHealthValueTv.setVisibility(View.GONE);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        baseAction.toUnregister();
    }
}
