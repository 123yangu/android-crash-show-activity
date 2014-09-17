package com.takwolf.androidcrashshow;

import android.app.Application;

/**
 * Application实例，需要在Manifest中注册
 * @author TakWolf
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //注册全局异常捕获器
        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.active(this); //开发环境下将此句注释，以便在LogCat中调试异常
    }

}
