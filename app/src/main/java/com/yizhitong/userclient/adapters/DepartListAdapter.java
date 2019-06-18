package com.yizhitong.userclient.adapters;

import com.yizhitong.userclient.R;
import com.yizhitong.userclient.event.DepartListDto;

public class DepartListAdapter extends BaseRecyclerAdapter<DepartListDto.DataBean> {
    public DepartListAdapter() {
        super(R.layout.layout_item_depart_list);
    }

    @Override
    protected void onBindViewHolder(SmartViewHolder holder, DepartListDto.DataBean model, int position) {
        holder.setIsRecyclable(false);
        holder.text(R.id.tv_depart_1,model.getName());
        String list = "";
        for (int i = 0; i <model.getDepart2().size() ; i++) {
            if (i > 0){
                list = list + "、";
            }
            list = list + model.getDepart2().get(i).getName();
        }
        holder.text(R.id.tv_depart_2,list);
    }
}
