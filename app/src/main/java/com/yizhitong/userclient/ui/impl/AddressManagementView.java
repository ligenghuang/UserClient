package com.yizhitong.userclient.ui.impl;

import com.lgh.huanglib.util.base.BaseView;
import com.yizhitong.userclient.event.AddressListDto;
import com.yizhitong.userclient.event.GeneralDto;

public interface AddressManagementView extends BaseView {

    /**
     * 获取收货地址
     */
    void getAddressList();
    void getAddressListSuccessful(AddressListDto addressListDto);

    /**
     * 删除收货地址
     * @param text
     */
    void deteleUserAddress(String text);
    void deteleUserAddressSuccessful(GeneralDto generalDto);
}
