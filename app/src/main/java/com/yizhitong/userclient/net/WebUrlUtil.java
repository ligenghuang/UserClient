package com.yizhitong.userclient.net;


import com.yizhitong.userclient.BuildConfig;

public class WebUrlUtil {
    static {
        //配合retrofit，需要以/结尾
        if (BuildConfig.DEBUG) {
            BASE_URL = "http://192.168.3.2:8014/";
            IMG_URL = "http://192.168.3.2:8014";
//            BASE_URL = "http://www.yizhitong100.com/";
//            IMG_URL = "http://www.yizhitong100.com";
        } else {
            BASE_URL = "http://192.168.3.2:8014/";
            IMG_URL = "http://192.168.3.2:8014";
//            BASE_URL = "http://www.yizhitong100.com/";
//            IMG_URL = "http://www.yizhitong100.com";
        }
    }

    public static String BASE_URL;
    public static String IMG_URL;
    public static String SOCKET = "wss://wss.tongdiandashan.com";

    /**
     * 登录
     */
    public static final String POST_LOGIN = "Mine/LoginAction";

    /**
     * 注册
     */
    public static final String POST_REGISTER = "DMine/RegisterApp";

    /**
     * 获取验证码(图片)
     */
    public static final String POST_CAPTCHAVAPP = "Mine/captchavApp";

    /**
     * 获取验证码
     */
    public static final String POST_CHECKS = "Mine/checks";

    /**
     * 找回密码 校验身份
     */
    public static final String POST_RETRIEVEPWS = "Mine/AppRetrievePws";

    /**
     * 找回密码 修改
     */
    public static final String POST_CHANGE_PWD = "Mine/AppRetrievePws1";


    /**
     * 接收到消息
     */
    public static final String GET_MESSAGE = "getMessageApp";
    public static final String GET_MESSAGE_1 = "getMessageApp_1";
}