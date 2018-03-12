package com.ytq.main.common

import network.entity.ApiResponse

/**
 * Created by tongqinyuan on 2018/3/9.
 */
interface OnRequestListener<T> {

    /**
     * @param response 返回调用传入的泛型对象
     */
     fun onSuccess(response: T)

     fun onFailure(errorApiResponse: ApiResponse<String>)

}