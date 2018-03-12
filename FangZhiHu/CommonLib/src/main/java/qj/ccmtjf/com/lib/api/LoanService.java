package qj.ccmtjf.com.lib.api;


import io.reactivex.Flowable;
import qj.ccmtjf.com.lib.entity.ListData;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by tongqinyuan on 2018/2/28.
 */

public interface LoanService {
//    http://www.wanandroid.com/article/list/1/json

    String HOST = "http://www.wanandroid.com/";
    @GET("article/list/{page}/json")
    Flowable<ApiResponse<ListData>> getListInfo(@Path("page") int page);

}
