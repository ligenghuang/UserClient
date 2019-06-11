package com.yizhitong.userclient.ui.mine;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectChangeListener;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lgh.huanglib.util.CheckNetwork;
import com.lgh.huanglib.util.L;
import com.lgh.huanglib.util.base.ActivityStack;
import com.lgh.huanglib.util.data.ResUtil;
import com.suke.widget.SwitchButton;
import com.yizhitong.userclient.R;
import com.yizhitong.userclient.actions.AddAddressAction;
import com.yizhitong.userclient.event.AddressInfoDto;
import com.yizhitong.userclient.event.AddressListDto;
import com.yizhitong.userclient.event.GeneralDto;
import com.yizhitong.userclient.event.JsonBean;
import com.yizhitong.userclient.event.post.AddUserAddressPost;
import com.yizhitong.userclient.ui.impl.AddAddressView;
import com.yizhitong.userclient.ui.login.LoginActivity;
import com.yizhitong.userclient.utils.base.UserBaseActivity;
import com.yizhitong.userclient.utils.data.GetJsonDataUtil;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class AddAddressActivity extends UserBaseActivity<AddAddressAction> implements AddAddressView {

    @BindView(R.id.top_view)
    View topView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.f_title_tv)
    TextView titleTv;

    @BindView(R.id.et_address_name)
    EditText addressNameEt;
    @BindView(R.id.et_address_phone)
    EditText addressPhoneEt;
    @BindView(R.id.tv_address_province)
    TextView addressProvinceTv;
    @BindView(R.id.et_address)
    EditText addressEt;
    @BindView(R.id.sb_default)
    SwitchButton defaultSb;

    private ArrayList<JsonBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();

    boolean isDefault = false;

    String iuid;
    String type;

    @Override
    public int intiLayout() {
        return R.layout.activity_add_address;
    }

    @Override
    protected AddAddressAction initAction() {
        return new AddAddressAction(this, this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityStack.getInstance().addActivity(new WeakReference<>(this));
        binding();
    }

    @Override
    protected void initTitlebar() {
        super.initTitlebar();
        mImmersionBar
                .statusBarView(R.id.top_view)
                .keyboardEnable(true)
                .statusBarDarkFont(true, 0.2f)
                .addTag("AddAddressActivity")  //给上面参数打标记，以后可以通过标记恢复
                .navigationBarWithKitkatEnable(false)
                .init();
        toolbar.setNavigationOnClickListener(view -> finish());
        titleTv.setText(ResUtil.getString(R.string.address_tip_12));
    }

    @Override
    protected void init() {
        super.init();
        mContext = this;
        mActicity = this;
        iuid = getIntent().getStringExtra("iuid");

        if (!TextUtils.isEmpty(iuid)) {
            getUserAddByIuid(iuid);
            type = "1";
        }else {
            type = "0";
        }

        loadView();
    }

    @Override
    protected void loadView() {
        super.loadView();
        defaultSb.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                //TODO 是否设为默认地址
                isDefault = isChecked;
            }
        });
    }

    @OnClick({R.id.tv_address_province, R.id.tv_btn})
    void OnClick(View view) {
        switch (view.getId()) {
            case R.id.tv_address_province:
                //todo 选择地区
                hideInput();
                setCityPickerView();
                break;
            case R.id.tv_btn:
                //todo 保存地址
                submit();
                break;

        }
    }

    private void submit() {
        AddUserAddressPost addUserAddressPost = new AddUserAddressPost();
        addUserAddressPost.setType(type);
        if (TextUtils.isEmpty(addressNameEt.getText().toString())) {
            //todo 收货人为空
            showNormalToast(ResUtil.getString(R.string.address_tip_13));
            return;
        }
        addUserAddressPost.setName(addressNameEt.getText().toString());

        if (TextUtils.isEmpty(addressPhoneEt.getText().toString())) {
            //todo 联系电话为空
            showNormalToast(ResUtil.getString(R.string.address_tip_14));
            return;
        }
        addUserAddressPost.setPhone(addressPhoneEt.getText().toString());

        if (TextUtils.isEmpty(addressProvinceTv.getText().toString())) {
            //todo 所在地区为空
            showNormalToast(ResUtil.getString(R.string.address_tip_15));
            return;
        }
        addUserAddressPost.setCityPicker(addressProvinceTv.getText().toString());
//        addUserAddressPost.setCityPicker("北京 丰台区 ");

        if (TextUtils.isEmpty(addressEt.getText().toString())) {
            //todo 详细地址为空
            showNormalToast(ResUtil.getString(R.string.address_tip_16));
            return;
        }
        addUserAddressPost.setTheAdd(addressEt.getText().toString());

        addUserAddressPost.setDefaultFlag(isDefault ? "1" : "0");
        addUserAddressPost.setIuid(iuid);
        addUserAddress(addUserAddressPost);
    }


    @Override
    public void addUserAddress(AddUserAddressPost addUserAddressPost) {
        if (CheckNetwork.checkNetwork2(mContext)) {
            loadDialog();
            baseAction.addUserAddress(addUserAddressPost);
        }
    }

    @Override
    public void addUserAddressSuccessful(GeneralDto generalDto) {
        loadDiss();
        showNormalToast(generalDto.getMsg());
        finish();
    }

    @Override
    public void getUserAddByIuid(String iuid) {
        if (CheckNetwork.checkNetwork2(mContext)) {
            loadDialog();
            baseAction.getUserAddByIuid(iuid);
        }
    }

    @Override
    public void getUserAddByIuidSuccessful(AddressInfoDto addressInfoDto) {
        loadDiss();
        AddressInfoDto.DataBean dataBean = addressInfoDto.getData();
        addressNameEt.setText(dataBean.getName());
        addressProvinceTv.setText(dataBean.getProvince()+" "+dataBean.getCity()+" "+dataBean.getZone());
        addressPhoneEt.setText(dataBean.getPhone());
        addressEt.setText(dataBean.getThe_add());
        defaultSb.setChecked(dataBean.getDefault_flag() == 1);
    }

    @Override
    public void onError(String message, int code) {
        loadDiss();
        showNormalToast(message);
    }

    @Override
    public void onLigonError() {
        loadDiss();
        jumpActivity(mContext, LoginActivity.class);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (baseAction != null) {
            baseAction.toRegister();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (baseAction != null) {
            baseAction.toUnregister();
        }
    }

    /**
     * 选择城市
     */
    public void setCityPickerView() {
        initJsonData(this);
        OptionsPickerView pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String tx = options1Items.get(options1).getPickerViewText() + " " +
                        options2Items.get(options1).get(options2) + " " +
                        options3Items.get(options1).get(options2).get(options3);
                String str = tx.replaceAll("市", " ");
                String str1 = str.replaceAll("省", " ");
                addressProvinceTv.setText(str1);
            }
        }).setOptionsSelectChangeListener(new OnOptionsSelectChangeListener() {
            @Override
            public void onOptionsSelectChanged(int options1, int options2, int options3) {
                String tx = options1Items.get(options1).getPickerViewText() +
                        options2Items.get(options1).get(options2) +
                        options3Items.get(options1).get(options2).get(options3);
                String str = tx.replaceAll("市", " ");
                String str1 = str.replaceAll("省", " ");
                addressProvinceTv.setText(str1);
            }
        })
                .setTitleText("选择收货地址")
                .setTitleSize(15)
                .setSubCalSize(15)
                .setCancelColor(getResources().getColor(R.color.textcolor_2))// TODO: 2018/11/5 取消的颜色
                .setSubmitColor(Color.BLACK)// TODO: 2018/11/5 确认的颜色
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(15)
                .build();

        pvOptions.setPicker(options1Items, options2Items, options3Items);//三级选择器
        pvOptions.show();
    }

    private void initJsonData(Context context) {//解析数据

        /**
         * 注意：assets 目录下的Json文件仅供参考，实际使用可自行替换文件
         * 关键逻辑在于循环体
         *
         * */
        String JsonData = new GetJsonDataUtil().getJson(context, "province.json");//获取assets目录下的json文件数据
        L.e("lgh_address", "JsonData  = " + JsonData);
        ArrayList<JsonBean> jsonBean = new Gson().fromJson(JsonData, new TypeToken<ArrayList<JsonBean>>() {
        }.getType());
        L.e("lgh_address", "jsonBean  = " + jsonBean.size());
        /**
         * 添加省份数据
         *
         * 注意：如果是添加的JavaBean实体，则实体类需要实现 IPickerViewData 接口，
         * PickerView会通过getPickerViewText方法获取字符串显示出来。
         */
        options1Items = jsonBean;

        for (int i = 0; i < jsonBean.size(); i++) {//遍历省份
            ArrayList<String> CityList = new ArrayList<>();//该省的城市列表（第二级）
            ArrayList<ArrayList<String>> Province_AreaList = new ArrayList<>();//该省的所有地区列表（第三极）

            for (int c = 0; c < jsonBean.get(i).getCityList().size(); c++) {//遍历该省份的所有城市
                String CityName = jsonBean.get(i).getCityList().get(c).getName();
                CityList.add(CityName);//添加城市
                ArrayList<String> City_AreaList = new ArrayList<>();//该城市的所有地区列表

                //如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
                if (jsonBean.get(i).getCityList().get(c).getArea() == null
                        || jsonBean.get(i).getCityList().get(c).getArea().size() == 0) {
                    City_AreaList.add("");
                } else {
                    City_AreaList.addAll(jsonBean.get(i).getCityList().get(c).getArea());
                }
                Province_AreaList.add(City_AreaList);//添加该省所有地区数据
            }

            /**
             * 添加城市数据
             */
            options2Items.add(CityList);

            /**
             * 添加地区数据
             */
            options3Items.add(Province_AreaList);
        }
    }

}
