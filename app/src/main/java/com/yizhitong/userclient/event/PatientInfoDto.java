package com.yizhitong.userclient.event;

public class PatientInfoDto {


    /**
     * code : 0
     * data : {"IUID":"a446a0bf-c03f-4ec4-8035-1a52853c6a8b","userid":"b4aece8e-0014-494c-bed1-ac900dac1f25","name":"小李","sex":"女","birt_date":"/Date(1527955200000)/","height":92705,"weight":132,"phone":"80950280582","relation":"","allergy_note":"大麦 大米 海鲜 咖啡 活动啊农村弄死你减肥noon搭讪","med_history":"风湿病 回复的loan你才哦哦","med_family":"精神病 绝对靠谱上课呢","create_time":"/Date(1551669966383)/","age":0,"med_drug":"氨基苄青霉素 侯赛尼烦死你分数高"}
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
         * IUID : a446a0bf-c03f-4ec4-8035-1a52853c6a8b
         * userid : b4aece8e-0014-494c-bed1-ac900dac1f25
         * name : 小李
         * sex : 女
         * birt_date : /Date(1527955200000)/
         * height : 92705.0
         * weight : 132.0
         * phone : 80950280582
         * relation :
         * allergy_note : 大麦 大米 海鲜 咖啡 活动啊农村弄死你减肥noon搭讪
         * med_history : 风湿病 回复的loan你才哦哦
         * med_family : 精神病 绝对靠谱上课呢
         * create_time : /Date(1551669966383)/
         * age : 0
         * med_drug : 氨基苄青霉素 侯赛尼烦死你分数高
         */

        private String IUID;
        private String userid;
        private String name;
        private String sex;
        private String birt_date;
        private long birt_date_stamp;
        private double height;
        private double weight;
        private String phone;
        private String relation;
        private String allergy_note;
        private String med_history;
        private String med_family;
        private String create_time;
        private int age;
        private String med_drug;

        public long getBirt_date_stamp() {
            return birt_date_stamp;
        }

        public void setBirt_date_stamp(long birt_date_stamp) {
            this.birt_date_stamp = birt_date_stamp;
        }

        public String getIUID() {
            return IUID == null ? "" : IUID;
        }

        public void setIUID(String IUID) {
            this.IUID = IUID;
        }

        public String getUserid() {
            return userid == null ? "" : userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getName() {
            return name == null ? "" : name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSex() {
            return sex == null ? "" : sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getBirt_date() {
            return birt_date == null ? "" : birt_date;
        }

        public void setBirt_date(String birt_date) {
            this.birt_date = birt_date;
        }

        public double getHeight() {
            return height;
        }

        public void setHeight(double height) {
            this.height = height;
        }

        public double getWeight() {
            return weight;
        }

        public void setWeight(double weight) {
            this.weight = weight;
        }

        public String getPhone() {
            return phone == null ? "" : phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getRelation() {
            return relation == null ? "" : relation;
        }

        public void setRelation(String relation) {
            this.relation = relation;
        }

        public String getAllergy_note() {
            return allergy_note == null ? "" : allergy_note;
        }

        public void setAllergy_note(String allergy_note) {
            this.allergy_note = allergy_note;
        }

        public String getMed_history() {
            return med_history == null ? "" : med_history;
        }

        public void setMed_history(String med_history) {
            this.med_history = med_history;
        }

        public String getMed_family() {
            return med_family == null ? "" : med_family;
        }

        public void setMed_family(String med_family) {
            this.med_family = med_family;
        }

        public String getCreate_time() {
            return create_time == null ? "" : create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getMed_drug() {
            return med_drug == null ? "" : med_drug;
        }

        public void setMed_drug(String med_drug) {
            this.med_drug = med_drug;
        }
    }
}
