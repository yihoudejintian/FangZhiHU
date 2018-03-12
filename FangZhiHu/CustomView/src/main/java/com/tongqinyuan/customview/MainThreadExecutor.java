package com.tongqinyuan.customview;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

import java.util.concurrent.Executor;

/**
 * Created by tongqinyuan on 2018/3/1.
 */

public class MainThreadExecutor implements Executor {


    private final Handler handler = new Handler(Looper.getMainLooper());

    @Override
    public void execute(Runnable runnable) {
        handler.post(runnable);


    }
}
