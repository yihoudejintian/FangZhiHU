package com.tongqinyuan.fangzhihu;

import android.database.Observable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;


import dagger.component.AppComponent;
import io.reactivex.Flowable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import network.BaseSubscriber;
import network.api.ApiManagerHelp;
import qj.ccmtjf.com.lib.HttpDataSourceImpl;
import qj.ccmtjf.com.lib.LoanApp;
import qj.ccmtjf.com.lib.LogUtil;
import qj.ccmtjf.com.lib.api.ApiResponse;
import qj.ccmtjf.com.lib.api.LoanService;
import qj.ccmtjf.com.lib.entity.ListData;
import retrofit2.Retrofit;

public class MainActivity extends BaseActivity<HomePresenterImpl> {
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void injectActivity() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG,"==执行了吗==");


    }



    public void requestData(View view){


        AppComponent appComponent = LoanApp.getInstance().getAppComponent();

        HttpDataSourceImpl sourceImpl = appComponent.getSourceImpl();
        Log.e(TAG,"==sourceImp=="+sourceImpl);
        Retrofit retrofit = appComponent.getRetrofit();
        LoanService loanService = appComponent.getLoanService();

        Log.e(TAG,loanService+"==retrofit=="+retrofit);
        mPresenter.getHomeData();

//        LoanService apiClass = ApiManagerHelp.getApiClass();
//        final Flowable<ApiResponse<ListData>> listInfo = apiClass.getListInfo(1);
//        BaseSubscriber<ApiResponse<ListData>> subscriber = new BaseSubscriber<ApiResponse<ListData>>() {
//            @Override
//            public void onSuccess(ApiResponse<ListData> listItem) {
//
//                Log.e("tests","==内容=="+listItem.toString());
//
//
//            }
//
//            @Override
//            public void onFailure(ApiResponse<String> error) {
//                Log.e("tests","==失败=="+error.toString());
//
//            }
//        };
//        listInfo.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribeWith(subscriber);

    }
}
