package com.yizhitong.userclient.ui.impl;

import com.lgh.huanglib.util.base.BaseView;
import com.yizhitong.userclient.event.AddressInfoDto;
import com.yizhitong.userclient.event.GeneralDto;
import com.yizhitong.userclient.event.post.AddUserAddressPost;

public interface AddAddressView extends BaseView {

    void addUserAddress(AddUserAddressPost addUserAddressPost);
    void addUserAddressSuccessful(GeneralDto generalDto);

    void getUserAddByIuid(String iuid);
    void getUserAddByIuidSuccessful(AddressInfoDto addressInfoDto);
}
