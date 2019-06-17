package com.yizhitong.userclient.event;

import java.util.ArrayList;
import java.util.List;

public class MyInquiryDto {


    /**
     * code : 1
     * data : [{"patienName":"小李","askIUID":"7e03534f-e8dd-4221-af49-51debf09c9a1","askdrugheadid":null,"userid":"9884b9cd-2222-40ee-8859-e6878b0081f1","patientid":"a446a0bf-c03f-4ec4-8035-1a52853c6a8b","PatientMV":null,"the_img":"/DOC/18529250717/20196315336507j.jpg","doctorid":"e855b837-0a36-42a4-8e75-66f54b2101d0","docUserId":null,"doctorName":"李松","the_level":"执业医师","hospital":null,"diagnosis":null,"departid":null,"ill_note":"我来问诊,我是谢仲钧","Ill_img":null,"doctor_money":null,"drug_money":null,"all_money":null,"drug_flag":null,"pay_money":0.01,"pay_class":null,"pay_flag":1,"start_time":null,"end_time":null,"create_time":"/Date(1560321311180)/","pay_time":null,"prescription_name":null,"status_flag":null,"ask_flag":0,"isEval":0,"DrugMV":null,"brokerage":0.007,"agree_flag":null,"finish_flag":null,"reback_flag":null,"askdrug_no":null,"Askdrug_img":null,"laveTime":null,"the_memo":null,"departName":null,"create_time_stamp":1560321311180,"pay_time_stamp":0},{"patienName":"小李","askIUID":"65e5ffe2-73de-4e16-9e23-1be633e812bb","askdrugheadid":null,"userid":"2eca06fa-3731-4301-b06e-fd7a0e548fff","patientid":"a446a0bf-c03f-4ec4-8035-1a52853c6a8b","PatientMV":null,"the_img":"/DOC/15521010992/20196514349262j.jpg","doctorid":"195f3875-fcdd-4fe6-84b5-19f4cab1031c","docUserId":null,"doctorName":"王医生","the_level":"放宽心","hospital":null,"diagnosis":null,"departid":null,"ill_note":"哈哈哈哈","Ill_img":null,"doctor_money":null,"drug_money":null,"all_money":null,"drug_flag":1,"pay_money":1,"pay_class":null,"pay_flag":1,"start_time":null,"end_time":null,"create_time":"/Date(1559716440607)/","pay_time":null,"prescription_name":null,"status_flag":null,"ask_flag":1,"isEval":0,"DrugMV":null,"brokerage":0.7,"agree_flag":null,"finish_flag":null,"reback_flag":null,"askdrug_no":null,"Askdrug_img":null,"laveTime":null,"the_memo":null,"departName":null,"create_time_stamp":1559716440607,"pay_time_stamp":0},{"patienName":"小李","askIUID":"c1414b22-808c-41d5-8fba-05fa8bc18eb6","askdrugheadid":null,"userid":"9884b9cd-2222-40ee-8859-e6878b0081f1","patientid":"a446a0bf-c03f-4ec4-8035-1a52853c6a8b","PatientMV":null,"the_img":"/DOC/18529250717/20196315336507j.jpg","doctorid":"e855b837-0a36-42a4-8e75-66f54b2101d0","docUserId":null,"doctorName":"李松","the_level":"执业医师","hospital":null,"diagnosis":null,"departid":null,"ill_note":"哦辛苦了善男信女","Ill_img":null,"doctor_money":null,"drug_money":null,"all_money":null,"drug_flag":1,"pay_money":0,"pay_class":null,"pay_flag":1,"start_time":null,"end_time":null,"create_time":"/Date(1559715546147)/","pay_time":null,"prescription_name":null,"status_flag":null,"ask_flag":1,"isEval":0,"DrugMV":null,"brokerage":0,"agree_flag":null,"finish_flag":null,"reback_flag":null,"askdrug_no":null,"Askdrug_img":null,"laveTime":null,"the_memo":null,"departName":null,"create_time_stamp":1559715546147,"pay_time_stamp":0},{"patienName":"小李","askIUID":"89a802b1-2f2e-4ba8-adb4-be13934e91c8","askdrugheadid":null,"userid":"235c038f-c992-4624-9005-f7ee1380d605","patientid":"a446a0bf-c03f-4ec4-8035-1a52853c6a8b","PatientMV":null,"the_img":"/DOC/15817046397/201964162222267j.jpg","doctorid":"dd97fdea-4327-46dd-80ad-6783dca2a1e5","docUserId":null,"doctorName":"小李","the_level":"执业","hospital":null,"diagnosis":null,"departid":null,"ill_note":"hi安心爱你","Ill_img":null,"doctor_money":null,"drug_money":null,"all_money":null,"drug_flag":null,"pay_money":0,"pay_class":null,"pay_flag":0,"start_time":null,"end_time":null,"create_time":"/Date(1559702343493)/","pay_time":null,"prescription_name":null,"status_flag":null,"ask_flag":0,"isEval":0,"DrugMV":null,"brokerage":0.7,"agree_flag":null,"finish_flag":null,"reback_flag":null,"askdrug_no":null,"Askdrug_img":null,"laveTime":null,"the_memo":null,"departName":null,"create_time_stamp":1559702343493,"pay_time_stamp":0},{"patienName":"小李","askIUID":"eca31b34-72e6-49ce-8f7f-db5235c8f3b6","askdrugheadid":null,"userid":"235c038f-c992-4624-9005-f7ee1380d605","patientid":"a446a0bf-c03f-4ec4-8035-1a52853c6a8b","PatientMV":null,"the_img":"/DOC/15817046397/201964162222267j.jpg","doctorid":"dd97fdea-4327-46dd-80ad-6783dca2a1e5","docUserId":null,"doctorName":"小李","the_level":"执业","hospital":null,"diagnosis":null,"departid":null,"ill_note":"2","Ill_img":null,"doctor_money":null,"drug_money":null,"all_money":null,"drug_flag":1,"pay_money":1,"pay_class":null,"pay_flag":1,"start_time":null,"end_time":null,"create_time":"/Date(1559611985020)/","pay_time":null,"prescription_name":null,"status_flag":null,"ask_flag":2,"isEval":1,"DrugMV":null,"brokerage":0.7,"agree_flag":null,"finish_flag":null,"reback_flag":null,"askdrug_no":null,"Askdrug_img":null,"laveTime":null,"the_memo":null,"departName":null,"create_time_stamp":1559611985020,"pay_time_stamp":0},{"patienName":"小李","askIUID":"0fa683d3-0861-4a68-80cd-af9bf17c7338","askdrugheadid":null,"userid":"235c038f-c992-4624-9005-f7ee1380d605","patientid":"a446a0bf-c03f-4ec4-8035-1a52853c6a8b","PatientMV":null,"the_img":"/DOC/15817046397/201964162222267j.jpg","doctorid":"dd97fdea-4327-46dd-80ad-6783dca2a1e5","docUserId":null,"doctorName":"小李","the_level":"执业","hospital":null,"diagnosis":null,"departid":null,"ill_note":"1","Ill_img":null,"doctor_money":null,"drug_money":null,"all_money":null,"drug_flag":1,"pay_money":1,"pay_class":null,"pay_flag":1,"start_time":null,"end_time":null,"create_time":"/Date(1559611973593)/","pay_time":null,"prescription_name":null,"status_flag":null,"ask_flag":2,"isEval":0,"DrugMV":null,"brokerage":0.7,"agree_flag":null,"finish_flag":null,"reback_flag":null,"askdrug_no":null,"Askdrug_img":null,"laveTime":null,"the_memo":null,"departName":null,"create_time_stamp":1559611973593,"pay_time_stamp":0},{"patienName":"小李","askIUID":"259d9279-a59b-477e-bb96-ceda515d19d9","askdrugheadid":null,"userid":"235c038f-c992-4624-9005-f7ee1380d605","patientid":"a446a0bf-c03f-4ec4-8035-1a52853c6a8b","PatientMV":null,"the_img":"/DOC/15817046397/201964162222267j.jpg","doctorid":"dd97fdea-4327-46dd-80ad-6783dca2a1e5","docUserId":null,"doctorName":"小李","the_level":"执业","hospital":null,"diagnosis":null,"departid":null,"ill_note":"0","Ill_img":null,"doctor_money":null,"drug_money":null,"all_money":null,"drug_flag":null,"pay_money":1,"pay_class":null,"pay_flag":1,"start_time":null,"end_time":null,"create_time":"/Date(1559611767553)/","pay_time":null,"prescription_name":null,"status_flag":null,"ask_flag":2,"isEval":0,"DrugMV":null,"brokerage":0.7,"agree_flag":null,"finish_flag":null,"reback_flag":null,"askdrug_no":null,"Askdrug_img":null,"laveTime":null,"the_memo":null,"departName":null,"create_time_stamp":1559611767553,"pay_time_stamp":0},{"patienName":"小李","askIUID":"4d3f5f9b-89f6-4977-b185-46054427e0ec","askdrugheadid":null,"userid":"235c038f-c992-4624-9005-f7ee1380d605","patientid":"a446a0bf-c03f-4ec4-8035-1a52853c6a8b","PatientMV":null,"the_img":"/DOC/15817046397/201964162222267j.jpg","doctorid":"dd97fdea-4327-46dd-80ad-6783dca2a1e5","docUserId":null,"doctorName":"小李","the_level":"执业","hospital":null,"diagnosis":null,"departid":null,"ill_note":"1","Ill_img":null,"doctor_money":null,"drug_money":null,"all_money":null,"drug_flag":null,"pay_money":1,"pay_class":null,"pay_flag":1,"start_time":null,"end_time":null,"create_time":"/Date(1559555053057)/","pay_time":null,"prescription_name":null,"status_flag":null,"ask_flag":2,"isEval":0,"DrugMV":null,"brokerage":0.7,"agree_flag":null,"finish_flag":null,"reback_flag":null,"askdrug_no":null,"Askdrug_img":null,"laveTime":null,"the_memo":null,"departName":null,"create_time_stamp":1559555053057,"pay_time_stamp":0},{"patienName":"小李","askIUID":"5cc2ab18-a555-4619-af36-796726915235","askdrugheadid":null,"userid":"235c038f-c992-4624-9005-f7ee1380d605","patientid":"a446a0bf-c03f-4ec4-8035-1a52853c6a8b","PatientMV":null,"the_img":"/DOC/15817046397/201964162222267j.jpg","doctorid":"dd97fdea-4327-46dd-80ad-6783dca2a1e5","docUserId":null,"doctorName":"小李","the_level":"执业","hospital":null,"diagnosis":null,"departid":null,"ill_note":"1234567890","Ill_img":null,"doctor_money":null,"drug_money":null,"all_money":null,"drug_flag":null,"pay_money":1,"pay_class":null,"pay_flag":1,"start_time":null,"end_time":null,"create_time":"/Date(1559554849973)/","pay_time":null,"prescription_name":null,"status_flag":null,"ask_flag":1,"isEval":0,"DrugMV":null,"brokerage":0.7,"agree_flag":null,"finish_flag":null,"reback_flag":null,"askdrug_no":null,"Askdrug_img":null,"laveTime":null,"the_memo":null,"departName":null,"create_time_stamp":1559554849973,"pay_time_stamp":0},{"patienName":"小李","askIUID":"dd360acc-34ff-4110-9be4-c8b9735eb2af","askdrugheadid":null,"userid":"235c038f-c992-4624-9005-f7ee1380d605","patientid":"a446a0bf-c03f-4ec4-8035-1a52853c6a8b","PatientMV":null,"the_img":"/DOC/15817046397/201964162222267j.jpg","doctorid":"dd97fdea-4327-46dd-80ad-6783dca2a1e5","docUserId":null,"doctorName":"小李","the_level":"执业","hospital":null,"diagnosis":null,"departid":null,"ill_note":"sssss","Ill_img":null,"doctor_money":null,"drug_money":null,"all_money":null,"drug_flag":null,"pay_money":1,"pay_class":null,"pay_flag":1,"start_time":null,"end_time":null,"create_time":"/Date(1559553879360)/","pay_time":null,"prescription_name":null,"status_flag":null,"ask_flag":1,"isEval":0,"DrugMV":null,"brokerage":0.7,"agree_flag":null,"finish_flag":null,"reback_flag":null,"askdrug_no":null,"Askdrug_img":null,"laveTime":null,"the_memo":null,"departName":null,"create_time_stamp":1559553879360,"pay_time_stamp":0},{"patienName":"小李","askIUID":"1436a5e0-e4a1-4d8e-822e-129a1a64e1f8","askdrugheadid":null,"userid":"235c038f-c992-4624-9005-f7ee1380d605","patientid":"a446a0bf-c03f-4ec4-8035-1a52853c6a8b","PatientMV":null,"the_img":"/DOC/15817046397/201964162222267j.jpg","doctorid":"dd97fdea-4327-46dd-80ad-6783dca2a1e5","docUserId":null,"doctorName":"小李","the_level":"执业","hospital":null,"diagnosis":null,"departid":null,"ill_note":"123456","Ill_img":null,"doctor_money":null,"drug_money":null,"all_money":null,"drug_flag":1,"pay_money":1,"pay_class":null,"pay_flag":1,"start_time":null,"end_time":null,"create_time":"/Date(1559552272113)/","pay_time":null,"prescription_name":null,"status_flag":null,"ask_flag":2,"isEval":1,"DrugMV":null,"brokerage":0.7,"agree_flag":null,"finish_flag":null,"reback_flag":null,"askdrug_no":null,"Askdrug_img":null,"laveTime":null,"the_memo":null,"departName":null,"create_time_stamp":1559552272113,"pay_time_stamp":0},{"patienName":"小李","askIUID":"c621444b-bda1-468c-b68e-4006e794802c","askdrugheadid":null,"userid":"235c038f-c992-4624-9005-f7ee1380d605","patientid":"a446a0bf-c03f-4ec4-8035-1a52853c6a8b","PatientMV":null,"the_img":"/DOC/15817046397/201964162222267j.jpg","doctorid":"dd97fdea-4327-46dd-80ad-6783dca2a1e5","docUserId":null,"doctorName":"小李","the_level":"执业","hospital":null,"diagnosis":null,"departid":null,"ill_note":"wwww","Ill_img":null,"doctor_money":null,"drug_money":null,"all_money":null,"drug_flag":null,"pay_money":25252,"pay_class":null,"pay_flag":1,"start_time":null,"end_time":null,"create_time":"/Date(1559552113427)/","pay_time":null,"prescription_name":null,"status_flag":null,"ask_flag":1,"isEval":0,"DrugMV":null,"brokerage":17676.4,"agree_flag":null,"finish_flag":null,"reback_flag":null,"askdrug_no":null,"Askdrug_img":null,"laveTime":null,"the_memo":null,"departName":null,"create_time_stamp":1559552113427,"pay_time_stamp":0},{"patienName":"小李","askIUID":"f7fbde0d-9cff-4b96-91b6-b17e43b9d68a","askdrugheadid":null,"userid":"235c038f-c992-4624-9005-f7ee1380d605","patientid":"a446a0bf-c03f-4ec4-8035-1a52853c6a8b","PatientMV":null,"the_img":"/DOC/15817046397/201964162222267j.jpg","doctorid":"dd97fdea-4327-46dd-80ad-6783dca2a1e5","docUserId":null,"doctorName":"小李","the_level":"执业","hospital":null,"diagnosis":null,"departid":null,"ill_note":"wwwwwwwwww","Ill_img":null,"doctor_money":null,"drug_money":null,"all_money":null,"drug_flag":null,"pay_money":25252,"pay_class":null,"pay_flag":1,"start_time":null,"end_time":null,"create_time":"/Date(1559550665167)/","pay_time":null,"prescription_name":null,"status_flag":null,"ask_flag":2,"isEval":1,"DrugMV":null,"brokerage":17676.4,"agree_flag":null,"finish_flag":null,"reback_flag":null,"askdrug_no":null,"Askdrug_img":null,"laveTime":null,"the_memo":null,"departName":null,"create_time_stamp":1559550665167,"pay_time_stamp":0},{"patienName":"小李","askIUID":"1bf2ba63-22e0-424d-8dc4-2ec9fa90bedb","askdrugheadid":null,"userid":"c5c38062-8153-4025-b209-628b4a1e9ea4","patientid":"a446a0bf-c03f-4ec4-8035-1a52853c6a8b","PatientMV":null,"the_img":"/DOC/17688856819/201963172014497j.jpg","doctorid":"2c5cb84c-4b5b-440d-86b2-56fd9e2532e7","docUserId":null,"doctorName":"zeng","the_level":"医师","hospital":null,"diagnosis":null,"departid":null,"ill_note":"1121","Ill_img":null,"doctor_money":null,"drug_money":null,"all_money":null,"drug_flag":1,"pay_money":1,"pay_class":null,"pay_flag":1,"start_time":null,"end_time":null,"create_time":"/Date(1559547350883)/","pay_time":null,"prescription_name":null,"status_flag":null,"ask_flag":1,"isEval":0,"DrugMV":null,"brokerage":0.7,"agree_flag":null,"finish_flag":null,"reback_flag":null,"askdrug_no":null,"Askdrug_img":null,"laveTime":null,"the_memo":null,"departName":null,"create_time_stamp":1559547350883,"pay_time_stamp":0},{"patienName":"小李","askIUID":"0c7bdff0-f5cd-4943-88c6-6269dccd49f6","askdrugheadid":null,"userid":"235c038f-c992-4624-9005-f7ee1380d605","patientid":"a446a0bf-c03f-4ec4-8035-1a52853c6a8b","PatientMV":null,"the_img":"/DOC/15817046397/201964162222267j.jpg","doctorid":"dd97fdea-4327-46dd-80ad-6783dca2a1e5","docUserId":null,"doctorName":"小李","the_level":"执业","hospital":null,"diagnosis":null,"departid":null,"ill_note":"fghgfdhdfg","Ill_img":null,"doctor_money":null,"drug_money":null,"all_money":null,"drug_flag":1,"pay_money":25252,"pay_class":null,"pay_flag":1,"start_time":null,"end_time":null,"create_time":"/Date(1559546892047)/","pay_time":null,"prescription_name":null,"status_flag":null,"ask_flag":2,"isEval":1,"DrugMV":null,"brokerage":17676.4,"agree_flag":null,"finish_flag":null,"reback_flag":null,"askdrug_no":null,"Askdrug_img":null,"laveTime":null,"the_memo":null,"departName":null,"create_time_stamp":1559546892047,"pay_time_stamp":0},{"patienName":"小李","askIUID":"6f9a643d-d170-41dc-b439-acab7f2d5bd7","askdrugheadid":null,"userid":"9884b9cd-2222-40ee-8859-e6878b0081f1","patientid":"a446a0bf-c03f-4ec4-8035-1a52853c6a8b","PatientMV":null,"the_img":"/DOC/18529250717/20196315336507j.jpg","doctorid":"e855b837-0a36-42a4-8e75-66f54b2101d0","docUserId":null,"doctorName":"李松","the_level":"执业医师","hospital":null,"diagnosis":null,"departid":null,"ill_note":"dfdsf","Ill_img":null,"doctor_money":null,"drug_money":null,"all_money":null,"drug_flag":null,"pay_money":11,"pay_class":null,"pay_flag":1,"start_time":null,"end_time":null,"create_time":"/Date(1551670008920)/","pay_time":null,"prescription_name":null,"status_flag":null,"ask_flag":2,"isEval":1,"DrugMV":null,"brokerage":7.7,"agree_flag":null,"finish_flag":null,"reback_flag":null,"askdrug_no":null,"Askdrug_img":null,"laveTime":null,"the_memo":null,"departName":null,"create_time_stamp":1551670008920,"pay_time_stamp":0},{"patienName":"小李","askIUID":"747edd52-63c9-4ba3-aae1-330848c5c2ff","askdrugheadid":null,"userid":"9884b9cd-2222-40ee-8859-e6878b0081f1","patientid":"a446a0bf-c03f-4ec4-8035-1a52853c6a8b","PatientMV":null,"the_img":"/DOC/18529250717/20196315336507j.jpg","doctorid":"e855b837-0a36-42a4-8e75-66f54b2101d0","docUserId":null,"doctorName":"李松","the_level":"执业医师","hospital":null,"diagnosis":null,"departid":null,"ill_note":"电饭锅电饭锅","Ill_img":null,"doctor_money":null,"drug_money":null,"all_money":null,"drug_flag":1,"pay_money":11,"pay_class":null,"pay_flag":1,"start_time":null,"end_time":null,"create_time":"/Date(1551670008920)/","pay_time":null,"prescription_name":null,"status_flag":null,"ask_flag":2,"isEval":0,"DrugMV":null,"brokerage":7.7,"agree_flag":null,"finish_flag":null,"reback_flag":null,"askdrug_no":null,"Askdrug_img":null,"laveTime":null,"the_memo":null,"departName":null,"create_time_stamp":1551670008920,"pay_time_stamp":0},{"patienName":"小李","askIUID":"b8bae022-835c-415a-8e4a-035aaac8ee41","askdrugheadid":null,"userid":"9884b9cd-2222-40ee-8859-e6878b0081f1","patientid":"a446a0bf-c03f-4ec4-8035-1a52853c6a8b","PatientMV":null,"the_img":"/DOC/18529250717/20196315336507j.jpg","doctorid":"e855b837-0a36-42a4-8e75-66f54b2101d0","docUserId":null,"doctorName":"李松","the_level":"执业医师","hospital":null,"diagnosis":null,"departid":null,"ill_note":"转账支持下","Ill_img":null,"doctor_money":null,"drug_money":null,"all_money":null,"drug_flag":1,"pay_money":2,"pay_class":null,"pay_flag":1,"start_time":null,"end_time":null,"create_time":"/Date(1551670008920)/","pay_time":null,"prescription_name":null,"status_flag":null,"ask_flag":3,"isEval":0,"DrugMV":null,"brokerage":1.4,"agree_flag":null,"finish_flag":null,"reback_flag":null,"askdrug_no":null,"Askdrug_img":null,"laveTime":null,"the_memo":null,"departName":null,"create_time_stamp":1551670008920,"pay_time_stamp":0},{"patienName":"小李","askIUID":"b8bae022-835c-415a-8e4a-035aaac8ee42","askdrugheadid":null,"userid":"9884b9cd-2222-40ee-8859-e6878b0081f1","patientid":"a446a0bf-c03f-4ec4-8035-1a52853c6a8b","PatientMV":null,"the_img":"/DOC/18529250717/20196315336507j.jpg","doctorid":"e855b837-0a36-42a4-8e75-66f54b2101d0","docUserId":null,"doctorName":"李松","the_level":"执业医师","hospital":null,"diagnosis":null,"departid":null,"ill_note":"撒的撒打算","Ill_img":null,"doctor_money":null,"drug_money":null,"all_money":null,"drug_flag":1,"pay_money":2,"pay_class":null,"pay_flag":1,"start_time":null,"end_time":null,"create_time":"/Date(1551670008920)/","pay_time":null,"prescription_name":null,"status_flag":null,"ask_flag":1,"isEval":0,"DrugMV":null,"brokerage":1.4,"agree_flag":null,"finish_flag":null,"reback_flag":null,"askdrug_no":null,"Askdrug_img":null,"laveTime":null,"the_memo":null,"departName":null,"create_time_stamp":1551670008920,"pay_time_stamp":0}]
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
         * patienName : 小李
         * askIUID : 7e03534f-e8dd-4221-af49-51debf09c9a1
         * askdrugheadid : null
         * userid : 9884b9cd-2222-40ee-8859-e6878b0081f1
         * patientid : a446a0bf-c03f-4ec4-8035-1a52853c6a8b
         * PatientMV : null
         * the_img : /DOC/18529250717/20196315336507j.jpg
         * doctorid : e855b837-0a36-42a4-8e75-66f54b2101d0
         * docUserId : null
         * doctorName : 李松
         * the_level : 执业医师
         * hospital : null
         * diagnosis : null
         * departid : null
         * ill_note : 我来问诊,我是谢仲钧
         * Ill_img : null
         * doctor_money : null
         * drug_money : null
         * all_money : null
         * drug_flag : null
         * pay_money : 0.01
         * pay_class : null
         * pay_flag : 1
         * start_time : null
         * end_time : null
         * create_time : /Date(1560321311180)/
         * pay_time : null
         * prescription_name : null
         * status_flag : null
         * ask_flag : 0
         * isEval : 0
         * DrugMV : null
         * brokerage : 0.007
         * agree_flag : null
         * finish_flag : null
         * reback_flag : null
         * askdrug_no : null
         * Askdrug_img : null
         * laveTime : null
         * the_memo : null
         * departName : null
         * create_time_stamp : 1560321311180
         * pay_time_stamp : 0
         */

