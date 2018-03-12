package com.tongqinyuan.customview.executor;

import com.tongqinyuan.customview.DefaultExecutorSupplier;

/**
 * Created by tongqinyuan on 2018/3/2.
 */

public class UsingThreadPool {

    public void doSomeBackgroundWork(){
        DefaultExecutorSupplier.getInstance().forBackgroundTasks()
                .execute(new Runnable() {
                    @Override
                    public void run() {
                        // do some background work here.
                    }
                });
    }

    /*
    * Using it for Light-Weight Background Tasks
    */
    public void doSomeLightWeightBackgroundWork(){
        DefaultExecutorSupplier.getInstance().forLightWeightBackgroundTasks()
                .execute(new Runnable() {
                    @Override
                    public void run() {
                        // do some light-weight background work here.
                    }
                });
    }

    /*
    * Using it for MainThread Tasks
    */
    public void doSomeMainThreadWork(){
        DefaultExecutorSupplier.getInstance().forMainThreadTasks()
                .execute(new Runnable() {
                    @Override
                    public void run() {
                        // do some Main Thread work here.
                    }
                });
    }
}
