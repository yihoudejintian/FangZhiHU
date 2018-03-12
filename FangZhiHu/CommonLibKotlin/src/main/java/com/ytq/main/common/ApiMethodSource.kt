package com.ytq.main.common

import com.kotlinokhttp.entity.ListData
import io.reactivex.Flowable
import network.entity.ApiResponse

/**
 * Created by tongqinyuan on 2018/3/9.
 *
 * 用于声明请求数据的方法
 */
interface ApiMethodSource {

    fun getInfo(page:Int) : Flowable<ApiResponse<ListData>>

}