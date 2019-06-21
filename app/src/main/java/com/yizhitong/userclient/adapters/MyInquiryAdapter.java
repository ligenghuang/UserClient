package com.yizhitong.userclient.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lgh.huanglib.util.L;
import com.lgh.huanglib.util.config.GlideUtil;
import com.lgh.huanglib.util.data.ResUtil;
import com.yizhitong.userclient.R;
import com.yizhitong.userclient.event.MyInquiryDto;
import com.yizhitong.userclient.net.WebUrlUtil;
import com.yizhitong.userclient.ui.home.DoctorDetailActivity;
import com.yizhitong.userclient.ui.message.MessageDetailActivity;
import com.yizhitong.userclient.ui.physicianvisits.InquiryInfoActivity;
import com.yizhitong.userclient.ui.physicianvisits.InquiryInfoEvaluateActivity;
import com.yizhitong.userclient.ui.physicianvisits.InquiryInfoPayActivity;
import com.yizhitong.userclient.utils.data.DynamicTimeFormat;

/**
* description ： 我的问诊单列表 适配器
* author : lgh
* email : 1045105946@qq.com
* date : 2019/6/14
*/
public class MyInquiryAdapter extends BaseRecyclerAdapter<MyInquiryDto.DataBean> {
    Context context;

    public MyInquiryAdapter(Context context) {
        super(R.layout.layout_item_my_inquiry);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(SmartViewHolder holder, MyInquiryDto.DataBean model, int position) {
        holder.setIsRecyclable(false);
        int type = 0;
        ImageView userPortaitIv = holder.itemView.findViewById(R.id.iv_item_portrait);
        String portrait = model.getThe_img();
        GlideUtil.setImageCircle(context, WebUrlUtil.IMG_URL+portrait,userPortaitIv,R.drawable.icon_placeholder);
        holder.text(R.id.tv_item_prescription,model.getThe_level());
        holder.text(R.id.tv_item_inquiry_note,model.getIll_note());
        holder.text(R.id.tv_item_inquiry_time, DynamicTimeFormat.LongToString2(model.getCreate_time_stamp()));


        if(model.getPay_flag() == 0){
            //todo 待付款
            type = 0;
        }else if (model.getAsk_flag() == 0){
            //todo 待接诊
            type = 1;
        }else if (model.getAsk_flag() == 1){
            //todo 问诊中
            type = 2;
        }else if (model.getAsk_flag() == 2){
            //todo 已完成
            type = 3;
        }else if (model.getAsk_flag() == 3){
            //todo 已取消
            type = 4;
        }
        TextView typeTv = holder.itemView.findViewById(R.id.tv_item_type);
        TextView btnTv = holder.itemView.findViewById(R.id.tv_item_inquiry_btn);
        setType(type,typeTv,btnTv,model.getIsEval());
        int finalType = type;
        btnTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                L.e("lgh_onclick","type  = "+ finalType);
                Intent intent = null;
                switch (finalType){
                    case 0:
                        //todo 待付款
                        intent = new Intent(context, InquiryInfoPayActivity.class);
                        intent.putExtra("iuid",model.getAskIUID());
                        break;
                    case 3:
                        //todo 已完成
                        intent = new Intent(context, InquiryInfoEvaluateActivity.class);
                        intent.putExtra("iuid",model.getAskIUID());
                        break;
                    case 4:
                        //todo 已取消
                        intent = new Intent(context, DoctorDetailActivity.class);
                        intent.putExtra("iuid",model.getDoctorid());
                        break;
                }
                context.startActivity(intent);
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              if (finalType == 2){
                  Intent intent1 = new Intent(context, MessageDetailActivity.class);
                  intent1.putExtra("touserId",model.getUserid());
                  intent1.putExtra("askId",model.getAskIUID());
                  context.startActivity(intent1);
              }else {
                  Intent intent = new Intent(context, InquiryInfoActivity.class);
                  intent.putExtra("iuid",model.getAskIUID());
                  context.startActivity(intent);
              }
            }
        });
    }

    /**
     * 设置状态
     * @param type
     * @param typeTv
     * @param btnTv
     */
    private void setType(int type, TextView typeTv, TextView btnTv,int isEval) {
        int resId = R.string.inquity_tip_2;
        int colorId = R.color.color_e22525;
        btnTv.setVisibility(View.GONE);
        switch (type){
            case 0:
                //todo 待付款
                resId = R.string.inquity_tip_2;
                colorId = R.color.color_e22525;
                btnTv.setText(ResUtil.getString(R.string.inquity_tip_6));
                btnTv.setVisibility(View.VISIBLE);
                break;
            case 1:
                //todo 待接诊
                resId = R.string.inquity_tip_3;
                colorId = R.color.color_ff932b;
                break;
            case 2:
                //todo 问诊中
                resId = R.string.inquity_tip_4;
                colorId = R.color.color_289d23;
                break;
            case 3:
                //todo 已完成
                resId = R.string.inquity_tip_5;
                colorId = R.color.color_9;
                btnTv.setText(ResUtil.getString(R.string.inquity_tip_7));
                if (isEval == 0){
                    btnTv.setVisibility(View.VISIBLE);
                }
                break;
            case 4:
                //todo 已取消
                resId = R.string.inquity_tip_9;
                colorId = R.color.color_9;
                btnTv.setText(ResUtil.getString(R.string.inquity_tip_8));
                btnTv.setVisibility(View.VISIBLE);
                break;
        }
        typeTv.setText(ResUtil.getString(resId));
        typeTv.setTextColor(ResUtil.getColor(colorId));


    }
}
