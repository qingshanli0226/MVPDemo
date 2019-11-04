package com.example.mvp.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.mvp.base.ActivityInstanceManager;
import com.example.mvp.base.IBasePresenter;
import com.example.mvp.base.IBaseView;

import java.util.List;

public class MemActivity extends AppCompatActivity implements IBaseView<HomeBean> {
    private IBasePresenter iBasePresenter;//声明一个接口

    private Button btn;



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
                iBasePresenter.getData();
            }
        });

        ActivityInstanceManager.addActivity(this);

        /*new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(500);
                        Log.d("LQS","work thread run!");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();*/
    }

    @Override
    public void onGetDataSuccess(HomeBean data) {

    }

    @Override
    public void onGetDataListSuccess(List<HomeBean> data) {

    }

    @Override
    public void onGetDataFailed(String message) {
        Log.d("onGetDataFailed LQS:", message);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityInstanceManager.removeActivity(this);
    }
}
