package com.yizhitong.userclient.adapters;

import com.yizhitong.userclient.R;

public class StringAdapter2 extends BaseRecyclerAdapter<String> {
    public StringAdapter2() {
        super(R.layout.layout_item_string_list2);
    }

    @Override
    protected void onBindViewHolder(SmartViewHolder holder, String model, int position) {
        holder.setIsRecyclable(false);
        holder.text(R.id.tv_item_string,model);
    }
}
