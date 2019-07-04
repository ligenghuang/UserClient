package com.yizhitong.userclient.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yizhitong.userclient.R;
import com.yizhitong.userclient.event.PatientListDto;
import com.yizhitong.userclient.ui.mine.AddPatientActivity;

import java.util.ArrayList;
import java.util.List;
/**
* description ： 问诊人列表  适配器
* author : lgh
* email : 1045105946@qq.com
* date : 2019/7/4
*/
public class HealthRecordAdapter extends RecyclerView.Adapter<HealthRecordAdapter.ViewHolder>{

    private List<PatientListDto.DataBean> mDataList;
    Context context;
    boolean isSelect = false;

    OnClickListener onClickListener;

    public List<PatientListDto.DataBean> getmDataList() {
        if (mDataList == null) {
            return new ArrayList<>();
        }
        return mDataList;
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public HealthRecordAdapter(Context context, boolean isSelect) {
        this.context = context;
        this.isSelect = isSelect;
    }

    public void notifyDataSetChanged(List<PatientListDto.DataBean> dataList) {
        this.mDataList = dataList;
        super.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mDataList == null ? 0 : mDataList.size();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_item_patient, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setData(mDataList.get(position));
        holder.iv_edit.setVisibility(isSelect?View.GONE:View.VISIBLE);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                if (isSelect){
                    String str = mDataList.get(position).getName()+" "+mDataList.get(position).getSex()+" "+mDataList.get(position).getAge()+"岁";
                    onClickListener.OnClick(mDataList.get(position).getIUID(),str);
                }else {
                    intent = new Intent(context, AddPatientActivity.class);
                    intent.putExtra("iuid",mDataList.get(position).getIUID());
                    context.startActivity(intent);
                }
            }
        });
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView name,sex,age;
        ImageView iv_edit;

        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tv_item_patient_name);
            sex = itemView.findViewById(R.id.tv_item_patient_sex);
            age = itemView.findViewById(R.id.tv_item_patient_age);
            iv_edit = itemView.findViewById(R.id.iv_edit);
        }

        public void setData(PatientListDto.DataBean model) {
            name.setText(model.getName());
            age.setText(model.getAge()+"岁");
            sex.setText(model.getSex());
        }
    }
    public interface OnClickListener{
        void OnClick(String id,String name);
    }
}
