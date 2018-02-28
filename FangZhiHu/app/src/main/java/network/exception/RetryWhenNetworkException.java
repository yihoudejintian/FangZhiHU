package network.exception;

import android.util.Log;

import org.reactivestreams.Publisher;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import io.reactivex.Flowable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;


public class RetryWhenNetworkException implements Function<Flowable<? extends Throwable>, Flowable<?>> {
//    retry次数
    private int count = 3;
//    延迟
    private long delay = 3000;
//    叠加延迟
    private long increaseDelay = 3000;

    public RetryWhenNetworkException() {

    }

    public RetryWhenNetworkException(int count, long delay) {
        this.count = count;
        this.delay = delay;
    }

    public RetryWhenNetworkException(int count, long delay, long increaseDelay) {
        this.count = count;
        this.delay = delay;
        this.increaseDelay = increaseDelay;
    }

//    @Override
//    public Observable<?> call(Observable<? extends Throwable> observable) {
//        return observable
//                .zipWith(Observable.range(1, count + 1), new Func2<Throwable, Integer, Wrapper>() {
//                    @Override
//                    public Wrapper call(Throwable throwable, Integer integer) {
//                        return new Wrapper(throwable, integer);
//                    }
//                }).flatMap(new Func1<Wrapper, Observable<?>>() {
//                    @Override
//                    public Observable<?> call(Wrapper wrapper) {
//                        if ((wrapper.throwable instanceof ConnectException
//                                || wrapper.throwable instanceof SocketTimeoutException
//                                || wrapper.throwable instanceof TimeoutException)
//                                && wrapper.index < count + 1) { //如果超出重试次数也抛出错误，否则默认是会进入onCompleted
//                            Log.e("tag","retry---->"+wrapper.index);
//                            return Observable.timer(delay + (wrapper.index - 1) * increaseDelay, TimeUnit.MILLISECONDS);
//
//                        }
//                        return Observable.error(wrapper.throwable);
//                    }
//                });
//    }

    @Override
    public Flowable<?> apply(Flowable<? extends Throwable> flowable) throws Exception {

        return flowable
                .zipWith(Flowable.range(1, count + 1), new BiFunction<Throwable, Integer, Wrapper>() {
                    @Override
                    public Wrapper apply(Throwable throwable, Integer integer) {
                        return new Wrapper(throwable, integer);
                    }
                }).flatMap(new Function<Wrapper, Publisher<?>>() {
                    @Override
                    public Publisher<?> apply(Wrapper wrapper) throws Exception {
                        if ((wrapper.throwable instanceof ConnectException
                                || wrapper.throwable instanceof SocketTimeoutException
                                || wrapper.throwable instanceof TimeoutException)
                                && wrapper.index < count + 1) { //如果超出重试次数也抛出错误，否则默认是会进入onCompleted
                            Log.e("tag","retry---->"+wrapper.index);
                            return Flowable.timer(delay + (wrapper.index - 1) * increaseDelay, TimeUnit.MILLISECONDS);

                        }
                        return Flowable.error(wrapper.throwable);
                    }
                });
    }

    private class Wrapper {
        private int index;
        private Throwable throwable;

        public Wrapper(Throwable throwable, int index) {
            this.index = index;
            this.throwable = throwable;
        }
    }

}
