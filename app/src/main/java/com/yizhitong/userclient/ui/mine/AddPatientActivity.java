package com.yizhitong.userclient.ui.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lgh.huanglib.util.CheckNetwork;
import com.lgh.huanglib.util.base.ActivityStack;
import com.lgh.huanglib.util.data.ResUtil;
import com.yizhitong.userclient.R;
import com.yizhitong.userclient.actions.AddPatientAction;
import com.yizhitong.userclient.event.GeneralDto;
import com.yizhitong.userclient.event.PatientInfoDto;
import com.yizhitong.userclient.event.post.AddPatientPost;
import com.yizhitong.userclient.ui.impl.AddPatientView;
import com.yizhitong.userclient.ui.login.LoginActivity;
import com.yizhitong.userclient.utils.base.UserBaseActivity;
import com.yizhitong.userclient.utils.data.DynamicTimeFormat;
import com.yizhitong.userclient.utils.dialog.ModifyDialog;
import com.yizhitong.userclient.utils.dialog.ModifyPhoneDialog;
import com.yizhitong.userclient.utils.picker.TimePickerBuilder;

import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
/**
* description ： 添加问诊人
* author : lgh
* email : 1045105946@qq.com
* date : 2019/6/17
*/
public class AddPatientActivity extends UserBaseActivity<AddPatientAction> implements AddPatientView {

