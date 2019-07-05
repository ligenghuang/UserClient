package com.yizhitong.userclient.ui.message;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.lgh.huanglib.util.L;
import com.lgh.huanglib.util.base.ActivityStack;
import com.lgh.huanglib.util.data.ResUtil;
import com.yizhitong.userclient.R;
import com.yizhitong.userclient.actions.BaseAction;
import com.yizhitong.userclient.utils.base.UserBaseActivity;
import com.yizhitong.userclient.utils.config.MyApp;
import com.yizhitong.userclient.utils.cusview.FlowLayout;
import com.yizhitong.userclient.utils.data.MySp;

import java.lang.ref.WeakReference;
import java.util.List;

import butterknife.BindView;
import cn.rongcloud.rtc.RTCErrorCode;
import cn.rongcloud.rtc.RongRTCEngine;
import cn.rongcloud.rtc.callback.JoinRoomUICallBack;
import cn.rongcloud.rtc.callback.RongRTCResultUICallBack;
import cn.rongcloud.rtc.engine.view.RongRTCVideoView;
import cn.rongcloud.rtc.events.RongRTCEventsListener;
import cn.rongcloud.rtc.room.RongRTCRoom;
import cn.rongcloud.rtc.stream.MediaType;
import cn.rongcloud.rtc.stream.local.RongRTCCapture;
import cn.rongcloud.rtc.stream.remote.RongRTCAVInputStream;
import cn.rongcloud.rtc.user.RongRTCLocalUser;
import cn.rongcloud.rtc.user.RongRTCRemoteUser;
import io.rong.imlib.model.Message;

/**
 * description ： 视频
 * author : lgh
 * email : 1045105946@qq.com
 * date : 2019/7/5
 */
public class RongRTCVideoActivity extends UserBaseActivity implements RongRTCEventsListener, View.OnClickListener{

    private static final String TAG = "RongRTCVideoActivity";
    @BindView(R.id.top_view)
    View topView;
    @BindView(R.id.local_container)
    FrameLayout localContainer;
    @BindView(R.id.finish)
    ImageView button;
    @BindView(R.id.remotes)
    LinearLayout remotes;
    private RongRTCVideoView local;
    private String mToken = "";         //用户token 不通的自己修改
    private String mRoomId = "roomId06"; //自己可以随意修改
    private RongRTCRoom mRongRTCRoom;
    private RongRTCLocalUser mLocalUser;

    @Override
    public int intiLayout() {
        return R.layout.activity_rong_rtcvideo;
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
                .addTag("RongRTCVideoActivity")  //给上面参数打标记，以后可以通过标记恢复
                .navigationBarWithKitkatEnable(false)
                .init();
    }

    @Override
    protected void init() {
        super.init();
        mContext = this;
        mActicity = this;

        mToken = MySp.getRoogToken(mContext);
        local = RongRTCEngine.getInstance().createVideoView(this);
        local.setOnClickListener(this);
        localContainer.addView(local);
//        button.setVisibility(View.GONE);
        button.setOnClickListener(this);
        mRoomId = getIntent().getStringExtra("mRoomId");
        joinRoom();
    }

    /**
     * 加入房间
     */
    private void joinRoom() {
        RongRTCEngine.getInstance().joinRoom(mRoomId, new JoinRoomUICallBack() {
            @Override
            protected void onUiSuccess(RongRTCRoom rongRTCRoom) {
                Toast.makeText(mContext, "加入房间成功", Toast.LENGTH_SHORT).show();
                L.e(TAG,"加入房间成功");
                mRongRTCRoom = rongRTCRoom;
                mLocalUser = rongRTCRoom.getLocalUser();
                RongRTCCapture.getInstance().setRongRTCVideoView(local); //设置本地预览视图
                RongRTCCapture.getInstance().startCameraCapture();       //开始采集数据
                setEventListener();                                      //设置监听
                addRemoteUsersView();
                subscribeAll();                                          //订阅资源
                publishDefaultStream();                                  //发布资源
            }

            @Override
            protected void onUiFailed(RTCErrorCode rtcErrorCode) {
                Toast.makeText(mContext, "加入房间失败 rtcErrorCode：" + rtcErrorCode, Toast.LENGTH_SHORT).show();
                L.e(TAG,"加入房间失败 rtcErrorCode：" + rtcErrorCode);
            }
        });
    }
    /**
     * 注册监听
     */
    private void setEventListener() {
        if (mRongRTCRoom != null) {
            mRongRTCRoom.registerEventsListener(this);
        }
    }

