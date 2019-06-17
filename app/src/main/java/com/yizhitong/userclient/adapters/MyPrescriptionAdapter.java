package com.yizhitong.userclient.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lgh.huanglib.util.config.GlideUtil;
import com.lgh.huanglib.util.data.IsFastClick;
import com.lgh.huanglib.util.data.PriceUtils;
import com.lgh.huanglib.util.data.ResUtil;
import com.yizhitong.userclient.R;
import com.yizhitong.userclient.event.MyPrescriptionDto;
import com.yizhitong.userclient.ui.mine.PrescriptionInfoActivity;
/**
* description ： 处方列表适配器
* author : lgh
* email : 1045105946@qq.com
* date : 2019/6/13
*/
public class MyPrescriptionAdapter extends BaseRecyclerAdapter<MyPrescriptionDto.DataBean>{
    Context context;
    OnClickListener onClickListener;

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public MyPrescriptionAdapter(Context context) {
        super(R.layout.layout_item_my_prescription);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(SmartViewHolder holder, MyPrescriptionDto.DataBean model, int position) {
        holder.setIsRecyclable(false);
        ImageView userPortaitIv = holder.itemView.findViewById(R.id.iv_item_prescription);
        GlideUtil.setImageCircle(context, model.getDoctorImg(), userPortaitIv, R.drawable.icon_placeholder);
        holder.text(R.id.tv_item_prescription_name, model.getDoctorName());
        holder.text(R.id.tv_item_prescription_level, "("+model.getThe_level()+")");
        holder.text(R.id.tv_item_money, ResUtil.getFormatString(R.string.prescription_tip_3, model.getDrugMV().size()+"",PriceUtils.formatPrice(model.getDrug_money())));

        RecyclerView recyclerView = holder.itemView.findViewById(R.id.rv_drug);
        MyPrescriptionDruyAdapter myPrescriptionDruyAdapter = new MyPrescriptionDruyAdapter(context);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(myPrescriptionDruyAdapter);
        myPrescriptionDruyAdapter.refresh(model.getDrugMV());

        TextView flagTv = holder.itemView.findViewById(R.id.tv_item_prescription_flag);
        TextView deleteTv = holder.itemView.findViewById(R.id.tv_item_delete);
        TextView buyTv = holder.itemView.findViewById(R.id.tv_item_buy);

        int type = 0;
        if (model.getReback_flag() == 1){
            //todo 已取消
            type = 0;
        }else if (model.getFinish_flag() == 1){
            //todo 已完成
            type = 1;
        }else if (model.getPay_flag() == 1){
            //todo 待发货
            type = 2;
        }else if (model.getPay_flag() == 0){
            //todo 待付款
            type = 3;
        }

        setFlag(flagTv,buyTv,type);

        deleteTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListener.delete(model.getAskdrugheadid());
            }
        });

        buyTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PrescriptionInfoActivity.class);
                intent.putExtra("iuid",model.getAskdrugheadid());
                context.startActivity(intent);
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PrescriptionInfoActivity.class);
                intent.putExtra("iuid",model.getAskdrugheadid());
                context.startActivity(intent);
            }
        });
    }

    private void setFlag(TextView flagTv, TextView buyTv, int type) {
        buyTv.setVisibility(View.GONE);
        int resid = R.string.prescription_tip_4;
        int colorId = R.color.color_e22525;
        switch (type){
            case 0:
                resid = R.string.prescription_tip_7;
                colorId = R.color.color_289d23;
                break;
            case 1:
                resid = R.string.prescription_tip_6;
                colorId = R.color.color_289d23;
                break;
            case 2:
                resid = R.string.prescription_tip_5;
                colorId = R.color.color_ff932b;
                break;
            case 3:
                buyTv.setVisibility(View.VISIBLE);
                resid = R.string.prescription_tip_4;
                colorId = R.color.color_e22525;
                break;
        }
        flagTv.setText(ResUtil.getString(resid));
        flagTv.setTextColor(ResUtil.getColor(colorId));

    }

    public interface OnClickListener{
        void delete(String iuid);
    }


}
