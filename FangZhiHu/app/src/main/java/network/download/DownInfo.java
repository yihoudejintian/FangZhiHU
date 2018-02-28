package network.download;


import network.DownProgressListener;

/**
 * Created by yuantongqin on 2017/8/8.
 */

public class DownInfo {

    public long read;//下载的长度
    public long count;//总长度
    public boolean complete;//是个完成
    public String mDownloadUrl;//下载的url
    public DownState state;
    private String savaPath;

    private DownProgressListener mListener;
    public DownInfo(String url, String savePath){
        this.mDownloadUrl = url;
        this.savaPath = savePath;
    }


    public DownProgressListener getmListener() {
        return mListener;
    }

    public void setmListener(DownProgressListener mListener) {
        this.mListener = mListener;
    }

    public void setSavaPath(String savaPath) {
        this.savaPath = savaPath;
    }

    public String getSavaPath() {
        return savaPath;
    }

    public enum DownState {
        START,
        PAUSE,
        ERROR,
        COMPLETE,
    }
}
