package com.yizhitong.userclient.ui.message;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lgh.huanglib.util.CheckNetwork;
import com.lgh.huanglib.util.L;
import com.lgh.huanglib.util.base.MyFragmentPagerAdapter;
import com.lgh.huanglib.util.cusview.CustomViewPager;
import com.lgh.huanglib.util.data.ResUtil;
import com.qmuiteam.qmui.widget.QMUITabSegment;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.yizhitong.userclient.R;
import com.yizhitong.userclient.actions.BaseAction;
import com.yizhitong.userclient.actions.MessageAction;
import com.yizhitong.userclient.adapters.MessageLlistAdapter;
import com.yizhitong.userclient.event.MessageDto;
import com.yizhitong.userclient.event.MessageListDto;
import com.yizhitong.userclient.ui.MainActivity;
import com.yizhitong.userclient.ui.impl.MessageView;
import com.yizhitong.userclient.ui.login.LoginActivity;
import com.yizhitong.userclient.ui.physicianvisits.MyInquiryFragment;
import com.yizhitong.userclient.utils.base.UserBaseFragment;
import com.yizhitong.userclient.utils.data.MySp;
import com.yizhitong.userclient.utils.diffcallback.CourseTableDiffCallBack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MessageFragment extends UserBaseFragment<MessageAction> implements MessageView {
    View view;
    @BindView(R.id.top_view)
    View topView;
    @BindView(R.id.f_title_tv)
    TextView titleTv;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.iv_null_message)
    ImageView nullMessageIV;

    MessageLlistAdapter messageLlistAdapter;

    List<MessageListDto.DataBean> mDatas = new ArrayList<>();
    private static final int H_CODE_UPDATE = 1;
    private List<MessageListDto.DataBean> mNewDatas;//增加一个变量暂存newList

    boolean isVisible = false;

    @Override
    protected MessageAction initAction() {
        return new MessageAction((RxAppCompatActivity) getActivity(), this);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mContext = getContext();
        mActivity = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_message, container, false);
        ButterKnife.bind(this, view);
        binding();
        mImmersionBar.setStatusBarView(getActivity(), topView);
        return view;
    }

    @Override
    protected void onFragmentFirstVisible() {
        super.onFragmentFirstVisible();
    }

    @Override
    protected void onFragmentVisibleChange(boolean isVisible) {
        super.onFragmentVisibleChange(isVisible);
        this.isVisible = isVisible;
        if (isVisible) {
            ((MainActivity) getActivity()).changeStatusBar(true, R.color.white);
            getMessageList();
        }

    }

    @Override
    protected void initialize() {
        init();
    }

    @Override
    protected void init() {
        super.init();
        titleTv.setText(ResUtil.getString(R.string.tab_message));

        messageLlistAdapter = new MessageLlistAdapter(mContext);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setAdapter(messageLlistAdapter);
    }


    /**
     * 获取消息列表
     */
    @Override
    public void getMessageList() {
        if (CheckNetwork.checkNetwork2(mContext)) {
            baseAction.getMessageList();
        }
    }

    /**
     * 获取消息列表成功
     *
     * @param messageListDto
     */
    @Override
    public void getMessageListSuccessful(MessageListDto messageListDto) {
        mNewDatas = messageListDto.getData();
        new Thread(new Runnable() {
            @Override
            public void run() {
                //放在子线程中计算DiffResult
                DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new CourseTableDiffCallBack(mDatas, mNewDatas), true);
                Message message = mHandler.obtainMessage(H_CODE_UPDATE);
                message.obj = diffResult;//obj存放DiffResult
                message.sendToTarget();
            }
        }).start();
        recyclerView.setVisibility(messageListDto.getData().size() == 0 ? View.GONE : View.VISIBLE);
        nullMessageIV.setVisibility(messageListDto.getData().size() == 0 ? View.VISIBLE : View.GONE);
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case H_CODE_UPDATE:
                    //取出Result
                    DiffUtil.DiffResult diffResult = (DiffUtil.DiffResult) msg.obj;
                    //利用DiffUtil.DiffResult对象的dispatchUpdatesTo（）方法，传入RecyclerView的Adapter，轻松成为文艺青年
                    diffResult.dispatchUpdatesTo(messageLlistAdapter);
                    //别忘了将新数据给Adapter
                    mDatas = mNewDatas;
                    messageLlistAdapter.refresh(mDatas);
                    break;
            }
        }
    };

    /**
     * Socket接收到消息
     *
     * @param messageDto
     */
    @Override
    public void getMessage(MessageDto messageDto) {
        getMessageList();
        ((MainActivity) getActivity()).setIv2(true);
    }

    @Override
    public void onError(String message, int code) {

    }

    @Override
    public void onLigonError() {

    }

    @Override
    public void onResume() {
        super.onResume();
        baseAction.toRegister();
        if (MySp.getToken(mContext) != null&&isVisible) {
            getMessageList();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        baseAction.toUnregister();
    }


}
