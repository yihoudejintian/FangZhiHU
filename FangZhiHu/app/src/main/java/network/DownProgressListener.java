package network;

/**
 * Created by yuantongqin on 2017/8/9.
 */

public interface DownProgressListener<T> {

    /**
     * 开始下载
     */
     void onStart();

    /**
     * 完成下载
     */
     void onComplete();


    /**
     * 下载进度
     */
    void updateProgress(long readLength, long countLength);

    /**
     * 失败
     */
     void onError(Throwable e);

}
