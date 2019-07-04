package com.yizhitong.userclient.event;

import java.util.ArrayList;
import java.util.List;

public class DepartListDto {


    /**
     * code : 1
     * data : [{"IUID":"1","name":"皮肤科","parentid":null,"the_memo":null,"depart2":[],"Accessid":null},{"IUID":"2","name":"内科","parentid":null,"the_memo":null,"depart2":[{"IUID":"3","name":"心脏科","parentid":"2","the_memo":null,"depart2":null,"Accessid":null},{"IUID":"4","name":"呼吸内科","parentid":"2","the_memo":null,"depart2":null,"Accessid":null}],"Accessid":null},{"IUID":"5","name":"骨科","parentid":null,"the_memo":null,"depart2":[{"IUID":"6","name":"接骨科","parentid":"5","the_memo":null,"depart2":null,"Accessid":null}],"Accessid":null},{"IUID":"7","name":"中医科","parentid":null,"the_memo":null,"depart2":[{"IUID":"10","name":"中药科","parentid":"7","the_memo":null,"depart2":null,"Accessid":null},{"IUID":"8","name":"针灸科","parentid":"7","the_memo":null,"depart2":null,"Accessid":null},{"IUID":"9","name":"推拿科","parentid":"7","the_memo":null,"depart2":null,"Accessid":null}],"Accessid":null}]
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
         * IUID : 1
         * name : 皮肤科
         * parentid : null
         * the_memo : null
         * depart2 : []
         * Accessid : null
         */

        private String IUID;
        private String name;
        private String parentid;
        private Object the_memo;
        private Object Accessid;
        private List<DataBean> depart2;
        private boolean isClick;

        public String getIUID() {
            return IUID == null ? "" : IUID;
        }

        public void setIUID(String IUID) {
            this.IUID = IUID;
        }

        public String getName() {
            return name == null ? "" : name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getParentid() {
            return parentid;
        }

        public void setParentid(String parentid) {
            this.parentid = parentid;
        }

        public Object getThe_memo() {
            return the_memo;
        }

        public void setThe_memo(Object the_memo) {
            this.the_memo = the_memo;
        }

        public Object getAccessid() {
            return Accessid;
        }

        public void setAccessid(Object accessid) {
            Accessid = accessid;
        }

        public List<DataBean> getDepart2() {
            if (depart2 == null) {
                return new ArrayList<>();
            }
            return depart2;
        }

        public void setDepart2(List<DataBean> depart2) {
            this.depart2 = depart2;
        }

        public boolean isClick() {
            return isClick;
        }

        public void setClick(boolean click) {
            isClick = click;
        }
    }
}
