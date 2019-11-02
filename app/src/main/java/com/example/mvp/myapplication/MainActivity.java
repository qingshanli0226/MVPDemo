package com.example.mvp.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.mvp.base.IBasePresenter;
import com.example.mvp.base.IBaseView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements IBaseView<HomeBean> {

    private IBasePresenter iBasePresenter;//声明一个接口

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iBasePresenter = new HomePresenter();
        iBasePresenter.attachView(this);

        findViewById(R.id.btnGet).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iBasePresenter.getData();
            }
        });
    }

    @Override
    public void onGetDataSucess(HomeBean data) {

    }

    @Override
    public void onGetDataListSucess(List<HomeBean> data) {
        Log.d("LQS", data.toString());
        Toast.makeText(this, "获取数据成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onGetDataFailed(String message) {
        Toast.makeText(this, "获取数据失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        iBasePresenter.detachView();
    }
}
