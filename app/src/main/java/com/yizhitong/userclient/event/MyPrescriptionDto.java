package com.yizhitong.userclient.event;

import java.util.ArrayList;
import java.util.List;

public class MyPrescriptionDto {

    /**
     * Accessid : 0
     * code : 1
     * data : [{"DrugMV":[{"name":"复方卡力孜然酊","the_spec":"每瓶装30ml","the_img":"/DOC/prescription/img/editorial_prescription_imgs.png","price":49.5,"drug_num":1}],"patientName":"小李","doctorName":"李松","doctorImg":"http://192.168.3.2:8014/DOC/18529250717/20196315336507j.jpg","the_level":"执业医师","drug_money":49.5,"pay_flag":1,"agree_flag":0,"reback_flag":0,"finish_flag":0,"brokerage":0.49,"askdrugheadid":"03db5f2d-2dce-42fd-8a73-2d94b78375f5","imt_url":"http://192.168.3.2:8014/H5/Uimg/18566144389/2019611111757871j.jpg"},{"DrugMV":[{"name":"小儿多维元素片(小儿善存片)","the_spec":"60片/瓶 30片/瓶 80片/瓶 120片/瓶 365片/瓶","the_img":"/DOC/prescription/img/editorial_prescription_imgs.png","price":49.5,"drug_num":1}],"patientName":"小李","doctorName":"李松","doctorImg":"http://192.168.3.2:8014/DOC/18529250717/20196315336507j.jpg","the_level":"执业医师","drug_money":49.5,"pay_flag":1,"agree_flag":0,"reback_flag":0,"finish_flag":0,"brokerage":0.49,"askdrugheadid":"0e1a1fb0-4572-49dc-be12-c527543cc2a7","imt_url":"http://192.168.3.2:8014/H5/Uimg/18566144389/2019611111757871j.jpg"},{"DrugMV":[{"name":"小儿多维元素片(小儿善存片)","the_spec":"60片/瓶 30片/瓶 80片/瓶 120片/瓶 365片/瓶","the_img":"/DOC/prescription/img/editorial_prescription_imgs.png","price":49.5,"drug_num":2}],"patientName":"小李","doctorName":"李松","doctorImg":"http://192.168.3.2:8014/DOC/18529250717/20196315336507j.jpg","the_level":"执业医师","drug_money":99,"pay_flag":1,"agree_flag":0,"reback_flag":0,"finish_flag":0,"brokerage":0.99,"askdrugheadid":"0bc2c1d5-8247-4a24-891b-a1c3ffbd0695","imt_url":"http://192.168.3.2:8014/H5/Uimg/18566144389/2019611111757871j.jpg"},{"DrugMV":[{"name":"复方丹参滴丸","the_spec":"每丸重27mg","the_img":"/DOC/prescription/img/editorial_prescription_imgs.png","price":49.5,"drug_num":2},{"name":"小儿多维元素片(小儿善存片)","the_spec":"60片/瓶 30片/瓶 80片/瓶 120片/瓶 365片/瓶","the_img":"/DOC/prescription/img/editorial_prescription_imgs.png","price":49.5,"drug_num":2}],"patientName":"小李","doctorName":"李松","doctorImg":"http://192.168.3.2:8014/DOC/18529250717/20196315336507j.jpg","the_level":"执业医师","drug_money":198,"pay_flag":1,"agree_flag":0,"reback_flag":0,"finish_flag":0,"brokerage":1.98,"askdrugheadid":"0e9830de-6d3a-42d4-bda7-819fc3fcc249","imt_url":"http://192.168.3.2:8014/H5/Uimg/18566144389/2019611111757871j.jpg"}]
     * msg :
     * wait : 0
     */

    private int Accessid;
    private int code;
    private String msg;
    private int wait;
    private List<DataBean> data;

    public int getAccessid() {
        return Accessid;
    }

    public void setAccessid(int Accessid) {
        this.Accessid = Accessid;
    }

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

    public int getWait() {
        return wait;
    }

