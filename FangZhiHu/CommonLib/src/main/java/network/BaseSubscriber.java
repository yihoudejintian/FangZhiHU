package network;


import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;

import io.reactivex.subscribers.ResourceSubscriber;
import network.exception.ErrorCode;
import network.exception.SendException;
import qj.ccmtjf.com.lib.api.ApiResponse;


/**
 * 网络请求返回数据分发处理
 * Created by yuantongqin on 2017/8/7.
 * modify by liyanxi on 2017/9/27.{@link }新增后端data数据传递子类
 */

public abstract class BaseSubscriber<T> extends ResourceSubscriber<T> {
    private static final String TAG = BaseSubscriber.class.getSimpleName();
    private  Gson gson;

    public BaseSubscriber() {
        super();
        gson = new GsonBuilder().create();
    }

    @Override
    public void onStart() {
        super.onStart();
        //检查是否有网络的信息

    }

    @Override
    public void onComplete() {
        onFinish();
    }

    public abstract void onSuccess(T t);

    public abstract void onFailure(ApiResponse<String> error);

    public void onFinish() {
    }

    @Override
    public void onNext(T t) {
        onSuccess(t);
    }

    @Override
    public void onError(Throwable e) {
        //同意处理错误信息
        try{
            if (e instanceof SendException) {
                SendException exce = (SendException) e;
                Log.e(TAG, exce.getCode() + "====" + exce.getData());
                if(exce.customState == ErrorCode.ERROR_STATE_ZERO){
                    if(gson == null){
                        gson = new GsonBuilder().create();
                    }
                    Type type = new TypeToken<ApiResponse<Object>>() {
                    }.getType();
                    ApiResponse<Object> error = gson.fromJson(exce.getData(), type);
                    String da = "";
                    if(error.getData() != null){
                        da = error.getData().toString();
                    }
                    onFailure(new ApiResponse<>(da, error.getMessage(), exce.getCode()));
                }else{
                    ApiResponse<String> error = new ApiResponse<>(exce.getData(), exce.getMsg(), exce.getCode());
                    onFailure(error);
                }

            } else {
                String message = "请稍后再试";
                Log.e(TAG,e.toString());
                if (e instanceof IOException) {
                    message = "网络链接异常，请稍后再试";
                }
                ApiResponse<String> error = new ApiResponse<>(ErrorCode.NETWORK_ERROR, message);
                onFailure(error);
            }
            onFinish();
        }catch (Exception e1){
            e1.printStackTrace();
        }


    }


}
