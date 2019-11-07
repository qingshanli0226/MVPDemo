package com.example.mvp.base;

import android.content.Context;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import com.example.mvp.common.ActivityInstanceManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class P2PCrashHandler implements Thread.UncaughtExceptionHandler{
    private static P2PCrashHandler instance;
    private Context context;

    private P2PCrashHandler(Context context) {
        this.context = context;
    }

    public static P2PCrashHandler getInstance(Context applicationContext) {
        if (instance == null) {
            instance = new P2PCrashHandler(applicationContext);
        }

        return instance;
    }

    //处理异常的方法。应用程序出现未捕获异常时，会把未捕获的异常通过该函数传递过来
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        Log.d("LQS==", e.toString());
        new Thread(){
            public void run(){
                //prepare()和loop()之间的操作就是在主线程中执行的！
                //在android系统中，默认情况下，一个线程中是不可以调用Looper进行消息的处理的。除非是主线程
                Looper.prepare();
                Toast.makeText(context, "亲，出现了未捕获的异常了！", Toast.LENGTH_SHORT).show();
                Looper.loop();
            }
        }.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }

        try {
            //把异常信息存入到文件中
            PrintStream printStream = new PrintStream(new FileOutputStream("/sdcard/exception.txt"));
            e.printStackTrace(printStream);
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }


        //2,把应用程序关掉，因为无法回到原来的执行上下文
        //第一步把所有的Activity finish掉
        ActivityInstanceManager.finishAllActivity();

        //第二步把应用程序停掉
        System.exit(1);
    }

    public void init() {
        //把当前类设置成未捕获异常默认处理类
        Thread.setDefaultUncaughtExceptionHandler(this);
    }
}
