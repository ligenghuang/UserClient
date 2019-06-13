package com.yizhitong.userclient.utils;

import com.lgh.huanglib.util.L;

import java.util.ArrayList;
import java.util.List;

public class Util {

    /**
     * 手机号码 替换部分号码
     * @param phone
     * @return
     */
    public static String replacePhone(String phone){
        return phone.replace(phone.substring(3,4), "****");
    }

    private static String[] stringToArray(String str, String key) {
        String[] temp = null;
        temp = str.split(key);
        return temp;
    }

    /**
     * 分割字符串
     * @param note
     * @return
     */
    public static List<String>  getStringList(String note){
        String [] temp = null;
        List<String> strList = new ArrayList<>();
        temp=stringToArray(note, " ");
        for (int i = 0; i < temp.length; i++) {
            L.e("lgh_array",temp[i]);
            strList.add(temp[i]);
        }
        return strList;
    }
}
