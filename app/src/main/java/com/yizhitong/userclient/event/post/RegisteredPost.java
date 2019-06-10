package com.yizhitong.userclient.event.post;
/**
* 注册请求体
* @author lgh
* created at 2019/5/14 0014 10:57
*/
public class RegisteredPost {

    private String type;
    private String userName;
    private String password;
    private String sms_code;
    private String invitName;

    public void setType(String type) {
        this.type = type;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type == null ? "" : type;
    }

    public String getUserName() {
        return userName == null ? "" : userName;
    }

    public String getPassword() {
        return password == null ? "" : password;
    }

    public String getSms_code() {
        return sms_code == null ? "" : sms_code;
    }

    public String getInvitName() {
        return invitName == null ? "" : invitName;
    }

    public void setSms_code(String sms_code) {
        this.sms_code = sms_code;
    }

    public void setInvitName(String invitName) {
        this.invitName = invitName;
    }


}
