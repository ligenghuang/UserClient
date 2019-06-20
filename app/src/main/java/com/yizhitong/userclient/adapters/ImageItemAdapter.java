package com.yizhitong.userclient.adapters;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.lgh.huanglib.util.L;
import com.lgh.huanglib.util.config.GlideUtil;
import com.lzy.imagepicker.bean.ImageItem;
import com.yizhitong.userclient.R;
import com.yizhitong.userclient.net.WebUrlUtil;

public class ImageItemAdapter extends BaseRecyclerAdapter<String> {
    Context context;

    OnClickListener onClickListener;
    boolean isDelete;

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public ImageItemAdapter(Context context, boolean isDelete) {
        super(R.layout.layout_item_img_rapid_interrogation);
        this.context = context;
        this.isDelete = isDelete;
    }

    @Override
    protected void onBindViewHolder(SmartViewHolder holder, String model, int position) {
        holder.setIsRecyclable(false);
        ImageView img = holder.itemView.findViewById(R.id.iv_item_img);
        GlideUtil.setImage(context, WebUrlUtil.IMG_URL + model, img, 0);
        holder.itemView.findViewById(R.id.ll_item_delete).setVisibility(isDelete ? View.VISIBLE : View.GONE);
        holder.itemView.findViewById(R.id.ll_item_delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListener.OnClick(position);
            }
        });
    }

    public interface OnClickListener {
        void OnClick(int position);
    }
}
