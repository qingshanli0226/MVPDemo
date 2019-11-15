package com.example.mvp.myapplication.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.example.mvp.myapplication.HomeBean;

public class CacheService extends Service {

    private IHomeDataListener iHomeDataListener;
    //谁维护接口，谁定义
    public interface IHomeDataListener {
        void onHomeDataReceived(HomeBean bean);
    }

    public class CacheBinder extends Binder {
        public CacheService getCacheService() {//返回service，让其他类调用
            return CacheService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new CacheBinder();
    }

    public void registerListener(IHomeDataListener listener) {
        this.iHomeDataListener = listener;
    }

    public void unRegisterListener() {
        this.iHomeDataListener = null;
    }

//获取数据，要在子线程找那个获取
    public void getHomeData() {
        //获取数据成功.
        HomeBean bean = null;
        //获取到数据，去通知Manager
        iHomeDataListener.onHomeDataReceived(bean);
    }






}
