package com.example.mvp.myapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.mvp.common.ActivityInstanceManager;
import com.example.mvp.base.IBasePresenter;
import com.example.mvp.base.IBaseView;
import com.example.mvp.common.P2PError;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.LinkedList;
import java.util.List;

//认为该页面就是一次网络请求,所以使用具体数据类型
public class MemActivity extends AppCompatActivity implements IBaseView<HomeBean> {
    private IBasePresenter iBasePresenter;//声明一个接口

    private Button btn;
    private List<Bitmap> bitmapList = new LinkedList<>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mem);

        iBasePresenter = new HomeMemPresenter();
        iBasePresenter.attachView(this);

        btn = findViewById(R.id.btnGet);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MemActivity.this, "开始内存泄漏测试，5秒内点击back，finish Activity!", Toast.LENGTH_SHORT).show();
                iBasePresenter.doHttpRequest(100);
            }
        });

        ActivityInstanceManager.addActivity(this);

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(500);
                        /*Bitmap bitmap = BitmapFactory.decodeFile("/sdcard/1610A.jpg");
                        bitmapList.add(bitmap);*/
                        try {
                            FileOutputStream fileOutputStream = new FileOutputStream("/sdcard/1610A.jpg");
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        Log.d("LQS","work thread run!");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    @Override
    public void onHttpRequestDataSuccess(int requestCode, HomeBean data) {

    }

    @Override
    public void onHttpRequestDataListSuccess(int requestCode, List<HomeBean> data) {

    }

    @Override
    public void onHttpRequestDataFailed(int requestCode, P2PError error) {
        Log.d("LQS:", error.getErrorMessage());

    }

    @Override
    public void showLoading(int requestCode) {

    }

    @Override
    public void hideLoading(int requestCode) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityInstanceManager.removeActivity(this);
    }
}
