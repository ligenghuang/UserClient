package com.yizhitong.userclient.event;

import java.util.ArrayList;
import java.util.List;

public class AddressListDto {


    /**
     * code : 1
     * data : [{"IUID":"3b9bf8e8-e130-421e-9f15-b3d24cea54a1","userid":"b4aece8e-0014-494c-bed1-ac900dac1f25","the_add":"dfgsfgsdfsdf","phone":"12316541654","name":"sdfsd","default_flag":1,"province":"北京","city":"东城区","zone":"","userAddress":"北京市东城区dfgsfgsdfsdf"}]
     * msg : 查询成功
     * url : null
     * wait : 0
     * Accessid : 0
     * data2 : null
     */

    private int code;
    private String msg;
    private Object url;
    private int wait;
    private int Accessid;
    private Object data2;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
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

    public List<DataBean> getData() {
        if (data == null) {
            return new ArrayList<>();
        }
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * IUID : 3b9bf8e8-e130-421e-9f15-b3d24cea54a1
         * userid : b4aece8e-0014-494c-bed1-ac900dac1f25
         * the_add : dfgsfgsdfsdf
         * phone : 12316541654
         * name : sdfsd
         * default_flag : 1
         * province : 北京
         * city : 东城区
         * zone :
         * userAddress : 北京市东城区dfgsfgsdfsdf
         */

        private String IUID;
        private String userid;
        private String the_add;
        private String phone;
        private String name;
        private int default_flag;
        private String province;
        private String city;
        private String zone;
        private String userAddress;

        private boolean isClick;

        public boolean isClick() {
            return isClick;
        }

        public void setClick(boolean click) {
            isClick = click;
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

        public String getThe_add() {
            return the_add == null ? "" : the_add;
        }

        public void setThe_add(String the_add) {
            this.the_add = the_add;
        }

        public String getPhone() {
            return phone == null ? "" : phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getName() {
            return name == null ? "" : name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getDefault_flag() {
            return default_flag;
        }

        public void setDefault_flag(int default_flag) {
            this.default_flag = default_flag;
        }

        public String getProvince() {
            return province == null ? "" : province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city == null ? "" : city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getZone() {
            return zone == null ? "" : zone;
        }

        public void setZone(String zone) {
            this.zone = zone;
        }

        public String getUserAddress() {
            return userAddress == null ? "" : userAddress;
        }

        public void setUserAddress(String userAddress) {
            this.userAddress = userAddress;
        }
    }
}
