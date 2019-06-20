package com.yizhitong.userclient.event;

public class TypeListDto {
    private String type;
    private boolean isClick;

    public String getType() {
        return type == null ? "" : type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isClick() {
        return isClick;
    }

    public void setClick(boolean click) {
        isClick = click;
    }

    @Override
    public String toString() {
        return "TypeListDto{" +
                "type='" + type + '\'' +
                ", isClick=" + isClick +
                '}';
    }
}
