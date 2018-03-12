package dagger.module;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.qualifier.Down;
import dagger.qualifier.Normal;
import network.converter.GsonConverterFactory;
import network.intercepter.BaseIntercepter;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import qj.ccmtjf.com.lib.BuildConfig;
import qj.ccmtjf.com.lib.api.LoanService;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * Created by tongqinyuan on 2017/12/2.
 */

@Module
public class HttpModule {

    private final int WRITE_TIME_OUT = 30;
    private final int CONNECT_TIMIE_OUT = 30;
    public static final int READ_TIME_OUT = 30;

    @Provides
    @Singleton //提供okhttpclint.builder
    public OkHttpClient.Builder provideOkhttpClickBuilder(){

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.writeTimeout(WRITE_TIME_OUT, TimeUnit.SECONDS);
        builder.readTimeout(READ_TIME_OUT,TimeUnit.SECONDS);
        builder.connectTimeout(CONNECT_TIMIE_OUT,TimeUnit.SECONDS);

        if(BuildConfig.DEBUG){
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
            builder.addInterceptor(interceptor);
        }
        return builder;
    }


    @Provides
    @Singleton
    public Retrofit.Builder provideRetrofitBuilder(){
        return new Retrofit.Builder();
    }


    @Provides
    @Singleton
    public Retrofit provideNormalRetrofit(Retrofit.Builder builder,OkHttpClient.Builder client){
        client.addInterceptor(new BaseIntercepter(""));
        OkHttpClient httpClient = client.build();

        builder.client(httpClient);
        builder.baseUrl(LoanService.HOST);//todo 建议动态设置
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        return builder.build();
    }
//
//    @Provides
//    @Singleton
//    @Down
//    public Retrofit provideDownRetrofit(Retrofit.Builder builder,OkHttpClient.Builder client){
//        client.addInterceptor(new BaseIntercepter("download"));
//        OkHttpClient httpClient = client.build();
//        builder.client(httpClient);
//        builder.baseUrl(LoanService.HOST);//todo 建议动态设置
//        builder.addConverterFactory(GsonConverterFactory.create());
//        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
//        return builder.build();
//    }

    @Provides
    @Singleton
    public LoanService provideLoanService(Retrofit retrofit){
        return retrofit.create(LoanService.class);
    }
//
//    @Provides
//    @Singleton
//    public DownUploadApi provideZSDownUploadApi(@Down Retrofit retrofit){
//        return retrofit.create(DownUploadApi.class);
//    }
}
