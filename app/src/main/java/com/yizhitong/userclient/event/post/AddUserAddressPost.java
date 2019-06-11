package com.yizhitong.userclient.event.post;

public class AddUserAddressPost {
    /**
     * type	0
     * iuid	null
     * name	11111
     * phone	12223332323
     * cityPicker	广东 广州 越秀区
     * theAdd	是是是
     * defaultFlag	0
     */
    private String name;
    private String phone;
    private String cityPicker;
    private String theAdd;
    private String defaultFlag;
    private String iuid;
    private String type;

    public String getType() {
        return type == null ? "" : type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIuid() {
        return iuid == null ? "" : iuid;
    }

    public void setIuid(String iuid) {
        this.iuid = iuid;
    }

    public String getName() {
        return name == null ? "" : name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone == null ? "" : phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCityPicker() {
        return cityPicker == null ? "" : cityPicker;
    }

    public void setCityPicker(String cityPicker) {
        this.cityPicker = cityPicker;
    }

    public String getTheAdd() {
        return theAdd == null ? "" : theAdd;
    }

    public void setTheAdd(String theAdd) {
        this.theAdd = theAdd;
    }

    public String getDefaultFlag() {
        return defaultFlag;
    }

    public void setDefaultFlag(String defaultFlag) {
        this.defaultFlag = defaultFlag;
    }
}
