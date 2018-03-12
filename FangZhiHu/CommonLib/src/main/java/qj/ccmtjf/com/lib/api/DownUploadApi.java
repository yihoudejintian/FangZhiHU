package qj.ccmtjf.com.lib.api;



import java.io.File;
import java.util.List;

import io.reactivex.Flowable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * Created by yuantongqin on 2017/8/10.
 */

public interface DownUploadApi {



    /**
     * 下载文件
     *
     * @param downloadUrl  下载地址
     */
    @Streaming
    @GET
    Flowable<ResponseBody> downloadFile(@Header("RANGE") String start, @Url String downloadUrl);

    /******************SplashApi****************/

    /**
     * <pre>
     * 图片上传
     * HTTP请求方式: POST
     * </pre>
     *
     * @param file                  ture 	string 	图片名字
     * List<ImagesUpload>类型数据- status 	返回结果状态。0：正常；其他：错误。
     */
    @POST("api/upload/imagesUpload")
    Flowable<ApiResponse<List<Object>>> imagesUpload(File file);





}
