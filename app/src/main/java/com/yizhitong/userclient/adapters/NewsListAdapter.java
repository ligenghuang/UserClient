package com.yizhitong.userclient.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.lgh.huanglib.util.config.GlideUtil;
import com.yizhitong.userclient.R;
import com.yizhitong.userclient.event.NewsBytheClassDto;
import com.yizhitong.userclient.net.WebUrlUtil;
import com.yizhitong.userclient.ui.home.HomeFragment;
import com.yizhitong.userclient.ui.home.NewsDetailActivity;

/**
 * description ： 首页新闻列表 适配器
 * author : lgh
 * email : 1045105946@qq.com
 * date : 2019/6/18
 */
public class NewsListAdapter extends BaseRecyclerAdapter<NewsBytheClassDto.DataBean> {
    Context context;

    public NewsListAdapter(Context context) {
        super(R.layout.layout_item_news);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(SmartViewHolder holder, NewsBytheClassDto.DataBean model, int position) {
        holder.setIsRecyclable(false);
        holder.text(R.id.tv_item_title, model.getThe_title());
//        XRichText xRichText = holder.itemView.findViewById(R.id.tv_item_content);
//        xRichText.text(AppUtil.replaceAll(AppUtil.toUtf8(model.getThe_note())));
//        L.e("lgh_note1",AppUtil.replaceAll(AppUtil.toUtf8(model.getThe_note())));

        holder.text(R.id.tv_item_content, model.getShow_text());
        ImageView imageView = holder.itemView.findViewById(R.id.iv_item_news);
        GlideUtil.setImage(context, WebUrlUtil.IMG_URL + model.getThe_img(), imageView, 0);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (IsFastClick.isFastClick()){
                HomeFragment.isJumpNewsDetail = true;
                Intent intent = new Intent(context, NewsDetailActivity.class);
                intent.putExtra("iuid", model.getIUID());
                context.startActivity(intent);
//                }
            }
        });
    }
}
