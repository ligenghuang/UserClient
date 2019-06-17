package com.yizhitong.userclient.adapters;

import android.content.Context;
import android.widget.ImageView;

import com.lgh.huanglib.util.L;
import com.lgh.huanglib.util.config.GlideUtil;
import com.yizhitong.userclient.R;
import com.yizhitong.userclient.net.WebUrlUtil;

public class IllessImgAdapter extends BaseRecyclerAdapter<String>{

    Context context;

    public IllessImgAdapter(Context context) {
        super(R.layout.layout_item_illess_img);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(SmartViewHolder holder, String model, int position) {
       holder.setIsRecyclable(false);
        ImageView userPortaitIv = holder.itemView.findViewById(R.id.iv_img_illess);
        GlideUtil.setImage(context, WebUrlUtil.IMG_URL+model,userPortaitIv,0);
        L.e("lgh",WebUrlUtil.IMG_URL+model);
    }
}
