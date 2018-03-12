package network.excepition

import java.lang.RuntimeException

/**
 * Created by tongqinyuan on 2018/3/7.
 */
class ServiceException :RuntimeException {
     var code: Int = 0
        private set
    var customState: Int = 0

     var messages: String? = null
        private set

     var data: String? = null
        private set


    constructor(code:Int,msg:String,data:String){
        this.code = code
        this.messages = msg
        this.data = data
    }
    constructor(code:Int,data:String){
        this.code = code
        this.data = data
    }
    constructor(code:Int,customState:Int,data:String){
        this.customState = customState
        this.code = code
        this.data = data
    }
}