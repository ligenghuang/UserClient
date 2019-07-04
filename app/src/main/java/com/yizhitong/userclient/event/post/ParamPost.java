package com.yizhitong.userclient.event.post;

public class ParamPost {

    private String nicename;
    private String idnumber;
    private String phome;

    public String getPhome() {
        return phome == null ? "" : phome;
    }

    public void setPhome(String phome) {
        this.phome = phome;
    }

    public String getNicename() {
        return nicename == null ? "" : nicename;
    }

    public void setNicename(String nicename) {
        this.nicename = nicename;
    }

    public String getIdnumber() {
        return idnumber == null ? "" : idnumber;
    }

    public void setIdnumber(String idnumber) {
        this.idnumber = idnumber;
    }

    public String toNicename() {
        return "{" +
                "\"nicename\":\'" + nicename + "\'" +
                '}';
    }

    public String toIdNumber() {
        return "{" +
                "\"idnumber\":\'" + idnumber + "\'" +
                '}';
    }
    public String toPhone() {
        return "{" +
                "\"phome\":\'" + phome + "\'" +
                '}';
    }

}
