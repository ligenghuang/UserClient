package com.yizhitong.userclient.adapters;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.lgh.huanglib.util.L;
import com.lgh.huanglib.util.config.GlideUtil;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.core.ImageViewerPopupView;
import com.lxj.xpopup.enums.PopupAnimation;
import com.lxj.xpopup.enums.PopupPosition;
import com.lxj.xpopup.enums.PopupType;
import com.lxj.xpopup.interfaces.OnSelectListener;
import com.lxj.xpopup.interfaces.XPopupImageLoader;
import com.yizhitong.userclient.R;
import com.yizhitong.userclient.event.MessageDetailListDto;
import com.yizhitong.userclient.net.WebUrlUtil;
import com.yizhitong.userclient.ui.message.ImageDetailActivity;
import com.yizhitong.userclient.utils.data.DynamicTimeFormat;
import com.yizhitong.userclient.utils.popup.CusViewXPopup;

import java.io.File;


/**
 * description:消息详情 列表适配器
 * autour: huang
 * date: 2019/5/23 16:17
 * update: 2019/5/23
 * version:
 */
public class MessageDetailListAdapter extends BaseRecyclerAdapter<MessageDetailListDto.DataBean.ListBean> {

    Context context;
    String touserid;

    public MessageDetailListAdapter(Context context, String touserid) {
        super(R.layout.layout_item_message_detail);
        this.context = context;
        this.touserid = touserid;
    }

    @Override
    protected void onBindViewHolder(SmartViewHolder holder, MessageDetailListDto.DataBean.ListBean model, int position) {
        holder.setIsRecyclable(false);
        RelativeLayout rightRl = holder.itemView.findViewById(R.id.rl_right);
        RelativeLayout leftRl = holder.itemView.findViewById(R.id.rl_left);
        rightRl.setVisibility(View.GONE);
        leftRl.setVisibility(View.GONE);
        if (!model.getUserid().equals(touserid)) {
            //todo 发送者
            rightRl.setVisibility(View.VISIBLE);
            holder.text(R.id.tv_right_time, DynamicTimeFormat.format(model.getChat_time_stamp()));
            setImage(model.getUserIMG(), holder.itemView.findViewById(R.id.iv_right));
            ImageView rightIv = holder.itemView.findViewById(R.id.iv_right_content);
            TextView rightTv = holder.itemView.findViewById(R.id.tv_right_content);
            setContent(model.getChat_note(), model.getThe_class(), rightIv, rightTv);
//            showXPopup(rightTv);
        } else {
            leftRl.setVisibility(View.VISIBLE);
            L.e("lgh_time", "time1 = " + model.getChat_time_stamp());
            holder.text(R.id.tv_left_time, DynamicTimeFormat.format(model.getChat_time_stamp()));
            setImage(model.getUserIMG(), holder.itemView.findViewById(R.id.iv_left));
            ImageView leftIv = holder.itemView.findViewById(R.id.iv_left_content);
            TextView leftTv = holder.itemView.findViewById(R.id.tv_left_content);
            setContent(model.getChat_note(), model.getThe_class(), leftIv, leftTv);
//            showXPopup(leftTv);

        }
    }

    private void jumpImageDetail(String url, ImageView imageView) {
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new CusViewXPopup.Builder(context)
                        .atView(imageView)
                        .asImageViewer(imageView, url, false,-1,-1,-1,false,new ImageLoader())
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

    /**
     * 弹窗
     *
     * @param rightTv
     */
    private void showXPopup(TextView rightTv) {
        // 必须在事件发生前，调用这个方法来监视View的触摸
//        final CusviewXPopup.Builder builder = new CusviewXPopup.Builder(context)
//                .watchView(rightTv);
//        rightTv.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                builder.asAttachList(new String[]{"复制", "删除"}, null,
//                        new OnSelectListener() {
//                            @Override
//                            public void onSelect(int position, String text) {
//                                L.e("lgh_message", "onSelect    = " + position + "   +   " + text);
//                                switch (position){
//                                    case 0:
//                                        //todo 复制
//                                        ClipboardManager cmb = (ClipboardManager) context.getSystemService(CLIPBOARD_SERVICE);
//                                        cmb.setText(rightTv.getText().toString());
//                                        break;
//                                    case 1:
//                                        //todo 删除
//                                        break;
//                                }
//                            }
//                        })
//                        .show();
//                return false;
//            }
//        });
    }


    private void setContent(String chat_note, int read_flag, ImageView rightIv, TextView rightTv) {
        L.e("lgh_message", "read_flag  =" + read_flag);
        L.e("lgh_message", "chat_note  =" + chat_note);
        rightIv.setVisibility(View.GONE);
        rightTv.setVisibility(View.GONE);
        switch (read_flag) {
            case 0:
                //todo 文字
                rightTv.setVisibility(View.VISIBLE);
                rightTv.setText(chat_note);
                break;
            case 1:
                //todo 图片
                rightIv.setVisibility(View.VISIBLE);
//                Glide.with(context).load(WebUrlUtil.IMG_URL + chat_note).apply(new RequestOptions().
//                        placeholder(R.color.white).override(Target.SIZE_ORIGINAL)).into(rightIv);

                GlideUtil.setImage(context, WebUrlUtil.IMG_URL + chat_note, rightIv, 0);
                jumpImageDetail(WebUrlUtil.IMG_URL + chat_note, rightIv);
                break;
        }
    }

    /**
     * 设置头像
     *
     * @param img
     * @param imageView
     */
    private void setImage(String img, ImageView imageView) {
        if (img.indexOf("DOC") != -1 || img.indexOf("H5/Uimg") != -1) {
            GlideUtil.setImageCircle(context, WebUrlUtil.IMG_URL + img, imageView, R.drawable.icon_placeholder);
            L.e("lgh", WebUrlUtil.IMG_URL + img);
        } else {
            GlideUtil.setImageCircle(context, WebUrlUtil.IMG_URL + "DOC/my" + img, imageView, R.drawable.icon_placeholder);
            L.e("lgh", WebUrlUtil.IMG_URL + "DOC/my" + img);
        }
    }
}
