package com.example.mvp.myapplication;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class HandlerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);

        handler.sendEmptyMessage(1);
        handler.post(new Runnable() {
            @Override
            public void run() {

            }
        });
    }


    //静态内部类不会引用外部类，所以不会导致内存泄漏
    private  Handler handler = new MemHandler();

    private class MemHandler extends Handler {
        public MemHandler() {
        }
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Log.d("LQS:", "handler receive one message");
            sendEmptyMessageDelayed(1, 1000);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }
}
