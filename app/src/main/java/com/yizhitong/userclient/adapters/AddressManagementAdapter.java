package com.yizhitong.userclient.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yizhitong.userclient.R;
import com.yizhitong.userclient.event.AddressListDto;
import com.yizhitong.userclient.ui.mine.AddAddressActivity;
import com.yizhitong.userclient.utils.Util;

public class AddressManagementAdapter extends BaseRecyclerAdapter<AddressListDto.DataBean> {
    boolean isEditor = false;
    Context context;
    public AddressManagementAdapter(Context context) {
        super(R.layout.layout_item_address);
        this.context = context;
    }

    public void setEditor(boolean isEditor){
        this.isEditor = isEditor;
        notifyDataSetChanged();
    }

    @Override
    protected void onBindViewHolder(SmartViewHolder holder, AddressListDto.DataBean model, int position) {
        holder.setIsRecyclable(false);
        holder.text(R.id.tv_item_address_name, model.getName());
        holder.text(R.id.tv_item_address, model.getUserAddress());
        holder.text(R.id.tv_item_address_phone, model.getPhone());
        TextView textView = holder.itemView.findViewById(R.id.tv_item_address_default);
        textView.setVisibility(model.getDefault_flag() == 1 ? View.VISIBLE : View.GONE);
        if (!isEditor){
            model.setClick(false);
        }
        ImageView imageView = holder.itemView.findViewById(R.id.iv_item);
        imageView.setImageResource(isEditor?R.drawable.selector_radio_bg:R.drawable.prescription_template1);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isEditor){
                    model.setClick(!model.isClick());
                    imageView.setSelected(model.isClick());
                }else {
                    //todo 跳转至地址管理
                    Intent intent = new Intent(context, AddAddressActivity.class);
                    intent.putExtra("iuid",model.getIUID());
                    context.startActivity(intent);
                }
            }
        });
    }
}
