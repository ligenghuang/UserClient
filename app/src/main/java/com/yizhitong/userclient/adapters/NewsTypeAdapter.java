package com.yizhitong.userclient.adapters;

import com.yizhitong.userclient.R;
import com.yizhitong.userclient.event.NewsTypeDto;
/**
* description ： 首页新闻类别
* author : lgh
* email : 1045105946@qq.com
* date : 2019/6/18
*/
public class NewsTypeAdapter extends BaseRecyclerAdapter<NewsTypeDto.DataBean> {
    public NewsTypeAdapter() {
        super(R.layout.layout_item_new_type);
    }

    @Override
    protected void onBindViewHolder(SmartViewHolder holder, NewsTypeDto.DataBean model, int position) {
        holder.setIsRecyclable(false);
        holder.text(R.id.tv_item_type,model.getName());
        holder.itemView.setSelected(model.isClick());
    }
}
