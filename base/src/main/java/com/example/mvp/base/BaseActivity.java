package com.example.mvp.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.mvp.common.ActivityInstanceManager;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());

        initView();

        initData();//一般需要异步操作。在oncreate中只是触发子线程获取数据

        //把自己加入到缓存的Activity列表中
        ActivityInstanceManager.addActivity(this);
    }

    //初始化数据
    protected abstract void initData();

    //初始化view
    protected abstract void initView();

    //让子类提供布局
    protected abstract int getLayoutId();


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityInstanceManager.removeActivity(this);
    }
}
