package com.yizhitong.userclient.event;

import java.util.ArrayList;
import java.util.List;

public class NewsBytheClassDto {


    /**
     * code : 1
     * data : [{"IUID":"879ab570-0ce8-4d44-aea1-28ad3937a5ff","the_title":"规划局规划局规划局答复","the_man":"CVBS","create_time":"/Date(1560155911193)/","create_time_stamp":null,"the_note":"%3Cp%3E%E6%96%B9%E9%9D%A2%E8%83%BD%E6%84%9F%E5%8F%97%E5%88%B0%E5%8C%85%E6%8B%AC%E5%87%8F%E8%82%A5%E4%BD%A0%E8%AF%B4%E7%9A%84%E7%A9%BA%E9%97%B4%E9%80%9F%E5%BA%A6%E5%BF%AB%E8%A7%A3%E6%94%BE%E5%8F%AF%E6%8E%A5%E5%8F%97%E7%9A%84%E6%98%AF%E7%9A%84%E7%A9%BA%E9%97%B4%E6%9D%A5%E7%83%A6%E4%BD%A0%E4%BA%86%E4%BC%9A%E8%AE%A1%E5%B8%88%E7%9A%84%E5%A5%B6%E7%B2%89%E7%9C%8B%E5%BE%97%E8%A7%81%E6%96%B9%E4%BC%9A%E8%AE%A1%E5%B8%88%E5%AF%B9%E6%96%B9%E4%BC%9A%E8%AE%A1%E5%B8%88%E7%9A%84%E4%B8%8D%E5%8F%AF%E6%96%B9%E5%9F%BA%E6%9C%AC%E4%B8%8A%E7%9A%84%E7%A9%BA%E9%97%B4%E5%88%9A%E5%BC%80%E5%A7%8B%E7%9A%84%E5%9F%BA%E6%9C%AC%E6%88%BF%E4%BA%A4%E4%BC%9A%E7%9A%84%E6%8A%8A%E6%8E%A7%E5%87%8F%E8%82%A5%E7%9A%84%E5%AE%A2%E6%88%B7%E7%9A%84%E8%AE%BE%E8%AE%A1%E8%B4%B9%E6%8A%8A%E5%9B%9B%E7%82%B9%E5%8D%8A%3C%2Fp%3E","the_memo":"是打发第三方","doctorid":"dd97fdea-4327-46dd-80ad-6783dca2a1e5","departid":null,"the_class":"美白","the_img":"/Manager/Mimg/18566144389/2019610163831189j.jpg","doctorMV":null,"DateStart":null,"EndDate":null,"Accessid":null}]
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
         * IUID : 879ab570-0ce8-4d44-aea1-28ad3937a5ff
         * the_title : 规划局规划局规划局答复
         * the_man : CVBS
         * create_time : /Date(1560155911193)/
         * create_time_stamp : null
         * the_note : %3Cp%3E%E6%96%B9%E9%9D%A2%E8%83%BD%E6%84%9F%E5%8F%97%E5%88%B0%E5%8C%85%E6%8B%AC%E5%87%8F%E8%82%A5%E4%BD%A0%E8%AF%B4%E7%9A%84%E7%A9%BA%E9%97%B4%E9%80%9F%E5%BA%A6%E5%BF%AB%E8%A7%A3%E6%94%BE%E5%8F%AF%E6%8E%A5%E5%8F%97%E7%9A%84%E6%98%AF%E7%9A%84%E7%A9%BA%E9%97%B4%E6%9D%A5%E7%83%A6%E4%BD%A0%E4%BA%86%E4%BC%9A%E8%AE%A1%E5%B8%88%E7%9A%84%E5%A5%B6%E7%B2%89%E7%9C%8B%E5%BE%97%E8%A7%81%E6%96%B9%E4%BC%9A%E8%AE%A1%E5%B8%88%E5%AF%B9%E6%96%B9%E4%BC%9A%E8%AE%A1%E5%B8%88%E7%9A%84%E4%B8%8D%E5%8F%AF%E6%96%B9%E5%9F%BA%E6%9C%AC%E4%B8%8A%E7%9A%84%E7%A9%BA%E9%97%B4%E5%88%9A%E5%BC%80%E5%A7%8B%E7%9A%84%E5%9F%BA%E6%9C%AC%E6%88%BF%E4%BA%A4%E4%BC%9A%E7%9A%84%E6%8A%8A%E6%8E%A7%E5%87%8F%E8%82%A5%E7%9A%84%E5%AE%A2%E6%88%B7%E7%9A%84%E8%AE%BE%E8%AE%A1%E8%B4%B9%E6%8A%8A%E5%9B%9B%E7%82%B9%E5%8D%8A%3C%2Fp%3E
         * the_memo : 是打发第三方
         * doctorid : dd97fdea-4327-46dd-80ad-6783dca2a1e5
         * departid : null
         * the_class : 美白
         * the_img : /Manager/Mimg/18566144389/2019610163831189j.jpg
         * doctorMV : null
         * DateStart : null
         * EndDate : null
         * Accessid : null
         */

        private String IUID;
        private String the_title;
        private String the_man;
        private String create_time;
        private Object create_time_stamp;
        private String the_note;
        private String the_memo;
        private String doctorid;
        private Object departid;
        private String the_class;
        private String the_img;
        private Object doctorMV;
        private Object DateStart;
        private Object EndDate;
        private Object Accessid;

        public String getIUID() {
            return IUID == null ? "" : IUID;
        }

        public void setIUID(String IUID) {
            this.IUID = IUID;
        }

        public String getThe_title() {
            return the_title == null ? "" : the_title;
        }

        public void setThe_title(String the_title) {
            this.the_title = the_title;
        }

        public String getThe_man() {
            return the_man == null ? "" : the_man;
        }

        public void setThe_man(String the_man) {
            this.the_man = the_man;
        }

        public String getCreate_time() {
            return create_time == null ? "" : create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public Object getCreate_time_stamp() {
            return create_time_stamp;
        }

        public void setCreate_time_stamp(Object create_time_stamp) {
            this.create_time_stamp = create_time_stamp;
        }

        public String getThe_note() {
            return the_note == null ? "" : the_note;
        }

        public void setThe_note(String the_note) {
            this.the_note = the_note;
        }

        public String getThe_memo() {
            return the_memo == null ? "" : the_memo;
        }

        public void setThe_memo(String the_memo) {
            this.the_memo = the_memo;
        }

        public String getDoctorid() {
            return doctorid == null ? "" : doctorid;
        }

        public void setDoctorid(String doctorid) {
            this.doctorid = doctorid;
        }

        public Object getDepartid() {
            return departid;
        }

        public void setDepartid(Object departid) {
            this.departid = departid;
        }

        public String getThe_class() {
            return the_class == null ? "" : the_class;
        }

        public void setThe_class(String the_class) {
            this.the_class = the_class;
        }

        public String getThe_img() {
            return the_img == null ? "" : the_img;
        }

        public void setThe_img(String the_img) {
            this.the_img = the_img;
        }

        public Object getDoctorMV() {
            return doctorMV;
        }

        public void setDoctorMV(Object doctorMV) {
            this.doctorMV = doctorMV;
        }

        public Object getDateStart() {
            return DateStart;
        }

        public void setDateStart(Object dateStart) {
            DateStart = dateStart;
        }

        public Object getEndDate() {
            return EndDate;
        }

        public void setEndDate(Object endDate) {
            EndDate = endDate;
        }

        public Object getAccessid() {
            return Accessid;
        }

        public void setAccessid(Object accessid) {
            Accessid = accessid;
        }
    }
}
