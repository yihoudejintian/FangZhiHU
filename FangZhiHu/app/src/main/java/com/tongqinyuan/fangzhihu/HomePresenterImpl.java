package com.tongqinyuan.fangzhihu;


import android.util.Log;

import javax.inject.Inject;

import qj.ccmtjf.com.lib.HttpDataSourceImpl;
import qj.ccmtjf.com.lib.LogUtil;
import qj.ccmtjf.com.lib.api.ApiResponse;
import qj.ccmtjf.com.lib.entity.ListData;

/**
 * 首页数据presenter实现
 * Created by liyanxi on 11/14/17.
 * Copyright (c) 2017 www.zhengshijr.com. All rights reserved.
 */

public class HomePresenterImpl extends BasePresenter<HomeContact.View> implements HomeContact.Presenter {
    private static final String TAG = HomePresenterImpl.class.getSimpleName();
    @Inject
    public HomePresenterImpl(HttpDataSourceImpl source) {
        super(source);
    }

    @Override
    public void getHomeData() {
        Log.e(TAG,"==发起请求");
        getHttpData(mSource.getInfo(1), new OnRequestListener<ListData>() {
            @Override
            public void onSuccess(ListData response) {
                Log.e(LogUtil.TAG,"==成功="+response.toString());
            }

            @Override
            public void onFailure(ApiResponse<String> errorApiResponse) {
                Log.e(LogUtil.TAG,"==失败="+errorApiResponse.toString());
            }
        });
    }
}
