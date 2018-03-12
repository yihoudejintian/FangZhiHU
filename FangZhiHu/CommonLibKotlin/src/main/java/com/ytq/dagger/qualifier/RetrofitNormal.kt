package com.ytq.dagger.qualifier

import javax.inject.Qualifier

/**
 * Created by tongqinyuan on 2018/3/9.
 * Qualifier（限定符），如果一个对象有多重实例方法
 * 如：public class A{
 *      public A(){}
 *      public A(String name)
 *      ...
 * }
 * 需要用过Qualifier 限制符做区分
 */
@Qualifier
@kotlin.annotation.Retention
@MustBeDocumented
annotation class RetrofitNormal