        private String patienName;
        private String askIUID;
        private Object askdrugheadid;
        private String userid;
        private String patientid;
        private Object PatientMV;
        private String the_img;
        private String doctorid;
        private Object docUserId;
        private String doctorName;
        private String the_level;
        private Object hospital;
        private Object diagnosis;
        private Object departid;
        private String ill_note;
        private Object Ill_img;
        private Object doctor_money;
        private Object drug_money;
        private Object all_money;
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
        private int ask_flag;
        private int isEval;
        private Object DrugMV;
        private double brokerage;
        private Object agree_flag;
        private Object finish_flag;
        private Object reback_flag;
        private Object askdrug_no;
        private Object Askdrug_img;
        private Object laveTime;
        private Object the_memo;
        private Object departName;
        private long create_time_stamp;
        private int pay_time_stamp;

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

        public Object getAskdrugheadid() {
            return askdrugheadid;
        }

        public void setAskdrugheadid(Object askdrugheadid) {
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

        public Object getPatientMV() {
            return PatientMV;
        }

        public void setPatientMV(Object patientMV) {
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

        public Object getDocUserId() {
            return docUserId;
        }

        public void setDocUserId(Object docUserId) {
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

        public Object getHospital() {
            return hospital;
        }

        public void setHospital(Object hospital) {
            this.hospital = hospital;
        }

        public Object getDiagnosis() {
            return diagnosis;
        }

        public void setDiagnosis(Object diagnosis) {
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

        public Object getIll_img() {
            return Ill_img;
        }

        public void setIll_img(Object ill_img) {
            Ill_img = ill_img;
        }

        public Object getDoctor_money() {
            return doctor_money;
        }

        public void setDoctor_money(Object doctor_money) {
            this.doctor_money = doctor_money;
        }

        public Object getDrug_money() {
            return drug_money;
        }

        public void setDrug_money(Object drug_money) {
            this.drug_money = drug_money;
        }

        public Object getAll_money() {
            return all_money;
        }

        public void setAll_money(Object all_money) {
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

        public int getAsk_flag() {
            return ask_flag;
        }

        public void setAsk_flag(int ask_flag) {
            this.ask_flag = ask_flag;
        }

        public int getIsEval() {
            return isEval;
        }

        public void setIsEval(int isEval) {
            this.isEval = isEval;
        }

        public Object getDrugMV() {
            return DrugMV;
        }

        public void setDrugMV(Object drugMV) {
            DrugMV = drugMV;
        }

        public double getBrokerage() {
            return brokerage;
        }

        public void setBrokerage(double brokerage) {
            this.brokerage = brokerage;
        }

        public Object getAgree_flag() {
            return agree_flag;
        }

        public void setAgree_flag(Object agree_flag) {
            this.agree_flag = agree_flag;
        }

        public Object getFinish_flag() {
            return finish_flag;
        }

        public void setFinish_flag(Object finish_flag) {
            this.finish_flag = finish_flag;
        }

        public Object getReback_flag() {
            return reback_flag;
        }

        public void setReback_flag(Object reback_flag) {
            this.reback_flag = reback_flag;
        }

        public Object getAskdrug_no() {
            return askdrug_no;
        }

        public void setAskdrug_no(Object askdrug_no) {
            this.askdrug_no = askdrug_no;
        }

        public Object getAskdrug_img() {
            return Askdrug_img;
        }

        public void setAskdrug_img(Object askdrug_img) {
            Askdrug_img = askdrug_img;
        }

        public Object getLaveTime() {
            return laveTime;
        }

        public void setLaveTime(Object laveTime) {
            this.laveTime = laveTime;
        }

        public Object getThe_memo() {
            return the_memo;
        }

        public void setThe_memo(Object the_memo) {
            this.the_memo = the_memo;
        }

        public Object getDepartName() {
            return departName;
        }

        public void setDepartName(Object departName) {
            this.departName = departName;
        }

        public long getCreate_time_stamp() {
            return create_time_stamp;
        }

        public void setCreate_time_stamp(long create_time_stamp) {
            this.create_time_stamp = create_time_stamp;
        }

        public int getPay_time_stamp() {
            return pay_time_stamp;
        }

        public void setPay_time_stamp(int pay_time_stamp) {
            this.pay_time_stamp = pay_time_stamp;
        }
    }
}
