package network.intercepter;

import android.util.Log;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by tongqinyuan on 2018/3/7.
 */

public class BaseInter implements Interceptor {

    private final static String TAG = BaseInter.class.getSimpleName();

    /**
     * 拦截的目的
     * 请求：就是添加头部参数，修改参数，删除
     * 获取：获取到数据，进结果修改
     * @param chain
     * @return
     * @throws IOException
     */
    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();
        String method = request.method();
        if("get".equalsIgnoreCase(method)){

        }
        Log.e(TAG,"请求方法==method=="+method);
//        HttpUrl httpUrl = request.url();
//        //这个是对url操作，修改url中的参数
//        Request build = request.newBuilder().url(httpUrl).build();


        Request.Builder builder = request.newBuilder();
        Map<String, Object> header = addHeader();
        if(header != null && header.size() >0){

        }
        builder.addHeader("","");//添加头部参数
        Request request1 = builder.build();

        chain.proceed(request1);

        return null;
    }

    public Map<String,Object> addHeader(){
        Map<String,Object> header = new HashMap<>();

        return header;
    }
}
