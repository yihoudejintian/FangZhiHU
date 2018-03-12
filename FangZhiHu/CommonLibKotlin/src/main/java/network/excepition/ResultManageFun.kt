package network.excepition

import io.reactivex.Flowable
import io.reactivex.functions.Function
import network.entity.ApiResponse

/**
 * Created by tongqinyuan on 2018/3/7.
 */
class ResultManageFun<T> :Function<Throwable,Flowable<ApiResponse<T>>> {
    override fun apply(t: Throwable): Flowable<ApiResponse<T>> {

        //log.e(TAG, "=跑出异常==" + throwable.getMessage());
        t.printStackTrace()
        return Flowable.error(ExceptionEngine.handleException(t))
    }
}