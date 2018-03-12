package com.tongqinyuan.customview.executor;

import com.tongqinyuan.customview.DefaultExecutorSupplier;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Future;

/**
 * Created by tongqinyuan on 2018/3/2.
 */

public class CancelTask {



    /*
* Get the future of the task by submitting it to the pool
*/
    Future future = DefaultExecutorSupplier.getInstance().forBackgroundTasks()
            .submit(new Runnable() {
                @Override
                public void run() {
                    // do some background work here.
                }
            });

/*
* cancelling the task
*/


    public void s(){
        future.cancel(true);
    }

}
