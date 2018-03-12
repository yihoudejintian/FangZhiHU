package com.ytq.dagger.module

import com.ytq.main.common.ApiMethodSource
import com.ytq.main.common.ApiMethodSourceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by tongqinyuan on 2018/3/9.
 * 用于提供 数据接口的实现类
 */
@Module
class CommonModule {


    /**
     * 数据接口的实现类
     */
    @Singleton
    @Provides
    fun provideHttpDataSource(httpDataSource: ApiMethodSourceImpl): ApiMethodSource {
        return httpDataSource
    }


}