package com.yizhitong.userclient.utils.diffcallback;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;

import com.yizhitong.userclient.event.MessageListDto;
import com.yizhitong.userclient.event.MyInquiryDto;

import java.util.List;

public class InquiryDiffCallBack extends DiffUtil.Callback {
    private List<MyInquiryDto.DataBean> mOldDatas, mNewDatas;//看名字

    public InquiryDiffCallBack(List<MyInquiryDto.DataBean> mOldDatas, List<MyInquiryDto.DataBean> mNewDatas) {
        this.mOldDatas = mOldDatas;
        this.mNewDatas = mNewDatas;
    }

    //老数据集size
    @Override
    public int getOldListSize() {
        return mOldDatas != null ? mOldDatas.size() : 0;
    }

    //新数据集size
    @Override
    public int getNewListSize() {
        return mNewDatas != null ? mNewDatas.size() : 0;
    }
    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        MyInquiryDto.DataBean oldData = mOldDatas.get(oldItemPosition);
        MyInquiryDto.DataBean newData = mNewDatas.get(newItemPosition);
        return oldData.getAskIUID().equals(newData.getAskIUID());
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        MyInquiryDto.DataBean beanOld = mOldDatas.get(oldItemPosition);
        MyInquiryDto.DataBean beanNew = mNewDatas.get(newItemPosition);
        boolean b = beanOld.getAskIUID().equals(beanNew.getAskIUID())&&beanNew.getAsk_flag()==beanOld.getAsk_flag();
        return b;//默认两个data内容是相同的
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        //实现这个方法 就能成为文艺青年中的文艺青年
        // 定向刷新中的部分更新
        // 效率最高
        //只是没有了ItemChange的白光一闪动画，（反正我也觉得不太重要）
        MyInquiryDto.DataBean oldBean = mOldDatas.get(oldItemPosition);
        MyInquiryDto.DataBean newBean = mNewDatas.get(newItemPosition);
        //这里就不用比较核心字段了,一定相等
        Bundle payload = new Bundle();
        if ((!oldBean.getAskIUID().equals(newBean.getAskIUID())) || oldBean.getAsk_flag()==newBean.getAsk_flag()) {
            payload.putString("KEY_PERMISSION", newBean.getAskIUID());
        }

//
        if (payload.size() == 0)//如果没有变化 就传空
            return null;
        return payload;//
    }
}
