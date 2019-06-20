package com.yizhitong.userclient.adapters;

import android.view.View;

import com.yizhitong.userclient.R;
import com.yizhitong.userclient.event.DepartListDto;

/**
* description ： 省市区二级列表适配器
* author : lgh
* email : 1045105946@qq.com
* date : 2019/6/20
*/
public class CityList2Adapter extends BaseRecyclerAdapter<String> {
    OnClickListener onClickListener;

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public CityList2Adapter() {
        super(R.layout.layout_item_depart_child);
    }

    @Override
    protected void onBindViewHolder(SmartViewHolder holder, String model, int position) {
        holder.setIsRecyclable(false);
        holder.text(R.id.tv_item_name,model);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListener.onClick(model);
            }
        });
    }

    public interface OnClickListener{
        void onClick(String name);
    }
}
