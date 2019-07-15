package com.yizhitong.userclient.utils.wechat;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Parcel;
import android.os.Parcelable;

import com.lgh.huanglib.util.L;
import com.lzy.imagepicker.util.Utils;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.yizhitong.userclient.utils.AppUtil;
import com.yizhitong.userclient.utils.Constanst;
import com.yizhitong.userclient.utils.config.MyApp;

public class PayUtil {



    public static final String ACTION_PAY_RESPONSE = "action_wx_pay_response";
    public static final String EXTRA_RESULT = "result";

    private final Context context;
    private OnResponseListener listener;
    private ResponseReceiver receiver;

    public PayUtil(Context context) {
        this.context = context;
    }

    public PayUtil register() {
        // 微信支付
        L.e("lgh_pay","register ");
        receiver = new ResponseReceiver();
        IntentFilter filter = new IntentFilter(ACTION_PAY_RESPONSE);
        context.registerReceiver(receiver, filter);
        return this;
    }

    public void unregister() {
        try {
            if (receiver != null) {
                L.e("lgh_pay","unregister ");
                context.unregisterReceiver(receiver);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 支付
     *
     * @param partnerId
     * @param appId
     * @param nonceStr
     * @param timestamp
     * @param prepayId
     * @param sign
     */
    public PayUtil pay(String partnerId, String appId, String nonceStr,
                       String timestamp, String prepayId, String sign) {
        String A="appid="+Constanst.APP_ID+"&noncestr="+nonceStr+"&package=Sign=WXPay"+"&partnerid="+partnerId+"&prepayid="+prepayId+"&timestamp="+timestamp;
        String stringSignTemp = A+"&key=jjvB0v1sRbQ8dJqnP1Rhx9myEPKAiIv1";
        String s="";
        try {
            s = AppUtil.HMACSHA256(stringSignTemp,"jjvB0v1sRbQ8dJqnP1Rhx9myEPKAiIv1").toUpperCase();
        } catch (Exception e) {
            e.printStackTrace();
        }
        PayReq req = new PayReq();
        req.appId = appId;
        req.nonceStr = nonceStr;
        req.packageValue = "Sign=WXPay";
        req.partnerId = partnerId;
        req.prepayId = prepayId;
        req.timeStamp = timestamp;
        req.sign = s;
        L.e("lgh_pay",req.sign);
        L.e("lgh_pay",req.appId);
        L.e("lgh_pay",req.partnerId);
        L.e("lgh_pay",req.prepayId);
        L.e("lgh_pay",req.timeStamp);
        L.e("lgh_pay",req.nonceStr);
        MyApp.getWxApi().sendReq(req);
        return this;
    }


    public void setListener(OnResponseListener listener) {
        this.listener = listener;
    }

    private class ResponseReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            L.e("lgh_pay","Response  ");
            Response response = intent.getParcelableExtra(EXTRA_RESULT);
            L.e("lgh_pay","Response  = "+ response.toString());
            L.e("lgh_pay","type: " + response.getType());
            L.e("lgh_pay","errCode: " + response.errCode);
            L.e("lgh_pay","errCode: " + response.type);
            L.e("lgh_pay","errCode: " + response.respType);
            String result;
            if (listener != null) {
                if (response.errCode == BaseResp.ErrCode.ERR_OK) {

                    listener.onSuccess();
                } else if (response.errCode == BaseResp.ErrCode.ERR_USER_CANCEL) {

                    listener.onCancel("已取消支付");
                } else {
                    switch (response.errCode) {
                        case BaseResp.ErrCode.ERR_AUTH_DENIED:
                            result = "发送被拒绝";
                            break;
                        case BaseResp.ErrCode.ERR_UNSUPPORT:
                            result = "不支持错误";
                            break;
                        default:
                            result = "发送返回";
                            break;
                    }

                    listener.onFail(result);
                }
            }
        }
    }

    public static class Response extends BaseResp implements Parcelable {

        public int errCode;
        public String errStr;
        public String transaction;
        public String openId;
        public String code;
        private String respType;

        private int type;
        private boolean checkResult;


        public Response(BaseResp baseResp, String _code) {

            errCode = baseResp.errCode;
            errStr = baseResp.errStr;
            transaction = baseResp.transaction;
            openId = baseResp.openId;
            type = baseResp.getType();
            code = _code;
            checkResult = baseResp.checkArgs();
        }

        public Response(BaseResp baseResp, String _code, String _respType) {

            errCode = baseResp.errCode;
            errStr = baseResp.errStr;
            transaction = baseResp.transaction;
            openId = baseResp.openId;
            type = baseResp.getType();
            respType = _respType;
            code = _code;
            checkResult = baseResp.checkArgs();
        }

        @Override
        public int getType() {
            return type;
        }

        @Override
        public boolean checkArgs() {
            return checkResult;
        }


        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.errCode);
            dest.writeString(this.errStr);
            dest.writeString(this.transaction);
            dest.writeString(this.openId);
            dest.writeString(this.code);
            dest.writeString(this.respType);
            dest.writeInt(this.type);
            dest.writeByte(this.checkResult ? (byte) 1 : (byte) 0);
        }

        protected Response(Parcel in) {
            this.errCode = in.readInt();
            this.errStr = in.readString();
            this.transaction = in.readString();
            this.openId = in.readString();
            this.code = in.readString();
            this.respType = in.readString();
            this.type = in.readInt();
            this.checkResult = in.readByte() != 0;
        }

        public static final Creator<Response> CREATOR = new Creator<Response>() {
            @Override
            public Response createFromParcel(Parcel source) {
                return new Response(source);
            }

            @Override
            public Response[] newArray(int size) {
                return new Response[size];
            }
        };
    }

    public interface OnResponseListener {
        void onSuccess();

        void onCancel(String message);

        void onFail(String message);
    }
}
