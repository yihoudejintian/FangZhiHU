package com.tongqinyuan.customview;

import android.os.Process;

import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by tongqinyuan on 2018/3/1.
 */

public class DefaultExecutorSupplier {
    /*
        * Number of cores to decide the number of threads
        * 决定线程数的内核数量
        */
    public static final int NUMBER_OF_CORES = Runtime.getRuntime().availableProcessors();

    /*
    * thread pool executor for background tasks
    * 线程池执行器用于后台任务
    */
    private final ThreadPoolExecutor mForBackgroundTasks;
    /*
    * thread pool executor for light weight background tasks
    * 线程池执行器，用于轻量级后台任务
    */
    private final ThreadPoolExecutor mForLightWeightBackgroundTasks;
    /*
    * thread pool executor for main thread tasks
    * 主线程任务的线程池执行程序
    */
    private final Executor mMainThreadExecutor;
    /*
    * an instance of DefaultExecutorSupplier
    * DefaultExecutorSupplier的一个实例
    */
    private static DefaultExecutorSupplier sInstance;

    /*
    * returns the instance of DefaultExecutorSupplier
    * 返回DefaultExecutorSupplier的实例
    *
    */
    public static DefaultExecutorSupplier getInstance() {
        if (sInstance == null) {
            synchronized (DefaultExecutorSupplier.class) {
                sInstance = new DefaultExecutorSupplier();
            }
        }
        return sInstance;
    }


    /*
    * constructor for  DefaultExecutorSupplier
    * DefaultExecutorSupplier的构造函数
    */
    private DefaultExecutorSupplier() {

        // setting the thread factory
        //设置线程工厂
        ThreadFactory backgroundPriorityThreadFactory = new
                PriorityThreadFactory(Process.THREAD_PRIORITY_BACKGROUND);

        // setting the thread pool executor for mForBackgroundTasks;
        //为mForBackgroundTasks设置线程池执行器;
        mForBackgroundTasks = new ThreadPoolExecutor(
                NUMBER_OF_CORES * 2,
                NUMBER_OF_CORES * 2,
                60L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(),
                backgroundPriorityThreadFactory
        );

        // setting the thread pool executor for mForLightWeightBackgroundTasks;
        //为mForLightWeightBackgroundTasks设置线程池执行器;
        mForLightWeightBackgroundTasks = new ThreadPoolExecutor(
                NUMBER_OF_CORES * 2,
                NUMBER_OF_CORES * 2,
                60L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(),
                backgroundPriorityThreadFactory
        );

        // setting the thread pool executor for mMainThreadExecutor;
        //设置mMainThreadExecutor的线程池执行器;
        mMainThreadExecutor = new MainThreadExecutor();
    }

    /*
    * returns the thread pool executor for background task
    * 返回后台任务的线程池执行程序
    */
    public ThreadPoolExecutor forBackgroundTasks() {
        return mForBackgroundTasks;
    }

    /*
    * returns the thread pool executor for light weight background task
    * 返回线程池执行器以实现轻量级后台任务
    */
    public ThreadPoolExecutor forLightWeightBackgroundTasks() {
        return mForLightWeightBackgroundTasks;
    }

    /*
    * returns the thread pool executor for main thread task
    * 返回主线程任务的线程池执行程序
    */
    public Executor forMainThreadTasks() {
        return mMainThreadExecutor;
    }

}
