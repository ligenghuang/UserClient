package com.yizhitong.userclient.event;

import java.util.ArrayList;
import java.util.List;

public class NewsBytheClassDto {


    /**
     * code : 1
     * data : [{"IUID":"9fcf11e9-cae3-4e29-a297-a975803f00aa","the_title":"1","the_man":"少的地方","create_time":"/Date(1560155222633)/","create_time_stamp":null,"the_note":"%3Cp%3E%E6%98%AF%E7%9A%84%E5%B0%BD%E5%BF%AB%E6%81%A2%E5%A4%8D%E5%81%A5%E5%BA%B7%E6%B0%B4%E7%94%B5%E8%B4%B9%E7%9C%8B%E8%A7%81%E4%B8%8D%E9%80%81%E7%9A%84%E5%BC%80%E5%A5%96%E5%8F%91%E4%BC%9A%E8%AE%A1%E5%B8%88%E5%AF%B9%E4%BD%A0%E4%BB%98%E6%AC%BE%E4%BA%86%E5%9D%9A%E5%AE%9E%E7%9A%84%E5%96%9D%E5%A5%B6%E7%B2%89%E9%80%9F%E5%BA%A6%E5%BF%AB%E8%A7%A3%E6%94%BE%E6%A0%B9%E6%9C%AC%E5%B0%B1%E8%80%83%E8%AF%95%E7%9A%84%E4%B8%8D%E8%80%90%E7%83%A6%E4%BC%9A%E8%AE%A1%E5%B8%88%E7%9A%84%E5%8D%8A%E5%B9%B4%E5%8D%A1%E7%A7%AF%E5%88%86%E9%80%9F%E5%BA%A6%E5%BF%AB%E8%A7%A3%E6%94%BE%E5%BC%80%E5%A7%8B%E7%9A%84%E4%BA%A4%E4%BB%98%E8%AF%A5%E9%80%9F%E5%BA%A6%E5%BF%AB%E8%A7%A3%E6%94%BE%E5%8F%AF%E6%8E%A5%E5%8F%97%E7%9A%84%3Cspan%20style%3D%22text-align%3A%20center%3B%22%3E%E6%98%AF%E7%9A%84%E5%B0%BD%E5%BF%AB%E6%81%A2%E5%A4%8D%E5%81%A5%E5%BA%B7%E6%B0%B4%E7%94%B5%E8%B4%B9%E7%9C%8B%E8%A7%81%E4%B8%8D%E9%80%81%E7%9A%84%E5%BC%80%E5%A5%96%E5%8F%91%E4%BC%9A%E8%AE%A1%E5%B8%88%E5%AF%B9%E4%BD%A0%E4%BB%98%E6%AC%BE%E4%BA%86%E5%9D%9A%E5%AE%9E%E7%9A%84%E5%96%9D%E5%A5%B6%E7%B2%89%E9%80%9F%E5%BA%A6%E5%BF%AB%E8%A7%A3%E6%94%BE%E6%A0%B9%E6%9C%AC%E5%B0%B1%E8%80%83%E8%AF%95%E7%9A%84%E4%B8%8D%E8%80%90%E7%83%A6%E4%BC%9A%E8%AE%A1%E5%B8%88%E7%9A%84%E5%8D%8A%E5%B9%B4%E5%8D%A1%E7%A7%AF%E5%88%86%E9%80%9F%E5%BA%A6%E5%BF%AB%E8%A7%A3%E6%94%BE%E5%BC%80%E5%A7%8B%E7%9A%84%E4%BA%A4%E4%BB%98%E8%AF%A5%E9%80%9F%E5%BA%A6%E5%BF%AB%E8%A7%A3%E6%94%BE%E5%8F%AF%E6%8E%A5%E5%8F%97%E7%9A%84%3C%2Fspan%3E%3Cspan%20style%3D%22text-align%3A%20center%3B%22%3E%E6%98%AF%E7%9A%84%E5%B0%BD%E5%BF%AB%E6%81%A2%E5%A4%8D%E5%81%A5%E5%BA%B7%E6%B0%B4%E7%94%B5%E8%B4%B9%E7%9C%8B%E8%A7%81%E4%B8%8D%E9%80%81%E7%9A%84%E5%BC%80%E5%A5%96%E5%8F%91%E4%BC%9A%E8%AE%A1%E5%B8%88%E5%AF%B9%E4%BD%A0%E4%BB%98%E6%AC%BE%E4%BA%86%E5%9D%9A%E5%AE%9E%E7%9A%84%E5%96%9D%E5%A5%B6%E7%B2%89%E9%80%9F%E5%BA%A6%E5%BF%AB%E8%A7%A3%E6%94%BE%E6%A0%B9%E6%9C%AC%E5%B0%B1%E8%80%83%E8%AF%95%E7%9A%84%E4%B8%8D%E8%80%90%E7%83%A6%E4%BC%9A%E8%AE%A1%E5%B8%88%E7%9A%84%E5%8D%8A%E5%B9%B4%E5%8D%A1%E7%A7%AF%E5%88%86%E9%80%9F%E5%BA%A6%E5%BF%AB%E8%A7%A3%E6%94%BE%E5%BC%80%E5%A7%8B%E7%9A%84%E4%BA%A4%E4%BB%98%E8%AF%A5%E9%80%9F%E5%BA%A6%E5%BF%AB%E8%A7%A3%E6%94%BE%E5%8F%AF%E6%8E%A5%E5%8F%97%E7%9A%84%3C%2Fspan%3E%3C%2Fp%3E","the_memo":"梵蒂冈发送到个","doctorid":"e855b837-0a36-42a4-8e75-66f54b2101d0","departid":null,"the_class":"去皱","the_img":"/Manager/Mimg/18566144389/201961016272623j.jpg","doctorMV":null,"DateStart":null,"EndDate":null,"Accessid":null,"show_text":null}]
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
         * IUID : 9fcf11e9-cae3-4e29-a297-a975803f00aa
         * the_title : 1
         * the_man : 少的地方
         * create_time : /Date(1560155222633)/
         * create_time_stamp : null
         * the_note : %3Cp%3E%E6%98%AF%E7%9A%84%E5%B0%BD%E5%BF%AB%E6%81%A2%E5%A4%8D%E5%81%A5%E5%BA%B7%E6%B0%B4%E7%94%B5%E8%B4%B9%E7%9C%8B%E8%A7%81%E4%B8%8D%E9%80%81%E7%9A%84%E5%BC%80%E5%A5%96%E5%8F%91%E4%BC%9A%E8%AE%A1%E5%B8%88%E5%AF%B9%E4%BD%A0%E4%BB%98%E6%AC%BE%E4%BA%86%E5%9D%9A%E5%AE%9E%E7%9A%84%E5%96%9D%E5%A5%B6%E7%B2%89%E9%80%9F%E5%BA%A6%E5%BF%AB%E8%A7%A3%E6%94%BE%E6%A0%B9%E6%9C%AC%E5%B0%B1%E8%80%83%E8%AF%95%E7%9A%84%E4%B8%8D%E8%80%90%E7%83%A6%E4%BC%9A%E8%AE%A1%E5%B8%88%E7%9A%84%E5%8D%8A%E5%B9%B4%E5%8D%A1%E7%A7%AF%E5%88%86%E9%80%9F%E5%BA%A6%E5%BF%AB%E8%A7%A3%E6%94%BE%E5%BC%80%E5%A7%8B%E7%9A%84%E4%BA%A4%E4%BB%98%E8%AF%A5%E9%80%9F%E5%BA%A6%E5%BF%AB%E8%A7%A3%E6%94%BE%E5%8F%AF%E6%8E%A5%E5%8F%97%E7%9A%84%3Cspan%20style%3D%22text-align%3A%20center%3B%22%3E%E6%98%AF%E7%9A%84%E5%B0%BD%E5%BF%AB%E6%81%A2%E5%A4%8D%E5%81%A5%E5%BA%B7%E6%B0%B4%E7%94%B5%E8%B4%B9%E7%9C%8B%E8%A7%81%E4%B8%8D%E9%80%81%E7%9A%84%E5%BC%80%E5%A5%96%E5%8F%91%E4%BC%9A%E8%AE%A1%E5%B8%88%E5%AF%B9%E4%BD%A0%E4%BB%98%E6%AC%BE%E4%BA%86%E5%9D%9A%E5%AE%9E%E7%9A%84%E5%96%9D%E5%A5%B6%E7%B2%89%E9%80%9F%E5%BA%A6%E5%BF%AB%E8%A7%A3%E6%94%BE%E6%A0%B9%E6%9C%AC%E5%B0%B1%E8%80%83%E8%AF%95%E7%9A%84%E4%B8%8D%E8%80%90%E7%83%A6%E4%BC%9A%E8%AE%A1%E5%B8%88%E7%9A%84%E5%8D%8A%E5%B9%B4%E5%8D%A1%E7%A7%AF%E5%88%86%E9%80%9F%E5%BA%A6%E5%BF%AB%E8%A7%A3%E6%94%BE%E5%BC%80%E5%A7%8B%E7%9A%84%E4%BA%A4%E4%BB%98%E8%AF%A5%E9%80%9F%E5%BA%A6%E5%BF%AB%E8%A7%A3%E6%94%BE%E5%8F%AF%E6%8E%A5%E5%8F%97%E7%9A%84%3C%2Fspan%3E%3Cspan%20style%3D%22text-align%3A%20center%3B%22%3E%E6%98%AF%E7%9A%84%E5%B0%BD%E5%BF%AB%E6%81%A2%E5%A4%8D%E5%81%A5%E5%BA%B7%E6%B0%B4%E7%94%B5%E8%B4%B9%E7%9C%8B%E8%A7%81%E4%B8%8D%E9%80%81%E7%9A%84%E5%BC%80%E5%A5%96%E5%8F%91%E4%BC%9A%E8%AE%A1%E5%B8%88%E5%AF%B9%E4%BD%A0%E4%BB%98%E6%AC%BE%E4%BA%86%E5%9D%9A%E5%AE%9E%E7%9A%84%E5%96%9D%E5%A5%B6%E7%B2%89%E9%80%9F%E5%BA%A6%E5%BF%AB%E8%A7%A3%E6%94%BE%E6%A0%B9%E6%9C%AC%E5%B0%B1%E8%80%83%E8%AF%95%E7%9A%84%E4%B8%8D%E8%80%90%E7%83%A6%E4%BC%9A%E8%AE%A1%E5%B8%88%E7%9A%84%E5%8D%8A%E5%B9%B4%E5%8D%A1%E7%A7%AF%E5%88%86%E9%80%9F%E5%BA%A6%E5%BF%AB%E8%A7%A3%E6%94%BE%E5%BC%80%E5%A7%8B%E7%9A%84%E4%BA%A4%E4%BB%98%E8%AF%A5%E9%80%9F%E5%BA%A6%E5%BF%AB%E8%A7%A3%E6%94%BE%E5%8F%AF%E6%8E%A5%E5%8F%97%E7%9A%84%3C%2Fspan%3E%3C%2Fp%3E
         * the_memo : 梵蒂冈发送到个
         * doctorid : e855b837-0a36-42a4-8e75-66f54b2101d0
         * departid : null
         * the_class : 去皱
         * the_img : /Manager/Mimg/18566144389/201961016272623j.jpg
         * doctorMV : null
         * DateStart : null
         * EndDate : null
         * Accessid : null
         * show_text : null
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
        private String show_text;

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

        public String getShow_text() {
            return show_text == null ? "" : show_text;
        }

        public void setShow_text(String show_text) {
            this.show_text = show_text;
        }
    }
}
