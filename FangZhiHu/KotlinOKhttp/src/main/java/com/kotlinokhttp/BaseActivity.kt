package com.kotlinokhttp

import android.os.Bundle
import android.os.PersistableBundle
import android.view.ContextMenu
import android.view.View
import com.kotlinokhttp.dagger.component.ActivityComponent
import com.kotlinokhttp.dagger.component.DaggerActivityComponent
import com.ytq.main.common.IBaseActivity
import com.ytq.main.common.IPresenter
import javax.inject.Inject

/**
 * Created by tongqinyuan on 2018/3/9.
 */
abstract class BaseActivity<P : IPresenter> :IBaseActivity() {

    /**
     * @jvmField 是让属性变成public static静态属性
     */
    @JvmField
    @Inject
    var mPresenter: P? = null

//    @Inject
//    lateinit var mPresenter : P//延迟初始化

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        injectActivity()
        if (mPresenter != null) {
            mPresenter!!.AttachedView(this)
        }
    }


    fun getActivityComponent() : ActivityComponent{
        val activityComponent = DaggerActivityComponent.builder().run {
            this.appComponent(com.ytq.main.common.QianJiaApp.mInstance!!.getAppComponent())
            build()
        }
        return activityComponent

    }

    open fun injectActivity(){}

    abstract fun getLayoutId():Int


}