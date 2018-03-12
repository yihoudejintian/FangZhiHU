package network.excepition


/**
 * Created by tongqinyuan on 2018/3/7.
 * 这个是用来传递消息的错误类
 */
class SendException : Exception {

    var code:Int = 0
    var msg:String? = null
    var data:String? = null
        private set
    var customState:Int = 0


    constructor(throwable:Throwable , code:Int ) :super(throwable){

    }


    constructor(throwable:Throwable , code:Int , msg:String ):super(throwable){
        this.code = code
        this.msg = msg
    }

    constructor(throwable:Throwable , code:Int , msg:String, data:String ):super(throwable){
        this.code = code
        this.msg = msg
        this.data = data
    }

    constructor(throwable:Throwable , code:Int , msg:String,data:String,customState:Int ):super(throwable){
        this.customState = customState;
        this.code = code;
        this.msg = msg;
        this.data = data;
    }






}