package qj.ccmtjf.com.lib;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;

import dagger.component.AppComponent;
import dagger.component.DaggerAppComponent;
import dagger.module.CommonModule;
import dagger.module.HttpModule;


/**
 * 全局应用程序
 * Created by liyanxi on 10/30/17.
 * Copyright (c) 2017 www.zhengshijr.com. All rights reserved.
 */

public class LoanApp extends Application {
    private static LoanApp sInstance; //全局应用
    private AppComponent mAppComponent;

    /**
     * 得到单例的application对象
     *
     * @return
     */
    public static LoanApp getInstance() {
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;

        init();
    }

    /**
     * 第三方工具库引用初始化操作
     */
    private void init() {
//        if (!BuildConfig.DEBUG) {
//            //如果是release模式 初始化崩溃处理
//            CrashHandler.getSingleCrash().init(this);
//        }
        initAppComponent();
    }

    /**
     * 初始化公用实例
     */
    private void initAppComponent(){
        mAppComponent = DaggerAppComponent.builder()
                .httpModule(new HttpModule())
                .commonModule(new CommonModule(this)).build();
        mAppComponent.inject(this);
    }

    public AppComponent getAppComponent(){
        return mAppComponent;
    }


//    /**
//     * 退出程序的方法
//     */
//    public void AppExit() {
//        // 停止服务
//        stopService(new Intent(this, NetWorkService.class));
//        stopService(new Intent(this, UploadService.class));
//
//        //关闭之前保存统计数据
//        MobclickAgent.onKillProcess(this);
//        //移除堆栈所有activity
//        AppManager.getAppManager().AppExit(this);
//
//    }
//
//    /**
//     * 重启程序，当程序崩溃时，将程序先退出再进行重启
//     */
//    public void restartApp() {
//        Intent intent = new Intent(this, SplashActivity.class);
//        PendingIntent resIntent = PendingIntent.getActivity(this, 0,
//                intent, PendingIntent.FLAG_ONE_SHOT);
//        AlarmManager manager = (AlarmManager) this
//                .getSystemService(ALARM_SERVICE);
//        // 1秒后重新启动
//        assert manager != null;
//        manager.set(AlarmManager.RTC, System.currentTimeMillis() + 1000,
//                resIntent);
//        AppExit();
//    }


    /**
     * 取得当前进程名
     *
     * @param context * @return
     */
    String getCurProcessName(Context context) {
        int pid = android.os.Process.myPid();
        ActivityManager mActivityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        assert mActivityManager != null;
        for (ActivityManager.RunningAppProcessInfo appProcess : mActivityManager
                .getRunningAppProcesses()) {
            if (appProcess.pid == pid) {
                return appProcess.processName;
            }
        }
        return null;
    }


}
