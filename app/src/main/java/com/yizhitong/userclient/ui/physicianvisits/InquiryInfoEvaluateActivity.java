package com.yizhitong.userclient.ui.physicianvisits;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.lgh.huanglib.util.CheckNetwork;
import com.lgh.huanglib.util.L;
import com.lgh.huanglib.util.base.ActivityStack;
import com.lgh.huanglib.util.config.GlideUtil;
import com.lgh.huanglib.util.data.ResUtil;
import com.yizhitong.userclient.R;
import com.yizhitong.userclient.actions.InquiryInfoEvaluateAction;
import com.yizhitong.userclient.adapters.StringAdapter3;
import com.yizhitong.userclient.event.DocByAskIdDto;
import com.yizhitong.userclient.event.GeneralDto;
import com.yizhitong.userclient.event.NoteListDto;
import com.yizhitong.userclient.event.post.AddDoctorEvalPost;
import com.yizhitong.userclient.net.WebUrlUtil;
import com.yizhitong.userclient.ui.impl.InquiryInfoEvaluateView;
import com.yizhitong.userclient.ui.login.LoginActivity;
import com.yizhitong.userclient.utils.base.UserBaseActivity;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * description ： 评价
 * author : lgh
 * email : 1045105946@qq.com
 * date : 2019/6/17
 */
public class InquiryInfoEvaluateActivity extends UserBaseActivity<InquiryInfoEvaluateAction> implements InquiryInfoEvaluateView {

