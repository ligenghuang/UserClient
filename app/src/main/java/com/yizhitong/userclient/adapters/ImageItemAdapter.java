package com.yizhitong.userclient.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.lgh.huanglib.util.L;
import com.lgh.huanglib.util.config.GlideUtil;
import com.lxj.xpopup.interfaces.XPopupImageLoader;
import com.lzy.imagepicker.bean.ImageItem;
import com.yizhitong.userclient.R;
import com.yizhitong.userclient.net.WebUrlUtil;
import com.yizhitong.userclient.utils.popup.CusViewXPopup;

import java.io.File;

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
        //todo 查看大图
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new CusViewXPopup.Builder(context)
                        .atView(img)
                        .asImageViewer(img, WebUrlUtil.IMG_URL + model, false,-1,-1,-1,false,new ImageLoader())
                        .show();
            }
        });
    }

    public static class ImageLoader implements XPopupImageLoader {
        @Override
        public void loadImage(int position, @NonNull Object url, @NonNull ImageView imageView) {
            //必须指定Target.SIZE_ORIGINAL，否则无法拿到原图，就无法享用天衣无缝的动画
            Glide.with(imageView).load(url).apply(new RequestOptions().placeholder(R.mipmap.ic_launcher_round).override(Target.SIZE_ORIGINAL)).into(imageView);
        }

        @Override
        public File getImageFile(@NonNull Context context, @NonNull Object uri) {
            try {
                return Glide.with(context).downloadOnly().load(uri).submit().get();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    public interface OnClickListener {
        void OnClick(int position);
    }
}
