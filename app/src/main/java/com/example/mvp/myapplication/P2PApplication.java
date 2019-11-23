package com.example.mvp.myapplication;

import android.app.Application;
import android.util.Log;

import com.example.mvp.base.CacheManager;
import com.example.mvp.base.NetConnectManager;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

public class P2PApplication extends Application {
    public static RefWatcher refWatcher;
    public static P2PApplication instance;
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        //在此处调用crashHandler的初始化函数
        //P2PCrashHandler.getInstance(instance).init();
        NetConnectManager.getInstance().init(this);//初始化网络连接管理类

        //使用leakcannary来检测当前应用是否有内存泄漏.
        if (!LeakCanary.isInAnalyzerProcess(this)) {
            //https://www.liaohuqiu.net/cn/posts/leak-canary-read-me/
            refWatcher = LeakCanary.install(this);
        }

        CacheManager.getInstance().init(this);
    }


    //当应用程序内存不足时，会回调该函数
    @Override
    public void onLowMemory() {
        super.onLowMemory();
        Log.d("LQS", "onLowMemory...............");
        CacheManager.getInstance().clearCache();
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        Log.d("LQS", "onTrimMemory...............");
        CacheManager.getInstance().clearCache();
    }
}
