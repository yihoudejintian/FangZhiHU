package network.excepition

import android.util.Log

import org.reactivestreams.Publisher

import java.net.ConnectException
import java.net.SocketTimeoutException
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

import io.reactivex.Flowable
import io.reactivex.functions.BiFunction
import io.reactivex.functions.Function


class RetryWhenNetworkException : Function<Flowable<out Throwable>, Flowable<*>> {
    //    retry次数
    private var count = 3
    //    延迟
    private var delay: Long = 3000
    //    叠加延迟
    private var increaseDelay: Long = 3000

    constructor() {

    }

    constructor(count: Int, delay: Long) {
        this.count = count
        this.delay = delay
    }

    constructor(count: Int, delay: Long, increaseDelay: Long) {
        this.count = count
        this.delay = delay
        this.increaseDelay = increaseDelay
    }


    @Throws(Exception::class)
    override fun apply(flowable: Flowable<out Throwable>): Flowable<*> {

        return flowable
                .zipWith(Flowable.range(1, count + 1), object : BiFunction<Throwable, Int, Wrapper> {
                    override fun apply(t1: Throwable, t2: Int): Wrapper {
                        return Wrapper(t1, t2)
                    }

                }).flatMap(Function<Wrapper, Publisher<*>> { wrapper ->
            if ((wrapper.throwable is ConnectException
                    || wrapper.throwable is SocketTimeoutException
                    || wrapper.throwable is TimeoutException) && wrapper.index < count + 1) { //如果超出重试次数也抛出错误，否则默认是会进入onCompleted
                Log.e("tag", "retry---->" + wrapper.index)
                return@Function Flowable.timer(delay + (wrapper.index - 1) * increaseDelay, TimeUnit.MILLISECONDS)

            }
             Flowable.error<Any>(wrapper.throwable)
        })
    }

    private inner class Wrapper( val throwable: Throwable,  val index: Int)

}
