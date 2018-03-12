package com.ytq.dagger.module

import com.ytq.dagger.qualifier.RetrofitDown
import com.ytq.dagger.qualifier.RetrofitNormal
import com.ytq.main.BuildConfig
import dagger.Module
import dagger.Provides
import network.api.ApiService
import network.api.ApiDownService
import network.converter.GsonConverterFactory
import network.intercepter.BaseIntercepter
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Created by tongqinyuan on 2018/3/9.
 * module工厂用于提供实例,提供实例需要使用provide标注
 * 同时方法名必须以provide作为前缀，component才能正确的检索到
 */
@Module
class HttpModule {

    private val write_time: Long = 30
    private val conn_time: Long = 30
    private val read_time: Long = 30


    @Singleton
    @Provides
    fun provideOkHttpClientBuilder(): OkHttpClient.Builder {
        var builder = OkHttpClient.Builder().apply {
            connectTimeout(conn_time,TimeUnit.SECONDS)
            writeTimeout(write_time,TimeUnit.SECONDS)
            readTimeout(read_time,TimeUnit.SECONDS)
            if(BuildConfig.DEBUG){
                var logging = HttpLoggingInterceptor()
                logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                addInterceptor(logging)
            }
        }
        return builder
    }

    @Singleton
    @Provides
    fun provideRetrofitBuilder():Retrofit.Builder{
        var builder = Retrofit.Builder()
        return builder
    }

    @Singleton
    @Provides
    @RetrofitNormal
    fun provideRetrofitNormal(retrofit: Retrofit.Builder,client: OkHttpClient.Builder) : Retrofit{
        client.addInterceptor(BaseIntercepter(""))
        val okHttpClient = client.build()
        retrofit.baseUrl(ApiService.HOST)
        retrofit.client(okHttpClient)
        retrofit.addConverterFactory(GsonConverterFactory.create())
        retrofit.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        return retrofit.build()
    }

    @Singleton
    @Provides
    @RetrofitDown
    fun provideRetrofitDown(retrofit: Retrofit.Builder,client: OkHttpClient.Builder) :Retrofit{
        client.addInterceptor(BaseIntercepter("downloan"))
        val okHttpClient = client.build()
        retrofit.baseUrl(ApiService.HOST)//这里可以配置其他host
        retrofit.client(okHttpClient)
        retrofit.addConverterFactory(GsonConverterFactory.create())
        retrofit.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        return retrofit.build()
    }

    /**
     * 生成apiService 接口实例
     */
    @Singleton
    @Provides
    fun provideApiService(@RetrofitNormal retrofit: Retrofit): ApiService{
        return retrofit.create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideDownUploadService(@RetrofitDown retrofit: Retrofit) :ApiDownService{
        return retrofit.create(ApiDownService::class.java)
    }



}