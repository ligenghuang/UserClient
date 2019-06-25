package com.yizhitong.userclient.adapters;

import android.view.ViewGroup;
import android.widget.ImageView;

import com.lgh.huanglib.util.config.GlideApp;
import com.yizhitong.userclient.R;

import cn.bingoogolapple.bgabanner.BGABanner;

public class Banner implements BGABanner.Adapter<ImageView, String> {


    @Override
    public void fillBannerItem(BGABanner banner, ImageView itemView, String model, int position) {
        itemView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        ViewGroup.LayoutParams params = banner.getLayoutParams();
        banner.setLayoutParams(params);
        GlideApp.with(itemView.getContext()).load(model).dontAnimate().placeholder(R.drawable.home_page1).error(R.drawable.home_page1)
                .into(itemView);
    }

}
