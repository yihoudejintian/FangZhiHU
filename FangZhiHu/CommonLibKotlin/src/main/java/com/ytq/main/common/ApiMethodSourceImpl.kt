package com.ytq.main.common

import com.kotlinokhttp.entity.ListData
import io.reactivex.Flowable
import network.api.ApiService
import network.api.ApiDownService
import network.entity.ApiResponse
import javax.inject.Inject

/**
 * Created by tongqinyuan on 2018/3/9.
 */
class  ApiMethodSourceImpl
@Inject constructor(private var mApiMethodSource : ApiService?,
                    private var mDownApiMethod:ApiDownService?) :ApiMethodSource {


    override fun getInfo(page: Int): Flowable<ApiResponse<ListData>> {
        return mApiMethodSource!!.getListInfo(page)
    }


}