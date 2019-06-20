package com.yizhitong.userclient.adapters;

import android.view.View;
import android.widget.TextView;

import com.yizhitong.userclient.R;
import com.yizhitong.userclient.event.CityListDto;
import com.yizhitong.userclient.event.DepartListDto;

import java.util.List;

/**
 * 省市区一级列表适配器
 *
 * @author lgh
 * created at 2019/5/18 0018 16:13
 */
public class CityList1Adapter extends BaseRecyclerAdapter<CityListDto.ProvincesBean> {

    OnClickListener onClickListener;

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public CityList1Adapter() {
        super(R.layout.layout_item_depart_1);
    }

    @Override
    protected void onBindViewHolder(SmartViewHolder holder, CityListDto.ProvincesBean model, int position) {
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
                onClickListener.onClick(model.getName(),position,model.getCitys());
            }
        });

    }

    public interface OnClickListener {
        void onClick(String name, int position, List<String> list);
    }
}
