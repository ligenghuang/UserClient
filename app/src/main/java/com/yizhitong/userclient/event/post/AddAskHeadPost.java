package com.yizhitong.userclient.event.post;

public class AddAskHeadPost {

    /**
     * patientid : 49c71942-f863-429c-82b0-c21689f14f3a
     * ill_note : qqqq
     * doctor_money : 11
     * all_money : 11
     */

    private String patientid;
    private String doctorid;
    private String ill_note;
    private double doctor_money;
    private double all_money;

    public String getDoctorid() {
        return doctorid == null ? "" : doctorid;
    }

    public void setDoctorid(String doctorid) {
        this.doctorid = doctorid;
    }

    public String getPatientid() {
        return patientid == null ? "" : patientid;
    }

    public void setPatientid(String patientid) {
        this.patientid = patientid;
    }

    public String getIll_note() {
        return ill_note == null ? "" : ill_note;
    }

    public void setIll_note(String ill_note) {
        this.ill_note = ill_note;
    }

    public double getDoctor_money() {
        return doctor_money;
    }

    public void setDoctor_money(double doctor_money) {
        this.doctor_money = doctor_money;
    }

    public double getAll_money() {
        return all_money;
    }

    public void setAll_money(double all_money) {
        this.all_money = all_money;
    }

    @Override
    public String toString() {
        return "{" +
                "\"patientid\":\'" + patientid + "\'" +
                ", \"ill_note\":\'" + ill_note + "\'" +
                ", \"doctor_money\":" + doctor_money +
                ", \"all_money\":" + all_money +
                '}';
    }

    public String toString2() {
        return "{" +
                "\"patientid\":\'" + patientid + "\'" +
                ", \"doctorid\":\'" + doctorid + "\'" +
                ", \"ill_note\":\'" + ill_note + "\'" +
                ", \"doctor_money\":" + doctor_money +
                ", \"all_money\":" + all_money +
                '}';
    }
}
