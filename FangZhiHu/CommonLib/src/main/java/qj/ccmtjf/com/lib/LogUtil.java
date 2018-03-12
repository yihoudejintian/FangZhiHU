package qj.ccmtjf.com.lib;

import android.text.TextUtils;

import timber.log.Timber;

/**
 * 日志工具类 在 {@link } 基础上进一步简易封装，可更改
 * Created by liyanxi on 11/6/17.
 * Copyright (c) 2017 www.zhengshijr.com. All rights reserved.
 */

public class LogUtil {

    public static final String TAG = "LogUtil";
    /**
     * set to false when app release.
     */
    public static boolean DEBUG = true;

    /**
     * 是否能显示信息
     *
     * @param msg 非空信息
     * @return true or false
     */
    public static boolean canShow(String msg) {
        return DEBUG && !TextUtils.isEmpty(msg);
    }

    public static void i(String msg) {
        if (canShow(msg)) {
            Timber.i(TAG + ":=====:" + msg);
        }
    }

    public static void i(String tag, String msg) {
        if (canShow(msg)) {
            Timber.i(tag + ":=====:" + msg);
        }
    }

    public static void d(String msg) {
        if (canShow(msg)) {
            Timber.d(TAG + ":=====:" + msg);
        }
    }

    public static void d(String tag, String msg) {
        if (canShow(msg)) {
            Timber.d(tag + ":=====:" + msg);
        }
    }


    public static void v(String msg) {
        if (canShow(msg)) {
            Timber.v(TAG + ":=====:" + msg);
        }
    }

    public static void v(String tag, String msg) {
        if (canShow(msg)) {
            Timber.v(tag + ":=====:" + msg);
        }
    }

    public static void e(String msg) {
        if (canShow(msg)) {
            Timber.e(TAG + ":=====:" + msg);
        }
    }

    public static void e(String tag, String msg) {
        if (canShow(msg)) {
            Timber.e(tag + ":=====:" + msg);
        }
    }

    /**
     * {@link com.ccmtjf.zhengshi.loan.LoanApp} 初始化日志配置
     */
    public static void init() {
        Timber.plant(new Timber.DebugTree());
    }
}
