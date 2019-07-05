package com.yizhitong.userclient.utils;

import com.lgh.huanglib.util.L;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AppUtil {

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
        String result ="";
        try{
           if (str.contains("<p><img")){
               result = str.replace(str.substring(str.indexOf("<p>")+1,str.indexOf("></p>")+5),"");
           }else {
               result  = str;
           }
        }catch (StringIndexOutOfBoundsException e){
            L.e("lgh_note",e.toString());
        }
        return result;
    }
    /**
     * 替换字符
     * @param str
     * @return
     */
    public static String replaceAll2(String str){
        String result = null;
        String str1 = str.replaceAll("<p>","");
        result = str1.replaceAll("</p>","");
        return result;
    }

    /***
     * 随机数
     * @return
     */
    public static int getRandom(){
        return new Random().nextInt(1000);
    }

    /**
     * SHA加密
     *
     * @param strSrc 明文
     *
     * @return 加密之后的密文
     */
    public static String shaEncrypt(String strSrc) {
        MessageDigest md = null;
        String strDes = null;
        byte[] bt = strSrc.getBytes();
        try {
            md = MessageDigest.getInstance("SHA-1");// 将此换成SHA-1、SHA-512、SHA-384等参数
            md.update(bt);
            strDes = bytes2Hex(md.digest()); // to HexString
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
        return strDes;
    }

    /**
     * byte数组转换为16进制字符串
     *
     * @param bts
     *            数据源
     * @return 16进制字符串
     */
    public static String bytes2Hex(byte[] bts) {
        String des = "";
        String tmp = null;
        for (int i = 0; i < bts.length; i++) {
            tmp = (Integer.toHexString(bts[i] & 0xFF));
            if (tmp.length() == 1) {
                des += "0";
            }
            des += tmp;
        }
        return des;
    }

}
