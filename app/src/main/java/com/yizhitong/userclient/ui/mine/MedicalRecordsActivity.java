package com.yizhitong.userclient.ui.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.lgh.huanglib.util.L;
import com.lgh.huanglib.util.base.ActivityStack;
import com.lgh.huanglib.util.data.ResUtil;
import com.yizhitong.userclient.R;
import com.yizhitong.userclient.actions.BaseAction;
import com.yizhitong.userclient.adapters.StringAdapter;
import com.yizhitong.userclient.adapters.StringAdapter2;
import com.yizhitong.userclient.event.NoteListDto;
import com.yizhitong.userclient.utils.Util;
import com.yizhitong.userclient.utils.base.UserBaseActivity;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MedicalRecordsActivity extends UserBaseActivity {

    @BindView(R.id.top_view)
    View topView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.f_title_tv)
    TextView titleTv;


    int type;
    List<String> list = new ArrayList<>();

    StringAdapter stringAdapter;
    StringAdapter2 stringAdapter2;

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.et_note)
    EditText noteEt;
    @BindView(R.id.tv_note_num)
    TextView noteNumTv;

    String note = "";
    String remain = "";
    List<NoteListDto> noteList = new ArrayList<>();

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
        note = getIntent().getStringExtra("note");

//        getData();
        stringAdapter = new StringAdapter();
        stringAdapter2 = new StringAdapter2();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 4);
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
                StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.HORIZONTAL);
                mRecyclerView.setLayoutManager(staggeredGridLayoutManager);
                mRecyclerView.setAdapter(stringAdapter);
                break;
        }

        stringAdapter.refresh(getData());
        stringAdapter2.refresh(getData());
        noteEt.setText(remain);
        noteNumTv.setText(remain.length() + "/200");
        loadView();
    }

    @Override
    protected void loadView() {
        super.loadView();
        noteEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (TextUtils.isEmpty(noteEt.getText().toString())) {
                    noteNumTv.setText("0/200");
                } else {
                    noteNumTv.setText(noteEt.getText().toString().length() + "/200");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @OnClick(R.id.tv_submit)
    void OnClick(View v) {
        switch (v.getId()) {
            case R.id.tv_submit:
                //todo 保存
                String str = "";
                if (type == 4) {
                    str = getString(stringAdapter.getAllData());
                }else {
                    str = getString(stringAdapter2.getAllData());
                }
                if (str.length()==0){
                    showNormalToast(ResUtil.getString(R.string.add_patient_tip_18));
                    return;
                }
                Intent intent = new Intent();
                intent.putExtra("type",type);
                intent.putExtra("note",str);
                setResult(200,intent);
                finish();
                break;
        }
    }

    /**
     * 获取病历
     * @param listDtos
     * @return
     */
    private String getString(List<NoteListDto> listDtos) {
        L.e("lgh_array",listDtos.toString());
        String str = "";
        int num = 0;
        for (int i = 0; i < listDtos.size(); i++) {
            if (listDtos.get(i).isClick()) {
                if (num > 0) {
                    str = str + " ";
                }
                num++;
                str = str + listDtos.get(i).getNote();
            }
        }
        if (!TextUtils.isEmpty(noteEt.getText().toString())) {
            str = str + " " + noteEt.getText().toString();
        }
        return str;
    }

    /**
     * 获取过往病历
     *
     * @return
     */
    private List<NoteListDto> getData() {
        List<String> stringList = Util.getStringList(note);
        List<String> stringList1 = new ArrayList<>();
        noteList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            NoteListDto noteListDto = new NoteListDto();
            noteListDto.setNote(list.get(i));
            for (int j = 0; j < stringList.size(); j++) {
                if (list.get(i).equals(stringList.get(j))) {
                    noteListDto.setClick(true);
                    stringList1.add(stringList.get(j));
                }
            }
            noteList.add(noteListDto);
        }
        stringList.removeAll(stringList1);
        if (stringList.size() > 0) {
            remain = stringList.get(0);
        }
        L.e("lgh_array", stringList.size() + "");
        return noteList;
    }
}