    private void removeListener() {
        if (mRongRTCRoom != null) {
            mRongRTCRoom.unRegisterEventsListener(this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        removeListener();
        RongRTCEngine.getInstance().quitRoom(mRoomId, new RongRTCResultUICallBack() {
            @Override
            public void onUiSuccess() {
                //Toast.makeText(mContext, "离开房间成功", Toast.LENGTH_SHORT).show();
                L.e(TAG,"离开房间成功");
            }

            @Override
            public void onUiFailed(RTCErrorCode rtcErrorCode) {
                //ToastakeText(mContext, "离开房间失败", Toast.LENGTH_SHORT).show();
                L.e(TAG,"离开房间失败");
            }
        });
    }

    /**
     * 添加远端用户View
     */
    private void addRemoteUsersView() {
        if (mRongRTCRoom != null) {
            for (RongRTCRemoteUser remoteUser : mRongRTCRoom.getRemoteUsers().values()) {
                for (RongRTCAVInputStream inputStream : remoteUser.getRemoteAVStreams()) {
                    if (inputStream.getMediaType() == MediaType.VIDEO) {
                        inputStream.setRongRTCVideoView(getNewVideoView());
                    }
                }
            }
        }
    }

    /**
     * 订阅所有当前在房间发布资源的用户
     */
    private void subscribeAll() {
        if (mRongRTCRoom != null) {
            for (RongRTCRemoteUser remoteUser : mRongRTCRoom.getRemoteUsers().values()) {
                remoteUser.subscribeAvStream(remoteUser.getRemoteAVStreams(), new RongRTCResultUICallBack() {
                    @Override
                    public void onUiSuccess() {
                        //ToastakeText(mContext, "订阅资源成功", Toast.LENGTH_SHORT).show();
                        L.e(TAG,"发布资源成功");
                    }

                    @Override
                    public void onUiFailed(RTCErrorCode rtcErrorCode) {
                        //ToastakeText(mContext, "订阅资源成功", Toast.LENGTH_SHORT).show();
                        L.e(TAG,"发布资源成功");
                    }
                });
            }
        }
    }

    /**
     * 发布资源
     */
    private void publishDefaultStream() {
        if (mLocalUser != null) {
            mLocalUser.publishDefaultAVStream(new RongRTCResultUICallBack() {
                @Override
                public void onUiSuccess() {
                    //ToastakeText(mContext, "发布资源成功", Toast.LENGTH_SHORT).show();
                    L.e(TAG,"发布资源成功");
                }

                @Override
                public void onUiFailed(RTCErrorCode rtcErrorCode) {
                    //ToastakeText(mContext, "发布资源失败", Toast.LENGTH_SHORT).show();
                    L.e(TAG,"发布资源失败");
                }
            });
        }

    }

    private RongRTCVideoView getNewVideoView() {
        Log.i(TAG, "getNewVideoView()");
        RongRTCVideoView videoView = RongRTCEngine.getInstance().createVideoView(this);
        videoView.setOnClickListener(this);
        remotes.addView(videoView, new LinearLayout.LayoutParams(remotes.getHeight(), remotes.getHeight()));
        remotes.bringToFront();
        return videoView;
    }


    @Override
    public void onRemoteUserPublishResource(RongRTCRemoteUser rongRTCRemoteUser, List<RongRTCAVInputStream> list) {
        for (RongRTCAVInputStream inputStream : rongRTCRemoteUser.getRemoteAVStreams()) {
            if (inputStream.getMediaType() == MediaType.VIDEO) {
                inputStream.setRongRTCVideoView(getNewVideoView());
            }
        }
        rongRTCRemoteUser.subscribeAvStream(rongRTCRemoteUser.getRemoteAVStreams(), new RongRTCResultUICallBack() {
            @Override
            public void onUiSuccess() {
                //ToastakeText(mContext, "订阅成功", Toast.LENGTH_SHORT).show();
                L.e(TAG,"订阅成功");
            }

            @Override
            public void onUiFailed(RTCErrorCode rtcErrorCode) {
                //ToastakeText(mContext, "订阅失败", Toast.LENGTH_SHORT).show();
                L.e(TAG,"订阅失败");
            }
        });
    }

    @Override
    public void onRemoteUserAudioStreamMute(RongRTCRemoteUser rongRTCRemoteUser, RongRTCAVInputStream rongRTCAVInputStream, boolean b) {

    }

    @Override
    public void onRemoteUserVideoStreamEnabled(RongRTCRemoteUser rongRTCRemoteUser, RongRTCAVInputStream rongRTCAVInputStream, boolean b) {

    }

    @Override
    public void onRemoteUserUnPublishResource(RongRTCRemoteUser rongRTCRemoteUser, List<RongRTCAVInputStream> list) {

    }

    @Override
    public void onUserJoined(RongRTCRemoteUser rongRTCRemoteUser) {

    }

    @Override
    public void onUserLeft(RongRTCRemoteUser rongRTCRemoteUser) {
        for (RongRTCAVInputStream inputStream : rongRTCRemoteUser.getRemoteAVStreams()) {
            if (inputStream.getMediaType() == MediaType.VIDEO) {
                remotes.removeView(inputStream.getRongRTCVideoView());
            }
        }
    }

    @Override
    public void onUserOffline(RongRTCRemoteUser rongRTCRemoteUser) {

    }

    @Override
    public void onVideoTrackAdd(String s, String s1) {

    }

    @Override
    public void onFirstFrameDraw(String s, String s1) {

    }

    @Override
    public void onLeaveRoom() {

    }

    @Override
    public void onReceiveMessage(Message message) {

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.finish) {
            quit();
            finish();
        } else if (v instanceof RongRTCVideoView) {
            int index = -1;
            for (int i = 0; i < remotes.getChildCount(); i++) {
                RongRTCVideoView videoView = (RongRTCVideoView) remotes.getChildAt(i);
                if (videoView == v) {
                    index = i;
                }
            }
            if (index != -1) {
                RongRTCVideoView big = (RongRTCVideoView) localContainer.getChildAt(0);
                localContainer.removeViewAt(0);
                remotes.addView(big,index, new LinearLayout.LayoutParams(remotes.getHeight(), remotes.getHeight()));
                remotes.removeView(v);
                localContainer.addView(v, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            }
        }
    }

    @Override
    public void onBackPressed() {
        quit();
        super.onBackPressed();

    }

    private void quit() {
        RongRTCEngine.getInstance().quitRoom(mRongRTCRoom.getRoomId(), new RongRTCResultUICallBack() {
            @Override
            public void onUiSuccess() {
                //ToastakeText(mContext, "离开房间成功", Toast.LENGTH_SHORT).show();
                L.e(TAG,"离开房间成功");
            }

            @Override
            public void onUiFailed(RTCErrorCode rtcErrorCode) {
                //ToastakeText(mContext, "离开房间失败", Toast.LENGTH_SHORT).show();
                L.e(TAG,"离开房间失败");
            }
        });
    }
}
