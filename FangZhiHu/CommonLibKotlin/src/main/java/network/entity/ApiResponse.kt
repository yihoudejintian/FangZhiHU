package network.entity

/**
 * Created by tongqinyuan on 2018/3/5.
 */
class ApiResponse<T> {


    var data: T? = null
    var code: Int = 0    // 返回码，0为成功
    var message: String? = null
    var status: Int = 0
    var errorCode: Int = 0
    var errorMsg: String? = null
    // 返回信息
    // 单个对象/数组对象

    constructor(data: T, message: String, status: Int) {
        this.data = data
        this.message = message
        this.code = status
    }

    constructor(status: Int, message: String) {
        this.code = status
        this.message = message
    }

    constructor(message: String) {
        this.message = message
    }

    override fun toString(): String {
        return "ApiResponse(data=$data, code=$code, message=$message, status=$status, errorCode=$errorCode, errorMsg=$errorMsg)"
    }


}