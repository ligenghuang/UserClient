package com.yizhitong.userclient.event;

import java.util.ArrayList;
import java.util.List;

public class DoctorDetailDto {


    /**
     * code : 1
     * data : {"IUID":"e855b837-0a36-42a4-8e75-66f54b2101d0","name":"李松","the_level":"执业医师","departid":"3","the_spec":"null06","the_note":"","price_day":2,"price_week":null,"price_month":null,"price_season":null,"price_year":null,"price_year3":null,"ask_num":7,"good_num":1,"buy_num":0,"the_per":null,"answer_len":"","now_flag":null,"the_pro":"广东1","the_city":"黄浦区","userid":"9884b9cd-2222-40ee-8859-e6878b0081f1","hospital":"广州中医院大学附属医院","the_img":"/DOC/18529250717/20196315336507j.jpg","the_star":null,"fact_price":0.01,"create_time":"/Date(1551666464000)/","departName":null,"EndDate":null,"DateStart":null,"phone":"18529250717","sex":"女","practicing_time":"/Date(1420041600000)/","practicing_certificate":"/DOC/18529250717/201961152141508j.jpg","income":null,"Accessid":null,"isPrescribe":null,"departList":null,"docevalMV":[{"IUID":"c03cfeeb-4778-4462-9f7e-0a129de1c55c","doctorid":"e855b837-0a36-42a4-8e75-66f54b2101d0","userid":"b4aece8e-0014-494c-bed1-ac900dac1f25","askid":"6f9a643d-d170-41dc-b439-acab7f2d5bd7","use_img":null,"nickname":"144389","the_note":"医术精湛 态度良好 dsfdsafsdf ","the_star":5,"create_time":"/Date(1559180182460)/","create_time_stamp":0,"doctorName":null,"phone":null,"DateStart":null,"EndDate":null,"anonymous_flag":null},{"IUID":"c03cfeeb-4778-4462-9f7e-0a129de1c55b","doctorid":"e855b837-0a36-42a4-8e75-66f54b2101d0","userid":"b4aece8e-0014-494c-bed1-ac900dac1f25","askid":"6f9a643d-d170-41dc-b439-acab7f2d5bd7","use_img":null,"nickname":"144389","the_note":"asdasfsadf","the_star":2,"create_time":"/Date(1559180182460)/","create_time_stamp":0,"doctorName":null,"phone":null,"DateStart":null,"EndDate":null,"anonymous_flag":null}],"show_flag":1,"show_note":"第三方士大夫","xq":null,"create_time_stamp":null,"practicing_time_stamp":null}
     * msg : 查询成功
     * url : null
     * wait : 0
     * Accessid : 0
     * data2 : null
     */

    private int code;
    private DataBean data;
    private String msg;
    private Object url;
    private int wait;
    private int Accessid;
    private Object data2;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
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

    public static class DataBean {
        /**
         * IUID : e855b837-0a36-42a4-8e75-66f54b2101d0
         * name : 李松
         * the_level : 执业医师
         * departid : 3
         * the_spec : null06
         * the_note :
         * price_day : 2.0
         * price_week : null
         * price_month : null
         * price_season : null
         * price_year : null
         * price_year3 : null
         * ask_num : 7
         * good_num : 1
         * buy_num : 0
         * the_per : null
         * answer_len :
         * now_flag : null
         * the_pro : 广东1
         * the_city : 黄浦区
         * userid : 9884b9cd-2222-40ee-8859-e6878b0081f1
         * hospital : 广州中医院大学附属医院
         * the_img : /DOC/18529250717/20196315336507j.jpg
         * the_star : null
         * fact_price : 0.01
         * create_time : /Date(1551666464000)/
         * departName : null
         * EndDate : null
         * DateStart : null
         * phone : 18529250717
         * sex : 女
         * practicing_time : /Date(1420041600000)/
         * practicing_certificate : /DOC/18529250717/201961152141508j.jpg
         * income : null
         * Accessid : null
         * isPrescribe : null
         * departList : null
         * docevalMV : [{"IUID":"c03cfeeb-4778-4462-9f7e-0a129de1c55c","doctorid":"e855b837-0a36-42a4-8e75-66f54b2101d0","userid":"b4aece8e-0014-494c-bed1-ac900dac1f25","askid":"6f9a643d-d170-41dc-b439-acab7f2d5bd7","use_img":null,"nickname":"144389","the_note":"医术精湛 态度良好 dsfdsafsdf ","the_star":5,"create_time":"/Date(1559180182460)/","create_time_stamp":0,"doctorName":null,"phone":null,"DateStart":null,"EndDate":null,"anonymous_flag":null},{"IUID":"c03cfeeb-4778-4462-9f7e-0a129de1c55b","doctorid":"e855b837-0a36-42a4-8e75-66f54b2101d0","userid":"b4aece8e-0014-494c-bed1-ac900dac1f25","askid":"6f9a643d-d170-41dc-b439-acab7f2d5bd7","use_img":null,"nickname":"144389","the_note":"asdasfsadf","the_star":2,"create_time":"/Date(1559180182460)/","create_time_stamp":0,"doctorName":null,"phone":null,"DateStart":null,"EndDate":null,"anonymous_flag":null}]
         * show_flag : 1
         * show_note : 第三方士大夫
         * xq : null
         * create_time_stamp : null
         * practicing_time_stamp : null
         */

