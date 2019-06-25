package com.yizhitong.userclient.utils.popup;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.lgh.huanglib.util.L;
import com.lxj.xpopup.impl.PartShadowPopupView;
import com.yizhitong.userclient.R;
import com.yizhitong.userclient.adapters.DepartList1Adapter;
import com.yizhitong.userclient.adapters.DepartList2Adapter;
import com.yizhitong.userclient.event.DepartListDto;
import com.yizhitong.userclient.event.DepartidDto;

import java.util.ArrayList;
import java.util.List;

public class CustomDepartPopupView extends PartShadowPopupView {
    Context context;
    DepartListDto departListDto;

    OnClickListener onClickListener;
    String title= "";
    String departId;

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public CustomDepartPopupView(@NonNull Context context, DepartListDto departListDto,String title,String departId) {
        super(context);
        this.context = context;
        this.departListDto = departListDto;
        this.title = title;
        this.departId = departId;
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.custom_part_shadow_popup;
    }

    TextView mTvTitle;
    RecyclerView mRvDepart_1;
    RecyclerView mRvDepart_2;

    DepartList1Adapter departList1Adapter;
    DepartList2Adapter departList2Adapter;

    @Override
    protected void onCreate() {
        super.onCreate();
        mTvTitle = findViewById(R.id.tv_popup_title);
        mRvDepart_1 = findViewById(R.id.rv_depart_1);
        mRvDepart_2 = findViewById(R.id.rv_depart_2);

        mTvTitle.setText("已选择："+title);

        departList1Adapter = new DepartList1Adapter();
        mRvDepart_1.setLayoutManager(new LinearLayoutManager(context));
        mRvDepart_1.setAdapter(departList1Adapter);

        departList2Adapter = new DepartList2Adapter();
        mRvDepart_2.setLayoutManager(new LinearLayoutManager(context));
        mRvDepart_2.setAdapter(departList2Adapter);

        setData();
        loadView();
    }

    /**
     * 设置数据
     */
    private void setData() {
        List<DepartListDto.DataBean> list = new ArrayList<>();
        DepartListDto.DataBean dataBean = new DepartListDto.DataBean();
        dataBean.setClick(true);
        dataBean.setName("全部项目");
        dataBean.setIUID("0");
        list.add(dataBean);
        list.addAll(departListDto.getData());
        for (int i = 0; i <list.size() ; i++) {
           list.get(i).setClick(list.get(i).getIUID().equals(departId));
           if (!departId.equals("0")){
               if (list.get(i).getIUID().equals(departId)){
                   departList2Adapter.refresh(list.get(i).getDepart2());
               }
           }
        }
        departList1Adapter.refresh(list);

    }

    /**
     * 事件监听
     */
    private void loadView() {
        departList1Adapter.setOnClickListener(new DepartList1Adapter.OnClickListener() {
            @Override
            public void onClick(String id, String name, int position, List<DepartListDto.DataBean> list) {
                if (id.equals("0")){
                    onClickListener.onDepartPopupClick(id,name);
                }else {
                    departList2Adapter.refresh(list);
                }
            }
        });
        departList2Adapter.setOnClickListener(new DepartList2Adapter.OnClickListener() {
            @Override
            public void onClick(String IUID, String name) {
                onClickListener.onDepartPopupClick(IUID, name);
            }
        });
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
        void onDepartPopupClick(String id,String name);
    }
}
