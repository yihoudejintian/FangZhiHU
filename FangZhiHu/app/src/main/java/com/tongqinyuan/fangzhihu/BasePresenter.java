package com.tongqinyuan.fangzhihu;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;

import java.io.Serializable;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import network.BaseSubscriber;
import network.exception.ResultManageFun;
import qj.ccmtjf.com.lib.HttpDataSourceImpl;
import qj.ccmtjf.com.lib.LoanApp;
import qj.ccmtjf.com.lib.LogUtil;
import qj.ccmtjf.com.lib.api.ApiResponse;

/**
 * Created by tongqinyuan on 2017/11/6.
 */

public abstract class BasePresenter<V>  {
    private static final String TAG = BasePresenter.class.getSimpleName();
    protected V mView;
    protected CompositeDisposable mSubscriptions = new CompositeDisposable();
    protected Context mContext;
    protected Handler mDelivery;
    protected HttpDataSourceImpl mSource;

    public BasePresenter(HttpDataSourceImpl source) {
        this.mSource = source;
        //获取主线程 初始化Handler对象
        mDelivery = new Handler(Looper.getMainLooper());
        mContext = LoanApp.getInstance().getAppComponent().getContext();
    }

    public void onDestory() {
        if (mContext != null) {
            mContext = null;
        }
        if (mSubscriptions != null) {
            mSubscriptions.dispose();
        }
    }


    public CompositeDisposable judgeSubscription(CompositeDisposable subscription) {
        if (subscription == null || subscription.isDisposed()) {
            return new CompositeDisposable();
        }
        return subscription;
    }

    private <A> Flowable<ApiResponse<A>> getNewObservable(Flowable<ApiResponse<A>> oldObervable) {
        if (oldObervable != null) {//.map(new ServiceFun<>())
            return oldObervable
                    .onErrorResumeNext(new ResultManageFun<A>())
                    .subscribeOn(Schedulers.io())
                    .unsubscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());

        }
        return null;
    }

    public <A> void getHttpData(Flowable<ApiResponse<A>> oldObervable, final OnRequestListener<A> listener) {

        Flowable<ApiResponse<A>> observable = getNewObservable(oldObervable);
        BaseSubscriber<ApiResponse<A>> subscriber = new BaseSubscriber<ApiResponse<A>>() {
            @Override
            public void onSuccess(ApiResponse<A> apiResponse) {
                LogUtil.d(TAG, "==成功==" + apiResponse.toString());
                if (onInterceptResponse(apiResponse)) {
                    return;
                }
                listener.onSuccess(apiResponse.getData());
            }

            @Override
            public void onFailure(ApiResponse<String> errorApiResponse) {
                LogUtil.e(TAG, "=hibai==" + errorApiResponse.toString());

                if (onInterceptResponse(errorApiResponse)) {
                    return;
                }
                listener.onFailure(errorApiResponse);
            }
        };
        mSubscriptions.add(observable.subscribeWith(subscriber));
    }

    /**
     * 获取请求返回的原始信息
     *
     * @param oldObservable
     * @param listener
     * @param <A>
     */
    public <A> void getOriginalHttpData(Flowable<ApiResponse<A>> oldObservable, final OnRequestListener<ApiResponse<A>> listener) {

        Flowable<ApiResponse<A>> observable = getNewObservable(oldObservable);
        BaseSubscriber<ApiResponse<A>> subscriber = new BaseSubscriber<ApiResponse<A>>() {
            @Override
            public void onSuccess(ApiResponse<A> apiResponse) {
                if (onInterceptResponse(apiResponse)) {
                    return;
                }
                if (apiResponse != null) {
                    listener.onSuccess(apiResponse);
                }
            }

            @Override
            public void onFailure(ApiResponse<String> errorApiResponse) {
                if (onInterceptResponse(errorApiResponse)) {
                    return;
                }
                listener.onFailure(errorApiResponse);
            }
        };
        mSubscriptions.add(observable.subscribeWith(subscriber));
    }

    public boolean onInterceptResponse(ApiResponse apiResponse) {
        if (apiResponse == null) {
            return true;
        }
        int status = apiResponse.getCode();
//        if (status == ErrorEvent.AUTHTOKEN_EXPIRE_ERROR) {  //登录失败情况拦截
//
//            //登录状态重置
//            LoanApp.getInstance().logout();
//            mView.tokenExpireLogin();
//            return true;
//        }

        return false;
    }


}
