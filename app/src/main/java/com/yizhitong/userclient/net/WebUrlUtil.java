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
    public static final String POST_REGISTER = "Mine/RegisterApp";

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
     * 判断登录
     */
    public static final String POST_ISLOGIN = "Mine/isLogin";

    /**
     * 获取个人信息
     */
    public static final String POST_USERINFO = "Mine/getSessionUserInfoApp";

    /**
     * 获取个人资料
     */
    public static final String POST_USER_INFO = "Mine/findUserInfoByIUID";

    /**
     * 修改个人资料
     */
    public static final String POST_USER_UPDATE = "Mine/updateUserInfo";

    /**
     * 退出登录
     */
    public static final String POST_LOGOUT = "Mine/userout";

    /**
     * 意见反馈
     */
    public static final String POST_ADDSERVICE_NOTE = "Mine/addServiceNote";

    /**
     * 地址管理
     */
    public static final String POST_ADDRESS_LIST = "Mine/getUserAddByUserIdApp";

    /**
     * 删除地址
     */
    public static final String POST_ADDRESS_DETELE = "Mine/deteleUserAddress";

    /**
     * 新增地址
     */
    public static final String POST_ADDRESS_ADD = "Mine/addUserAddress";

    /**
     * 获取地址详情
     */
    public static final String POST_ADDRESS_INFO = "Mine/getUserAddByIuidApp";

    /**
     * 获取我关注的医生
     */
    public static final String POST_FIND_FAV_DOCTORS_LIST = "Mine/findFavDoctors";

    /**
     * 获取科室
     */
    public static final String POST_FIND_DEPARTID = "Doctors/findDepartid";

    /**
     * 获取医生认证资料
     */
    public static final String POST_DOCTOR_INFO = "Mine/getDoctorsInfoApp";

    /**
     * 医院列表
     */
    public static final String POST_HOSPITALNAME = "Mine/getHospitalName";

    /**
     * 保存个人认证资料
     */
    public static final String POST_DOCTORSAUTH = "Mine/doctorsAuth";

    /**
     * 获取问诊人信息列表
     */
    public static final String POST_MY_PATIENT = "Mine/getMyPatientApp";

    /**
     * 获取问诊人详情
     */
    public static final String POST_PATIENT_INFO = "Mine/getPatientApp";

    /**
     * 新增、编辑问诊人详情
     */
    public static final String POST_ADD_PATIENT_INFO = "Mine/addPatient";

    /**
     * 我的处方列表
     */
    public static final String POST_PRESCRIPTION_LIST = "Prescription/getPrescriptionApp";

    /**
     * 删除处方订单
     */
    public static final String POST_PRESCRIPTION_DELETE = "Prescription/deleteAskDrug";

    /**
     * 处方详情
     */
    public static final String POST_PRESCRIPTION_INFO = "Prescription/getPreInfoApp";

    /**
     * 问诊单列表
     */
    public static final String POST_ASKHEAD ="Ask/getAskHeadApp";

    /**
     * 问诊单支付
     */
    public static final String POST_ASKHEAD_BYID ="Ask/getAskHeadByIdApp";

    /**
     * 问诊单评价 医生详情
     */
    public static final String POST_DOCTOR_BYID ="Doctors/getDocByAskIdApp";

    /**
     * 问诊单评价  提交评价
     */
    public static final String POST_ADD_DOCTOR_EVAL = "Doctors/addDoctorEval";

    /**
     * 问诊单详情
     */
    public static final String POST_ASKHEAD_BY_ID = "Ask/getAskHeadByIdApp";

    /**
     * 消息列表
     */
    public static final String POST_MESSAGE_LIST = "Inquiry/findAskChatByTouserID";
    /**
     * 消息详情  用户信息
     */
    public static final String POST_MESSAGE_USERID = "Ask/getAskHeadByUserIdApp";

    /**
     * 消息详情  消息列表
     */
    public static final String POST_ASKCHAT = "Ask/getAskChatApp";

    /**
     * 发送消息 文字
     */
    public static final String POST_SEND_MESSAGE  = "Ask/sendMessageApp";
    /**
     * 发送消息 图片
     */
    public static final String POST_SEND_PICTURESA = "Ask/sendPicturesApp";

    /**
     * 获取常用语
     */
    public static final String POST_COMMONLANGUGE = "Ask/getCommonLanguageApp";

    /**
     * 新增常用语
     */
    public static final String POST_ADD_COMMONLANGUGE = "Ask/addCommonLanguage";

    /**
     * 删除常用语
     */
    public static final String POST_DELETE_COMMONLANGUAGE = "Ask/deleteCommonLanguage";

    /**
     * 获取新闻类别
     */
    public static final String POST_NEWS_TYPE = "Mine/getNewsTypeApp";

    /**
     * 获取新闻列表
     */
    public static final String POST_NEWS_BYID = "Home/findNewsBytheClassApp";

    /**
     * 获取新闻详情
     */
    public static final String POST_NEWS_DETAIL = "Article/findTheNewsByIUID";

    /**
     * 获取科室
     */
    public static final String POST_DEPART_ALL = "Home/findDepartByAllApp";

    /**
     * 获取问诊价格
     */
    public static final String POST_DOCTOR_AMOUNT = "Doctors/getRegisteredAmount";

    /**
     * 上传图片
     */
    public static final String POST_ASK_FILENAME = "Ask/fileName";

    /**
     * 提交快速问诊单
     */
    public static final String POST_ADD_ASKHEAD = "Ask/addAskHead";

    /**
     * 搜索医生
     */
    public static final String POST_FINDDOCTOR_LIST = "Doctors/findDoctorBydepartidApp";

    /**
     * 根据关键字搜索医生
     */
    public static final String POST_FINDDOCTOR_CONDITION_LIST = "Doctors/findDoctorConditionApp";

    /**
     * 医生详情
     */
    public static final String POST_DOCTOR_DETAIL = "Doctors/getDoctorApp";

    /**
     * 获取是否关注此医生
     */
    public static final String POST_FAV_DOCTOR = "Doctors/getFavDoctorByuserApp";

    /**
     * 取消关注
     */
    public static final String POST_REMOVE_DOCTOR = "Doctors/removeDoctor";

    /**
     * 关注
     */
    public static final String POST_CONCERNS_DOCOTR = "Doctors/concernsDoctor";

    /**
     * 接收到消息
     */
    public static final String GET_MESSAGE = "getMessageApp";
    public static final String GET_MESSAGE_1 = "getMessageApp_1";
}