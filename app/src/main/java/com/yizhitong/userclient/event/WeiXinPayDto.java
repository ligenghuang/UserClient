package com.yizhitong.userclient.event;

public class WeiXinPayDto {


    /**
     * code : 1
     * data : {"appid":"wxa7adeb0c22a4bc88","mch_id":"1522653341","nonce_str":"ZLbtzu6SQ9xtCKgA","prepay_id":"wx151457558491530e02ce16a91489552200","result_code":"SUCCESS","return_code":"SUCCESS","return_msg":"OK","sign":"65C0A75549BE0B9303921A0B07A5709E374093EE93E87BE317654A267333AB99","trade_type":"APP"}
     * msg : 统一下单成功
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

    public static class DataBean {
        /**
         * appid : wxa7adeb0c22a4bc88
         * mch_id : 1522653341
         * nonce_str : ZLbtzu6SQ9xtCKgA
         * prepay_id : wx151457558491530e02ce16a91489552200
         * result_code : SUCCESS
         * return_code : SUCCESS
         * return_msg : OK
         * sign : 65C0A75549BE0B9303921A0B07A5709E374093EE93E87BE317654A267333AB99
         * trade_type : APP
         */

        private String appid;
        private String mch_id;
        private String nonce_str;
        private String prepay_id;
        private String result_code;
        private String return_code;
        private String return_msg;
        private String sign;
        private String trade_type;

        public String getAppid() {
            return appid == null ? "" : appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }

        public String getMch_id() {
            return mch_id == null ? "" : mch_id;
        }

        public void setMch_id(String mch_id) {
            this.mch_id = mch_id;
        }

        public String getNonce_str() {
            return nonce_str == null ? "" : nonce_str;
        }

        public void setNonce_str(String nonce_str) {
            this.nonce_str = nonce_str;
        }

        public String getPrepay_id() {
            return prepay_id == null ? "" : prepay_id;
        }

        public void setPrepay_id(String prepay_id) {
            this.prepay_id = prepay_id;
        }

        public String getResult_code() {
            return result_code == null ? "" : result_code;
        }

        public void setResult_code(String result_code) {
            this.result_code = result_code;
        }

        public String getReturn_code() {
            return return_code == null ? "" : return_code;
        }

        public void setReturn_code(String return_code) {
            this.return_code = return_code;
        }

        public String getReturn_msg() {
            return return_msg == null ? "" : return_msg;
        }

        public void setReturn_msg(String return_msg) {
            this.return_msg = return_msg;
        }

        public String getSign() {
            return sign == null ? "" : sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        public String getTrade_type() {
            return trade_type == null ? "" : trade_type;
        }

        public void setTrade_type(String trade_type) {
            this.trade_type = trade_type;
        }

        @Override
        public String toString() {
            return "{" +
                    "\"appid\":\'" + appid + "\'" +
                    ", \"mch_id\":\'" + mch_id + "\'" +
                    ", \"nonce_str\":\'" + nonce_str + "\'" +
                    ", \"prepay_id\":\'" + prepay_id + "\'" +
                    ", \"result_code\":\'" + result_code + "\'" +
                    ", \"return_code\":\'" + return_code + "\'" +
                    ", \"return_msg\":\'" + return_msg + "\'" +
                    ", \"sign\":\'" + sign + "\'" +
                    ", \"trade_type\":\'" + trade_type + "\'" +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "{" +
                "\"code\":" + code +
                ", \"data\":" + data +
                ", \"msg\":\'" + msg + "\'" +
                ", \"url\":" + url +
                ", \"wait\":" + wait +
                ", \"Accessid\":" + Accessid +
                ", \"data2\":" + data2 +
                '}';
    }
}
