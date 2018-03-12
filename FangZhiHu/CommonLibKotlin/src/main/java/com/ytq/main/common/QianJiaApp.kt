package com.ytq.main.common

import android.app.Application
import com.ytq.dagger.component.AppComponent
import com.ytq.dagger.component.DaggerAppComponent
import com.ytq.dagger.module.CommonModule
import com.ytq.dagger.module.HttpModule

/**
 * Created by tongqinyuan on 2018/3/9.
 */
class QianJiaApp :Application() {

    private var appComponent: AppComponent? = null

    companion object {
        /**
         * 得到单例的application对象
         */
        var mInstance: QianJiaApp? = null
            private set //全局应用
    }
    override fun onCreate() {
        super.onCreate()
        mInstance = this
        initAppComponent()
    }

    /**
     * 初始化公用实例
     */

    private fun initAppComponent(){
        appComponent = DaggerAppComponent.builder().run {
            this.httpModule(HttpModule())
            commonModule(CommonModule())
            build()
        }
    }

    fun getAppComponent():AppComponent{
        return appComponent!!
    }

}