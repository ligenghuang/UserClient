package com.yizhitong.userclient.ui.login;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.lgh.huanglib.util.CheckNetwork;
import com.lgh.huanglib.util.base.ActivityStack;
import com.lgh.huanglib.util.data.FormatUtils;
import com.lgh.huanglib.util.data.ResUtil;
import com.yizhitong.userclient.R;
import com.yizhitong.userclient.actions.RegisteredAction;
import com.yizhitong.userclient.event.GeneralDto;
import com.yizhitong.userclient.event.RegisteredCodeDto;
import com.yizhitong.userclient.event.post.RegisteredPost;
import com.yizhitong.userclient.ui.impl.RegisteredView;
import com.yizhitong.userclient.utils.base.UserBaseActivity;
import com.yizhitong.userclient.utils.data.MySp;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.OnClick;

/**
* 注册
* @author lgh
* created at 2019/5/14 0014 9:22
*/
public class RegisteredActivity extends UserBaseActivity<RegisteredAction> implements RegisteredView {

    @BindView(R.id.top_view)
    View topView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.f_title_tv)
    TextView titleTv;

    @BindView(R.id.et_registered_doctor_account)
    EditText doctorAccountEt;
    @BindView(R.id.et_registered_auth_code)
    EditText authCodeEt;
    @BindView(R.id.tv_registered_get_code)
    TextView getCodeTv;
    @BindView(R.id.et_registered_pwd)
    EditText pwdEt;
    @BindView(R.id.et_registered_again_pwd)
    EditText againPwdEt;
    @BindView(R.id.et_registered_invite_code)
    EditText inviteCodeEt;
//    @BindView(R.id.iv_registered_agreement)
//    ImageView agreementIv;

    //获取验证码倒计时
    private MyCountDownTimer timer;


    @Override
    public int intiLayout() {
        return R.layout.activity_registered;
    }

    @Override
    protected RegisteredAction initAction() {
        return new RegisteredAction(this,this);
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
                .addTag("RegisteredActivity")  //给上面参数打标记，以后可以通过标记恢复
                .navigationBarWithKitkatEnable(false)
                .init();
        toolbar.setNavigationOnClickListener(view -> finish());
        titleTv.setText(ResUtil.getString(R.string.registered_tip_10));
    }

    @Override
    protected void init() {
        super.init();
        mActicity = this;
        mContext = this;
        timer = new MyCountDownTimer(60000,1000);
    }

    /**
     * 点击事件
     * @param view
     */
    @OnClick({R.id.tv_registered_get_code,
           R.id.tv_registered_btn_registered,R.id.tv_registered_to_login})
    public void OnClick(View view){
        switch (view.getId()){
            case R.id.tv_registered_get_code:
                //todo 获取验证码
                if (TextUtils.isEmpty(doctorAccountEt.getText().toString())){
                    //todo 医生手机号判空
                    showNormalToast(ResUtil.getString(R.string.registered_tip_2));
                    return;
                }
                registeredCode(doctorAccountEt.getText().toString());
                break;
            case R.id.tv_registered_btn_registered:
                //todo 注册
                submit();
                break;
            case R.id.tv_registered_to_login:
                //todo 返回登录
                finish();
                break;
        }
    }

    /**
     * 注册
     */
    private void submit() {
        RegisteredPost post = new RegisteredPost();

        if (TextUtils.isEmpty(doctorAccountEt.getText().toString())){
            //todo 医生手机号判空
            showNormalToast(ResUtil.getString(R.string.registered_tip_2));
            return;
        }
        post.setUserName(doctorAccountEt.getText().toString());

        if (TextUtils.isEmpty(authCodeEt.getText().toString())){
            //todo 判空验证码
            showNormalToast(ResUtil.getString(R.string.registered_tip_3));
            return;
        }
        post.setSms_code(authCodeEt.getText().toString());

        if (TextUtils.isEmpty(pwdEt.getText().toString())){
            //TODO 判空密码
            showNormalToast(ResUtil.getString(R.string.registered_tip_4));
            return;
        }
        String pwd = pwdEt.getText().toString();
        if (TextUtils.isEmpty(againPwdEt.getText().toString())){
            //todo 判空 再次输入的密码
            showNormalToast(ResUtil.getString(R.string.registered_tip_15));
            return;
        }
        String againPwd = againPwdEt.getText().toString();
        if (!againPwd.equals(pwd)){
            //todo 判断两次密码是否一致
            showNormalToast(ResUtil.getString(R.string.registered_tip_15));
            return;
        }
        post.setPassword(pwd);

        if (!TextUtils.isEmpty(inviteCodeEt.getText().toString())){
            //todo  邀请码
           post.setInvitName(inviteCodeEt.getText().toString());
            return;
        }


        //todo 注册
        registered(post);
    }

    /**
     * 注册
     */
    @Override
    public void registered(RegisteredPost registeredPost) {
        if (CheckNetwork.checkNetwork2(mContext)){
            loadDialog(ResUtil.getString(R.string.registered_tip_16));
            baseAction.registered(registeredPost);
        }
    }

    /**
     * 注册成功
     */
    @Override
    public void registeredSuccessful(GeneralDto generalDto) {
        loadDiss();
        if (generalDto.getCode() == 1){
            showNormalToast(generalDto.getMsg());
            if (timer != null) {
                timer.cancel();
            }
            MySp.setToken(mContext,generalDto.getData());
            ActivityStack.getInstance().exitClass(LoginActivity.class);
            finish();
        }else {
            getCodeTv.setEnabled(true);
            getCodeTv.setSelected(false);
            getCodeTv.setText(R.string.registered_tip_14);
        }
    }

    /**
     * 获取验证码
     */
    @Override
    public void registeredCode(String userName) {
        if (CheckNetwork.checkNetwork2(mContext)){
            loadDialog(ResUtil.getString(R.string.registered_tip_17));
            baseAction.getCode(userName);
        }
    }

    /**
     * 获取验证码成功
     */
    @Override
    public void CodeSuccessful(RegisteredCodeDto registeredCodeDto) {
        loadDiss();
        showNormalToast(registeredCodeDto.getMsg());
       if (registeredCodeDto.getCode() == 1){
           if (timer != null) {
               timer.cancel();
           }
           timer.start();
       }
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

    }


    /**************************************计时器 start*******************************************/
    class MyCountDownTimer extends CountDownTimer {
        public MyCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
            // TODO Auto-generated constructor stub

        }

        @Override
        public void onTick(long millisUntilFinished) {
            // TODO Auto-generated method stub
            getCodeTv.setEnabled(false);
            getCodeTv.setSelected(true);
            getCodeTv.setText(FormatUtils.format(getString(R.string.registered_tip_13), millisUntilFinished / 1000));
        }

        @Override
        public void onFinish() {
            // TODO Auto-generated method stub
            getCodeTv.setEnabled(true);
            getCodeTv.setSelected(false);
            getCodeTv.setText(R.string.registered_tip_14);
        }
    }
/*****************************************计时器 end**************************************************/

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
            if (timer != null) {
                timer.cancel();
            }
            if (baseAction != null) {
                baseAction.toUnregister();
            }
        }
}
