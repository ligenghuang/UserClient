package com.yizhitong.userclient.adapters;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.RatingBar;

import com.lgh.huanglib.util.L;
import com.lgh.huanglib.util.config.GlideUtil;
import com.yizhitong.userclient.R;
import com.yizhitong.userclient.event.DoctorDetailDto;
import com.yizhitong.userclient.net.WebUrlUtil;
import com.yizhitong.userclient.utils.data.DynamicTimeFormat;

/**
 * description: 我的评价 列表 适配器
 * autour: huang
 * date: 2019/5/30 11:00
 * update: 2019/5/30
 * version:
 */
public class EvaluationAdapter extends BaseRecyclerAdapter<DoctorDetailDto.DataBean.DocevalMVBean> {

    Context context;


    public EvaluationAdapter(Context context) {
        super(R.layout.layout_item_evaluation);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(SmartViewHolder holder, DoctorDetailDto.DataBean.DocevalMVBean model, int position) {
        holder.setIsRecyclable(false);
        holder.text(R.id.tv_item_name,model.getNickname());
        holder.text(R.id.tv_item_content,model.getThe_note());
        holder.text(R.id.tv_item_time, DynamicTimeFormat.LongToString5(model.getCreate_time_stamp()));
        ImageView imageView = holder.itemView.findViewById(R.id.iv_item_portrait);
        String portrait = model.getUse_img();
        if (!TextUtils.isEmpty(portrait)){
            if (portrait.indexOf("DOC") != -1|| portrait.indexOf("H5/Uimg") != -1) {
                GlideUtil.setImage(context, WebUrlUtil.IMG_URL + portrait, imageView, R.drawable.icon_placeholder);
                L.e("lgh", WebUrlUtil.IMG_URL + portrait);
            } else {
                GlideUtil.setImage(context, WebUrlUtil.IMG_URL + "DOC/my" + portrait, imageView, R.drawable.icon_placeholder);
                L.e("lgh", WebUrlUtil.IMG_URL + "DOC/my" + portrait);
            }
        }

        RatingBar ratingBar = holder.itemView.findViewById(R.id.item_star_rb);
        ratingBar.setRating(model.getThe_star());
//        holder.text(R.id.tv_item_star_num,model.getThe_star()+"");
    }
}
