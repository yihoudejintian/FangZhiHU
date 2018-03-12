package network.api

import com.kotlinokhttp.entity.ListData
import io.reactivex.Flowable
import network.entity.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by tongqinyuan on 2018/3/5.
 */
interface ApiService {

    companion object {//伴生对象中的变量和函数是静态的
        val HOST = "http://www.wanandroid.com/"
    }



    @GET("article/list/{page}/json")
    fun getListInfo(@Path("page") page: Int): Flowable<ApiResponse<ListData>>


}