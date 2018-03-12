package network.test

import network.RetrofitHelp
import network.api.ApiService

/**
 * Created by tongqinyuan on 2018/3/6.
 */
object ApiHelp {


    private val operation = Any()

    private var retrofitHelp: RetrofitHelp? = null
    private var retrofitDownload: RetrofitHelp? = null

    //获取api
    val apiClass: ApiService
        get() {
            synchronized(operation) {
                if (retrofitHelp == null) {
                    retrofitHelp = RetrofitHelp(RetrofitHelp.EMPTY)
                }
                return retrofitHelp!!.mRetrofit.create(ApiService::class.java)
            }
        }


    //获取下载api
    fun <T> getDownloadApiClass(tClass: Class<T>): T {
        synchronized(operation) {
            if (retrofitDownload == null) {
                retrofitDownload = RetrofitHelp(RetrofitHelp.DOWNLOAD)
            }
            return retrofitDownload!!.mRetrofit.create(tClass)
        }
    }

}