package com.ytq.main.common


/**
 * Created by tongqinyuan on 2017/11/6.
 */

interface IPresenter {

    fun isAttachedView(): Boolean

    fun onDestory()

    fun <Vv> AttachedView(view: Vv)
}
