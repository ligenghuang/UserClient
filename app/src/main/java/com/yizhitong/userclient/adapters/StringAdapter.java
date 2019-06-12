package com.yizhitong.userclient.adapters;

import com.yizhitong.userclient.R;

public class StringAdapter extends BaseRecyclerAdapter<String> {
    public StringAdapter() {
        super(R.layout.layout_item_string_list);
    }

    @Override
    protected void onBindViewHolder(SmartViewHolder holder, String model, int position) {
        holder.setIsRecyclable(false);
        holder.text(R.id.tv_item_string,model);
    }
}
