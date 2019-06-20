package com.yizhitong.userclient.adapters;

import android.view.View;

import com.yizhitong.userclient.R;
import com.yizhitong.userclient.event.TypeListDto;

/**
* description ：
* author : lgh
* email : 1045105946@qq.com
* date : 2019/6/20
*/
public class SortListAdapter extends BaseRecyclerAdapter<TypeListDto> {
    OnClickListener onClickListener;

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public SortListAdapter() {
        super(R.layout.layout_item_sort);
    }

    @Override
    protected void onBindViewHolder(SmartViewHolder holder, TypeListDto model, int position) {
        holder.setIsRecyclable(false);
        holder.text(R.id.tv_item_name,model.getType());
        holder.itemView.findViewById(R.id.iv_item).setVisibility(model.isClick()?View.VISIBLE:View.GONE);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListener.onClick(position);
            }
        });
    }

    public interface OnClickListener{
        void onClick(int position);
    }
}
