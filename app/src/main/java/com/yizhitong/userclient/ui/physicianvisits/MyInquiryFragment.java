package com.yizhitong.userclient.ui.physicianvisits;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;
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
import com.yizhitong.userclient.actions.MyInquiryAction;
import com.yizhitong.userclient.adapters.MyInquiryAdapter;
import com.yizhitong.userclient.event.MyInquiryDto;
import com.yizhitong.userclient.ui.impl.MyInquiryView;
import com.yizhitong.userclient.ui.login.LoginActivity;
import com.yizhitong.userclient.utils.base.UserBaseFragment;
import com.yizhitong.userclient.utils.data.MySp;
import com.yizhitong.userclient.utils.diffcallback.InquiryDiffCallBack;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
* description ： 我的问诊单 fragment
* author : lgh
* email : 1045105946@qq.com
* date : 2019/6/14
*/
public class MyInquiryFragment extends UserBaseFragment<MyInquiryAction> implements MyInquiryView {
    View view;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.iv_null_data)
    ImageView nullDataIv;

    MyInquiryAdapter myInquiryAdapter;
    int position = 0;

    String ConfirmationId;
    String UserId;
    boolean isMessage = false;

    List<MyInquiryDto.DataBean> mDatas = new ArrayList<>();
    private static final int H_CODE_UPDATE = 1;
    private List<MyInquiryDto.DataBean> mNewDatas;//增加一个变量暂存newList

    boolean isVisible = false;

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

        myInquiryAdapter = new MyInquiryAdapter(mContext);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setAdapter(myInquiryAdapter);

    }

    @Override
    protected void initView() {
        super.initView();

    }

    @Override
    protected MyInquiryAction initAction() {
        return new MyInquiryAction((RxAppCompatActivity) mActivity, this);
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
    protected void loadView() {
        super.loadView();
    }


    @Override
    protected void onFragmentVisibleChange(boolean isVisible) {
        L.e("lgh_myInquiry", "个人中心 onFragmentVisibleChange........." + isVisible);
        this.isVisible = isVisible;
        if (isVisible && MySp.iSLoginLive(mContext) && PhysicianvisitsFragment.Position == position) {
            //更新界面数据，如果数据还在下载中，就显示加载框
//            loadNet();
            L.e("lgh_myInquiry", "个人中心 onFragmentVisibleChange11........." + isVisible);
            getAskHead();
        }
//        else if(!MySp.iSLoginLive(mContext) && MyInquiryActivity.Position == position){
//            onLigonError();
//        }
    }

    @Override
    protected void onFragmentFirstVisible() {
        //去服务器下载数据
        L.e("xx", "个人中心 onFragmentFirstVisible.........");
    }


    public static MyInquiryFragment newInstance(int position) {
        MyInquiryFragment fragment = new MyInquiryFragment();
        Bundle args = new Bundle();
        args.putInt("position", position);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * 获取问诊单列表
     */
    @Override
    public void getAskHead() {
        if (CheckNetwork.checkNetwork2(mContext)) {
            baseAction.getAskHead(position );
        }
    }

    /**
     * 获取问诊单列表成功
     *
     * @param myInquiryDto
     */
    @Override
    public void getAskHeadSuccessful(MyInquiryDto myInquiryDto) {
      if (myInquiryDto.getData().size() == 0){
          nullDataIv.setVisibility(View.VISIBLE);
          recyclerView.setVisibility(View.GONE);
      }else {
          mNewDatas = myInquiryDto.getData();
          new Thread(new Runnable() {
              @Override
              public void run() {
                  //放在子线程中计算DiffResult
                  DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new InquiryDiffCallBack(mDatas, mNewDatas), true);
                  Message message = mHandler.obtainMessage(H_CODE_UPDATE);
                  message.obj = diffResult;//obj存放DiffResult
                  message.sendToTarget();
              }
          }).start();
      }

    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case H_CODE_UPDATE:
                    //取出Result
                    DiffUtil.DiffResult diffResult = (DiffUtil.DiffResult) msg.obj;
                    //利用DiffUtil.DiffResult对象的dispatchUpdatesTo（）方法，传入RecyclerView的Adapter，轻松成为文艺青年
                    diffResult.dispatchUpdatesTo(myInquiryAdapter);
                    //别忘了将新数据给Adapter
                    mDatas = mNewDatas;
                    myInquiryAdapter.refresh(mDatas);
                    nullDataIv.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                    break;
            }
        }
    };

    /**
     * 失败
     *
     * @param message
     * @param code
     */
    @Override
    public void onError(String message, int code) {
        loadDiss();
        if (!TextUtils.isEmpty(message)) {
            showToast(message);
        }
    }

    /**
     * 未登录
     */
    @Override
    public void onLigonError() {
        loadDiss();
        jumpActivityNotFinish(mContext, LoginActivity.class);
    }

    @Override
    public void onResume() {
        super.onResume();
        IsFastClick.lastClickTime = 0;
        if (baseAction != null) {
            baseAction.toRegister();
        }

        if (PhysicianvisitsFragment.Position == position&&MySp.iSLoginLive(mContext)&&isVisible) {
            L.e("lgh", "onResume  = " + true);
            getAskHead();
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
