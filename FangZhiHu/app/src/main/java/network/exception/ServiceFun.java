package network.exception;

import com.tongqinyuan.fangzhihu.api.ApiResponse;

import io.reactivex.functions.Function;


/**
 * Created by yuantongqin on 2017/8/15.
 * modify by liyanxi on 2017/9/27.
 *
 */

public class ServiceFun<T> implements Function<ApiResponse<T>, ApiResponse<T>> {
    public static final String TAG = ServiceFun.class.getSimpleName();

    @Override
    public ApiResponse<T> apply(ApiResponse<T> tApiResponse) {

//        //这里是服务器返回的错误信息，密码错误，等等
//        if (tApiResponse.getCode() != 0) {
//            String msg = ErrorEvent.createMessage(tApiResponse.getCode()); //封装错误信息提示
//            // TODO: 2017/9/27 新增(status!=0)传递错误状态下data的传递
//            String data = "";
//            if(tApiResponse.getData() != null){
////                data = JsonUtils.serialize(tApiResponse.getData());
//                data = "";
//            }
//            throw new ServiceException(tApiResponse.getCode(), TextUtils.isEmpty(msg) ? tApiResponse.getMessage() : msg, data);
//        }
        return tApiResponse;
    }
}
