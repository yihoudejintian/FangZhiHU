package network.download

/**
 * Created by tongqinyuan on 2018/3/7.
 */
interface DownProgressListener<T> {

    /**
     * 开始下载
     */
    fun onStart()

    /**
     * 完成
     */
    fun  onComplete()


    /**
     * 下载进度
     */
    fun updateProgress(readLength: Long, countLength: Long)

    /**
     * 出错
     */
    fun onError()
}