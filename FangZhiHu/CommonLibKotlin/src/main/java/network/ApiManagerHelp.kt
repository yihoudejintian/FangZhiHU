package network

import network.api.ApiService

/**
 * Created by tongqinyuan on 2018/3/6.
 */
object ApiManagerHelp {

    private val operation = Any()

    private var mRetrofitHelp: RetrofitHelp? = null
    private var mRetrofitDownloan: RetrofitHelp? = null


    fun <T> getDownloadApiClass(tClass: Class<T>): T {
        synchronized(operation) {
            if (mRetrofitDownloan == null) {
                mRetrofitDownloan = RetrofitHelp(RetrofitHelp.DOWNLOAD)
            }

            return mRetrofitDownloan!!.mRetrofit.create(tClass)
        }

    }

    fun <T> getLoanService(tClass: Class<T>): T {
        synchronized(operation) {
            if (mRetrofitHelp == null) {
                mRetrofitHelp = RetrofitHelp(RetrofitHelp.EMPTY)
            }
            return mRetrofitHelp!!.mRetrofit.create(tClass)
        }
    }



}