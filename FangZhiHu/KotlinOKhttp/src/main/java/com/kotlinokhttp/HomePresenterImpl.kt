package com.kotlinokhttp

import android.util.Log
import com.kotlinokhttp.entity.ListData
import com.ytq.main.common.ApiMethodSourceImpl
import com.ytq.main.common.OnRequestListener
import network.entity.ApiResponse
import javax.inject.Inject

/**
 * Created by tongqinyuan on 2018/3/9.
 */
class HomePresenterImpl @Inject constructor(source: ApiMethodSourceImpl) : BasePresenter<HomeContact.View>(source),HomeContact.Presenter {
    private val TAG = HomePresenterImpl::class.java.simpleName

    override fun getHomeData() {
        getHttpData(mSource!!.getInfo(1), object : OnRequestListener<ListData> {
            override fun onSuccess(response: ListData) {
                Log.e(TAG, "==成功=" + response.toString())
            }

            override fun onFailure(errorApiResponse: ApiResponse<String>) {
                Log.e(TAG, "==失败=" + errorApiResponse.toString())
            }
        })
    }
}