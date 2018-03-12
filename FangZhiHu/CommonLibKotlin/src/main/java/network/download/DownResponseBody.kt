package network.download

import network.RxBus
import network.download.DownInfo
import okhttp3.MediaType
import okhttp3.ResponseBody
import okio.*

/**
 * Created by tongqinyuan on 2018/3/7.
 */
class DownResponseBody(responseBody: ResponseBody) : ResponseBody() {
    private val mDownInfo :DownInfo
    init {
        mDownInfo =  DownInfo("","")
    }
    private var bufferedSource: BufferedSource? = null
    var mBody:ResponseBody = responseBody


    override fun contentLength(): Long {
        return mBody.contentLength()
    }

    override fun contentType(): MediaType? {
        return mBody.contentType()
    }

    override fun source(): BufferedSource {
        if(bufferedSource == null){
            bufferedSource = Okio.buffer(getSource(mBody.source()))
        }
       return bufferedSource!!

    }

    fun getSource(source: Source):Source{

        var s = object :ForwardingSource(source){
            internal var totalBytesRead = 0L
            override fun read(sink: Buffer?, byteCount: Long): Long {
                var byteread :Long = super.read(sink, byteCount)
                totalBytesRead += if (byteread != -1L) byteread else 0
                mDownInfo.read = totalBytesRead
                mDownInfo.count = mBody.contentLength()
                mDownInfo.complete = byteread == -1L
                RxBus.getInstance!!.OnEventPost(mDownInfo)
                return byteread
            }

        }
        return s
    }



}