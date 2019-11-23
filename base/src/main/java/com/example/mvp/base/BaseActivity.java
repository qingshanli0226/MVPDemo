package com.example.mvp.base;

import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.mvp.common.ActivityInstanceManager;
import com.example.mvp.common.view.TitleBar;

public abstract class BaseActivity extends AppCompatActivity implements TitleBar.ITitleViewClickListener, NetConnectManager.INetConnectListener {
    protected TitleBar titleBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());

        titleBar = findViewById(R.id.titleBar);
        iniTitle();
        initView();

        initData();//一般需要异步操作。在oncreate中只是触发子线程获取数据

        //把自己加入到缓存的Activity列表中
        ActivityInstanceManager.addActivity(this);

        titleBar.setTitleClickListener(this);

        //注册listener，监听当前网络连接的变化
        NetConnectManager.getInstance().registerNetConnectListener(this);
    }

    //子类
    protected abstract void iniTitle();

    protected void setTitle(String title) {
        titleBar.setTitleText(title);
    }

    protected void setLeftText(String leftText) {
        titleBar.setLeftText(leftText);
    }

    protected void setRightText(String rightText) {
        titleBar.setRightTvText(rightText);
    }

    protected void setLeftImg(@DrawableRes int imgId) {
        titleBar.setLeftImage(imgId);
    }

    protected void setRightImg(@DrawableRes int imgId) {
        titleBar.setRightImage(imgId);
    }

    protected void showLeftTv() {
        titleBar.showLeftTv();
    }

    protected void showRightImg() {
        titleBar.showRightImg();
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
        titleBar.clearListener();
        NetConnectManager.getInstance().unRegisterNetConnectListener(this);
    }

    @Override
    public void onLeftImgClick() {
        finish();//默认左侧按钮是关闭页面
    }

    @Override
    public void onRightImgClick() {

    }

    @Override
    public void onRightTvClick() {

    }

    public boolean isConnected() {
        return NetConnectManager.getInstance().isConnectStatus();
    }

    @Override
    public void onConnected() {

    }

    @Override
    public void onDisConnected() {

    }
}
