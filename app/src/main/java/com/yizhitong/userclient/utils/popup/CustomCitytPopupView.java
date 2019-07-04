package com.yizhitong.userclient.utils.popup;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lxj.xpopup.impl.PartShadowPopupView;
import com.yizhitong.userclient.R;
import com.yizhitong.userclient.adapters.CityList1Adapter;
import com.yizhitong.userclient.adapters.CityList2Adapter;
import com.yizhitong.userclient.adapters.DepartList1Adapter;
import com.yizhitong.userclient.adapters.DepartList2Adapter;
import com.yizhitong.userclient.event.CityListDto;
import com.yizhitong.userclient.event.DepartListDto;
import com.yizhitong.userclient.utils.data.GetJsonDataUtil;

import java.util.ArrayList;
import java.util.List;

public class CustomCitytPopupView extends PartShadowPopupView {
    Context context;

    OnClickListener onClickListener;
    String cityName = "";
    String title= "";
    CityListDto cityListDto;
    int index = 0;

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public CustomCitytPopupView(@NonNull Context context,String title,int index) {
        super(context);
        this.index = index;
        this.context = context;
        this.title = title;
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.custom_part_shadow_popup;
    }

    TextView mTvTitle;
    RecyclerView mRvDepart_1;
    RecyclerView mRvDepart_2;

    CityList1Adapter cityList1Adapter;
    CityList2Adapter cityList2Adapter;

    @Override
    protected void onCreate() {
        super.onCreate();
        mTvTitle = findViewById(R.id.tv_popup_title);
        mRvDepart_1 = findViewById(R.id.rv_depart_1);
        mRvDepart_2 = findViewById(R.id.rv_depart_2);

        mTvTitle.setText("已选择："+title);

        cityList1Adapter = new CityList1Adapter();
        mRvDepart_1.setLayoutManager(new LinearLayoutManager(context));
        mRvDepart_1.setAdapter(cityList1Adapter);

        cityList2Adapter = new CityList2Adapter();
        mRvDepart_2.setLayoutManager(new LinearLayoutManager(context));
        mRvDepart_2.setAdapter(cityList2Adapter);

        initJsonData();
        loadView();
    }


    /**
     * 事件监听
     */
    private void loadView() {
        cityList1Adapter.setOnClickListener(new CityList1Adapter.OnClickListener() {
            @Override
            public void onClick( String name, int position, List<String> list) {
                cityList2Adapter.refresh(list);
                cityName = name;
               index = position;
            }
        });
        cityList2Adapter.setOnClickListener(new CityList2Adapter.OnClickListener() {
            @Override
            public void onClick(String name) {
                cityName = cityName+"-"+name;
                onClickListener.onDepartPopupClick(cityName,name,index);
            }
        });
    }

    /**
     * 设置数据
     */
    private void initJsonData() {//解析数据
        String JsonData = new GetJsonDataUtil().getJson(context, "a.json");//获取assets目录下的json文件数据
        cityListDto = new Gson().fromJson(JsonData, new TypeToken<CityListDto>() {
        }.getType());
        cityListDto.getProvinces().get(index).setClick(true);
        cityList2Adapter.refresh(cityListDto.getProvinces().get(index).getCitys());
        cityName = cityListDto.getProvinces().get(index).getName();
        cityList1Adapter.refresh(cityListDto.getProvinces());
    }

    @Override
    protected void onShow() {
        super.onShow();
        Log.e("tag", "CustomPartShadowPopupView onShow");
    }

    @Override
    protected void onDismiss() {
        super.onDismiss();
        Log.e("tag", "CustomPartShadowPopupView onDismiss");
    }

    public interface OnClickListener {
        void onDepartPopupClick(String city,String name, int index);
    }
}
