package com.yizhitong.userclient.ui.mine;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.lgh.huanglib.util.CheckNetwork;
import com.lgh.huanglib.util.L;
import com.lgh.huanglib.util.data.IsFastClick;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.yizhitong.userclient.R;
import com.yizhitong.userclient.actions.MyPrescriptionAction;
import com.yizhitong.userclient.adapters.MyPrescriptionAdapter;
import com.yizhitong.userclient.event.GeneralDto;
import com.yizhitong.userclient.event.MyPrescriptionDto;
import com.yizhitong.userclient.ui.impl.MyPrescriptionView;
import com.yizhitong.userclient.ui.login.LoginActivity;
import com.yizhitong.userclient.utils.base.UserBaseFragment;
import com.yizhitong.userclient.utils.data.MySp;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.lgh.huanglib.util.config.MyApplication.getContext;

/**
 * 我的处方
 *
 * @author lgh
 * created at 2019/5/18 0018 10:19
 */
public class MyPrescriptionFragment extends UserBaseFragment<MyPrescriptionAction> implements MyPrescriptionView {
    View view;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.iv_null_data)
    ImageView nullDataIv;

    MyPrescriptionAdapter myPrescriptionAdapter;
    int position = 0;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mContext = getContext();
        mActivity = activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        L.e("xx", "ArrangingManageFragment  个人中心   onCreate.........");
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            position = bundle.getInt("position");
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_my_inquiry, container, false);
        ButterKnife.bind(this, view);
        binding();
        mImmersionBar.statusBarDarkFont(true, 0.2f).init();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onStart() {
        super.onStart();
    }


    @Override
    protected void init() {
        super.init();

        myPrescriptionAdapter = new MyPrescriptionAdapter(mContext);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setAdapter(myPrescriptionAdapter);

    }

    @Override
    protected void initView() {
        super.initView();

    }

    @Override
    protected MyPrescriptionAction initAction() {
        return new MyPrescriptionAction((RxAppCompatActivity) mActivity, this);
    }

    @Override
    protected void initialize() {
        init();
        initView();
        loadView();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }

    @Override
    protected void onFragmentVisibleChange(boolean isVisible) {
        L.e("xx", "个人中心 onFragmentVisibleChange........." + isVisible);
        if (isVisible && MySp.iSLoginLive(mContext) && MyPrescriptionActivity.Position == position) {
            //更新界面数据，如果数据还在下载中，就显示加载框
//            loadNet();
            loadDialog();
          if (!MyPrescriptionActivity.isIsLogin){

              isLogin();
          }else {
              getPrescription();
          }
        }
//        else if(!MySp.iSLoginLive(mContext) && MyPrescriptionActivity.Position == position){
//            onLigonError();
//        }
    }

    @Override
    protected void onFragmentFirstVisible() {
        //去服务器下载数据
        L.e("xx", "个人中心 onFragmentFirstVisible.........");
    }


    public static MyPrescriptionFragment newInstance(int position) {
        MyPrescriptionFragment fragment = new MyPrescriptionFragment();
        Bundle args = new Bundle();
        args.putInt("position", position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void loadView() {
        super.loadView();
        myPrescriptionAdapter.setOnClickListener(new MyPrescriptionAdapter.OnClickListener() {
            @Override
            public void delete(String iuid) {
                deletePrescription(iuid);
            }
        });
    }

    @Override
    public void isLogin() {
        if (CheckNetwork.checkNetwork2(mContext)) {
            MyPrescriptionActivity.isIsLogin = true;
            baseAction.isLogin();
        }
    }

    @Override
    public void isLoginSuccessful() {
        if (MyPrescriptionActivity.Position == position) {
            getPrescription();
        }
    }

    @Override
    public void isLoginError() {
        loadDiss();
        MyPrescriptionActivity.isIsLogin = false;
        jumpActivityNotFinish(mContext, LoginActivity.class);
    }

    /**
     * 获取处方订单列表
     */
    @Override
    public void getPrescription() {
        if (CheckNetwork.checkNetwork2(mContext)) {
            baseAction.getPrescription(position);
        }
    }

    /**
     * 获取处方订单列表成功
     * @param myPrescriptionDto
     */
    @Override
    public void getPrescriptionSuccessful(MyPrescriptionDto myPrescriptionDto) {
        loadDiss();
        loadDiss();
       if (myPrescriptionDto.getData().size() == 0){
           recyclerView.setVisibility(View.GONE);
           nullDataIv.setVisibility(View.VISIBLE);
       }else {
           recyclerView.setVisibility(View.VISIBLE);
           nullDataIv.setVisibility(View.GONE);
           myPrescriptionAdapter.refresh(myPrescriptionDto.getData());
       }
    }

    /**
     * 删除处方订单
     * @param iuid
     */
    @Override
    public void deletePrescription(String iuid) {
        if (CheckNetwork.checkNetwork2(mContext)){
            loadDialog();
            baseAction.deletePrescription(iuid);
        }
    }

    /**
     * 删除处方订单成功
     * @param generalDto
     */
    @Override
    public void deletePrescriptionSuccessful(GeneralDto generalDto) {
        showToast(generalDto.getMsg());
        if (MyPrescriptionActivity.Position == position) {
            //TODO 删除成功  重新获取数据
            getPrescription();
        }
    }


    @Override
    public void onError(String message, int code) {
        loadDiss();
        if (MyPrescriptionActivity.Position == position) {
            showToast(message);
        }
    }

    @Override
    public void onLigonError() {
        loadDiss();
        if (MyPrescriptionActivity.Position == position) {
            jumpActivity(mContext, LoginActivity.class);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        IsFastClick.lastClickTime = 0;
        if (baseAction != null) {
            baseAction.toRegister();
        }
        if (MyPrescriptionActivity.Position == position) {
            if (!MyPrescriptionActivity.isIsLogin){
                isLogin();
            }else {
                getPrescription();
            }
        }
        L.e("lgh", "onResume  = " + position);
    }

    @Override
    public void onPause() {
        super.onPause();
        if (baseAction != null) {
            baseAction.toUnregister();
        }
    }


}
