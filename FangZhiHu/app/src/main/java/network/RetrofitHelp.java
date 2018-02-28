package network;


import com.tongqinyuan.fangzhihu.BuildConfig;
import com.tongqinyuan.fangzhihu.api.LoanService;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import network.converter.GsonConverterFactory;
import network.intercepter.BaseIntercepter;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by yuantongqin on 2017/8/7.
 */

public class RetrofitHelp {


    private static RetrofitHelp mRetrofitHelp;
    private final Retrofit mRetrofit;
    private static Map<String, Object> mHeader = new HashMap<>();
    public final static String EMPTY = "";
    public final static String DOWNLOAD = "Download";
    private String mType = EMPTY;

    private final int WRITE_TIME_OUT = 30;
    private final int CONNECT_TIMIE_OUT = 30;
    public static final int READ_TIME_OUT = 30;

    public RetrofitHelp(String type) {
        mType = type;

        OkHttpClient.Builder okBuilder = new OkHttpClient.Builder();
        okBuilder.connectTimeout(CONNECT_TIMIE_OUT, TimeUnit.SECONDS);
        okBuilder.writeTimeout(WRITE_TIME_OUT, TimeUnit.SECONDS);
        okBuilder.readTimeout(READ_TIME_OUT, TimeUnit.SECONDS);

        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor log = new HttpLoggingInterceptor();
            log.setLevel(HttpLoggingInterceptor.Level.BODY);
            okBuilder.addInterceptor(log);
        }

        okBuilder.addInterceptor(new BaseIntercepter(mHeader, mType));
        OkHttpClient httpClient = okBuilder.build();

        Retrofit.Builder retrofitBuilder = new Retrofit.Builder();
        retrofitBuilder.client(httpClient);
        retrofitBuilder.baseUrl(LoanService.HOST);//todo 建议动态设置
        retrofitBuilder.addConverterFactory(ScalarsConverterFactory.create());
        retrofitBuilder.addConverterFactory(GsonConverterFactory.create());
        retrofitBuilder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        mRetrofit = retrofitBuilder.build();

    }


    public static void addHeader(String key, Object value) {
        if (key != null && value != null) {
            mHeader.put(key, value);
        }
    }

    public static void setHeader(Map<String, Object> header) {
        if (header != null && header.size() > 0) {
            mHeader.clear();
            mHeader.putAll(header);
        }
    }

    public Retrofit getRetrofit() {
        return mRetrofit;
    }


}
