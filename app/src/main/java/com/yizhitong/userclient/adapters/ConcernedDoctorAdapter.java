package com.yizhitong.userclient.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;

import com.lgh.huanglib.util.L;
import com.lgh.huanglib.util.config.GlideUtil;
import com.lgh.huanglib.util.data.ResUtil;
import com.yizhitong.userclient.R;
import com.yizhitong.userclient.event.ConcernedDoctorListDto;
import com.yizhitong.userclient.net.WebUrlUtil;
import com.yizhitong.userclient.ui.home.DoctorDetailActivity;

public class ConcernedDoctorAdapter extends BaseRecyclerAdapter<ConcernedDoctorListDto.DataBean> {
    Context context;

    public ConcernedDoctorAdapter(Context context) {
        super(R.layout.layout_item_concerned_doctor);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(SmartViewHolder holder, ConcernedDoctorListDto.DataBean model, int position) {
        holder.setIsRecyclable(false);
        holder.text(R.id.tv_item_doctor_name, model.getName());
        holder.text(R.id.tv_item_doctor_level, model.getThe_level());
        holder.text(R.id.tv_item_doctor_spec, ResUtil.getFormatString(R.string.concerned_doctor_tip_3,model.getThe_spec()));
        holder.text(R.id.tv_item_doctor_fact_price,"￥"+model.getFact_price());
        holder.text(R.id.tv_item_hospital,model.getHospital());
        RatingBar ratingBar = holder.itemView.findViewById(R.id.item_star_rb);
        ratingBar.setRating((float) model.getThe_star());
        holder.text(R.id.tv_item_star_num,model.getThe_star()+"");
        ImageView imageView = holder.itemView.findViewById(R.id.iv_item_doctor);
        String portrait = model.getThe_img();
        GlideUtil.setImage(context, WebUrlUtil.IMG_URL + portrait, imageView, R.drawable.icon_placeholder);

        holder.itemView.findViewById(R.id.tv_item_doctor_consulting).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DoctorDetailActivity.class);
                intent.putExtra("iuid",model.getIUID());
                context.startActivity(intent);
            }
        });

    }
}
