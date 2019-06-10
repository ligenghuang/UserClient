package com.yizhitong.userclient.ui;

import android.content.Intent;
import android.os.Handler;
import android.os.Process;
import android.os.Bundle;

import com.lgh.huanglib.util.base.ActivityStack;
import com.yizhitong.userclient.R;
import com.yizhitong.userclient.actions.BaseAction;
import com.yizhitong.userclient.ui.login.LoginActivity;
import com.yizhitong.userclient.utils.base.UserBaseActivity;

import java.lang.ref.WeakReference;
import java.util.List;

public class StartPageActivity extends UserBaseActivity {


    @Override
    public int intiLayout() {
        return R.layout.activity_start_page;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityStack.getInstance().addActivity(new WeakReference<>(this));
        binding();
    }

    @Override
    protected void init() {
        super.init();
        mActicity = this;
        mContext = this;
        mImmersionBar
                .statusBarView(R.id.top_view)
                .navigationBarWithKitkatEnable(false)
                .statusBarDarkFont(true)
                .addTag("StartPageActivity")  //给上面参数打标记，以后可以通过标记恢复
                .init();
    }

    @Override
    protected void initTitlebar() {
        super.initTitlebar();

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isNeedCheck) {
            checkPermissions(needPermissions);
        }
        List<String> needRequestPermissionList = findDeniedPermissions(needPermissions);
        if (null != needRequestPermissionList && needRequestPermissionList.size() > 0) {
        } else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    Intent intent = new Intent(mContext, LoginActivity.class);
                    startActivity(intent);
                    isNeedAnim = false;
                    finish();
                }
            }, 2000);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Process.killProcess(Process.myPid());
    }


    @Override
    protected BaseAction initAction() {
        return null;
    }
}
