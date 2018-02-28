package network;


import java.util.HashMap;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.processors.FlowableProcessor;
import io.reactivex.processors.PublishProcessor;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by yuantongqin on 2017/8/8.
 */

public class RxBus {

    private static volatile RxBus mInstance;
    private FlowableProcessor mSubject;
    private HashMap<String, CompositeDisposable> mSubscriptionMap;

    /**
     *  PublishSubject只会把在订阅发生的时间点之后来自原始Observable的数据发射给观察者
     *  Subject同时充当了Observer和Observable的角色，Subject是非线程安全的，要避免该问题，
     *  需要将 Subject转换为一个 SerializedSubject ，上述RxBus类中把线程非安全的PublishSubject包装成线程安全的Subject。
     */
    private RxBus() {
        mSubject = PublishProcessor.create().toSerialized();
    }

    /**
     * 单例 双重锁
     * @return
     */
    public static RxBus getInstance() {
        if (mInstance == null) {
            synchronized (RxBus.class) {
                if (mInstance == null) {
                    mInstance = new RxBus();
                }
            }
        }
        return mInstance;
    }

    /**
     * 发送一个新的事件
     * @param o
     */
    public void OnEventPost(Object o) {
        mSubject.onNext(o);
    }

    //事件订阅
    public <T> Flowable<T> OnEventSubscibe(Class<T> tClass){
        return mSubject.ofType(tClass);
    }



    public <T> Disposable doSubscribe(Class<T> type, Consumer<T> next, Consumer<Throwable> error) {
        return OnEventSubscibe(type)
                .onBackpressureBuffer()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(next, error);
    }

    public void addSubscription(Object o, Disposable subscription) {

        if (mSubscriptionMap == null) {
            mSubscriptionMap = new HashMap<>();
        }
        String key = o.getClass().getName();
        if (mSubscriptionMap.get(key) != null) {
            mSubscriptionMap.get(key).add(subscription);
        } else {
            CompositeDisposable compositeSubscription = new CompositeDisposable();
            compositeSubscription.add(subscription);
            mSubscriptionMap.put(key, compositeSubscription);
        }
    }

    public void unSubscribe(Object o) {
        if (mSubscriptionMap == null) {
            return;
        }
        String key = o.getClass().getName();
        if (!mSubscriptionMap.containsKey(key)) {
            return;
        }
        if (mSubscriptionMap.get(key) != null) {
            mSubscriptionMap.get(key).dispose();
        }
        mSubscriptionMap.remove(key);
    }
}
