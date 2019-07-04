package com.yizhitong.userclient.event;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PreInfoDto implements Serializable {


    /**
     * code : 1
     * data : {"patienName":null,"askIUID":"5364a288-5d11-4440-9477-9b7d32c93a0a","askdrugheadid":"4f9ee624-20fe-4936-b30a-9dde3c80afbd","userid":null,"patientid":"2ed4faa4-b4b5-4854-924b-d0fb49cfdea6","PatientMV":{"IUID":null,"userid":null,"name":"xiezhongjun","sex":"男","birt_date":null,"birt_date_stamp":null,"height":null,"weight":5555,"phone":null,"relation":null,"allergy_note":"玉米 羊肉 就吐","med_history":"精神病 癌症 高血压 坎坎坷坷","med_family":" 暂无mm","create_time":null,"age":49,"med_drug":null},"the_img":null,"doctorid":"1db4883e-8d1f-4d68-bf2a-582146b554f3","docUserId":null,"doctorName":"Zeng","the_level":null,"hospital":null,"diagnosis":"哈哈哈","departid":null,"ill_note":"Test","Ill_img":[],"doctor_money":null,"drug_money":49.5,"all_money":null,"drug_flag":null,"pay_money":0,"pay_class":null,"pay_flag":0,"start_time":null,"end_time":null,"create_time":"/Date(1562065512060)/","pay_time":null,"prescription_name":null,"status_flag":null,"ask_flag":null,"isEval":0,"DrugMV":[{"IUID":"74","askDrugId":"66aae9eb-a4cf-437f-acb7-0e0b19c3397a","name":"龙血竭片","the_class":null,"otc_class":null,"the_company":null,"the_spec":"基片重0.4克","element":null,"appear":null,"indication":null,"num_note":"口服，一次4～6片，一日3次，或遵医嘱","bad_effect":null,"bad_no":null,"attention_note":null,"drug_response":null,"theory":null,"keep_note":null,"the_img":"/DOC/prescription/img/editorial_prescription_imgs.png","Accessid":null,"EndDate":null,"DateStart":null,"create_time":null,"brokerage":null,"price":49.5,"drug_num":1,"pinyin":null,"py":null,"xq":null}],"UserAddMV":{"IUID":"06f8cc40-7a27-47f3-abad-844023cc8c9b","userid":null,"the_add":null,"phone":"55555","name":"阿里啦咯啦","default_flag":null,"province":null,"city":null,"zone":null,"userAddress":"北京市丰台区土土"},"brokerage":504.9,"agree_flag":0,"finish_flag":0,"reback_flag":0,"askdrug_no":"20190702190512058","Askdrug_img":[],"laveTime":null,"the_memo":"好","departName":"皮肤科","create_time_stamp":1562065512060,"pay_time_stamp":1562065512060}
     * msg :
     * url : null
     * wait : 0
     * Accessid : 0
     * data2 : null
     */

    private int code;
    private DataBean data;
    private String msg;
    private Object url;
    private int wait;
    private int Accessid;
    private Object data2;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
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

    public static class DataBean implements Serializable{
        /**
         * patienName : null
         * askIUID : 5364a288-5d11-4440-9477-9b7d32c93a0a
         * askdrugheadid : 4f9ee624-20fe-4936-b30a-9dde3c80afbd
         * userid : null
         * patientid : 2ed4faa4-b4b5-4854-924b-d0fb49cfdea6
         * PatientMV : {"IUID":null,"userid":null,"name":"xiezhongjun","sex":"男","birt_date":null,"birt_date_stamp":null,"height":null,"weight":5555,"phone":null,"relation":null,"allergy_note":"玉米 羊肉 就吐","med_history":"精神病 癌症 高血压 坎坎坷坷","med_family":" 暂无mm","create_time":null,"age":49,"med_drug":null}
         * the_img : null
         * doctorid : 1db4883e-8d1f-4d68-bf2a-582146b554f3
         * docUserId : null
         * doctorName : Zeng
         * the_level : null
         * hospital : null
         * diagnosis : 哈哈哈
         * departid : null
         * ill_note : Test
         * Ill_img : []
         * doctor_money : null
         * drug_money : 49.5
         * all_money : null
         * drug_flag : null
         * pay_money : 0.0
         * pay_class : null
         * pay_flag : 0
         * start_time : null
         * end_time : null
         * create_time : /Date(1562065512060)/
         * pay_time : null
         * prescription_name : null
         * status_flag : null
         * ask_flag : null
         * isEval : 0
         * DrugMV : [{"IUID":"74","askDrugId":"66aae9eb-a4cf-437f-acb7-0e0b19c3397a","name":"龙血竭片","the_class":null,"otc_class":null,"the_company":null,"the_spec":"基片重0.4克","element":null,"appear":null,"indication":null,"num_note":"口服，一次4～6片，一日3次，或遵医嘱","bad_effect":null,"bad_no":null,"attention_note":null,"drug_response":null,"theory":null,"keep_note":null,"the_img":"/DOC/prescription/img/editorial_prescription_imgs.png","Accessid":null,"EndDate":null,"DateStart":null,"create_time":null,"brokerage":null,"price":49.5,"drug_num":1,"pinyin":null,"py":null,"xq":null}]
         * UserAddMV : {"IUID":"06f8cc40-7a27-47f3-abad-844023cc8c9b","userid":null,"the_add":null,"phone":"55555","name":"阿里啦咯啦","default_flag":null,"province":null,"city":null,"zone":null,"userAddress":"北京市丰台区土土"}
         * brokerage : 504.9
         * agree_flag : 0
         * finish_flag : 0
         * reback_flag : 0
         * askdrug_no : 20190702190512058
         * Askdrug_img : []
         * laveTime : null
         * the_memo : 好
         * departName : 皮肤科
         * create_time_stamp : 1562065512060
         * pay_time_stamp : 1562065512060
         */

        private String patienName;
        private String askIUID;
        private String askdrugheadid;
        private String userid;
        private String patientid;
        private PatientMVBean PatientMV;
        private String the_img;
        private String doctorid;
        private String docUserId;
        private String doctorName;
        private String the_level;
        private String hospital;
        private String diagnosis;
        private Object departid;
        private String ill_note;
        private String doctor_money;
        private double drug_money;
        private String all_money;
        private Object drug_flag;
        private double pay_money;
        private Object pay_class;
        private int pay_flag;
        private Object start_time;
        private Object end_time;
        private String create_time;
        private Object pay_time;
        private Object prescription_name;
        private Object status_flag;
        private Object ask_flag;
        private int isEval;
        private UserAddMVBean UserAddMV;
        private double brokerage;
        private int agree_flag;
        private int finish_flag;
        private int reback_flag;
        private String askdrug_no;
        private Object laveTime;
        private String the_memo;
        private String departName;
        private long create_time_stamp;
        private long pay_time_stamp;
        private List<String> Ill_img;
        private List<DrugMVBean> DrugMV;
        private List<String> Askdrug_img;

        public String getPatienName() {
            return patienName == null ? "" : patienName;
        }

        public void setPatienName(String patienName) {
            this.patienName = patienName;
        }

        public String getAskIUID() {
            return askIUID == null ? "" : askIUID;
        }

        public void setAskIUID(String askIUID) {
            this.askIUID = askIUID;
        }

        public String getAskdrugheadid() {
            return askdrugheadid == null ? "" : askdrugheadid;
        }

        public void setAskdrugheadid(String askdrugheadid) {
            this.askdrugheadid = askdrugheadid;
        }

        public String getUserid() {
            return userid == null ? "" : userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getPatientid() {
            return patientid == null ? "" : patientid;
        }

        public void setPatientid(String patientid) {
            this.patientid = patientid;
        }

        public PatientMVBean getPatientMV() {
            return PatientMV;
        }

        public void setPatientMV(PatientMVBean patientMV) {
            PatientMV = patientMV;
        }

        public String getThe_img() {
            return the_img == null ? "" : the_img;
        }

        public void setThe_img(String the_img) {
            this.the_img = the_img;
        }

        public String getDoctorid() {
            return doctorid == null ? "" : doctorid;
        }

        public void setDoctorid(String doctorid) {
            this.doctorid = doctorid;
        }

        public String getDocUserId() {
            return docUserId == null ? "" : docUserId;
        }

        public void setDocUserId(String docUserId) {
            this.docUserId = docUserId;
        }

        public String getDoctorName() {
            return doctorName == null ? "" : doctorName;
        }

        public void setDoctorName(String doctorName) {
            this.doctorName = doctorName;
        }

        public String getThe_level() {
            return the_level == null ? "" : the_level;
        }

        public void setThe_level(String the_level) {
            this.the_level = the_level;
        }

        public String getHospital() {
            return hospital == null ? "" : hospital;
        }

        public void setHospital(String hospital) {
            this.hospital = hospital;
        }

        public String getDiagnosis() {
            return diagnosis == null ? "" : diagnosis;
        }

        public void setDiagnosis(String diagnosis) {
            this.diagnosis = diagnosis;
        }

        public Object getDepartid() {
            return departid;
        }

        public void setDepartid(Object departid) {
            this.departid = departid;
        }

        public String getIll_note() {
            return ill_note == null ? "" : ill_note;
        }

        public void setIll_note(String ill_note) {
            this.ill_note = ill_note;
        }

        public String getDoctor_money() {
            return doctor_money == null ? "" : doctor_money;
        }

        public void setDoctor_money(String doctor_money) {
            this.doctor_money = doctor_money;
        }

        public double getDrug_money() {
            return drug_money;
        }

        public void setDrug_money(double drug_money) {
            this.drug_money = drug_money;
        }

        public String getAll_money() {
            return all_money == null ? "" : all_money;
        }

        public void setAll_money(String all_money) {
            this.all_money = all_money;
        }

        public Object getDrug_flag() {
            return drug_flag;
        }

        public void setDrug_flag(Object drug_flag) {
            this.drug_flag = drug_flag;
        }

        public double getPay_money() {
            return pay_money;
        }

        public void setPay_money(double pay_money) {
            this.pay_money = pay_money;
        }

        public Object getPay_class() {
            return pay_class;
        }

        public void setPay_class(Object pay_class) {
            this.pay_class = pay_class;
        }

        public int getPay_flag() {
            return pay_flag;
        }

        public void setPay_flag(int pay_flag) {
            this.pay_flag = pay_flag;
        }

        public Object getStart_time() {
            return start_time;
        }

        public void setStart_time(Object start_time) {
            this.start_time = start_time;
        }

        public Object getEnd_time() {
            return end_time;
        }

        public void setEnd_time(Object end_time) {
            this.end_time = end_time;
        }

        public String getCreate_time() {
            return create_time == null ? "" : create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public Object getPay_time() {
            return pay_time;
        }

        public void setPay_time(Object pay_time) {
            this.pay_time = pay_time;
        }

        public Object getPrescription_name() {
            return prescription_name;
        }

        public void setPrescription_name(Object prescription_name) {
            this.prescription_name = prescription_name;
        }

        public Object getStatus_flag() {
            return status_flag;
        }

        public void setStatus_flag(Object status_flag) {
            this.status_flag = status_flag;
        }

        public Object getAsk_flag() {
            return ask_flag;
        }

        public void setAsk_flag(Object ask_flag) {
            this.ask_flag = ask_flag;
        }

        public int getIsEval() {
            return isEval;
        }

        public void setIsEval(int isEval) {
            this.isEval = isEval;
        }

        public UserAddMVBean getUserAddMV() {
            return UserAddMV;
        }

        public void setUserAddMV(UserAddMVBean userAddMV) {
            UserAddMV = userAddMV;
        }

        public double getBrokerage() {
            return brokerage;
        }

        public void setBrokerage(double brokerage) {
            this.brokerage = brokerage;
        }

        public int getAgree_flag() {
            return agree_flag;
        }

        public void setAgree_flag(int agree_flag) {
            this.agree_flag = agree_flag;
        }

        public int getFinish_flag() {
            return finish_flag;
        }

        public void setFinish_flag(int finish_flag) {
            this.finish_flag = finish_flag;
        }

        public int getReback_flag() {
            return reback_flag;
        }

        public void setReback_flag(int reback_flag) {
            this.reback_flag = reback_flag;
        }

        public String getAskdrug_no() {
            return askdrug_no == null ? "" : askdrug_no;
        }

        public void setAskdrug_no(String askdrug_no) {
            this.askdrug_no = askdrug_no;
        }

        public Object getLaveTime() {
            return laveTime;
        }

        public void setLaveTime(Object laveTime) {
            this.laveTime = laveTime;
        }

        public String getThe_memo() {
            return the_memo == null ? "" : the_memo;
        }

        public void setThe_memo(String the_memo) {
            this.the_memo = the_memo;
        }

        public String getDepartName() {
            return departName == null ? "" : departName;
        }

        public void setDepartName(String departName) {
            this.departName = departName;
        }

        public long getCreate_time_stamp() {
            return create_time_stamp;
        }

        public void setCreate_time_stamp(long create_time_stamp) {
            this.create_time_stamp = create_time_stamp;
        }

        public long getPay_time_stamp() {
            return pay_time_stamp;
        }

        public void setPay_time_stamp(long pay_time_stamp) {
            this.pay_time_stamp = pay_time_stamp;
        }

        public List<String> getIll_img() {
            if (Ill_img == null) {
                return new ArrayList<>();
            }
            return Ill_img;
        }

        public void setIll_img(List<String> ill_img) {
            Ill_img = ill_img;
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

        public List<String> getAskdrug_img() {
            if (Askdrug_img == null) {
                return new ArrayList<>();
            }
            return Askdrug_img;
        }

        public void setAskdrug_img(List<String> askdrug_img) {
            Askdrug_img = askdrug_img;
        }

        public static class PatientMVBean implements Serializable{
            /**
             * IUID : null
             * userid : null
             * name : xiezhongjun
             * sex : 男
             * birt_date : null
             * birt_date_stamp : null
             * height : null
             * weight : 5555.0
             * phone : null
             * relation : null
             * allergy_note : 玉米 羊肉 就吐
             * med_history : 精神病 癌症 高血压 坎坎坷坷
             * med_family :  暂无mm
             * create_time : null
             * age : 49
             * med_drug : null
             */

            private String IUID;
            private String userid;
            private String name;
            private String sex;
            private Object birt_date;
            private Object birt_date_stamp;
            private Object height;
            private double weight;
            private String phone;
            private Object relation;
            private String allergy_note;
            private String med_history;
            private String med_family;
            private Object create_time;
            private int age;
            private Object med_drug;

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

            public String getName() {
                return name == null ? "" : name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getSex() {
                return sex == null ? "" : sex;
            }

            public void setSex(String sex) {
                this.sex = sex;
            }

            public Object getBirt_date() {
                return birt_date;
            }

            public void setBirt_date(Object birt_date) {
                this.birt_date = birt_date;
            }

            public Object getBirt_date_stamp() {
                return birt_date_stamp;
            }

            public void setBirt_date_stamp(Object birt_date_stamp) {
                this.birt_date_stamp = birt_date_stamp;
            }

            public Object getHeight() {
                return height;
            }

            public void setHeight(Object height) {
                this.height = height;
            }

            public double getWeight() {
                return weight;
            }

            public void setWeight(double weight) {
                this.weight = weight;
            }

            public String getPhone() {
                return phone == null ? "" : phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public Object getRelation() {
                return relation;
            }

            public void setRelation(Object relation) {
                this.relation = relation;
            }

            public String getAllergy_note() {
                return allergy_note == null ? "" : allergy_note;
            }

            public void setAllergy_note(String allergy_note) {
                this.allergy_note = allergy_note;
            }

            public String getMed_history() {
                return med_history == null ? "" : med_history;
            }

            public void setMed_history(String med_history) {
                this.med_history = med_history;
            }

            public String getMed_family() {
                return med_family == null ? "" : med_family;
            }

            public void setMed_family(String med_family) {
                this.med_family = med_family;
            }

            public Object getCreate_time() {
                return create_time;
            }

            public void setCreate_time(Object create_time) {
                this.create_time = create_time;
            }

            public int getAge() {
                return age;
            }

            public void setAge(int age) {
                this.age = age;
            }

            public Object getMed_drug() {
                return med_drug;
            }

            public void setMed_drug(Object med_drug) {
                this.med_drug = med_drug;
            }
        }

        public static class UserAddMVBean implements Serializable{
            /**
             * IUID : 06f8cc40-7a27-47f3-abad-844023cc8c9b
             * userid : null
             * the_add : null
             * phone : 55555
             * name : 阿里啦咯啦
             * default_flag : null
             * province : null
             * city : null
             * zone : null
             * userAddress : 北京市丰台区土土
             */

            private String IUID;
            private Object userid;
            private Object the_add;
            private String phone;
            private String name;
            private Object default_flag;
            private Object province;
            private Object city;
            private Object zone;
            private String userAddress;

            public String getIUID() {
                return IUID == null ? "" : IUID;
            }

            public void setIUID(String IUID) {
                this.IUID = IUID;
            }

            public Object getUserid() {
                return userid;
            }

            public void setUserid(Object userid) {
                this.userid = userid;
            }

            public Object getThe_add() {
                return the_add;
            }

            public void setThe_add(Object the_add) {
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

            public Object getDefault_flag() {
                return default_flag;
            }

            public void setDefault_flag(Object default_flag) {
                this.default_flag = default_flag;
            }

            public Object getProvince() {
                return province;
            }

            public void setProvince(Object province) {
                this.province = province;
            }

            public Object getCity() {
                return city;
            }

            public void setCity(Object city) {
                this.city = city;
            }

            public Object getZone() {
                return zone;
            }

            public void setZone(Object zone) {
                this.zone = zone;
            }

            public String getUserAddress() {
                return userAddress == null ? "" : userAddress;
            }

            public void setUserAddress(String userAddress) {
                this.userAddress = userAddress;
            }
        }

        public static class DrugMVBean implements Serializable{
            /**
             * IUID : 74
             * askDrugId : 66aae9eb-a4cf-437f-acb7-0e0b19c3397a
             * name : 龙血竭片
             * the_class : null
             * otc_class : null
             * the_company : null
             * the_spec : 基片重0.4克
             * element : null
             * appear : null
             * indication : null
             * num_note : 口服，一次4～6片，一日3次，或遵医嘱
             * bad_effect : null
             * bad_no : null
             * attention_note : null
             * drug_response : null
             * theory : null
             * keep_note : null
             * the_img : /DOC/prescription/img/editorial_prescription_imgs.png
             * Accessid : null
             * EndDate : null
             * DateStart : null
             * create_time : null
             * brokerage : null
             * price : 49.5
             * drug_num : 1.0
             * pinyin : null
             * py : null
             * xq : null
             */

            private String IUID;
            private String askDrugId;
            private String name;
            private Object the_class;
            private Object otc_class;
            private Object the_company;
            private String the_spec;
            private Object element;
            private Object appear;
            private Object indication;
            private String num_note;
            private Object bad_effect;
            private Object bad_no;
            private Object attention_note;
            private Object drug_response;
            private Object theory;
            private Object keep_note;
            private String the_img;
            private Object Accessid;
            private Object EndDate;
            private Object DateStart;
            private Object create_time;
            private Object brokerage;
            private double price;
            private double drug_num;
            private Object pinyin;
            private Object py;
            private Object xq;

            public String getIUID() {
                return IUID == null ? "" : IUID;
            }

            public void setIUID(String IUID) {
                this.IUID = IUID;
            }

            public String getAskDrugId() {
                return askDrugId == null ? "" : askDrugId;
            }

            public void setAskDrugId(String askDrugId) {
                this.askDrugId = askDrugId;
            }

            public String getName() {
                return name == null ? "" : name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public Object getThe_class() {
                return the_class;
            }

            public void setThe_class(Object the_class) {
                this.the_class = the_class;
            }

            public Object getOtc_class() {
                return otc_class;
            }

            public void setOtc_class(Object otc_class) {
                this.otc_class = otc_class;
            }

            public Object getThe_company() {
                return the_company;
            }

            public void setThe_company(Object the_company) {
                this.the_company = the_company;
            }

            public String getThe_spec() {
                return the_spec == null ? "" : the_spec;
            }

            public void setThe_spec(String the_spec) {
                this.the_spec = the_spec;
            }

            public Object getElement() {
                return element;
            }

            public void setElement(Object element) {
                this.element = element;
            }

            public Object getAppear() {
                return appear;
            }

            public void setAppear(Object appear) {
                this.appear = appear;
            }

            public Object getIndication() {
                return indication;
            }

            public void setIndication(Object indication) {
                this.indication = indication;
            }

            public String getNum_note() {
                return num_note == null ? "" : num_note;
            }

            public void setNum_note(String num_note) {
                this.num_note = num_note;
            }

            public Object getBad_effect() {
                return bad_effect;
            }

            public void setBad_effect(Object bad_effect) {
                this.bad_effect = bad_effect;
            }

            public Object getBad_no() {
                return bad_no;
            }

            public void setBad_no(Object bad_no) {
                this.bad_no = bad_no;
            }

            public Object getAttention_note() {
                return attention_note;
            }

            public void setAttention_note(Object attention_note) {
                this.attention_note = attention_note;
            }

            public Object getDrug_response() {
                return drug_response;
            }

            public void setDrug_response(Object drug_response) {
                this.drug_response = drug_response;
            }

            public Object getTheory() {
                return theory;
            }

            public void setTheory(Object theory) {
                this.theory = theory;
            }

            public Object getKeep_note() {
                return keep_note;
            }

            public void setKeep_note(Object keep_note) {
                this.keep_note = keep_note;
            }

            public String getThe_img() {
                return the_img == null ? "" : the_img;
            }

            public void setThe_img(String the_img) {
                this.the_img = the_img;
            }

            public Object getAccessid() {
                return Accessid;
            }

            public void setAccessid(Object accessid) {
                Accessid = accessid;
            }

            public Object getEndDate() {
                return EndDate;
            }

            public void setEndDate(Object endDate) {
                EndDate = endDate;
            }

            public Object getDateStart() {
                return DateStart;
            }

            public void setDateStart(Object dateStart) {
                DateStart = dateStart;
            }

            public Object getCreate_time() {
                return create_time;
            }

            public void setCreate_time(Object create_time) {
                this.create_time = create_time;
            }

            public Object getBrokerage() {
                return brokerage;
            }

            public void setBrokerage(Object brokerage) {
                this.brokerage = brokerage;
            }

            public double getPrice() {
                return price;
            }

            public void setPrice(double price) {
                this.price = price;
            }

            public double getDrug_num() {
                return drug_num;
            }

            public void setDrug_num(double drug_num) {
                this.drug_num = drug_num;
            }

            public Object getPinyin() {
                return pinyin;
            }

            public void setPinyin(Object pinyin) {
                this.pinyin = pinyin;
            }

            public Object getPy() {
                return py;
            }

            public void setPy(Object py) {
                this.py = py;
            }

            public Object getXq() {
                return xq;
            }

            public void setXq(Object xq) {
                this.xq = xq;
            }
        }
    }
}
