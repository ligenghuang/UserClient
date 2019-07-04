package com.yizhitong.userclient.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.UiThread;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lgh.huanglib.util.CheckNetwork;
import com.lgh.huanglib.util.L;
import com.lgh.huanglib.util.base.ActivityStack;
import com.lgh.huanglib.util.config.GlideUtil;
import com.lgh.huanglib.util.data.PriceUtils;
import com.lgh.huanglib.util.data.ResUtil;
import com.yizhitong.userclient.R;
import com.yizhitong.userclient.actions.DoctorDetailAction;
import com.yizhitong.userclient.adapters.EvaluationAdapter;
import com.yizhitong.userclient.event.DoctorDetailDto;
import com.yizhitong.userclient.event.FavDoctorDto;
import com.yizhitong.userclient.event.GeneralDto;
import com.yizhitong.userclient.net.WebUrlUtil;
import com.yizhitong.userclient.ui.impl.DoctorDetailView;
import com.yizhitong.userclient.ui.login.LoginActivity;
import com.yizhitong.userclient.utils.base.UserBaseActivity;
import com.yizhitong.userclient.utils.cusview.CollapsibleTextView;
import com.yizhitong.userclient.utils.cusview.CustomLinearLayoutManager;
import com.yizhitong.userclient.utils.cusview.ScoreCircle;
import com.yizhitong.userclient.utils.data.MySp;

import java.lang.ref.WeakReference;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * description ： 医生详情
 * author : lgh
 * email : 1045105946@qq.com
 * date : 2019/6/21
 */
