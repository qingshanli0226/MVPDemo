package com.example.mvp.myapplication;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class ThreadActivity extends AppCompatActivity {
    private boolean isStop = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);

        new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while (!isStop) {
                    Log.d("LQS", "thread run: " + i++);
                    SystemClock.sleep(500);
                }
                Log.d("LQS", "thread stop. ");
            }
        }).start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        isStop = true;
    }
}
