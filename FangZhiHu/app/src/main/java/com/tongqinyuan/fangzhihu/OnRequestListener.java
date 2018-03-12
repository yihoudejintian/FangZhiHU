package com.tongqinyuan.fangzhihu;


import qj.ccmtjf.com.lib.api.ApiResponse;

/**
 * Created by tongqinyuan on 2017/11/4.
 */

public interface OnRequestListener<T> {

    /**
     * @param response 返回调用传入的泛型对象
     */
    void onSuccess(T response);

    void onFailure(ApiResponse<String> errorApiResponse);
}
