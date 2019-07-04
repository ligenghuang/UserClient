package com.yizhitong.userclient.adapters;

import android.view.View;

import com.yizhitong.userclient.R;
import com.yizhitong.userclient.event.DepartListDto;
import com.yizhitong.userclient.event.DepartidDto;


public class DepartList2Adapter extends BaseRecyclerAdapter<DepartListDto.DataBean> {
    OnClickListener onClickListener;

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public DepartList2Adapter() {
        super(R.layout.layout_item_depart_child);
    }

    @Override
    protected void onBindViewHolder(SmartViewHolder holder, DepartListDto.DataBean model, int position) {
        holder.setIsRecyclable(false);
        holder.text(R.id.tv_item_name,model.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListener.onClick(model.getIUID(),model.getName(),model.getParentid());
            }
        });
    }

    public interface OnClickListener{
        void onClick(String IUID, String name,String departId);
    }
}
