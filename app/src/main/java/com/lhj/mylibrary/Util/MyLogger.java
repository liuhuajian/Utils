package com.lhj.mylibrary.Util;

import android.util.Log;


/**
 * 项目：xxx_xxx
 * 作    者：julyzeng （曾招林)  364298140@qq.com
 * 版    本：1.0
 * 创建日期：2018/6/10  9:27
 * 描    述： 日志管理类
 * 修订历史：
 */

public class MyLogger {

    static String className;
    static String methodName;
    static int lineNumber;

    private MyLogger() {
        /* Protect from instantiations */
    }

    public static boolean isDebuggable() {
//        return BuildConfig.isDebug;
        return true;
    }

    private static String createLogTag() {

        StringBuffer buffer = new StringBuffer();
        buffer.append(className);
        buffer.append("[");
        buffer.append(methodName);
        buffer.append(":");
        buffer.append(lineNumber);
        buffer.append("]");

        return buffer.toString();
    }

    private static void getMethodNames(StackTraceElement[] sElements) {
        try {
//            className = getCurActivityInfo(sElements[2]) + sElements[1].getFileName().split("\\.")[0];
            className = sElements[1].getFileName();
            methodName = sElements[1].getMethodName();
            lineNumber = sElements[1].getLineNumber();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void e(String message) {
        if (!isDebuggable())
            return;

        // Throwable instance must be created before any methods
        getMethodNames(new Throwable().getStackTrace());
        if (message == null) {
            Log.e(createLogTag(), message);
            return;
        }
        for (int i = 0; i < (message.length() / 2048) + 1; i++) {

            if (message.length() < 2048 * (i + 1)) {
                Log.e(createLogTag(), message.substring(2048 * i, message.length()));
            } else {
                Log.e(createLogTag(), message.substring(2048 * i, 2048 * (i + 1)));
            }
        }

    }

    public static void i(String message) {
        if (!isDebuggable())
            return;

        getMethodNames(new Throwable().getStackTrace());
        Log.i(createLogTag(), message);
    }

    public static void d(String message) {
        if (!isDebuggable())
            return;

        getMethodNames(new Throwable().getStackTrace());
        Log.d(createLogTag(), message);
    }

    public static void v(String message) {
        if (!isDebuggable())
            return;

        getMethodNames(new Throwable().getStackTrace());
        Log.v(createLogTag(), message);
    }

    public static void w(String message) {
        if (!isDebuggable())
            return;

        getMethodNames(new Throwable().getStackTrace());
        Log.w(createLogTag(), message);
    }
}