public class DoctorDetailActivity extends UserBaseActivity<DoctorDetailAction> implements DoctorDetailView {
    @BindView(R.id.top_view)
    View topView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.f_title_tv)
    TextView titleTv;

    @BindView(R.id.iv_doctor_img)
    ImageView mIvDoctorImg;
    @BindView(R.id.tv_doctor_name)
    TextView mTvDoctorName;
    @BindView(R.id.tv_doctor_level)
    TextView mTvDoctorLevel;
    @BindView(R.id.tv_doctor_hospital)
    TextView mTvDoctorHospital;
    @BindView(R.id.tv_doctor_attention)
    TextView mTvDoctorAttention;
    @BindView(R.id.tv_doctor_note)
    CollapsibleTextView mTvDoctorNote;
    @BindView(R.id.tv_doctor_spec)
    CollapsibleTextView mTvDoctorSpec;
    @BindView(R.id.tv_doctor_consulting)
    TextView mTvDoctorConsulting;
    @BindView(R.id.tv_doctor_good_reputation)
    TextView mTvDoctorGoodReputation;
    @BindView(R.id.tv_doctor_buy)
    TextView mTvDoctorBuy;
    @BindView(R.id.tv_doctor_num)
    TextView mTvDoctorNum;
    @BindView(R.id.tv_money)
    TextView mTvMoney;
    @BindView(R.id.tv_pay)
    TextView mTvPay;

    @BindView(R.id.rv_evaluation)
    RecyclerView evaluationRv;
    @BindView(R.id.tv_item_ratio)
    TextView ratioTv;
    @BindView(R.id.tv_time)
    TextView mTvTime;
    @BindView(R.id.scoreCircle)
    ScoreCircle scoreCircle;

    EvaluationAdapter evaluationAdapter;
    CustomLinearLayoutManager linearLayoutManager;

    String iuid;
    boolean isAttention;

    @Override
    public int intiLayout() {
        return R.layout.activity_doctor_detail;
    }

    @Override
    protected DoctorDetailAction initAction() {
        return new DoctorDetailAction(this, this);
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
                .addTag("DoctorDetailActivity")  //给上面参数打标记，以后可以通过标记恢复
                .navigationBarWithKitkatEnable(false)
                .init();
        toolbar.setNavigationOnClickListener(view -> finish());
    }

    @Override
    protected void init() {
        super.init();
        mContext = this;
        mActicity = this;

        evaluationAdapter = new EvaluationAdapter(this);
        evaluationRv.setAdapter(evaluationAdapter);
        linearLayoutManager = new CustomLinearLayoutManager(this);
        linearLayoutManager.setScrollEnabled(true);
        linearLayoutManager.setStackFromEnd(true);
        evaluationRv.setLayoutManager(linearLayoutManager);

        iuid = getIntent().getStringExtra("iuid");

        getDoctor();
        getFavDoctorByuser();
    }

    @OnClick({R.id.tv_doctor_attention, R.id.tv_pay})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.tv_doctor_attention:
                if (!MySp.iSLoginLive(mContext)) {
                    jumpActivityNotFinish(mContext, LoginActivity.class);
                    return ;
                }
                if (isAttention) {
                    removeDoctor();
                } else {
                    concernsDoctor();
                }
                break;
            case R.id.tv_pay:
                if (!MySp.iSLoginLive(mContext)) {
                    jumpActivityNotFinish(mContext, LoginActivity.class);
                    return ;
                }
                Intent intent = new Intent(mContext, DoctorVisitsActivity.class);
                intent.putExtra("isShow", true);
                intent.putExtra("doctorid",iuid);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void getDoctor() {
        if (CheckNetwork.checkNetwork2(mContext)) {
            loadDialog();
            baseAction.getDoctor(iuid);
        }

    }

    @Override
    public void getDoctorSuccessful(DoctorDetailDto doctorDetailDto) {
        loadDiss();
        DoctorDetailDto.DataBean dataBean = doctorDetailDto.getData();
        GlideUtil.setImage(mContext, WebUrlUtil.IMG_URL + dataBean.getThe_img(), mIvDoctorImg, R.drawable.icon_placeholder);
        mTvDoctorName.setText(dataBean.getName());
        titleTv.setText(dataBean.getName() + "  医生");
        mTvDoctorHospital.setText(dataBean.getHospital());
        mTvDoctorLevel.setText(dataBean.getThe_level());
        mTvDoctorNote.setDesc(TextUtils.isEmpty(dataBean.getThe_note())?"暂无":dataBean.getThe_note());
        mTvDoctorSpec.setDesc(TextUtils.isEmpty(dataBean.getThe_spec())?"暂无":dataBean.getThe_spec());
        mTvDoctorConsulting.setText(dataBean.getAsk_num() + "人");
        mTvDoctorGoodReputation.setText(dataBean.getGood_num() + "人");
        mTvDoctorBuy.setText(dataBean.getBuy_num() + "人");
        mTvTime.setText(ResUtil.getFormatString(R.string.doctor_detail_tip_13, TextUtils.isEmpty(dataBean.getAnswer_len())?"0":dataBean.getAnswer_len()));
        int num = (int) (dataBean.getThe_per() * 100);
        L.e("lgh_num", "num = " + num);
        mTvDoctorNum.setText(Html.fromHtml(ResUtil.getFormatString(R.string.doctor_detail_tip_8, num + "%")));
        List<DoctorDetailDto.DataBean.DocevalMVBean> list = dataBean.getDocevalMV();
        int score = 0;
        if (list.size() != 0) {
            for (int i = 0; i < list.size(); i++) {
                DoctorDetailDto.DataBean.DocevalMVBean bean = list.get(i);
                score = score + bean.getThe_star();
            }
            int s = (score * 100) / list.size() / 5;
            ratioTv.setText(s + "%");
        }
        evaluationAdapter.refresh(list);
        mTvMoney.setText(PriceUtils.formatPrice(dataBean.getFact_price()) + "/次");
        scoreCircle.setScore(num);
    }

    @Override
    public void getFavDoctorByuser() {
        if (CheckNetwork.checkNetwork2(mContext)) {
            baseAction.getFavDoctorByuser(iuid);
        }
    }

    @Override
    public void getFavDoctorByuserSuccessful(FavDoctorDto favDoctorDto) {
        loadDiss();
        FavDoctorDto.DataBean dataBean = favDoctorDto.getData();
        isAttention = dataBean.isAttention();
        mTvDoctorAttention.setText(ResUtil.getString(dataBean.isAttention() ? R.string.doctor_detail_tip_2 : R.string.doctor_detail_tip_1));
    }

    @Override
    public void removeDoctor() {
        if (CheckNetwork.checkNetwork2(mContext)) {
            loadDialog();
            baseAction.removeDoctor(iuid);
        }
    }

    @Override
    public void removeoctorSuccessful(GeneralDto generalDto) {
        loadDiss();
        isAttention = false;
        mTvDoctorAttention.setText(ResUtil.getString(R.string.doctor_detail_tip_1));
    }

    @Override
    public void concernsDoctor() {
        if (CheckNetwork.checkNetwork2(mContext)) {
            loadDialog();
            baseAction.concernsDoctor(iuid);
        }
    }

    @Override
    public void concernsDoctorSuccessful(GeneralDto generalDto) {
        loadDiss();
        isAttention = true;
        mTvDoctorAttention.setText(ResUtil.getString(R.string.doctor_detail_tip_2));
    }

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
