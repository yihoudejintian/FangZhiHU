package com.kotlinokhttp

import java.security.Key

/**
 * Created by tongqinyuan on 2018/3/6.
 */
class Sa(val value: Int) {

     var ss :String = "妮妮"

    companion object {
        @JvmField
        var string:String  = "哈哈哈";

        @JvmField
        val COMPARATOR: Comparator<Sa> = compareBy<Sa> { it.value }

        var name:String = "中国"
    }
}