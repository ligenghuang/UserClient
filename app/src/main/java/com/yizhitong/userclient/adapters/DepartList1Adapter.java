package com.yizhitong.userclient.adapters;

import android.view.View;
import android.widget.TextView;

import com.yizhitong.userclient.R;
import com.yizhitong.userclient.event.DepartListDto;
import com.yizhitong.userclient.event.DepartidDto;

import java.util.List;

/**
 * 科室列表适配器
 *
 * @author lgh
 * created at 2019/5/18 0018 16:13
 */
public class DepartList1Adapter extends BaseRecyclerAdapter<DepartListDto.DataBean> {

    OnClickListener onClickListener;

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public DepartList1Adapter() {
        super(R.layout.layout_item_depart_1);
    }

    @Override
    protected void onBindViewHolder(SmartViewHolder holder, DepartListDto.DataBean model, int position) {
        holder.setIsRecyclable(false);
        TextView textView = holder.itemView.findViewById(R.id.tv_item_depart_name);
        textView.setSelected(model.isClick());
        textView.setText(model.getName());
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < getAllData().size(); i++) {
                    getAllData().get(i).setClick(i == position);
                }
                notifyDataSetChanged();
                onClickListener.onClick(model.getIUID(),model.getName(),position,model.getDepart2());
            }
        });

    }

    public interface OnClickListener {
        void onClick(String id, String name,int position, List<DepartListDto.DataBean> list);
    }
}
