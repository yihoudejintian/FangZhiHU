package com.tongqinyuan.customview.executor;

/**
 * Created by tongqinyuan on 2018/3/2.
 */

public class PriorityRunnable implements Runnable {

    private final Priority priority;

    public PriorityRunnable(Priority priority) {
        this.priority = priority;
    }

    @Override
    public void run() {
        // nothing to do here.
    }

    public Priority getPriority() {
        return priority;
    }


}