    public void setWait(int wait) {
        this.wait = wait;
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
         * DrugMV : [{"name":"复方卡力孜然酊","the_spec":"每瓶装30ml","the_img":"/DOC/prescription/img/editorial_prescription_imgs.png","price":49.5,"drug_num":1}]
         * patientName : 小李
         * doctorName : 李松
         * doctorImg : http://192.168.3.2:8014/DOC/18529250717/20196315336507j.jpg
         * the_level : 执业医师
         * drug_money : 49.5
         * pay_flag : 1.0
         * agree_flag : 0.0
         * reback_flag : 0.0
         * finish_flag : 0.0
         * brokerage : 0.49
         * askdrugheadid : 03db5f2d-2dce-42fd-8a73-2d94b78375f5
         * imt_url : http://192.168.3.2:8014/H5/Uimg/18566144389/2019611111757871j.jpg
         */

        private String patientName;
        private String doctorName;
        private String doctorImg;
        private String the_level;
        private double drug_money;
        private double pay_flag;
        private double agree_flag;
        private double reback_flag;
        private double finish_flag;
        private double brokerage;
        private String askdrugheadid;
        private String imt_url;
        private List<DrugMVBean> DrugMV;

        public String getPatientName() {
            return patientName == null ? "" : patientName;
        }

        public void setPatientName(String patientName) {
            this.patientName = patientName;
        }

        public String getDoctorName() {
            return doctorName == null ? "" : doctorName;
        }

        public void setDoctorName(String doctorName) {
            this.doctorName = doctorName;
        }

        public String getDoctorImg() {
            return doctorImg == null ? "" : doctorImg;
        }

        public void setDoctorImg(String doctorImg) {
            this.doctorImg = doctorImg;
        }

        public String getThe_level() {
            return the_level == null ? "" : the_level;
        }

        public void setThe_level(String the_level) {
            this.the_level = the_level;
        }

        public double getDrug_money() {
            return drug_money;
        }

        public void setDrug_money(double drug_money) {
            this.drug_money = drug_money;
        }

        public double getPay_flag() {
            return pay_flag;
        }

        public void setPay_flag(double pay_flag) {
            this.pay_flag = pay_flag;
        }

        public double getAgree_flag() {
            return agree_flag;
        }

        public void setAgree_flag(double agree_flag) {
            this.agree_flag = agree_flag;
        }

        public double getReback_flag() {
            return reback_flag;
        }

        public void setReback_flag(double reback_flag) {
            this.reback_flag = reback_flag;
        }

        public double getFinish_flag() {
            return finish_flag;
        }

        public void setFinish_flag(double finish_flag) {
            this.finish_flag = finish_flag;
        }

        public double getBrokerage() {
            return brokerage;
        }

        public void setBrokerage(double brokerage) {
            this.brokerage = brokerage;
        }

        public String getAskdrugheadid() {
            return askdrugheadid == null ? "" : askdrugheadid;
        }

        public void setAskdrugheadid(String askdrugheadid) {
            this.askdrugheadid = askdrugheadid;
        }

        public String getImt_url() {
            return imt_url == null ? "" : imt_url;
        }

        public void setImt_url(String imt_url) {
            this.imt_url = imt_url;
        }

        public List<DrugMVBean> getDrugMV() {
            if (DrugMV == null) {
                return new ArrayList<>();
            }
            return DrugMV;
        }

        public void setDrugMV(List<DrugMVBean> drugMV) {
            DrugMV = drugMV;
        }

        public static class DrugMVBean {
            /**
             * name : 复方卡力孜然酊
             * the_spec : 每瓶装30ml
             * the_img : /DOC/prescription/img/editorial_prescription_imgs.png
             * price : 49.5
             * drug_num : 1.0
             */

            private String name;
            private String the_spec;
            private String the_img;
            private double price;
            private int drug_num;

            public String getName() {
                return name == null ? "" : name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getThe_spec() {
                return the_spec == null ? "" : the_spec;
            }

            public void setThe_spec(String the_spec) {
                this.the_spec = the_spec;
            }

            public String getThe_img() {
                return the_img == null ? "" : the_img;
            }

            public void setThe_img(String the_img) {
                this.the_img = the_img;
            }

            public double getPrice() {
                return price;
            }

            public void setPrice(double price) {
                this.price = price;
            }

            public int getDrug_num() {
                return drug_num;
            }

            public void setDrug_num(int drug_num) {
                this.drug_num = drug_num;
            }
        }
    }
}
