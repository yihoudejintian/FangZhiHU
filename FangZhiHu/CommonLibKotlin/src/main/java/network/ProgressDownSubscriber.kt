package network

import io.reactivex.subscribers.ResourceSubscriber
import network.download.DownInfo
import network.download.DownInfo.DownState.*
import network.download.DownProgressListener

/**
 * Created by tongqinyuan on 2018/3/8.
 */
class ProgressDownSubscriber<T> :ResourceSubscriber<T> {
    var mDownInfo:DownInfo? = null
    constructor()
    constructor(downInfo: DownInfo){
        mDownInfo = downInfo
    }

    override fun onStart() {
        super.onStart()
        if(mDownInfo != null){
            mDownInfo!!.getListener()!!.onStart()
        }
        mDownInfo!!.state = START
    }



    override fun onNext(t: T) {

    }

    override fun onError(t: Throwable?) {
        if(mDownInfo != null){
            mDownInfo!!.getListener()!!.onError()
        }
        mDownInfo!!.state = ERROR
    }

    override fun onComplete() {
        if(mDownInfo != null){
            mDownInfo!!.getListener()!!.onComplete()
        }
        mDownInfo!!.state = COMPLETE
    }
}