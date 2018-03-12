package com.tongqinyuan.fangzhihu;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import javax.inject.Inject;

import dagger.component.ActivityComponent;
import dagger.component.DaggerActivityComponent;
import qj.ccmtjf.com.lib.LoanApp;

/**
 * Created by tongqinyuan on 2018/3/8.
 */

public abstract class BaseActivity<P> extends AppCompatActivity {
    private static final String TAG = BaseActivity.class.getSimpleName();
    @Inject
    public P mPresenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        injectActivity();

    }

    public void injectActivity() {
    }

    public ActivityComponent getActivityComponent() {
        ActivityComponent activityComponent = DaggerActivityComponent.builder()
                .appComponent(LoanApp.getInstance().getAppComponent()).build();
        Log.e(TAG,"==一个初始化="+activityComponent);
        return activityComponent;
    }

    abstract int getLayoutId();

}
