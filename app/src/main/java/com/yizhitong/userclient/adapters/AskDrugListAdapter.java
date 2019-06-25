package com.yizhitong.userclient.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.lgh.huanglib.util.data.ResUtil;
import com.yizhitong.userclient.R;
import com.yizhitong.userclient.event.AskDrugListDto;

/**
 * description:拍照开方  处方药列表 适配器
 * autour: huang
 * date: 2019/5/28 11:31
 * update: 2019/5/28
 * version:
 */
public class AskDrugListAdapter extends BaseRecyclerAdapter<AskDrugListDto.DataBean> {
    Context context;
    OnClickListener onClickListener;

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public AskDrugListAdapter(Context context) {
        super(R.layout.layout_item_photo_prescription);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(SmartViewHolder holder, AskDrugListDto.DataBean model, int position) {
        holder.setIsRecyclable(false);
        int num = position +1;
        holder.text(R.id.tv_item_title, ResUtil.getFormatString(R.string.inquity_info_tip_14,num+""));
        holder.text(R.id.tv_note,model.getThe_memo());
        RecyclerView recyclerView = holder.itemView.findViewById(R.id.rv_drug);
        AskDrugChildAdapter prescriptionDrugChildAdapter = new AskDrugChildAdapter(context);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(prescriptionDrugChildAdapter);
        prescriptionDrugChildAdapter.refresh(model.getAskDrugMV());
        TextView payTv = holder.itemView.findViewById(R.id.tv_pay);
        payTv.setText(ResUtil.getString(model.getPay_flag() != 0?R.string.inquity_tip_5:R.string.inquity_info_tip_12));
        payTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (model.getPay_flag() == 0){
                    onClickListener.onClick(model.getIUID());
                }
            }
        });
    }

    public interface OnClickListener{
        void onClick(String iuid);
    }
}
