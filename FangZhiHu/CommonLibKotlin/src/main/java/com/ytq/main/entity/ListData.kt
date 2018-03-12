package com.kotlinokhttp.entity

/**
 * Created by tongqinyuan on 2018/3/5.
 */

class ListData {

    /**
     * offset: 20,
     * over: false,
     * pageCount: 55,
     * size: 20,
     * total: 1084
     * curPage: 2,
     * datas:
     */

    var offset: Int = 0//: 20,
    var over: Boolean = false//: false,
    var pageCount: Int = 0//: 55,
    var size: Int = 0//: 20,
    var total: Int = 0//: 1084
    var curPage: Int = 0//: 2,
    var datas: List<ListItem>? = null//

    override fun toString(): String {
        return "ListData{" +
                "offset=" + offset +
                ", over=" + over +
                ", pageCount=" + pageCount +
                ", size=" + size +
                ", total=" + total +
                ", curPage=" + curPage +
                ", datas=" + datas +
                '}'
    }
}
