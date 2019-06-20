package com.yizhitong.userclient.event;

import java.util.ArrayList;
import java.util.List;

public class CityListDto {

    private List<ProvincesBean> provinces;

    public List<ProvincesBean> getProvinces() {
        return provinces;
    }

    public void setProvinces(List<ProvincesBean> provinces) {
        this.provinces = provinces;
    }

    public static class ProvincesBean {
        /**
         * name : 全国
         * citys : ["全国"]
         */

        private String name;
        private List<String> citys;
        private boolean isClick;

        public String getName() {
            return name == null ? "" : name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<String> getCitys() {
            if (citys == null) {
                return new ArrayList<>();
            }
            return citys;
        }

        public void setCitys(List<String> citys) {
            this.citys = citys;
        }

        public boolean isClick() {
            return isClick;
        }

        public void setClick(boolean click) {
            isClick = click;
        }
    }
}
