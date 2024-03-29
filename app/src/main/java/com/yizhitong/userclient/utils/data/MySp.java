package com.yizhitong.userclient.utils.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.lgh.huanglib.util.L;
import com.lgh.huanglib.util.data.MySharedPreferencesUtil;


/**
 * <pre>
 *     author : feijin_lgc
 *     e-mail : 595184932@qq.com
 *     time   : 2018/1/17 20:18
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class MySp extends MySharedPreferencesUtil {

    public static void clearAllSP(Context context) {
        cleanSp(context);
        setToken(context,null);
        setIsFirst(context,true);
        setAskId(context,null);
        setMessage(context,true);
        setVibration(context,true);
        setVoice(context,true);
    }





    /**
     * 引导页
     *
     * @param context
     * @param b
     */
    public static boolean setFirst(Context context, boolean b) {
        SharedPreferences sp = getProjectSP(context);
        SharedPreferences.Editor editor = sp.edit();
        return editor.putBoolean("getFirst", b).commit();
    }


    /**
     * 判断是否登录
     *
     * @param context
     * @return
     */
    public static boolean iSLoginLive(Context context) {
//        String txSig = getTxSig(context);
        String accessToken = getToken(context);
        String Cookie = MySharedPreferencesUtil.getSessionId(context);
        if (accessToken != null && Cookie != null) {
            L.e("MySharedPreferencesUtil", " 登陆了");
            return true;
        } else {
            L.e("MySharedPreferencesUtil", " 没有 登陆");
            return false;
        }
    }

    /**
     * 返回是否为第一次运行
     * @param context
     * @return
     */
    public static boolean isFirst(Context context){
        SharedPreferences sp = getProjectSP(context);
        return sp.getBoolean("isUserFirst", true);
    }

    /**
     * 是否为第一次运行
     * @param context
     * @param isFirst
     * @return
     */
    public static boolean setIsFirst(Context context,boolean isFirst){
        SharedPreferences sp = getProjectSP(context);
        SharedPreferences.Editor editor = sp.edit();
        return editor.putBoolean("isUserFirst", isFirst).commit();
    }



    /**
     * 获取 刷新token
     *
     * @param context
     * @return
     */
    public static String getToken(Context context) {
        SharedPreferences sp = getProjectSP(context);
        return sp.getString("UserToken", null);
    }

    /**
     * 设置 刷新token
     *
     * @param context
     * @param userName
     */
    public static boolean setToken(Context context, String userName) {
        SharedPreferences sp = getProjectSP(context);
        SharedPreferences.Editor editor = sp.edit();
        return editor.putString("UserToken", userName).commit();
    }

    /**
     * 获取 用户ID
     *
     * @param context
     * @return
     */
    public static String getAskId(Context context) {
        SharedPreferences sp = getProjectSP(context);
        return sp.getString("UserAskId", null);
    }

    /**
     * 设置 用户ID
     *
     * @param context
     * @param userName
     */
    public static boolean setAskId(Context context, String userName) {
        SharedPreferences sp = getProjectSP(context);
        SharedPreferences.Editor editor = sp.edit();
        return editor.putString("UserAskId", userName).commit();
    }

    /**
     * 设置 是否新消息提示
     * @param context
     * @param b
     * @return
     */
    public static boolean setMessage(Context context,boolean b){
        SharedPreferences sp = getProjectSP(context);
        SharedPreferences.Editor editor = sp.edit();
        return editor.putBoolean("UserMessage", b).commit();
    }

    /**
     * 获取 是否新消息提示
     * @param context
     * @return
     */
    public static boolean getMessage(Context context){
        SharedPreferences sp = getProjectSP(context);
        return sp.getBoolean("UserMessage", true);
    }

    /**
     * 设置 是否声音提示
     * @param context
     * @param b
     * @return
     */
    public static boolean setVoice(Context context,boolean b){
        SharedPreferences sp = getProjectSP(context);
        SharedPreferences.Editor editor = sp.edit();
        return editor.putBoolean("UserVoice", b).commit();
    }

    /**
     * 获取 是否声音提示
     * @param context
     * @return
     */
    public static boolean getVoice(Context context){
        SharedPreferences sp = getProjectSP(context);
        return sp.getBoolean("UserVoice", true);
    }

    /**
     * 设置 是否震动提示
     * @param context
     * @param b
     * @return
     */
    public static boolean setVibration(Context context,boolean b){
        SharedPreferences sp = getProjectSP(context);
        SharedPreferences.Editor editor = sp.edit();
        return editor.putBoolean("UserVibration", b).commit();
    }

    /**
     * 获取 是否震动提示
     * @param context
     * @return
     */
    public static boolean getVibration(Context context){
        SharedPreferences sp = getProjectSP(context);
        return sp.getBoolean("UserVibration", true);
    }

    /**
     * 融云userID
     * @param context
     * @param userId
     * @return
     */
    public static boolean setRoogUserId(Context context,String userId){
        SharedPreferences sp = getProjectSP(context);
        SharedPreferences.Editor editor = sp.edit();
        return editor.putString("RoogUserId", userId).commit();
    }

    public static String getRoogUserId(Context context){
        SharedPreferences sp = getProjectSP(context);
        return sp.getString("RoogUserId", "");
    }


    /**
     * 融云user名字
     * @param context
     * @param userId
     * @return
     */
    public static boolean setRoogUserName(Context context,String userId){
        SharedPreferences sp = getProjectSP(context);
        SharedPreferences.Editor editor = sp.edit();
        return editor.putString("RoogUserName", userId).commit();
    }

    public static String getRoogUserName(Context context){
        SharedPreferences sp = getProjectSP(context);
        return sp.getString("RoogUserName", "");
    }

    /**
     * 融云user头像
     * @param context
     * @param userId
     * @return
     */
    public static boolean setRoogUserImg(Context context,String userId){
        SharedPreferences sp = getProjectSP(context);
        SharedPreferences.Editor editor = sp.edit();
        return editor.putString("RoogUserImg", userId).commit();
    }

    public static String getRoogUserImg(Context context){
        SharedPreferences sp = getProjectSP(context);
        return sp.getString("RoogUserImg", "");
    }

    /**
     * 融云登录userid
     * @param context
     * @param userId
     * @return
     */
    public static boolean setRoogLoginUserId(Context context,String userId){
        SharedPreferences sp = getProjectSP(context);
        SharedPreferences.Editor editor = sp.edit();
        return editor.putString("RoogLoginUserId", userId).commit();
    }

    public static String getRoogLoginUserId(Context context){
        SharedPreferences sp = getProjectSP(context);
        return sp.getString("RoogLoginUserId", "");
    }

    /**
     * 融云Token
     * @param context
     * @param userId
     * @return
     */
    public static boolean setRoogToken(Context context,String userId){
        SharedPreferences sp = getProjectSP(context);
        SharedPreferences.Editor editor = sp.edit();
        return editor.putString("RoogToken", userId).commit();
    }

    public static String getRoogToken(Context context){
        SharedPreferences sp = getProjectSP(context);
        return sp.getString("RoogToken", "");
    }

}
