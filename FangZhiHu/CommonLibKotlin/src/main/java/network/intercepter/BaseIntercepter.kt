package network.intercepter

import android.util.Log
import com.ytq.main.BuildConfig
import network.download.DownResponseBody
import network.RetrofitHelp
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Created by tongqinyuan on 2018/3/7.
 */
class BaseIntercepter(type: String) :Interceptor {

    val mType :String = type
    private val TAG:String = BaseIntercepter::class.java.simpleName

    override fun intercept(chain: Interceptor.Chain?): Response {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.


        var request  = chain.run {
            val request = this!!.request()
            val method = request.method()
            if (!"get".equals(method,true)){
                if(BuildConfig.DEBUG){

                }
            }else{
                Log.e(TAG,"==get==请求==")
            }
            val newBuilder = request.newBuilder()
            val headers = addHeader()
            if (headers.isNotEmpty()) {
                val keys = headers.keys

                for (item in keys) {
                    val value = headers[item] ?: ""
                    newBuilder.addHeader(item, value.toString())
                }

            }


            newBuilder.build()

        }
        Log.e(TAG,"==请求参数="+request.url().query().toString())

        //如果是下载
        if(RetrofitHelp.DOWNLOAD.equals(mType,true)){
            val response = chain!!.proceed(request)
            return response.newBuilder().body(DownResponseBody(response.body()!!)).build()
        }

        return chain!!.proceed(request)


    }


    fun addHeader(): Map<String,Any>{
        val header = HashMap<String,Any>()
        return header
    }





}