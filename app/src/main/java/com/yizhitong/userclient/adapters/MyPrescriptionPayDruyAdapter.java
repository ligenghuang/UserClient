package com.yizhitong.userclient.adapters;

import android.content.Context;
import android.widget.ImageView;

import com.lgh.huanglib.util.config.GlideUtil;
import com.lgh.huanglib.util.data.PriceUtils;
import com.yizhitong.userclient.R;
import com.yizhitong.userclient.event.MyPrescriptionDto;
import com.yizhitong.userclient.event.PreInfoDto;
import com.yizhitong.userclient.net.WebUrlUtil;

public class MyPrescriptionPayDruyAdapter extends BaseRecyclerAdapter<PreInfoDto.DataBean.DrugMVBean> {
    Context context;
    public MyPrescriptionPayDruyAdapter(Context context) {
        super(R.layout.layout_item_prescription_pay_druy);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(SmartViewHolder holder, PreInfoDto.DataBean.DrugMVBean model, int position) {
        holder.setIsRecyclable(false);
        ImageView imageView = holder.itemView.findViewById(R.id.iv_item_druy);
        GlideUtil.setImage(context, WebUrlUtil.IMG_URL+model.getThe_img(),imageView,0);
        holder.text(R.id.tv_item_druy_name,model.getName());
        holder.text(R.id.tv_item_druy_money,"￥"+ PriceUtils.formatPrice(model.getPrice()));
        holder.text(R.id.tv_item_druy_num,"X"+model.getDrug_num());
        holder.text(R.id.tv_item_druy_spec,model.getThe_spec());

    }
}
