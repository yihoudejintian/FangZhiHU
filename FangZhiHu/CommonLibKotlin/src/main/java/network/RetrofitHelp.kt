package network

import android.util.Log
import com.ytq.main.BuildConfig
import network.api.ApiService
import network.converter.GsonConverterFactory
import network.intercepter.BaseIntercepter
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by tongqinyuan on 2018/3/5.
 */
class RetrofitHelp(type: String = "") {
    private val TAG :String  = RetrofitHelp::class.java.simpleName
    val mRetrofit: Retrofit
    private val WRITE_TIME_OUT = 30
    private var mType = type

    companion object {
        val DOWNLOAD:String = "Download"
        val EMPTY :String = ""
    }

    init {//初始化代码库
        var builder = OkHttpClient().newBuilder().apply {
            connectTimeout(WRITE_TIME_OUT.toLong(),TimeUnit.SECONDS)
            readTimeout(WRITE_TIME_OUT.toLong(),TimeUnit.SECONDS)
            writeTimeout(WRITE_TIME_OUT.toLong(),TimeUnit.SECONDS)
        }
        if(BuildConfig.DEBUG){
            var httpLogging = HttpLoggingInterceptor()
            httpLogging.setLevel(HttpLoggingInterceptor.Level.BODY)
            builder.addInterceptor(httpLogging)
        }
        builder.addInterceptor(BaseIntercepter(mType))


        val okHttpClient = builder.build()

        var rbuilder = Retrofit.Builder().apply {
            client(okHttpClient)
            baseUrl(ApiService.HOST)
            addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            addConverterFactory(GsonConverterFactory.create())
        }
        mRetrofit = rbuilder.build()

    }




}