    @BindView(R.id.top_view)
    View topView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.f_title_tv)
    TextView titleTv;


    String iuid = "";
    int type;

    @BindView(R.id.tv_patient_name)
    TextView tvPatientName;
    @BindView(R.id.rl_patient_name)
    RelativeLayout rlPatientName;
    @BindView(R.id.tv_patient_sex)
    TextView tvPatientSex;
    @BindView(R.id.rl_patient_sex)
    RelativeLayout rlPatientSex;
    @BindView(R.id.tv_patient_birthday)
    TextView mTvPatientBirthday;
    @BindView(R.id.rl_patient_birthday)
    RelativeLayout mRlPatientBirthday;
    @BindView(R.id.tv_patient_phone)
    TextView mTvPatientPhone;
    @BindView(R.id.rl_patient_phone)
    RelativeLayout mRlPatientPhone;
    @BindView(R.id.tv_patient_relation)
    TextView mTvPatientRelation;
    @BindView(R.id.rl_patient_relation)
    RelativeLayout mRlPatientRelation;
    @BindView(R.id.tv_patient_height)
    TextView mTvPatientHeight;
    @BindView(R.id.rl_patient_height)
    RelativeLayout mRlPatientHeight;
    @BindView(R.id.tv_patient_weight)
    TextView mTvPatientWeight;
    @BindView(R.id.rl_patient_weight)
    RelativeLayout mRlPatientWeight;
    @BindView(R.id.tv_patient_allergy)
    TextView mTvPatientAllergy;
    @BindView(R.id.rl_patient_allergy)
    RelativeLayout mRlPatientAllergy;
    @BindView(R.id.tv_patient_family_history)
    TextView mTvPatientFamilyHistory;
    @BindView(R.id.rl_patient_family_history)
    RelativeLayout mRlPatientFamilyHistory;
    @BindView(R.id.tv_patient_past)
    TextView mTvPatientPast;
    @BindView(R.id.rl_patient_past)
    RelativeLayout mRlPatientPast;
    @BindView(R.id.tv_patient_drug_allergy)
    TextView mTvPatientDrugAllergy;
    @BindView(R.id.rl_patient_drug_allergy)
    RelativeLayout mRlPatientDrugAllergy;
    @BindView(R.id.tv_btn)
    TextView mTvBtn;
    List<String> sexLists = new ArrayList<>();
    List<String> relationList = new ArrayList<>();

    String height;
    String weight;
    String allergy = "";
    String familyHistory = "";
    String past = "";
    String drugAllergy = "";

    @Override
    public int intiLayout() {
        return R.layout.activity_add_patient;
    }

    @Override
    protected AddPatientAction initAction() {
        return new AddPatientAction(this, this);
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
                .addTag("AddPatientActivity")  //给上面参数打标记，以后可以通过标记恢复
                .navigationBarWithKitkatEnable(false)
                .init();
        toolbar.setNavigationOnClickListener(view -> finish());
        titleTv.setText(ResUtil.getString(R.string.add_patient_tip_title));
    }

    @Override
    protected void init() {
        super.init();
        mContext = this;
        mActicity = this;
        iuid = getIntent().getStringExtra("iuid");

        getData();

        isLogin();
    }

    private void getData() {
        sexLists = new ArrayList<>();
        sexLists.add("男");
        sexLists.add("女");

        relationList = new ArrayList<>();
        relationList.add("本人");
        relationList.add("家庭成员");
        relationList.add("亲戚");
        relationList.add("朋友");
        relationList.add("其他");
    }

    @OnClick({R.id.rl_patient_name, R.id.rl_patient_sex,
            R.id.rl_patient_birthday, R.id.rl_patient_phone,
            R.id.rl_patient_relation, R.id.rl_patient_height,
            R.id.rl_patient_weight, R.id.rl_patient_allergy,
            R.id.rl_patient_family_history, R.id.rl_patient_past,
            R.id.rl_patient_drug_allergy, R.id.tv_btn})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_patient_name:
                //todo 修改姓名
                ModifyDialog modifyDialog = new ModifyDialog(mContext, R.style.MY_AlertDialog, ResUtil.getString(R.string.doctor_certified_tip_17),tvPatientName.getText().toString());
                modifyDialog.setOnClickListener(new ModifyDialog.OnClickListener() {
                    @Override
                    public void confirm(String txet) {
                        if (TextUtils.isEmpty(txet)) {
                            showNormalToast(ResUtil.getString(R.string.doctor_certified_tip_17));
                        } else {
                            tvPatientName.setText(txet);
                            hideInput();
                            modifyDialog.dismiss();
                        }
                    }
                });
                modifyDialog.show();
                break;
            case R.id.rl_patient_sex:
                //todo 修改性别
                new TimePickerBuilder(mContext).setSexPicker(sexLists, "选择性别", new TimePickerBuilder.SexPickerCustomListener() {
                    @Override
                    public void sexSelect(String sexStr) {
                        tvPatientSex.setText(sexStr);
                    }
                }).show();
                break;
            case R.id.rl_patient_birthday:
                //todo 出生日期
                new TimePickerBuilder(mContext).TimePickerBuilder(new TimePickerBuilder.TimePickerCustomListener() {
                    @Override
                    public void customLayout(Date date) {
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                        mTvPatientBirthday.setText(format.format(date));
                    }
                }).show();
                break;
            case R.id.rl_patient_phone:
                //todo 电话
                ModifyPhoneDialog modifyPhoneDialog = new ModifyPhoneDialog(mContext, R.style.MY_AlertDialog, ResUtil.getString(R.string.add_patient_tip_4));
                modifyPhoneDialog.setOnClickListener(new ModifyPhoneDialog.OnClickListener() {
                    @Override
                    public void confirm(String txet) {
                        if (TextUtils.isEmpty(txet)) {
                            showNormalToast(ResUtil.getString(R.string.add_patient_tip_14));
                        } else {
                            mTvPatientPhone.setText(txet);
                            hideInput();
                            modifyPhoneDialog.dismiss();
                        }
                    }
                });
                modifyPhoneDialog.show();
                break;
            case R.id.rl_patient_relation:
                //TODO 与本人关系
                new TimePickerBuilder(mContext).setSexPicker(relationList, "与本人关系", new TimePickerBuilder.SexPickerCustomListener() {
                    @Override
                    public void sexSelect(String sexStr) {
                        mTvPatientRelation.setText(sexStr);
                    }
                }).show();
                break;
            case R.id.rl_patient_height:
                //todo 身高
                ModifyPhoneDialog heightDialog = new ModifyPhoneDialog(mContext, R.style.MY_AlertDialog, ResUtil.getString(R.string.add_patient_tip_7));
                heightDialog.setOnClickListener(new ModifyPhoneDialog.OnClickListener() {
                    @Override
                    public void confirm(String txet) {
                        if (TextUtils.isEmpty(txet)) {
                            showNormalToast(ResUtil.getString(R.string.add_patient_tip_15));
                        } else {
                            height = txet;
                            mTvPatientHeight.setText(txet + "cm");
                            hideInput();
                            heightDialog.dismiss();
                        }
                    }
                });
                heightDialog.show();
                break;
            case R.id.rl_patient_weight:
                //todo 体重
                ModifyPhoneDialog weightDialog = new ModifyPhoneDialog(mContext, R.style.MY_AlertDialog, ResUtil.getString(R.string.add_patient_tip_8));
                weightDialog.setOnClickListener(new ModifyPhoneDialog.OnClickListener() {
                    @Override
                    public void confirm(String txet) {
                        if (TextUtils.isEmpty(txet)) {
                            showNormalToast(ResUtil.getString(R.string.add_patient_tip_16));
                        } else {
                            weight = txet;
                            mTvPatientWeight.setText(txet + "Kg");
                            hideInput();
                            weightDialog.dismiss();
                        }
                    }
                });
                weightDialog.show();
                break;
            case R.id.rl_patient_allergy:
                //todo 过敏史
                jumpMedicalRecordsActivity(1, allergy);
                break;
            case R.id.rl_patient_family_history:
                //todo 家族史
                jumpMedicalRecordsActivity(2, familyHistory);
                break;
            case R.id.rl_patient_past:
                //TODO 过往病史
                jumpMedicalRecordsActivity(3, past);
                break;
            case R.id.rl_patient_drug_allergy:
                //TODO 过敏药物
                jumpMedicalRecordsActivity(4, drugAllergy);
                break;
            case R.id.tv_btn:
                //TODO 提交
                submit();
                break;
        }
    }

    private void submit() {
        AddPatientPost addPatientPost = new AddPatientPost();
        addPatientPost.setIUID(iuid);

        //todo 姓名
        if (!isEmpty(tvPatientName.getText().toString(), R.string.doctor_certified_tip_17)) {
            return;
        }
        addPatientPost.setName(tvPatientName.getText().toString());

        //TODO 性别
        if (!isEmpty(tvPatientSex.getText().toString(), R.string.add_patient_tip_19)) {
            return;
        }
        addPatientPost.setSex(tvPatientSex.getText().toString());

        //todo 出生日期
        if (!isEmpty(mTvPatientBirthday.getText().toString(), R.string.add_patient_tip_20)) {
            return;
        }
        addPatientPost.setBirt_date(mTvPatientBirthday.getText().toString());

        //todo 电话
        if (!isEmpty(mTvPatientPhone.getText().toString(), R.string.add_patient_tip_14)) {
            return;
        }
        addPatientPost.setPhone(mTvPatientPhone.getText().toString());

        //TODO 与本人关系
        if(!TextUtils.isEmpty(mTvPatientRelation.getText().toString())){
            addPatientPost.setRelation(mTvPatientRelation.getText().toString());
        }


        //todo 身高
        if (!TextUtils.isEmpty(mTvPatientHeight.getText().toString())){
            addPatientPost.setHeight(Float.parseFloat(height));
        }


        //todo 体重
        if (!TextUtils.isEmpty(mTvPatientWeight.getText().toString())){
            addPatientPost.setWeight(Float.parseFloat(weight));
        }


        //todo 过敏史
        if (!TextUtils.isEmpty(mTvPatientAllergy.getText().toString())){
            addPatientPost.setAllergy_note(mTvPatientAllergy.getText().toString());
        }


        //TODO 家族史
        if (!TextUtils.isEmpty(mTvPatientFamilyHistory.getText().toString())){
            addPatientPost.setMed_family(mTvPatientFamilyHistory.getText().toString());
        }


        //todo 过往病历
        if (!TextUtils.isEmpty(mTvPatientPast.getText().toString())){
            addPatientPost.setMed_history(mTvPatientPast.getText().toString());
        }


        //todo 过敏药物
        if (!TextUtils.isEmpty(mTvPatientDrugAllergy.getText().toString())){
            addPatientPost.setMed_drug(mTvPatientDrugAllergy.getText().toString());
        }
      

        addPatient(addPatientPost);

    }

    /**
     * 判空
     *
     * @param str
     * @param resId
     * @return
     */
    private boolean isEmpty(String str, int resId) {
        if (TextUtils.isEmpty(str)) {
            showNormalToast(ResUtil.getString(resId));
            return false;
        }
        return true;
    }

    private void jumpMedicalRecordsActivity(int i, String str) {
        Intent intent = new Intent(mContext, MedicalRecordsActivity.class);
        intent.putExtra("type", i);
        intent.putExtra("note", str);
        startActivityForResult(intent, 200);
    }

    @Override
    public void isLogin() {
        if (CheckNetwork.checkNetwork2(mContext)) {
            loadDialog();
            baseAction.isLogin();
        }
    }

    @Override
    public void isLoginSuccessful() {
        if (!TextUtils.isEmpty(iuid)) {
            getPatient();
            type = 1;
        } else {
            loadDiss();
            type = 0;
        }
    }

    @Override
    public void isLoginError() {
        loadDiss();
        jumpActivityNotFinish(mContext, LoginActivity.class);
    }

    /**
     * 新增、修改问诊人信息
     *
     * @param addPatientPost
     */
    @Override
    public void addPatient(AddPatientPost addPatientPost) {
        if (CheckNetwork.checkNetwork2(mContext)) {
            loadDialog();
            baseAction.addPatient(type, addPatientPost);
        }
    }

    /**
     * 新增、修改问诊人信息成功
     *
     * @param generalDto
     */
    @Override
    public void addPatientSuccessful(GeneralDto generalDto) {
        loadDiss();
        finish();
        showNormalToast(generalDto.getMsg());
    }

    /**
     * 获取问诊人详情
     */
    @Override
    public void getPatient() {
        if (CheckNetwork.checkNetwork2(mContext)) {
            baseAction.getPatient(iuid);
        } else {
            loadDiss();
        }
    }

    /**
     * 获取问诊人详情成功
     *
     * @param patientInfoDto
     */
    @Override
    public void getPatientSuccessful(PatientInfoDto patientInfoDto) {
        loadDiss();
        PatientInfoDto.DataBean dataBean = patientInfoDto.getData();
        tvPatientName.setText(dataBean.getName());
        tvPatientSex.setText(dataBean.getSex());
        mTvPatientBirthday.setText(DynamicTimeFormat.LongToString(dataBean.getBirt_date_stamp()));
        mTvPatientPhone.setText(dataBean.getPhone());

        mTvPatientRelation.setText(dataBean.getRelation());
        mTvPatientHeight.setText(dataBean.getHeight() + "cm");
        height = dataBean.getHeight() + "";
        mTvPatientWeight.setText(dataBean.getWeight() + "Kg");
        weight = dataBean.getWeight() + "";

        mTvPatientAllergy.setText(dataBean.getAllergy_note());
        allergy = dataBean.getAllergy_note();
        mTvPatientFamilyHistory.setText(dataBean.getMed_family());
        familyHistory = dataBean.getMed_family();
        mTvPatientPast.setText(dataBean.getMed_history());
        past = dataBean.getMed_history();
        mTvPatientDrugAllergy.setText(dataBean.getMed_drug());
        drugAllergy = dataBean.getMed_drug();
    }

    /**
     * 失败
     *
     * @param message
     * @param code
     */
    @Override
    public void onError(String message, int code) {
        loadDiss();
        if (code == -2) {
            showNormalToast(message);
            jumpActivity(mContext, LoginActivity.class);
        } else {
            showNormalToast(message);
        }
    }

    @Override
    public void onLigonError() {
        jumpActivity(mContext, LoginActivity.class);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (baseAction != null) {
            baseAction.toRegister();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (baseAction != null) {
            baseAction.toUnregister();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 200) {
            if (data != null) {
                switch (data.getIntExtra("type", 1)) {
                    case 1:
                        allergy = data.getStringExtra("note");
                        mTvPatientAllergy.setText(allergy);
                        break;
                    case 2:
                        familyHistory = data.getStringExtra("note");
                        mTvPatientFamilyHistory.setText(familyHistory);
                        break;
                    case 3:
                        past = data.getStringExtra("note");
                        mTvPatientPast.setText(past);
                        break;
                    case 4:
                        drugAllergy = data.getStringExtra("note");
                        mTvPatientDrugAllergy.setText(drugAllergy);
                        break;
                }
            }
        }
    }


}
