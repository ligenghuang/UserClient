package com.yizhitong.userclient.adapters;

import android.content.Context;
import android.widget.ImageView;

import com.lgh.huanglib.util.config.GlideUtil;
import com.yizhitong.userclient.R;
import com.yizhitong.userclient.event.MyPrescriptionDto;
import com.yizhitong.userclient.net.WebUrlUtil;

public class MyPrescriptionDruyAdapter extends BaseRecyclerAdapter<MyPrescriptionDto.DataBean.DrugMVBean> {
    Context context;
    public MyPrescriptionDruyAdapter(Context context) {
        super(R.layout.layout_item_druy);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(SmartViewHolder holder, MyPrescriptionDto.DataBean.DrugMVBean model, int position) {
        holder.setIsRecyclable(false);
        ImageView imageView = holder.itemView.findViewById(R.id.iv_item_druy);
        GlideUtil.setImage(context, WebUrlUtil.IMG_URL+model.getThe_img(),imageView,0);
    }
}
