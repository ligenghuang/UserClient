package com.yizhitong.userclient.utils.config;

import android.content.Context;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.multidex.MultiDex;

import com.lgh.huanglib.util.config.MyApplication;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.yizhitong.userclient.utils.Constanst;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import cn.rongcloud.rtc.room.RongRTCRoom;
import io.rong.callkit.RongCallKit;
import io.rong.calllib.RongCallClient;
import io.rong.imkit.RongContext;
import io.rong.imkit.RongIM;
import io.rong.imlib.IChatRoomHistoryMessageCallback;
import io.rong.imlib.IConnectionStatusListener;
import io.rong.imlib.IDownloadMediaCallback;
import io.rong.imlib.IDownloadMediaFileCallback;
import io.rong.imlib.IDownloadMediaMessageCallback;
import io.rong.imlib.IFwLogCallback;
import io.rong.imlib.IGetNotificationQuietHoursCallback;
import io.rong.imlib.IGetUserStatusCallback;
import io.rong.imlib.IHandler;
import io.rong.imlib.IIntegerCallback;
import io.rong.imlib.ILongCallback;
import io.rong.imlib.IOperationCallback;
import io.rong.imlib.IRTCConfigCallback;
import io.rong.imlib.IRTCJoinRoomCallback;
import io.rong.imlib.IResultCallback;
import io.rong.imlib.IRtcIODataListener;
import io.rong.imlib.ISendMediaMessageCallback;
import io.rong.imlib.ISendMessageCallback;
import io.rong.imlib.ISetPushSettingCallback;
import io.rong.imlib.ISetUserStatusCallback;
import io.rong.imlib.IStringCallback;
import io.rong.imlib.ISubscribeUserStatusCallback;
import io.rong.imlib.IUploadCallback;
import io.rong.imlib.OnGetHistoryMessagesCallback;
import io.rong.imlib.OnReceiveMessageListener;
import io.rong.imlib.PushNotificationListener;
import io.rong.imlib.RTCDataListener;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Group;
import io.rong.imlib.model.Message;
import io.rong.imlib.model.RCEncryptedSession;
import io.rong.imlib.model.SearchConversationResult;
import io.rong.imlib.model.SendMessageOption;
import io.rong.imlib.model.UserData;

import static com.yizhitong.userclient.utils.Constanst.appkey;


/**
*
* @author lgh
* created at 2019/5/13 0013 14:32
*/
public class MyApp extends MyApplication {
    /**
     * <pre>
     *     desc   :  ☆ ☆ ☆ ☆ ☆ ☆ ☆ ☆ ☆ ☆ ☆ ☆ ☆ ☆ ☆ ☆ ☆ ☆ ☆ ☆ ☆ ☆ ☆ ☆ ☆ ☆ ☆ :   desc
     *     desc   :                                                                           :   desc
     *     desc   :                                                                           :   desc
     *     desc   :  全局   SmartRefreshLayout 设置
     *     desc   :                                                                           :   desc
     *     desc   :                                                                           :   desc
     *     desc   :                                                                           :   desc
     *     desc   : ☆ ☆ ☆ ☆ ☆ ☆ ☆ ☆ ☆ ☆ ☆ ☆ ☆ ☆ ☆ ☆ ☆ ☆ ☆ ☆ ☆ ☆ ☆ ☆ ☆ ☆ ☆  :   desc
     * </pre>
     */
    static {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @NonNull
            @Override
            public RefreshHeader createRefreshHeader(@NonNull Context context, @NonNull RefreshLayout layout) {
                ClassicsHeader header = new ClassicsHeader(context).setSpinnerStyle(SpinnerStyle.FixedBehind);
                header.setPrimaryColorId(com.lgh.huanglib.R.color.white);
                header.setAccentColorId(android.R.color.black);
                return header;//指定为经典Header，默认是 贝塞尔雷达Header
            }
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() {
            @NonNull
            @Override
            public RefreshFooter createRefreshFooter(@NonNull Context context, @NonNull RefreshLayout layout) {
                layout.setEnableLoadMoreWhenContentNotFull(true);//内容不满一页时候启用加载更多
                ClassicsFooter footer = new ClassicsFooter(context);
                footer.setBackgroundResource(android.R.color.white);
                footer.setSpinnerStyle(SpinnerStyle.Scale);//设置为拉伸模式
                return footer;//指定为经典Footer，默认是 BallPulseFooter
            }
        });

    }


    public static IWXAPI api;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();
//        api = WXAPIFactory.createWXAPI(this, Constanst.APP_ID, true);
//        // 将该app注册到微信
//        api.registerApp(Constanst.APP_ID);

        RongIMClient.init(this);
        RongContext.init(this);
        RongIM.init(this);

    }

    public static IWXAPI getWxApi() {
        return api;
    }


//

}
