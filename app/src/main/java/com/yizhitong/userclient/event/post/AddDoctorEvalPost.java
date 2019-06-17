package com.yizhitong.userclient.event.post;

public class AddDoctorEvalPost {

    /**
     * askId : 0fa683d3-0861-4a68-80cd-af9bf17c7338
     * the_note : 医术精湛 rrrr
     * the_star : 3
     * anonymous_flag : 0
     */

    private String askId;
    private String the_note;
    private int the_star;
    private int anonymous_flag;

    public String getAskId() {
        return askId;
    }

    public void setAskId(String askId) {
        this.askId = askId;
    }

    public String getThe_note() {
        return the_note;
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

    public int getAnonymous_flag() {
        return anonymous_flag;
    }

    public void setAnonymous_flag(int anonymous_flag) {
        this.anonymous_flag = anonymous_flag;
    }
}
