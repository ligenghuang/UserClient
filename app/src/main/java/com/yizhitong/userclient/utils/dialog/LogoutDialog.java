package com.yizhitong.userclient.utils.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;
import android.view.Window;


import com.yizhitong.userclient.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
* 退出登录
* @author lgh
* created at 2019/5/17 0017 9:59
*/
public class LogoutDialog extends Dialog {

    Context context;


    OnClickListener onClickListener;

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public LogoutDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_logout_dialog);
        ButterKnife.bind(this);
        setCanceledOnTouchOutside(true);
        Window win = this.getWindow();
        win.setGravity(Gravity.CENTER);

    }

    @OnClick({R.id.tv_cancel,R.id.tv_confirm})
    void OnClick(View view){
        switch (view.getId()){
            case R.id.tv_cancel:
                dismiss();
                break;
            case R.id.tv_confirm:
                onClickListener.confirm();
                break;
        }
    }

    public interface OnClickListener{
        void confirm();
    }

}
