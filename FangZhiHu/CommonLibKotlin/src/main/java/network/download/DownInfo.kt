package network.download

/**
 * Created by tongqinyuan on 2018/3/7.
 */
data class DownInfo(var savaPath:String,var mDownloadUrl:String) {

    var read: Long = 0L//下载的长度
    var count: Long = 0L//总长度
    var complete: Boolean = false//是个完成
    var state:DownState? = null

    private var mListener :DownProgressListener<*>? = null

    fun getListener() :DownProgressListener<*>?{
        return mListener
    }

    fun setListener(listener: DownProgressListener<*>){
        this.mListener = listener
    }

    enum class DownState{
        START,
        PAUSE,
        ERROR,
        COMPLETE
    }


}