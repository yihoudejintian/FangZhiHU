package network.api

import io.reactivex.Flowable
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Streaming
import retrofit2.http.Url

/**
 * Created by tongqinyuan on 2018/3/9.
 * 用于下载
 */
interface ApiDownService {


    /**
     * 下载 文件
     * @Header("RANGE") String start, @Url String downloadUrl
     */
    @Streaming
    @GET
    fun downloadFile(@Header("RANGE")start:String,@Url downloadUrl:String) :Flowable<ResponseBody>


}