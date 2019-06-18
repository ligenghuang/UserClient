package com.yizhitong.userclient.utils;

import com.lgh.huanglib.util.L;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
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

    /**
     * 转utf-8
     * @param str
     * @return
     */
    public static String toUtf8(String str){
        String result = null;

        try {
            result = URLDecoder.decode(str,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            L.e("lgh_news",e.toString());
        }
        return result;
    }

    /**
     * 替换字符
     * @param str
     * @return
     */
    public static String replaceAll(String str){
        String result = null;
        String str1 = str.replaceAll("<p>","");
        result = str1.replaceAll("</p>","");
        return result;
    }

}
