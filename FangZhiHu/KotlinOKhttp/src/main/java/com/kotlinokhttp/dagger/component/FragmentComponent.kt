package com.kotlinokhttp.dagger.component

import com.ytq.dagger.component.AppComponent
import com.ytq.dagger.scope.FragmentScope
import dagger.Component

/**
 * Created by tongqinyuan on 2018/3/9.
 */
@FragmentScope
@Component(dependencies = arrayOf(AppComponent::class))
interface FragmentComponent {
}