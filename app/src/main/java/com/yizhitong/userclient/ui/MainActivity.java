package com.yizhitong.userclient.ui;

import android.content.Context;
import android.os.Handler;
import android.os.Process;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lgh.huanglib.actions.Action;
import com.lgh.huanglib.event.StoreEvent;
import com.lgh.huanglib.util.L;
import com.lgh.huanglib.util.base.ActivityStack;
import com.lgh.huanglib.util.base.MyFragmentPagerAdapter;
import com.lgh.huanglib.util.cusview.CustomViewPager;
import com.yizhitong.userclient.R;
import com.yizhitong.userclient.actions.BaseAction;
import com.yizhitong.userclient.event.MessageDto;
import com.yizhitong.userclient.event.SendMessageDto;
import com.yizhitong.userclient.event.post.SendMessagePost;
import com.yizhitong.userclient.net.WebUrlUtil;
import com.yizhitong.userclient.ui.home.HomeFragment;
import com.yizhitong.userclient.ui.login.LoginActivity;
import com.yizhitong.userclient.ui.message.MessageFragment;
import com.yizhitong.userclient.ui.mine.MineFragment;
import com.yizhitong.userclient.ui.mine.MyPrescriptionFragment;
import com.yizhitong.userclient.ui.physicianvisits.PhysicianvisitsFragment;
import com.yizhitong.userclient.utils.base.UserBaseActivity;
import com.yizhitong.userclient.utils.cusview.NotificationHelper;
import com.yizhitong.userclient.utils.data.MySp;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.exceptions.WebsocketNotConnectedException;
import org.java_websocket.handshake.ServerHandshake;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.ArrayList;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import butterknife.BindView;
import butterknife.OnTouch;

import static com.lgh.huanglib.event.EventBusUtils.post;

public class MainActivity extends UserBaseActivity {

    private static final String TAG = "MainActivity";
    public static int Position = 0;
    private static final int POIONTONE = 0;
    private static final int POIONTTWO = 1;
    private static final int POIONTTHREE = 2;
    private static final int POIONTFOUR = 3;

    MineFragment mineFragment;
    MessageFragment messageFragment;
    HomeFragment homeFragment;
    PhysicianvisitsFragment physicianvisitsFragment;

    @BindView(R.id.top_view)
    View topView;
    @BindView(R.id.my_pager)
    CustomViewPager myPager;
    @BindView(R.id.lin_1)
    LinearLayout lin1;
    @BindView(R.id.lin_2)
    LinearLayout lin2;
    @BindView(R.id.lin_3)
    LinearLayout lin3;
    @BindView(R.id.lin_4)
    LinearLayout lin4;

    private ArrayList<Fragment> fragments;
    private MyFragmentPagerAdapter fragmentPagerAdapter;
    private int fragmentSize = 4;
    // 是否能够退出
    private boolean isBack = false;

    // 上次按退出的时间
    private long downTime;

    static WebSocketClient client;

    int Heartbeat = 0;//心跳包发送失败次数

