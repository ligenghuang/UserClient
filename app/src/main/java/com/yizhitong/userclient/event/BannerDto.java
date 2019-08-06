package com.yizhitong.userclient.event;

import java.util.ArrayList;
import java.util.List;

public class BannerDto {

    /**
     * code : 1
     * data : [{"Iuid":"1","the_img":"/img/home_page1.jpg","the_url":"1","url_flag":0},{"Iuid":"2","the_img":"/img/home_page1.jpg","the_url":"2","url_flag":0},{"Iuid":"3","the_img":"/img/home_page1.jpg","the_url":"3","url_flag":0},{"Iuid":"4","the_img":"/img/home_page1.jpg","the_url":"http://www.baidu.com/","url_flag":1}]
     * msg : 查询成功
     * url : null
     * wait : 0
     * Accessid : 0
     * data2 : null
     */

    private int code;
    private String msg;
    private Object url;
    private int wait;
    private int Accessid;
    private Object data2;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getUrl() {
        return url;
    }

    public void setUrl(Object url) {
        this.url = url;
    }

    public int getWait() {
        return wait;
    }

    public void setWait(int wait) {
        this.wait = wait;
    }

    public int getAccessid() {
        return Accessid;
    }

    public void setAccessid(int Accessid) {
        this.Accessid = Accessid;
    }

    public Object getData2() {
        return data2;
    }

    public void setData2(Object data2) {
        this.data2 = data2;
    }

    public List<DataBean> getData() {
        if (data == null) {
            return new ArrayList<>();
        }
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * Iuid : 1
         * the_img : /img/home_page1.jpg
         * the_url : 1
         * url_flag : 0
         */

        private String Iuid;
        private String the_img;
        private String the_url;
        private int url_flag;

        public String getIuid() {
            return Iuid == null ? "" : Iuid;
        }

        public void setIuid(String iuid) {
            Iuid = iuid;
        }

        public String getThe_img() {
            return the_img == null ? "" : the_img;
        }

        public void setThe_img(String the_img) {
            this.the_img = the_img;
        }

        public String getThe_url() {
            return the_url == null ? "" : the_url;
        }

        public void setThe_url(String the_url) {
            this.the_url = the_url;
        }

        public int getUrl_flag() {
            return url_flag;
        }

        public void setUrl_flag(int url_flag) {
            this.url_flag = url_flag;
        }
    }
}
