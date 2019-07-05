package com.yizhitong.userclient.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yizhitong.userclient.R;
import com.yizhitong.userclient.event.AddressListDto;
import com.yizhitong.userclient.ui.mine.AddAddressActivity;

public class AddressManagementAdapter extends BaseRecyclerAdapter<AddressListDto.DataBean> {
    boolean isEditor = false;
    Context context;
    int type;
    OnClickListenter onClickListenter;

    public void setOnClickListenter(OnClickListenter onClickListenter) {
        this.onClickListenter = onClickListenter;
    }

    public AddressManagementAdapter(Context context, int type) {
        super(R.layout.layout_item_address);
        this.context = context;
        this.type = type;
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
        int resId = 0;
        if (type == 0){
            resId = R.drawable.prescription_template1;
        }
        imageView.setImageResource(isEditor?R.drawable.selector_radio_bg:resId);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isEditor){
                    model.setClick(!model.isClick());
                    imageView.setSelected(model.isClick());
                }else {
                   if (type == 0){
                       //todo 跳转至地址管理
                       Intent intent = new Intent(context, AddAddressActivity.class);
                       intent.putExtra("iuid",model.getIUID());
                       context.startActivity(intent);
                   }else if (type == 1){
                       onClickListenter.OnClick(model);
                   }
                }
            }
        });
    }

    public interface OnClickListenter{
        void OnClick(AddressListDto.DataBean m);
    }
}
