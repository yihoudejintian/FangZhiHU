package network

import io.reactivex.Flowable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.processors.FlowableProcessor
import io.reactivex.processors.PublishProcessor
import io.reactivex.schedulers.Schedulers
import java.util.HashMap

/**
 * Created by tongqinyuan on 2018/3/7.
 * 单列模式的Rxbus
 */
class RxBus private constructor(){

    private var mSubject:FlowableProcessor<Any>? = null
    private var mSubscriptionMap: HashMap<String, CompositeDisposable>? = null

    companion object {
        //单例模式
        @Volatile private var mInstance :RxBus? = null

        val getInstance : RxBus?
        get() {
            if(mInstance == null){
                synchronized(RxBus::class.java){
                    if(mInstance == null){
                        mInstance = RxBus()
                    }

                }
            }
            return mInstance
        }
    }

    init {
        mSubject = PublishProcessor.create<Any>().toSerialized()
    }

    /**
     * 发送一个事件
     */
    fun OnEventPost(any: Any){
        mSubject!!.onNext(any)
    }

    /**
     * 订阅一本杂志
     */
    fun <T> OnEventSubscibe(tClass:Class<T>) : Flowable<T> {
        return mSubject!!.ofType(tClass)
    }

    fun <T> doSubscribe(type:Class<T>,next:Consumer<T>,error:Consumer<Throwable>) : Disposable {
        return OnEventSubscibe(type)
                .onBackpressureBuffer()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(next,error)
    }

    /**
     *
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
     */


    /**
     * 添加到订阅管理
     */
    fun addSubscription(any: Any,disposable: Disposable){
        if(mSubscriptionMap == null){
            mSubscriptionMap = HashMap()
        }
        var key = any::class.java.name
        if(mSubscriptionMap!!.get(key) != null){
            mSubscriptionMap!!.get(key)!!.add(disposable)
        }else{
            var compositeSubscription = CompositeDisposable()
            compositeSubscription.add(disposable)
            mSubscriptionMap!!.put(key,compositeSubscription)
        }


    }

    /**
     * 取消订阅
     */
    fun unSubscribe(any: Any){
        if(mSubscriptionMap == null){
            return
        }
        var key = any::class.java.name
        if(!mSubscriptionMap!!.containsKey(key)){
            return
        }
        if (mSubscriptionMap!!.get(key) != null) {
            mSubscriptionMap!!.get(key)!!.dispose()
        }
        mSubscriptionMap!!.remove(key)

    }






}