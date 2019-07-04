package com.yizhitong.userclient.adapters;

import android.view.View;

import com.lgh.huanglib.util.L;
import com.yizhitong.userclient.R;
import com.yizhitong.userclient.event.TypeListDto;

/**
* description ：
* author : lgh
* email : 1045105946@qq.com
* date : 2019/6/20
*/
public class TypeListAdapter extends BaseRecyclerAdapter<TypeListDto> {
    OnClickListener onClickListener;

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public TypeListAdapter() {
        super(R.layout.layout_item_title_list);
    }

    @Override
    protected void onBindViewHolder(SmartViewHolder holder, TypeListDto model, int position) {
        holder.setIsRecyclable(false);
        holder.text(R.id.tv_item_string,model.getType());
        holder.itemView.setSelected(model.isClick());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (model.isClick()){
                    onClickListener.onNoClick(position);
                }else {
                    onClickListener.onClick(model.getType(),position);
                }
            }
        });
    }

    public interface OnClickListener{
        void onClick(String name,int position);
        void onNoClick(int position);
    }
}
