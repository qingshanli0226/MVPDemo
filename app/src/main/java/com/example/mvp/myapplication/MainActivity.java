package com.example.mvp.myapplication;

import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.mvp.base.BaseActivity;
import com.example.mvp.base.IBasePresenter;
import com.example.mvp.base.IBaseView;
import com.example.mvp.common.P2PError;
import com.google.android.gms.fitness.Fitness;
import com.squareup.leakcanary.LeakCanary;

import java.util.List;

public class MainActivity extends BaseActivity implements IBaseView<HomeBean> {

    private IBasePresenter iBasePresenter;//声明一个接口


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        findViewById(R.id.btnGet).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iBasePresenter.getData();
            }
        });
        findViewById(R.id.btnTestMem).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MemActivity.class));
            }
        });
    }

    @Override
    protected void initData() {
        iBasePresenter = new HomePresenter();
        iBasePresenter.attachView(this);
    }

    @Override
    public void onGetDataSuccess(HomeBean data) {

    }

    @Override
    public void onGetDataListSuccess(List<HomeBean> data) {
        Log.d("LQS", data.toString());
        Toast.makeText(MainActivity.this, "获取数据成功", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onGetDataFailed(P2PError error) {
        Toast.makeText(MainActivity.this, error.getErrorMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {
        //显示加载页面
    }

    @Override
    public void hideLoading() {
        //关闭加载页面
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        iBasePresenter.detachView();
    }
}
