package network.excepition

import com.google.gson.JsonParseException
import org.json.JSONException
import retrofit2.HttpException
import java.net.ConnectException
import java.text.ParseException

/**
 * Created by tongqinyuan on 2018/3/7.
 * 解析网络返回数据错误类型
 *
 */
class ExceptionEngine {

    object ERROR {
        /**
         * 未知错误
         */
        val UNKNOWN = 1000
        /**
         * 解析错误
         */
        val PARSE_ERROR = 1001
        /**
         * 网络错误
         */
        val NETWORD_ERROR = 1002
        /**
         * 协议出错
         */
        val HTTP_ERROR = 1003
    }

    companion object {

        private val UNAUTHORIZED = 401
        private val FORBIDDEN = 403
        private val NOT_FOUND = 404
        private val REQUEST_TIMEOUT = 408
        private val INTERNAL_SERVER_ERROR = 500
        private val BAD_GATEWAY = 502
        private val SERVICE_UNAVAILABLE = 503
        private val GATEWAY_TIMEOUT = 504

        fun handleException(e:Throwable) : SendException {
            val ex:SendException
            if(e is HttpException){
                ex = SendException(e,ERROR.HTTP_ERROR)
                ex.msg = ""
                when(e.code()){
                    UNAUTHORIZED,
                    FORBIDDEN,
                            NOT_FOUND,
                            REQUEST_TIMEOUT,
                            GATEWAY_TIMEOUT,
                            INTERNAL_SERVER_ERROR,
                            BAD_GATEWAY,
                            SERVICE_UNAVAILABLE ->
                    ex.msg = "网络链接异常，请检查"  //均视为网络错误
                    else -> ex.msg = "网络链接异常，请检查"
                }
                return ex
            }else if (e is ServiceException){//服务器返回的错误
                ex = SendException(e,e.code, e.messages!!, e.data!!,e.customState)
                return ex
            }else if(e is JsonParseException
                    || e is JSONException
                    || e is ParseException){//均视为解析错误
                ex = SendException(e,ERROR.PARSE_ERROR,"解析异常")
                return ex
            } else if (e is ConnectException) {//均视为网络错误
                ex = SendException(e, ERROR.NETWORD_ERROR, "网络链接异常，请稍后再试")
                return ex
            }else{
                 ex = SendException(e, ERROR.UNKNOWN, "服务器异常，请稍后再试")
                return ex
            }

        }
    }


}