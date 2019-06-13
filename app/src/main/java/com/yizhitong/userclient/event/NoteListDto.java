package com.yizhitong.userclient.event;

public class NoteListDto {

    private String note;
    private boolean isClick;

    public String getNote() {
        return note == null ? "" : note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public boolean isClick() {
        return isClick;
    }

    public void setClick(boolean click) {
        isClick = click;
    }

    @Override
    public String toString() {
        return "NoteListDto{" +
                "note='" + note + '\'' +
                ", isClick=" + isClick +
                '}';
    }
}