    @Override
    public int intiLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityStack.getInstance().addActivity(new WeakReference<>(this));
        binding();
    }

    @Override
    protected BaseAction initAction() {
        return null;
    }

    @Override
    protected void init() {
        super.init();
        mActicity = this;
        mContext = this;
        mImmersionBar
//                .statusBarView(R.id.top_view)
                .fullScreen(true)
                .navigationBarWithKitkatEnable(false)
                .statusBarDarkFont(true)
                .addTag("main")  //给上面参数打标记，以后可以通过标记恢复
                .init();
        initViewPager();


    }

    /**
     * 初始化ViewPager
     */
    private void initViewPager() {
        fragments = new ArrayList<Fragment>();
        for (int i = 0; i < fragmentSize; i++) {
            switch (i) {
                case POIONTONE://
                    homeFragment = new HomeFragment();
                    if (Position != POIONTONE) {
                        homeFragment.setUserVisibleHint(false);//
                    }

                    fragments.add(homeFragment);
                    break;
                case POIONTTWO://
                  messageFragment = new MessageFragment();
                    if (Position != POIONTTWO) {
                        messageFragment.setUserVisibleHint(false);//
                    }

                    fragments.add(messageFragment);
                    break;
                case POIONTTHREE://
                    physicianvisitsFragment = new PhysicianvisitsFragment();
                    if (Position != POIONTTHREE) {
                        physicianvisitsFragment.setUserVisibleHint(false);//
                    }
                    fragments.add(physicianvisitsFragment);

                    break;
                case POIONTFOUR:
                    mineFragment = new MineFragment();
                    if (Position != POIONTFOUR) {
                        mineFragment.setUserVisibleHint(false);
                    }
                    fragments.add(mineFragment);

                    break;

                default:
                    break;
            }
        }

        fragmentPagerAdapter = new MyFragmentPagerAdapter(
                getSupportFragmentManager(), fragments);

        fragmentPagerAdapter.setFragments(fragments);
        setSelectedLin(Position);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                myPager.setAdapter(fragmentPagerAdapter);
                myPager.setCurrentItem(Position, false);
                myPager.setOffscreenPageLimit(fragmentSize);


            }
        }, 500);

    }

    @OnTouch({R.id.lin_1, R.id.lin_2, R.id.lin_3, R.id.lin_4})
    public boolean onTouch(View v) {
        switch (v.getId()) {
            case R.id.lin_1:
                if (!MySp.iSLoginLive(mContext)) {
                    jumpActivityNotFinish(mContext, LoginActivity.class);
                    return false;
                }
                Position = POIONTONE;
                break;
            case R.id.lin_2:
                if (!MySp.iSLoginLive(mContext)) {
                    jumpActivityNotFinish(mContext, LoginActivity.class);
                    return false;
                }
                Position = POIONTTWO;
                break;
            case R.id.lin_3:
                if (!MySp.iSLoginLive(mContext)) {
                    jumpActivityNotFinish(mContext, LoginActivity.class);
                    return false;
                }
                Position = POIONTTHREE;
                break;
            case R.id.lin_4:
                Position = POIONTFOUR;
                break;
            default:
                break;
        }
        setSelectedLin(Position);
        myPager.setCurrentItem(Position, false);
        return false;
    }

    /**
     * 跳转fragment
     * @param position
     */
    public void setPosition(int position){
        Position = position;
        setSelectedLin(Position);
        myPager.setCurrentItem(Position, false);
    }

    public void changeStatusBar(boolean isBlack, int bgColor) {
        mImmersionBar.statusBarDarkFont(isBlack).statusBarColor(bgColor).init();
    }

    /**
     * 选择
     *
     * @param position
     */
    private void setSelectedLin(int position) {
        lin1.setSelected(false);
        lin2.setSelected(false);
        lin3.setSelected(false);
        lin4.setSelected(false);
        //设置状态栏黑色字体与图标
//        QMUIStatusBarHelper.setStatusBarLightMode(this);
        switch (position) {
            case 0:
                lin1.setSelected(true);
                break;
            case 1:
                lin2.setSelected(true);
                break;
            case 2:
                lin3.setSelected(true);
                break;
            case 3:
                lin4.setSelected(true);
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub

        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:

                if (!isBack) {
                    showNormalToast(R.string.main_exit);
                    downTime = event.getDownTime();
                    isBack = true;
                    return true;
                } else {
                    if (event.getDownTime() - downTime <= 2000) {
                        ActivityStack.getInstance().removeAll();
                        Process.killProcess(Process.myPid());
                    } else {
                        showNormalToast(R.string.main_exit);
                        downTime = event.getDownTime();
                        return true;
                    }
                }
                break;
        }
        return super.onKeyDown(keyCode, event);
    }


    boolean isFirst = true;

    private WebSocketClient initSocket(String url) throws URISyntaxException {
        return new WebSocketClient(new URI(url), new Draft_6455()) {
            @Override
            public void onOpen(ServerHandshake handshakedata) {
                Log.e("lgh_Socket:", "------连接成功!!!");
                Heartbeat = 0;
                isFirst = true;
                handler.postDelayed(runnable, 1000);
            }

            @Override
            public void onMessage(String message) {
                Log.e("lgh_Socket:", "接受到消息 message = " + message);
                if (isFirst) {
                    sendLogin();
                    isFirst = false;
                } else {
                    MessageDto messageDto = new Gson().fromJson(message, new TypeToken<MessageDto>() {
                    }.getType());
                    if (messageDto.getEvent().equals("chat")) {
                        sendEvent(StoreEvent.ACTION_KEY_SUCCESS, 200, WebUrlUtil.GET_MESSAGE,
                                Action.KEY_OBJ, message);
                        sendEvent(StoreEvent.ACTION_KEY_SUCCESS, 200, WebUrlUtil.GET_MESSAGE_1,
                                Action.KEY_OBJ, message);
                        if (MySp.getMessage(mContext)) {
                            NotificationHelper.show(mContext, messageDto);
                        }
                    } else if (messageDto.getEvent().equals("return")) {
                        sendEvent(StoreEvent.ACTION_KEY_SUCCESS, 200, WebUrlUtil.GET_MESSAGE,
                                Action.KEY_OBJ, message);
                        sendEvent(StoreEvent.ACTION_KEY_SUCCESS, 200, WebUrlUtil.GET_MESSAGE_1,
                                Action.KEY_OBJ, message);

                    } else if (messageDto.getEvent().equals("Heartbeat")) {
                        Heartbeat = 0;
                    }
                }

            }

            @Override
            public void onClose(int code, String reason, boolean remote) {
                Log.e("lgh_Socket:", "------连接关闭!!!" + reason);
                isFirst = true;
                if (client.getReadyState() == READYSTATE.OPEN) {
                    client.close();
                    L.e("lgh_Socket", "Close the current client, ready to reconnect");
                } else if (client.getReadyState() == READYSTATE.NOT_YET_CONNECTED || client.getReadyState() == READYSTATE.CLOSED || client.getReadyState() == READYSTATE.CLOSING) {
                    try {
                        Thread.currentThread().sleep(500);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                    try {
                        client = initSocket(WebUrlUtil.SOCKET);
                    } catch (URISyntaxException e) {
                        e.printStackTrace();
                    }
                    client.connect();
                    L.e("lgh_Socket", "正在重连----------------------------");
                }
            }

            @Override
            public void onError(Exception ex) {
                Log.e("lgh_socket:", ex.toString());
            }
        };
    }

    /**
     * 登录Socket
     */
    private void loginSocket() {
        try {
            client = initSocket(WebUrlUtil.SOCKET);
            // wss需添加
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, new TrustManager[]{new X509TrustManager() {

                @Override
                public void checkClientTrusted(X509Certificate[] chain,
                                               String authType) {
                    Log.e("lgh_Socket:", "------checkClientTrusted!!!" + authType);
                }

                @Override
                public void checkServerTrusted(X509Certificate[] chain,
                                               String authType) {
                    Log.e("lgh_Socket:", "------checkServerTrusted!!!" + authType);
                }

                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    Log.e("lgh_Socket:", "------getAcceptedIssuers!!!");
                    return new X509Certificate[0];
                }
            }}, new SecureRandom());
            SSLSocketFactory factory = sslContext.getSocketFactory();
            client.setSocket(factory.createSocket());
            client.connect();
        } catch (URISyntaxException e) {
            e.printStackTrace();
            Log.e("lgh_Socket:", "------URISyntaxException!!!" + e.getMessage());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            Log.e("lgh_Socket:", "------NoSuchAlgorithmException!!!" + e.getMessage());
        } catch (KeyManagementException e) {
            e.printStackTrace();
            Log.e("lgh_Socket:", "------KeyManagementException!!!" + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("lgh_Socket:", "------IOException!!!" + e.getMessage());
        }
    }


    public void sendEvent(String type, int errorType, String identifying, Object... data) {
        if (isEmpty(type)) {
            throw new IllegalArgumentException("Type must not be empty");
        }

        if (data.length % 2 != 0) {
            throw new IllegalArgumentException("StoreData must be a valid list of key,value pairs");
        }

        Action.Builder actionBuilder = Action.type(type, errorType, identifying);
//        actionBuilder.clazz(actionClazz);
        int i = 0;
        while (i < data.length) {
            String key = (String) data[i++];
            Object value = data[i++];
            actionBuilder.bundle(key, value);
        }
        Log.e("xx", "准备post------->");
        post(actionBuilder.buildWithIdentifying());
    }

    private boolean isEmpty(String type) {
        return type == null || type.isEmpty();
    }

    Handler handler = new Handler();


    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            // TODO Auto-generated method stub
            //要做的事情
            if (Heartbeat == 3) {
                try {
                    client = initSocket(WebUrlUtil.SOCKET);
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }
                client.connect();
                L.e("lgh_Socket", "正在重连----------------------------");
            } else {
                sendHeartbeat();
                handler.postDelayed(this, 30000);
            }

        }
    };

    /**
     * 发送消息
     *
     * @param type
     */
    public static void sendMessage(String type, SendMessageDto sendMessageDto, Context context) {
        SendMessagePost post = new SendMessagePost();
        post.setEvent("chat");
        post.set_class(type);
        post.setIuid(sendMessageDto.getData().getIUID());
        post.setNote(sendMessageDto.getData().getChat_note());
        post.setTime(sendMessageDto.getData().getChat_time_stamp());
        post.setToUserId(sendMessageDto.getData().getTouserid());
        post.setUserid(MySp.getToken(context));
        post.setUserImg(sendMessageDto.getData().getUserIMG());
        L.e("lgh_Socket", post.toString());
        if (client != null && client.isOpen()) {
            client.send(post.toString());
        }

    }

    /**
     * 登录
     */
    public void sendLogin() {
        SendMessagePost post = new SendMessagePost();
        post.setEvent("login");
        post.setUserid(MySp.getToken(mContext));
        L.e("lgh_Socket", post.toLogin());
        client.send(post.toLogin());
    }

    /**
     * 发送心跳包
     */
    public void sendHeartbeat() {
        SendMessagePost post = new SendMessagePost();
        Heartbeat = Heartbeat + 1;
        post.setEvent("Heartbeat");
        post.setUserid(MySp.getToken(mContext));
        L.e("lgh_Socket", post.toLogin());
        if (client != null) {
            try {
                client.send(post.toLogin());
            } catch (WebsocketNotConnectedException e) {

            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isFirst && MySp.iSLoginLive(mContext)) {
            loginSocket();
        }
        if (!MySp.iSLoginLive(mContext)) {
            if (client != null) {
                client.close();
            }
        }
       setPosition(Position);

    }

    @Override
    public void finish() {
        super.finish();
        client.close();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnable);
    }
}
