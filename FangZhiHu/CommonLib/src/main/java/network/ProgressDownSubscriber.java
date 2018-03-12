package network;



import io.reactivex.subscribers.ResourceSubscriber;
import network.download.DownInfo;

public class ProgressDownSubscriber<T> extends ResourceSubscriber<T> {
    /*下载数据*/
    private DownInfo downInfo;

    public ProgressDownSubscriber() {
        super();
    }

    public ProgressDownSubscriber(DownInfo downInfo) {
        this.downInfo=downInfo;
    }

    @Override
    public void onStart() {
        super.onStart();
        if(downInfo.getmListener() != null){
            downInfo.getmListener().onStart();
        }
        downInfo.state = DownInfo.DownState.START;
    }

    @Override
    public void onError(Throwable e) {
        if(downInfo.getmListener() != null){
            downInfo.getmListener().onError(e);
        }
        downInfo.state = DownInfo.DownState.ERROR;
    }

    @Override
    public void onComplete() {
        if(downInfo.getmListener() != null){
            downInfo.getmListener().onComplete();
        }
        downInfo.state = DownInfo.DownState.COMPLETE;
    }

    @Override
    public void onNext(T t) {
    }

}