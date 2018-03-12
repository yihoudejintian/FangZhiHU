package com.kotlinokhttp

import com.ytq.main.common.IPresenter

/**
 * Created by tongqinyuan on 2018/3/9.
 */
interface HomeContact {

    interface View {
        fun requestDataSuccess(homeDetailEntity: Any)
    }

    interface Presenter :IPresenter {
        fun getHomeData()

    }
}