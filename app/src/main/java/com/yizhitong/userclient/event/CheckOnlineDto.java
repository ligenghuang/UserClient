package com.yizhitong.userclient.event;

public class CheckOnlineDto {

    /**
     * code : 200
     * status : 1
     */

    private int code;
    private String status;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getStatus() {
        return status == null ? "" : status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
