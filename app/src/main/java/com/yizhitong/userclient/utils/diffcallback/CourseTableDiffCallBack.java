package com.yizhitong.userclient.utils.diffcallback;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;
import com.yizhitong.userclient.event.MessageListDto;

import java.util.List;

public class CourseTableDiffCallBack extends DiffUtil.Callback {
    private List<MessageListDto.DataBean> mOldDatas, mNewDatas;//看名字

    public CourseTableDiffCallBack(List<MessageListDto.DataBean> mOldDatas, List<MessageListDto.DataBean> mNewDatas) {
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
        MessageListDto.DataBean oldData = mOldDatas.get(oldItemPosition);
        MessageListDto.DataBean newData = mNewDatas.get(newItemPosition);
        return oldData.getChat_note().equals(newData.getChat_note());
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        MessageListDto.DataBean beanOld = mOldDatas.get(oldItemPosition);
        MessageListDto.DataBean beanNew = mNewDatas.get(newItemPosition);
        boolean b = beanOld.getNiceName().equals(beanNew.getNiceName())&&beanNew.getNotread()==beanOld.getNotread();
        return b;//默认两个data内容是相同的
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        //实现这个方法 就能成为文艺青年中的文艺青年
        // 定向刷新中的部分更新
        // 效率最高
        //只是没有了ItemChange的白光一闪动画，（反正我也觉得不太重要）
        MessageListDto.DataBean oldBean = mOldDatas.get(oldItemPosition);
        MessageListDto.DataBean newBean = mNewDatas.get(newItemPosition);
        //这里就不用比较核心字段了,一定相等
        Bundle payload = new Bundle();
        if ((!oldBean.getChat_note().equals(newBean.getChat_note())) || oldBean.getNotread()==newBean.getNotread()) {
            payload.putString("KEY_PERMISSION", newBean.getChat_note());
        }

//
        if (payload.size() == 0)//如果没有变化 就传空
            return null;
        return payload;//
    }
}
