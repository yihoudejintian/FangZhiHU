package network

import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import io.reactivex.observers.ResourceObserver
import io.reactivex.subscribers.ResourceSubscriber
import network.converter.GsonConverterFactory
import network.entity.ApiResponse
import network.excepition.ErrorCode
import network.excepition.SendException
import java.io.IOException

/**
 * Created by tongqinyuan on 2018/3/7.
 */
abstract class BaseSubscriber<T> : ResourceSubscriber<T>() {
    private val TAG:String = BaseSubscriber::class.java.simpleName
    private var mGosn:Gson ?= null
    init {
        mGosn = GsonBuilder().create()
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onNext(t: T) {
        onSuccess(t)
    }

    override fun onComplete() {
        onFinish()
    }

    fun onFinish() {}

    override fun onError(t: Throwable?) {
        try {
            if (t is SendException) {
                val exce = t as SendException
                Log.e(TAG,  "===$exce.code=" + exce.data)
                if (exce.customState == ErrorCode.ERROR_STATE_ZERO) {
                    if (mGosn == null) {
                        mGosn = GsonBuilder().create()
                    }
                    val type = object : TypeToken<ApiResponse<Any>>() {

                    }.type
                    val error :ApiResponse<Any> = mGosn!!.fromJson(exce.data, type)
                    var da  = ""
                    if (error.data != null) {
                        da = error.data.toString()
                    }
                    onFailure(ApiResponse(da, error.message!!, exce.code))
                } else {
                    val error = ApiResponse<String>(exce.data!!, exce.msg!!, exce.code)
                    onFailure(error)
                }

            } else {
                var message = "请稍后再试"
                Log.e(TAG, t.toString())
                if (t is IOException) {
                    message = "网络链接异常，请稍后再试"
                }
                val error = ApiResponse<String>(ErrorCode.NETWORK_ERROR, message)
                onFailure(error)
            }
            onFinish()
        } catch (e1: Exception) {
            e1.printStackTrace()
        }


    }

    abstract fun onSuccess(t:T)

    abstract fun onFailure(error :ApiResponse<String>)
}