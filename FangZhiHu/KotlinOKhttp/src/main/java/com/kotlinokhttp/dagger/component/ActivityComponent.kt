package com.kotlinokhttp.dagger.component

import com.kotlinokhttp.MainActivity
import com.ytq.dagger.component.AppComponent
import com.ytq.dagger.scope.ActivityScope
import dagger.Component

/**
 * Created by tongqinyuan on 2018/3/9.
 */
@Component(dependencies = arrayOf(AppComponent::class))
@ActivityScope
interface ActivityComponent {

    fun inject(mainActivity: MainActivity)
}