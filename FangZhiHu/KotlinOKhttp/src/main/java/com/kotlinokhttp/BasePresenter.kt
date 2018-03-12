package com.kotlinokhttp

import android.content.Context
import android.util.Log
import com.ytq.main.common.ApiMethodSourceImpl
import com.ytq.main.common.IPresenter
import com.ytq.main.common.OnRequestListener
import com.ytq.main.common.QianJiaApp
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import network.BaseSubscriber
import network.entity.ApiResponse
import network.excepition.ResultManageFun

/**
 * Created by tongqinyuan on 2018/3/9.
 */
abstract class BasePresenter<V>(source: ApiMethodSourceImpl) :IPresenter{

    private val TAG = BasePresenter::class.java.simpleName
    protected var mView: V? = null
    protected var mSubscriptions: CompositeDisposable? = CompositeDisposable()
    protected var mContext: Context? = null
    protected var mSource: ApiMethodSourceImpl? = null
    init {
        //获取主线程 初始化Handler对象
        mContext = QianJiaApp.mInstance
        this.mSource = source
    }

    override fun isAttachedView(): Boolean {
        return mView == null
    }


    override fun <Vv> AttachedView(view: Vv) {
        mView = view as V
    }

    override
    fun onDestory() {
        if (mContext != null) {
            mContext = null
        }
        if (mSubscriptions != null) {
            mSubscriptions!!.dispose()
        }
    }


    fun judgeSubscription(subscription: CompositeDisposable?): CompositeDisposable {
        return if (subscription == null || subscription.isDisposed) {
            CompositeDisposable()
        } else subscription
    }

    private fun <A> getNewObservable(oldObervable: Flowable<ApiResponse<A>>?): Flowable<ApiResponse<A>>? {
        return oldObervable?.onErrorResumeNext(ResultManageFun())?.subscribeOn(Schedulers.io())?.unsubscribeOn(Schedulers.io())?.observeOn(AndroidSchedulers.mainThread())
    }

    fun <A> getHttpData(oldObervable: Flowable<ApiResponse<A>>, listener: OnRequestListener<A>) {

        val observable = getNewObservable(oldObervable)
        val subscriber = object : BaseSubscriber<ApiResponse<A>>() {
            override fun onSuccess(apiResponse: ApiResponse<A>) {
                if (onInterceptResponse(apiResponse)) {
                    return
                }
                listener.onSuccess(apiResponse.data!!)
            }

           override fun onFailure(errorApiResponse: ApiResponse<String>) {
                if (onInterceptResponse(errorApiResponse)) {
                    return
                }
                listener.onFailure(errorApiResponse)
            }
        }
        mSubscriptions!!.add(observable!!.subscribeWith<BaseSubscriber<ApiResponse<A>>>(subscriber))
    }

    /**
     * 获取请求返回的原始信息
     *
     * @param oldObservable
     * @param listener
     * @param <A>
    </A> */
    fun <A> getOriginalHttpData(oldObservable: Flowable<ApiResponse<A>>, listener: OnRequestListener<ApiResponse<A>>) {

        val observable = getNewObservable(oldObservable)
        val subscriber = object : BaseSubscriber<ApiResponse<A>>() {
            override fun onSuccess(t: ApiResponse<A>) {
                if (onInterceptResponse(t)) {
                    return
                }
                if (t != null) {
                    listener.onSuccess(t)
                }
            }

           override fun onFailure(errorApiResponse: ApiResponse<String>) {
                if (onInterceptResponse(errorApiResponse)) {
                    return
                }
                listener.onFailure(errorApiResponse)
            }
        }
        mSubscriptions!!.add(observable!!.subscribeWith<BaseSubscriber<ApiResponse<A>>>(subscriber))
    }

    fun onInterceptResponse(apiResponse: ApiResponse<*>?): Boolean {
        if (apiResponse == null) {
            return true
        }
        val status = apiResponse.code
        //        if (status == ErrorEvent.AUTHTOKEN_EXPIRE_ERROR) {  //登录失败情况拦截
        //
        //            //登录状态重置
        //            LoanApp.getInstance().logout();
        //            mView.tokenExpireLogin();
        //            return true;
        //        }

        return false
    }


}