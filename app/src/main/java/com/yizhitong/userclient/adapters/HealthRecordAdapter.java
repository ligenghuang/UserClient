package com.yizhitong.userclient.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.yizhitong.userclient.R;
import com.yizhitong.userclient.event.PatientListDto;
import com.yizhitong.userclient.ui.mine.AddPatientActivity;

public class HealthRecordAdapter extends BaseRecyclerAdapter<PatientListDto.DataBean>{
    Context context;
    boolean isSelect = false;

    OnClickListener onClickListener;

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public HealthRecordAdapter(Context context, boolean isSelect) {
        super(R.layout.layout_item_patient);
        this.context = context;
        this.isSelect = isSelect;
    }

    @Override
    protected void onBindViewHolder(SmartViewHolder holder, PatientListDto.DataBean model, int position) {
        holder.setIsRecyclable(false);
        holder.text(R.id.tv_item_patient_name,model.getName());
        holder.text(R.id.tv_item_patient_sex,model.getSex());
        holder.text(R.id.tv_item_patient_age,model.getAge()+"岁");
        ImageView iv_edit = holder.itemView.findViewById(R.id.iv_edit);
        iv_edit.setVisibility(isSelect?View.GONE:View.VISIBLE);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
               if (isSelect){
                   String str = model.getName()+" "+model.getSex()+" "+model.getAge()+"岁";
                   onClickListener.OnClick(model.getIUID(),str);
               }else {
                    intent = new Intent(context, AddPatientActivity.class);
                   intent.putExtra("iuid",model.getIUID());
                   context.startActivity(intent);
               }
            }
        });
    }

    public interface OnClickListener{
        void OnClick(String id,String name);
    }
}
