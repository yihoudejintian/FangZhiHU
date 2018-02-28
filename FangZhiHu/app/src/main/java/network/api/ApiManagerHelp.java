package network.api;


import com.tongqinyuan.fangzhihu.api.LoanService;

import network.RetrofitHelp;

/**
 * Created by yuantongqin on 2017/8/7.
 */

public class ApiManagerHelp {


    private final static Object operation = new Object();

    private static RetrofitHelp retrofitHelp;
    private static RetrofitHelp retrofitDownload;


    //获取下载api
    public static <T> T getDownloadApiClass(Class<T> tClass) {
        synchronized (operation) {
            if (retrofitDownload == null) {
                retrofitDownload = new RetrofitHelp(RetrofitHelp.DOWNLOAD);
            }
            return retrofitDownload.getRetrofit().create(tClass);
        }
    }

    //获取api
    public static LoanService getApiClass() {
        synchronized (operation) {
            if (retrofitHelp == null) {
                retrofitHelp = new RetrofitHelp(RetrofitHelp.EMPTY);
            }
            return retrofitHelp.getRetrofit().create(LoanService.class);
        }
    }

}
