package com.yizhitong.userclient.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.yizhitong.userclient.R;
import com.yizhitong.userclient.event.PatientListDto;
import com.yizhitong.userclient.ui.mine.AddPatientActivity;

public class HealthRecordAdapter extends BaseRecyclerAdapter<PatientListDto.DataBean>{
    Context context;
    public HealthRecordAdapter(Context context) {
        super(R.layout.layout_item_patient);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(SmartViewHolder holder, PatientListDto.DataBean model, int position) {
        holder.setIsRecyclable(false);
        holder.text(R.id.tv_item_patient_name,model.getName());
        holder.text(R.id.tv_item_patient_sex,model.getSex());
        holder.text(R.id.tv_item_patient_age,model.getAge()+"岁");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AddPatientActivity.class);
                intent.putExtra("iuid",model.getIUID());
                context.startActivity(intent);
            }
        });
    }
}
