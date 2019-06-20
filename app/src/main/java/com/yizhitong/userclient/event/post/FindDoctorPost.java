package com.yizhitong.userclient.event.post;

public class FindDoctorPost {
    private String departid;
    private String region;
    private String sort;
    private String chufang;
    private String the_level;
    private String JIAGEQ;

    public String getDepartid() {
        return departid == null ? "" : departid;
    }

    public void setDepartid(String departid) {
        this.departid = departid;
    }

    public String getRegion() {
        return region == null ? "" : region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getSort() {
        return sort == null ? "" : sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getChufang() {
        return chufang == null ? "" : chufang;
    }

    public void setChufang(String chufang) {
        this.chufang = chufang;
    }

    public String getThe_level() {
        return the_level == null ? "" : the_level;
    }

    public void setThe_level(String the_level) {
        this.the_level = the_level;
    }

    public String getJIAGEQ() {
        return JIAGEQ == null ? "" : JIAGEQ;
    }

    public void setJIAGEQ(String JIAGEQ) {
        this.JIAGEQ = JIAGEQ;
    }
}
