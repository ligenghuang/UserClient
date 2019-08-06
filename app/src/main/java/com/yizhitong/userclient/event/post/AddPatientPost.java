package com.yizhitong.userclient.event.post;

public class AddPatientPost {

    /**
     * name : 小李
     * sex : 女
     * birt_date : 2018-06-03
     * phone : 80950280582
     * relation :
     * height : 92705
     * weight : 132
     * allergy_note : 大麦+活动啊农村弄死你减肥noon搭讪
     * med_family : 精神病+绝对靠谱上课呢
     * med_history : 风湿病+回复的loan你才哦哦
     * IUID : a446a0bf-c03f-4ec4-8035-1a52853c6a8b
     * med_drug : 氨基苄青霉素+侯赛尼烦死你分数高
     */

    private String name;
    private String sex;
    private String birt_date;
    private String phone;
    private String relation="";
    private float height;
    private float weight;
    private String allergy_note="";
    private String med_family="";
    private String med_history="";
    private String IUID;
    private String med_drug="";

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

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public String getAllergy_note() {
        return allergy_note == null ? "" : allergy_note;
    }

    public void setAllergy_note(String allergy_note) {
        this.allergy_note = allergy_note;
    }

    public String getMed_family() {
        return med_family == null ? "" : med_family;
    }

    public void setMed_family(String med_family) {
        this.med_family = med_family;
    }

    public String getMed_history() {
        return med_history == null ? "" : med_history;
    }

    public void setMed_history(String med_history) {
        this.med_history = med_history;
    }

    public String getIUID() {
        return IUID == null ? "" : IUID;
    }

    public void setIUID(String IUID) {
        this.IUID = IUID;
    }

    public String getMed_drug() {
        return med_drug == null ? "" : med_drug;
    }

    public void setMed_drug(String med_drug) {
        this.med_drug = med_drug;
    }

    @Override
    public String toString() {
        return "{" +
                "\"name\":\'" + name + "\'" +
                ", \"sex\":\'" + sex + "\'" +
                ", \"birt_date\":\'" + birt_date + "\'" +
                ", \"phone\":\'" + phone + "\'" +
                ", \"relation\":\'" + relation + "\'" +
                ", \"height\":" + height +
                ", \"weight\":" + weight +
                ", \"allergy_note\":\'" + allergy_note + "\'" +
                ", \"med_family\":\'" + med_family + "\'" +
                ", \"med_history\":\'" + med_history + "\'" +
                ", \"IUID\":\'" + IUID + "\'" +
                ", \"med_drug\":\'" + med_drug + "\'" +
                '}';
    }
}