    @BindView(R.id.top_view)
    View topView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.f_title_tv)
    TextView titleTv;

    String iuid;
    @BindView(R.id.iv_doctor)
    ImageView mIvDoctor;
    @BindView(R.id.tv_doctor_name)
    TextView mTvDoctorName;
    @BindView(R.id.tv_doctor_hospital)
    TextView mTvDoctorHospital;
    @BindView(R.id.tv_doctor_level)
    TextView mTvDoctorLevel;
    @BindView(R.id.star_rb)
    RatingBar mStarRb;
    @BindView(R.id.tv_star_num)
    TextView mTvStarNum;
    @BindView(R.id.tv_rate)
    TextView mTvRate;
    @BindView(R.id.doctor_star_rb)
    RatingBar mDoctorStarRb;
    @BindView(R.id.tv_doctor_evaluate)
    TextView mTvDoctorEvaluate;
    @BindView(R.id.rv_doctor_evaluate)
    RecyclerView mRvDoctorEvaluate;
    @BindView(R.id.et_doctor_evaluate)
    EditText mEtDoctorEvaluate;
    @BindView(R.id.tv_doctor_evaluate_num)
    TextView mTvDoctorEvaluateNum;
    @BindView(R.id.checkbox)
    CheckBox mCheckbox;
    @BindView(R.id.ll_cb)
    LinearLayout mLlCb;

    StringAdapter3 stringAdapter;
    @BindView(R.id.tv_submit)
    TextView mTvSubmit;

    int anonymous_flag = 0;
    int the_star = 1;

    @Override
    public int intiLayout() {
        return R.layout.activity_inquiry_info_evaluate;
    }

    @Override
    protected InquiryInfoEvaluateAction initAction() {
        return new InquiryInfoEvaluateAction(this, this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityStack.getInstance().addActivity(new WeakReference<>(this));
        binding();
        initView();
    }

    @Override
    protected void initTitlebar() {
        super.initTitlebar();
        mImmersionBar
                .statusBarView(R.id.top_view)
                .keyboardEnable(true)
                .statusBarDarkFont(true, 0.2f)
                .addTag("InquiryInfoEvaluateActivity")  //给上面参数打标记，以后可以通过标记恢复
                .navigationBarWithKitkatEnable(false)
                .init();
        toolbar.setNavigationOnClickListener(view -> finish());
        titleTv.setText(ResUtil.getString(R.string.inquity_tip_19));
    }

    @Override
    protected void init() {
        super.init();
        mContext = this;
        mActicity = this;
        iuid = getIntent().getStringExtra("iuid");

        stringAdapter = new StringAdapter3();
        mRvDoctorEvaluate.setLayoutManager(new GridLayoutManager(mContext, 4));
        mRvDoctorEvaluate.setAdapter(stringAdapter);
        stringAdapter.refresh(getDoctorEvaluateData());

        getDocByAskId();
        loadView();
    }


    @Override
    protected void loadView() {
        super.loadView();
        mEtDoctorEvaluate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (TextUtils.isEmpty(mEtDoctorEvaluate.getText().toString())) {
                  mTvDoctorEvaluateNum.setText("0/200");
                }else {
                    mTvDoctorEvaluateNum.setText(mEtDoctorEvaluate.getText().length()+"/200");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mDoctorStarRb.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                mDoctorStarRb.setRating(rating);
                the_star = Math.round(rating);
                L.e("lgh_rating", "rating  = " + rating);
                L.e("lgh_rating", "num  = " + the_star);

                if (the_star < 4) {
                    mTvDoctorEvaluate.setText(ResUtil.getString(R.string.inquity_tip_21));
                } else if (the_star == 4) {
                    mTvDoctorEvaluate.setText(ResUtil.getString(R.string.inquity_tip_22));
                } else if (the_star == 5) {
                    mTvDoctorEvaluate.setText(ResUtil.getString(R.string.inquity_tip_23));
                }
            }
        });
    }

    private List<NoteListDto> getDoctorEvaluateData() {
        List<String> list = Arrays.asList(getResources().getStringArray(R.array.evaluate_list));
        List<NoteListDto> noteListDtos = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            NoteListDto noteListDto = new NoteListDto();
            noteListDto.setNote(list.get(i));
            noteListDtos.add(noteListDto);
        }
        return noteListDtos;
    }


    @OnClick({R.id.checkbox, R.id.ll_cb, R.id.tv_submit})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.checkbox:
            case R.id.ll_cb:
                if (anonymous_flag == 0) {
                    anonymous_flag = 1;
                    mCheckbox.setChecked(true);
                } else {
                    anonymous_flag = 0;
                    mCheckbox.setChecked(false);
                }
                break;
            case R.id.tv_submit:
                submit();
                break;
        }
    }

    /**
     * 提交评价
     */
    private void submit() {
        AddDoctorEvalPost addDoctorEvalPost = new AddDoctorEvalPost();
        addDoctorEvalPost.setAnonymous_flag(anonymous_flag);
        addDoctorEvalPost.setAskId(iuid);
        addDoctorEvalPost.setThe_star(the_star);
        String note = "";
        for (int i = 0; i < stringAdapter.getAllData().size(); i++) {
            NoteListDto noteListDto = stringAdapter.getAllData().get(i);
            if (noteListDto.isClick()) {
                if (i > 0) {
                    note = note + " ";
                }
                note = note + noteListDto.getNote();
            }
        }
        if (!TextUtils.isEmpty(mEtDoctorEvaluate.getText().toString())) {
            note = note + " " + mEtDoctorEvaluate.getText().toString();
        }
        addDoctorEvalPost.setThe_note(note);
        addDoctorEval(addDoctorEvalPost);
    }

    /**
     * 获取医生信息
     */
    @Override
    public void getDocByAskId() {
        if (CheckNetwork.checkNetwork2(mContext)) {
            loadDialog();
            baseAction.getDocByAskId(iuid);
        }
    }

    /**
     * 获取医生信息 成功
     * @param docByAskIdDto
     */
    @Override
    public void getDocByAskIdSuccessful(DocByAskIdDto docByAskIdDto) {
        loadDiss();
        DocByAskIdDto.DataBean dataBean = docByAskIdDto.getData();
        GlideUtil.setImage(mContext, WebUrlUtil.IMG_URL + dataBean.getThe_img(), mIvDoctor, R.drawable.icon_placeholder);
        mTvDoctorName.setText(dataBean.getName());
        mTvDoctorLevel.setText(dataBean.getThe_level());
        mTvDoctorHospital.setText(dataBean.getHospital());
        mStarRb.setRating(dataBean.getThe_star());
        mTvStarNum.setText(((int) dataBean.getThe_star()) + "");
        double rate = (dataBean.getGood_num() / dataBean.getAsk_num()) * 100;
        mTvRate.setText(ResUtil.getFormatString(R.string.inquity_tip_20, rate + "%"));
    }

    /**
     * 提交评价
     * @param addDoctorEvalPost
     */
    @Override
    public void addDoctorEval(AddDoctorEvalPost addDoctorEvalPost) {
        if (CheckNetwork.checkNetwork2(mContext)){
            loadDialog();
            baseAction.addDoctorEval(addDoctorEvalPost);
        }
    }

    /**
     * 提交评价 成功
     * @param generalDto
     */
    @Override
    public void addDoctorEvalSuccessful(GeneralDto generalDto) {
        loadDiss();
        showNormalToast(generalDto.getMsg());
        finish();
    }

    /**
     * 失败
     * @param message
     * @param code
     */
    @Override
    public void onError(String message, int code) {
        loadDiss();
        showNormalToast(message);
    }

    @Override
    public void onLigonError() {
        loadDiss();
        jumpActivity(mContext, LoginActivity.class);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (baseAction != null) {
            baseAction.toRegister();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (baseAction != null) {
            baseAction.toUnregister();
        }
    }


}
