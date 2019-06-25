package com.yizhitong.userclient.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lgh.huanglib.util.L;
import com.lgh.huanglib.util.config.GlideUtil;
import com.lgh.huanglib.util.cusview.richtxtview.XRichText;
import com.lgh.huanglib.util.data.IsFastClick;
import com.yizhitong.userclient.R;
import com.yizhitong.userclient.event.NewsBytheClassDto;
import com.yizhitong.userclient.net.WebUrlUtil;
import com.yizhitong.userclient.ui.home.NewsDetailActivity;
import com.yizhitong.userclient.utils.Util;
import com.zzhoujay.richtext.RichText;
import com.zzhoujay.richtext.callback.OnUrlClickListener;

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
        holder.text(R.id.tv_item_title,model.getThe_title());
        XRichText xRichText = holder.itemView.findViewById(R.id.tv_item_content);
        xRichText.text(Util.replaceAll(Util.toUtf8(model.getThe_note())));
        L.e("lgh_note1",Util.replaceAll(Util.toUtf8(model.getThe_note())));

//        holder.text(R.id.tv_item_content, Util.replaceAll2(Util.replaceAll(Util.toUtf8(model.getThe_note()))));
        ImageView imageView = holder.itemView.findViewById(R.id.iv_item_news);
        GlideUtil.setImage(context, WebUrlUtil.IMG_URL+model.getThe_img(),imageView,0);
        xRichText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, NewsDetailActivity.class);
                intent.putExtra("iuid",model.getIUID());
                context.startActivity(intent);
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (IsFastClick.isFastClick()){
                    Intent intent = new Intent(context, NewsDetailActivity.class);
                    intent.putExtra("iuid",model.getIUID());
                    context.startActivity(intent);
//                }
            }
        });
    }
}
