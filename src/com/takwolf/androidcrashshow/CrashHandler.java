package com.takwolf.androidcrashshow;

import java.lang.Thread.UncaughtExceptionHandler;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/**
 * 自定义未捕获异常处理器，用于替换系统默认实现
 * @author TakWolf
 */
public class CrashHandler implements UncaughtExceptionHandler {

    private static CrashHandler instance; //当前异常处理器-单例模式
    @SuppressWarnings("unused")
    private UncaughtExceptionHandler defaultHandler; //系统默认未捕获异常处理器
    private Context context; //应用上下文

    /**
     * 隐藏构造器
     */
    private CrashHandler() {}

    /**
     * 获取异常处理器单例
     */
    public static synchronized CrashHandler getInstance() {
        if (instance == null) {
            instance = new CrashHandler();
        }
        return instance;
    }

    /**
     * 激活处理器，该函数需要在Application的onCreate函数中调用
     * @param context
     */
    public void active(Context context) {
        this.context = context; 
        defaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        //关键的一句，替换系统默认的未捕获异常处理器
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    /**
     * 未被捕获的异常，会调用该方法处理
     */
    @Override
    public void uncaughtException(Thread thread, final Throwable e) {
        //系统默认处理异常
        //defaultHandler.uncaughtException(thread, e);

        //启动CrashShowActivity
        Intent intent = new Intent(context, CrashShowActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
        Bundle bundle = new Bundle();
        bundle.putSerializable("e", e); //将异常对象序列化并通过Intent对象传递
        intent.putExtras(bundle);
        context.startActivity(intent);

        //PS：这里也可以添加应用重启逻辑，原理和上面类似

        //退出程序
        //android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }

}
