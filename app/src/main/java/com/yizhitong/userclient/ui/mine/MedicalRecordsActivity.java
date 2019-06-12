package com.yizhitong.userclient.ui.mine;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.lgh.huanglib.util.base.ActivityStack;
import com.lgh.huanglib.util.data.ResUtil;
import com.yizhitong.userclient.R;
import com.yizhitong.userclient.actions.BaseAction;
import com.yizhitong.userclient.adapters.StringAdapter;
import com.yizhitong.userclient.adapters.StringAdapter2;
import com.yizhitong.userclient.utils.base.UserBaseActivity;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;

public class MedicalRecordsActivity extends UserBaseActivity {

    @BindView(R.id.top_view)
    View topView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.f_title_tv)
    TextView titleTv;


    @BindView(R.id.tv_note)
    EditText mTvNote;
    @BindView(R.id.tv_note_num)
    TextView mTvNoteNum;

    int type;
    List<String> list = new ArrayList<>();

    StringAdapter stringAdapter;
    StringAdapter2 stringAdapter2;

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    @Override
    public int intiLayout() {
        return R.layout.activity_medical_records;
    }

    @Override
    protected BaseAction initAction() {
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityStack.getInstance().addActivity(new WeakReference<>(this));
        binding();
    }

    @Override
    protected void initTitlebar() {
        super.initTitlebar();
        mImmersionBar
                .statusBarView(R.id.top_view)
                .keyboardEnable(true)
                .statusBarDarkFont(true, 0.2f)
                .addTag("MedicalRecordsActivity")  //给上面参数打标记，以后可以通过标记恢复
                .navigationBarWithKitkatEnable(false)
                .init();
        toolbar.setNavigationOnClickListener(view -> finish());

    }

    @Override
    protected void init() {
        super.init();
        type = getIntent().getIntExtra("type", 1);

        stringAdapter = new StringAdapter();
        stringAdapter2 = new StringAdapter2();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,4);
        switch (type) {
            case 1:
                //todo 过敏史
                titleTv.setText(ResUtil.getString(R.string.add_patient_tip_9));
                list = Arrays.asList(getResources().getStringArray(R.array.allergy_list));
                mRecyclerView.setLayoutManager(gridLayoutManager);
                mRecyclerView.setAdapter(stringAdapter2);
                break;
            case 2:
                //todo 家族史
                titleTv.setText(ResUtil.getString(R.string.add_patient_tip_10));
                list = Arrays.asList(getResources().getStringArray(R.array.family_history_list));
                mRecyclerView.setLayoutManager(gridLayoutManager);
                mRecyclerView.setAdapter(stringAdapter2);
                break;
            case 3:
                //TODO 过往病史
                titleTv.setText(ResUtil.getString(R.string.add_patient_tip_11));
                list = Arrays.asList(getResources().getStringArray(R.array.past_list));
                mRecyclerView.setLayoutManager(gridLayoutManager);
                mRecyclerView.setAdapter(stringAdapter2);
                break;
            case 4:
                //TODO 过敏药物
                titleTv.setText(ResUtil.getString(R.string.add_patient_tip_12));
                list = Arrays.asList(getResources().getStringArray(R.array.drug_allergy_list));
                StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(4,StaggeredGridLayoutManager.HORIZONTAL);
                mRecyclerView.setLayoutManager(staggeredGridLayoutManager);
                mRecyclerView.setAdapter(stringAdapter);
                break;
        }

        stringAdapter.refresh(list);
        stringAdapter2.refresh(list);
    }
}
