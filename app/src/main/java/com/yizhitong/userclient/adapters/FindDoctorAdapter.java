package com.yizhitong.userclient.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;

import com.lgh.huanglib.util.config.GlideUtil;
import com.lgh.huanglib.util.data.PriceUtils;
import com.lgh.huanglib.util.data.ResUtil;
import com.yizhitong.userclient.R;
import com.yizhitong.userclient.event.FindDoctorDto;
import com.yizhitong.userclient.net.WebUrlUtil;
import com.yizhitong.userclient.ui.home.DoctorDetailActivity;

/**
* description ： 医生列表 适配器
* author : lgh
* email : 1045105946@qq.com
* date : 2019/6/20
*/
public class FindDoctorAdapter extends BaseRecyclerAdapter<FindDoctorDto.DataBean> {
    Context context;
    public FindDoctorAdapter(Context context) {
        super(R.layout.layout_item_find_doctor);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(SmartViewHolder holder, FindDoctorDto.DataBean model, int position) {
        holder.setIsRecyclable(false);
        holder.text(R.id.tv_item_doctor_name,model.getName());
        holder.text(R.id.tv_item_doctor_level,model.getThe_level());
        holder.text(R.id.tv_item_doctor_hospital,model.getHospital());
        holder.text(R.id.tv_item_doctor_spec,model.getThe_spec());
        holder.text(R.id.tv_item_doctor_money, ResUtil.getFormatString(R.string.search_doctor_tip_13, PriceUtils.formatPrice(model.getFact_price())));
        holder.text(R.id.tv_item_doctor_prescribe,ResUtil.getString(model.getIsPrescribe()==1?R.string.search_doctor_tip_10:R.string.search_doctor_tip_11));
        holder.text(R.id.tv_star_num,model.getThe_star()+"");
        RatingBar ratingBar = holder.itemView.findViewById(R.id.star_rb);
        ratingBar.setRating(model.getThe_star());
        ImageView imageView = holder.itemView.findViewById(R.id.iv_item_doctor_img);
        GlideUtil.setImage(context, WebUrlUtil.IMG_URL+model.getThe_img(),imageView,R.drawable.icon_placeholder);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DoctorDetailActivity.class);
                intent.putExtra("iuid",model.getIUID());
                context.startActivity(intent);
            }
        });
    }
}
