package com.yizhitong.userclient.adapters;

import com.yizhitong.userclient.R;
import com.yizhitong.userclient.event.PreInfoDto;

public class PrescriptionInfoDruyAdapter extends BaseRecyclerAdapter<PreInfoDto.DataBean.DrugMVBean> {

    public PrescriptionInfoDruyAdapter() {
        super(R.layout.layout_item_prescription_info_druy);
    }

    @Override
    protected void onBindViewHolder(SmartViewHolder holder, PreInfoDto.DataBean.DrugMVBean model, int position) {
        holder.setIsRecyclable(false);
        int num = position + 1;
        holder.text(R.id.tv_item_druy_name,num+"、"+model.getName());
        holder.text(R.id.tv_item_druy_spec,model.getThe_spec());
        holder.text(R.id.tv_item_druy_num,model.getDrug_num()+"");
        holder.text(R.id.tv_item_druy_note,model.getNum_note());
    }
}