        private String IUID;
        private String name;
        private String the_level;
        private String departid;
        private String the_spec;
        private String the_note;
        private double price_day;
        private Object price_week;
        private Object price_month;
        private Object price_season;
        private Object price_year;
        private Object price_year3;
        private int ask_num;
        private int good_num;
        private int buy_num;
        private float the_per;
        private String answer_len;
        private Object now_flag;
        private String the_pro;
        private String the_city;
        private String userid;
        private String hospital;
        private String the_img;
        private Object the_star;
        private double fact_price;
        private String create_time;
        private Object departName;
        private Object EndDate;
        private Object DateStart;
        private String phone;
        private String sex;
        private String practicing_time;
        private String practicing_certificate;
        private Object income;
        private Object Accessid;
        private Object isPrescribe;
        private Object departList;
        private int show_flag;
        private String show_note;
        private Object xq;
        private Object create_time_stamp;
        private Object practicing_time_stamp;
        private List<DocevalMVBean> docevalMV;

        public String getIUID() {
            return IUID == null ? "" : IUID;
        }

        public void setIUID(String IUID) {
            this.IUID = IUID;
        }

        public String getName() {
            return name == null ? "" : name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getThe_level() {
            return the_level == null ? "" : the_level;
        }

        public void setThe_level(String the_level) {
            this.the_level = the_level;
        }

        public String getDepartid() {
            return departid == null ? "" : departid;
        }

        public void setDepartid(String departid) {
            this.departid = departid;
        }

        public String getThe_spec() {
            return the_spec == null ? "" : the_spec;
        }

        public void setThe_spec(String the_spec) {
            this.the_spec = the_spec;
        }

        public String getThe_note() {
            return the_note == null ? "" : the_note;
        }

        public void setThe_note(String the_note) {
            this.the_note = the_note;
        }

        public double getPrice_day() {
            return price_day;
        }

        public void setPrice_day(double price_day) {
            this.price_day = price_day;
        }

        public Object getPrice_week() {
            return price_week;
        }

        public void setPrice_week(Object price_week) {
            this.price_week = price_week;
        }

        public Object getPrice_month() {
            return price_month;
        }

        public void setPrice_month(Object price_month) {
            this.price_month = price_month;
        }

        public Object getPrice_season() {
            return price_season;
        }

        public void setPrice_season(Object price_season) {
            this.price_season = price_season;
        }

        public Object getPrice_year() {
            return price_year;
        }

        public void setPrice_year(Object price_year) {
            this.price_year = price_year;
        }

        public Object getPrice_year3() {
            return price_year3;
        }

        public void setPrice_year3(Object price_year3) {
            this.price_year3 = price_year3;
        }

        public int getAsk_num() {
            return ask_num;
        }

        public void setAsk_num(int ask_num) {
            this.ask_num = ask_num;
        }

        public int getGood_num() {
            return good_num;
        }

        public void setGood_num(int good_num) {
            this.good_num = good_num;
        }

        public int getBuy_num() {
            return buy_num;
        }

        public void setBuy_num(int buy_num) {
            this.buy_num = buy_num;
        }

        public float getThe_per() {
            return the_per;
        }

        public void setThe_per(float the_per) {
            this.the_per = the_per;
        }

        public String getAnswer_len() {
            return answer_len == null ? "0" : answer_len;
        }

        public void setAnswer_len(String answer_len) {
            this.answer_len = answer_len;
        }

        public Object getNow_flag() {
            return now_flag;
        }

        public void setNow_flag(Object now_flag) {
            this.now_flag = now_flag;
        }

        public String getThe_pro() {
            return the_pro == null ? "" : the_pro;
        }

        public void setThe_pro(String the_pro) {
            this.the_pro = the_pro;
        }

        public String getThe_city() {
            return the_city == null ? "" : the_city;
        }

        public void setThe_city(String the_city) {
            this.the_city = the_city;
        }

        public String getUserid() {
            return userid == null ? "" : userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getHospital() {
            return hospital == null ? "" : hospital;
        }

        public void setHospital(String hospital) {
            this.hospital = hospital;
        }

        public String getThe_img() {
            return the_img == null ? "" : the_img;
        }

        public void setThe_img(String the_img) {
            this.the_img = the_img;
        }

        public Object getThe_star() {
            return the_star;
        }

        public void setThe_star(Object the_star) {
            this.the_star = the_star;
        }

        public double getFact_price() {
            return fact_price;
        }

        public void setFact_price(double fact_price) {
            this.fact_price = fact_price;
        }

        public String getCreate_time() {
            return create_time == null ? "" : create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public Object getDepartName() {
            return departName;
        }

        public void setDepartName(Object departName) {
            this.departName = departName;
        }

        public Object getEndDate() {
            return EndDate;
        }

        public void setEndDate(Object endDate) {
            EndDate = endDate;
        }

        public Object getDateStart() {
            return DateStart;
        }

        public void setDateStart(Object dateStart) {
            DateStart = dateStart;
        }

        public String getPhone() {
            return phone == null ? "" : phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getSex() {
            return sex == null ? "" : sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getPracticing_time() {
            return practicing_time == null ? "" : practicing_time;
        }

        public void setPracticing_time(String practicing_time) {
            this.practicing_time = practicing_time;
        }

        public String getPracticing_certificate() {
            return practicing_certificate == null ? "" : practicing_certificate;
        }

        public void setPracticing_certificate(String practicing_certificate) {
            this.practicing_certificate = practicing_certificate;
        }

        public Object getIncome() {
            return income;
        }

        public void setIncome(Object income) {
            this.income = income;
        }

        public Object getAccessid() {
            return Accessid;
        }

        public void setAccessid(Object accessid) {
            Accessid = accessid;
        }

        public Object getIsPrescribe() {
            return isPrescribe;
        }

        public void setIsPrescribe(Object isPrescribe) {
            this.isPrescribe = isPrescribe;
        }

        public Object getDepartList() {
            return departList;
        }

        public void setDepartList(Object departList) {
            this.departList = departList;
        }

        public int getShow_flag() {
            return show_flag;
        }

        public void setShow_flag(int show_flag) {
            this.show_flag = show_flag;
        }

        public String getShow_note() {
            return show_note == null ? "" : show_note;
        }

        public void setShow_note(String show_note) {
            this.show_note = show_note;
        }

        public Object getXq() {
            return xq;
        }

        public void setXq(Object xq) {
            this.xq = xq;
        }

        public Object getCreate_time_stamp() {
            return create_time_stamp;
        }

        public void setCreate_time_stamp(Object create_time_stamp) {
            this.create_time_stamp = create_time_stamp;
        }

        public Object getPracticing_time_stamp() {
            return practicing_time_stamp;
        }

        public void setPracticing_time_stamp(Object practicing_time_stamp) {
            this.practicing_time_stamp = practicing_time_stamp;
        }

        public List<DocevalMVBean> getDocevalMV() {
            if (docevalMV == null) {
                return new ArrayList<>();
            }
            return docevalMV;
        }

        public void setDocevalMV(List<DocevalMVBean> docevalMV) {
            this.docevalMV = docevalMV;
        }

        public static class DocevalMVBean {
            /**
             * IUID : c03cfeeb-4778-4462-9f7e-0a129de1c55c
             * doctorid : e855b837-0a36-42a4-8e75-66f54b2101d0
             * userid : b4aece8e-0014-494c-bed1-ac900dac1f25
             * askid : 6f9a643d-d170-41dc-b439-acab7f2d5bd7
             * use_img : null
             * nickname : 144389
             * the_note : 医术精湛 态度良好 dsfdsafsdf
             * the_star : 5
             * create_time : /Date(1559180182460)/
             * create_time_stamp : 0
             * doctorName : null
             * phone : null
             * DateStart : null
             * EndDate : null
             * anonymous_flag : null
             */

            private String IUID;
            private String doctorid;
            private String userid;
            private String askid;
            private String use_img;
            private String nickname;
            private String the_note;
            private int the_star;
            private String create_time;
            private long create_time_stamp;
            private Object doctorName;
            private Object phone;
            private Object DateStart;
            private Object EndDate;
            private Object anonymous_flag;

            public String getIUID() {
                return IUID == null ? "" : IUID;
            }

            public void setIUID(String IUID) {
                this.IUID = IUID;
            }

            public String getDoctorid() {
                return doctorid == null ? "" : doctorid;
            }

            public void setDoctorid(String doctorid) {
                this.doctorid = doctorid;
            }

            public String getUserid() {
                return userid == null ? "" : userid;
            }

            public void setUserid(String userid) {
                this.userid = userid;
            }

            public String getAskid() {
                return askid == null ? "" : askid;
            }

            public void setAskid(String askid) {
                this.askid = askid;
            }

            public String getUse_img() {
                return use_img;
            }

            public void setUse_img(String use_img) {
                this.use_img = use_img;
            }

            public String getNickname() {
                return nickname == null ? "" : nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getThe_note() {
                return the_note == null ? "" : the_note;
            }

            public void setThe_note(String the_note) {
                this.the_note = the_note;
            }

            public int getThe_star() {
                return the_star;
            }

            public void setThe_star(int the_star) {
                this.the_star = the_star;
            }

            public String getCreate_time() {
                return create_time == null ? "" : create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public long getCreate_time_stamp() {
                return create_time_stamp;
            }

            public void setCreate_time_stamp(long create_time_stamp) {
                this.create_time_stamp = create_time_stamp;
            }

            public Object getDoctorName() {
                return doctorName;
            }

            public void setDoctorName(Object doctorName) {
                this.doctorName = doctorName;
            }

            public Object getPhone() {
                return phone;
            }

            public void setPhone(Object phone) {
                this.phone = phone;
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

            public Object getAnonymous_flag() {
                return anonymous_flag;
            }

            public void setAnonymous_flag(Object anonymous_flag) {
                this.anonymous_flag = anonymous_flag;
            }
        }
    }
}
