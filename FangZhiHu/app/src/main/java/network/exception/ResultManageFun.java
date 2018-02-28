package network.exception;


import com.tongqinyuan.fangzhihu.api.ApiResponse;
import com.tongqinyuan.fangzhihu.common.LogUtil;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;

/**
 * Created by yuantongqin on 2017/8/15.
 */

public class ResultManageFun<T> implements Function<Throwable, Flowable<ApiResponse<T>>> {
    private static final String TAG = ResultManageFun.class.getSimpleName();

    @Override
    public Flowable<ApiResponse<T>> apply(Throwable throwable) {
        LogUtil.e(TAG, "=跑出异常==" + throwable.getMessage());
        throwable.printStackTrace();
        return Flowable.error(ExceptionEngine.handleException(throwable));
    }

}
