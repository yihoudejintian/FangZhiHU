package com.ytq.dagger.component

import com.ytq.dagger.module.CommonModule
import com.ytq.dagger.module.HttpModule
import com.ytq.main.common.ApiMethodSourceImpl
import com.ytq.main.common.QianJiaApp
import dagger.Component
import network.api.ApiDownService
import network.api.ApiService
import javax.inject.Singleton

/**
 * Created by tongqinyuan on 2018/3/9.
 */
@Singleton
@Component(modules = arrayOf(HttpModule::class, CommonModule::class))
interface AppComponent {

     fun inject(app: QianJiaApp)
    //获取基本数据实现类
    val getApiMethodSourceImpl: ApiMethodSourceImpl
//
    //获取基本数据接口
    val getApiService: ApiService

    //获取下载数据接口
    val getApiDownService: ApiDownService


}