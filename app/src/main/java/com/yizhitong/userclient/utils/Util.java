package com.yizhitong.userclient.utils;

public class Util {

    /**
     * 手机号码 替换部分号码
     * @param phone
     * @return
     */
    public static String replacePhone(String phone){
        return phone.replace(phone.substring(3,4), "****");
    }
}
