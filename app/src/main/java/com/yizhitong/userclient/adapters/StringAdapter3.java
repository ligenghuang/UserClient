package com.yizhitong.userclient.adapters;

import android.view.View;

import com.lgh.huanglib.util.L;
import com.yizhitong.userclient.R;
import com.yizhitong.userclient.event.NoteListDto;

public class StringAdapter3 extends BaseRecyclerAdapter<NoteListDto> {
    public StringAdapter3() {
        super(R.layout.layout_item_string_list3);
    }

    @Override
    protected void onBindViewHolder(SmartViewHolder holder, NoteListDto model, int position) {
        holder.setIsRecyclable(false);
        holder.text(R.id.tv_item_string,model.getNote());
        holder.itemView.setSelected(model.isClick());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                model.setClick(!model.isClick());
                holder.itemView.setSelected(model.isClick());
                L.e("lgh_array",model.toString());
            }
        });
    }
}
