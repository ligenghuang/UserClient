package com.yizhitong.userclient.utils.popup;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.lgh.huanglib.util.L;
import com.lxj.xpopup.core.BasePopupView;
import com.lxj.xpopup.impl.PartShadowPopupView;
import com.suke.widget.SwitchButton;
import com.yizhitong.userclient.R;
import com.yizhitong.userclient.adapters.SortListAdapter;
import com.yizhitong.userclient.adapters.TypeListAdapter;
import com.yizhitong.userclient.event.TypeListDto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * description ： 筛选弹窗
 * author : lgh
 * email : 1045105946@qq.com
 * date : 2019/6/20
 */
public class CustomScreenPopupView extends PartShadowPopupView {
    Context context;

    OnClickListener onClickListener;

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public CustomScreenPopupView(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.custom_screen_shadow_popup;
    }


    RecyclerView recyclerView1, recyclerView2;
    TextView mTvBtn;
    SwitchButton mSb1, mSb2;

    TypeListAdapter typeListAdapter1, typeListAdapter2;
    String the_level = "";
    String money = "";
    int isPrescription = 0;


    @Override
    protected void onCreate() {
        super.onCreate();

        mTvBtn = findViewById(R.id.tv_btn);
        mSb1 = findViewById(R.id.sb_1);
        mSb2 = findViewById(R.id.sb_2);
        recyclerView1 = findViewById(R.id.rv_level);
        recyclerView2 = findViewById(R.id.rv_money);

        typeListAdapter1 = new TypeListAdapter();
        recyclerView1.setLayoutManager(new GridLayoutManager(context, 4));
        recyclerView1.setAdapter(typeListAdapter1);

        typeListAdapter2 = new TypeListAdapter();
        recyclerView2.setLayoutManager(new GridLayoutManager(context, 4));
        recyclerView2.setAdapter(typeListAdapter2);

        initData();
        loadView();
    }

    private void loadView() {

        typeListAdapter1.setOnClickListener(new TypeListAdapter.OnClickListener() {
            @Override
            public void onClick(String name, int position) {
                for (int i = 0; i < typeListAdapter1.getAllData().size(); i++) {
                    typeListAdapter1.getAllData().get(i).setClick(i == position);
                }
                typeListAdapter1.notifyDataSetChanged();
            }
        });

        typeListAdapter2.setOnClickListener(new TypeListAdapter.OnClickListener() {
            @Override
            public void onClick(String name, int position) {
                for (int i = 0; i < typeListAdapter2.getAllData().size(); i++) {
                    typeListAdapter2.getAllData().get(i).setClick(i == position);
                }
                typeListAdapter2.notifyDataSetChanged();
            }
        });
        mSb2.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                isPrescription = isChecked ? 1 : 0;
            }
        });

        mTvBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListener.onDepartPopupClick(the_level,money,isPrescription);
            }
        });
    }


    /**
     * 设置数据 screen
     */
    private void initData() {//解析数据
        List<String> list1 = Arrays.asList(getResources().getStringArray(R.array.level_list));
        List<TypeListDto> listDtos1 = new ArrayList<>();
        for (int i = 0; i < list1.size(); i++) {
            TypeListDto typeListDto = new TypeListDto();
            typeListDto.setType(list1.get(i));
            listDtos1.add(typeListDto);
        }
        typeListAdapter1.refresh(listDtos1);

        List<String> list2 = Arrays.asList(getResources().getStringArray(R.array.money_list));
        List<TypeListDto> listDtos2 = new ArrayList<>();
        for (int i = 0; i < list2.size(); i++) {
            TypeListDto typeListDto = new TypeListDto();
            typeListDto.setType(list2.get(i));
            listDtos2.add(typeListDto);
        }
        typeListAdapter2.refresh(listDtos2);
    }


    @Override
    protected void onShow() {
        super.onShow();
    }

    @Override
    protected void onDismiss() {
        super.onDismiss();
        Log.e("tag", "CustomPartShadowPopupView onDismiss");
    }

    public interface OnClickListener {
        void onDepartPopupClick(String the_level ,String money, int isPrescription);
    }
}
