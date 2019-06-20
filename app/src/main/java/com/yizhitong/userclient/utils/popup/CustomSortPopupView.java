package com.yizhitong.userclient.utils.popup;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lgh.huanglib.util.L;
import com.lxj.xpopup.impl.PartShadowPopupView;
import com.yizhitong.userclient.R;
import com.yizhitong.userclient.adapters.CityList1Adapter;
import com.yizhitong.userclient.adapters.CityList2Adapter;
import com.yizhitong.userclient.adapters.SortListAdapter;
import com.yizhitong.userclient.event.CityListDto;
import com.yizhitong.userclient.event.TypeListDto;
import com.yizhitong.userclient.utils.data.GetJsonDataUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
* description ： 排序弹窗
* author : lgh
* email : 1045105946@qq.com
* date : 2019/6/20
*/
public class CustomSortPopupView extends PartShadowPopupView {
    Context context;

    OnClickListener onClickListener;

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public CustomSortPopupView(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.custom_sort_shadow_popup;
    }


    RecyclerView mRvSort;
    SortListAdapter sortListAdapter;


    @Override
    protected void onCreate() {
        super.onCreate();

        mRvSort = findViewById(R.id.rv_sort);


        sortListAdapter = new SortListAdapter();
        mRvSort.setLayoutManager(new LinearLayoutManager(context));
        mRvSort.setAdapter(sortListAdapter);

        initData();
        loadView();
    }


    /**
     * 事件监听
     */
    private void loadView() {
        sortListAdapter.setOnClickListener(new SortListAdapter.OnClickListener() {
            @Override
            public void onClick( int position) {
                for (int i = 0; i <sortListAdapter.getAllData().size() ; i++) {
                    sortListAdapter.getAllData().get(i).setClick(i==position);
                }
                int id = position+1;
                onClickListener.onDepartPopupClick(id+"");
                sortListAdapter.notifyDataSetChanged();
            }
        });

    }

    /**
     * 设置数据 screen
     */
    private void initData() {//解析数据
        List<String> list = Arrays.asList(getResources().getStringArray(R.array.sort_list));
        List<TypeListDto> listDtos = new ArrayList<>();
        for (int i = 0; i <list.size() ; i++) {
            TypeListDto typeListDto = new TypeListDto();
            typeListDto.setType(list.get(i));
            listDtos.add(typeListDto);
        }
        sortListAdapter.refresh(listDtos);
    }

    @Override
    protected void onShow() {
        super.onShow();
        Log.e("tag", "CustomPartShadowPopupView onShow");
        L.e("lgh_show",sortListAdapter.mList.toString());
    }

    @Override
    protected void onDismiss() {
        super.onDismiss();
        Log.e("tag", "CustomPartShadowPopupView onDismiss");
    }

    public interface OnClickListener {
        void onDepartPopupClick(String id);
    }
}
