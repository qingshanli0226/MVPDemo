package com.example.mvp.base;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.os.IBinder;
import android.util.Log;
import android.util.LruCache;

import com.example.mvp.base.service.CacheService;


import java.util.LinkedList;
import java.util.List;

public class CacheManager {
    private Context context;
    private CacheService cacheService;

    private List<IHomeReceivedListener> iHomeReceivedListeners = new LinkedList<>();

    //使用lrucache来做bitmap的缓存。 给它设定的最大缓存值是应用程序课使用内存空间的1/8
    private LruCache<String, Bitmap> bitmapLruCache = new LruCache<>((int) (Runtime.getRuntime().maxMemory()/8));
    //磁盘SD缓存
    //DiskLruCache

    private static CacheManager instance;

    private CacheManager(){
    }

    public static CacheManager getInstance() {
        if (instance == null) {
            instance = new CacheManager();
        }
        return instance;
    }
    boolean isStop=false;

    public void init(Context context) {
        this.context = context;

        Intent intent = new Intent();
        intent.setClass(context, CacheService.class);

        context.startService(intent);
        context.bindService(intent, new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                cacheService = ((CacheService.CacheBinder)service).getCacheService();
                cacheService.registerListener(new CacheService.IHomeDataListener() {
                    @Override
                    public void onHomeDataReceived(HomeBean bean) {
                        //service通知数据已经获取到
                        for(IHomeReceivedListener listener:iHomeReceivedListeners) {
                            listener.onHomeDataReceived(bean);
                        }

                        //本地存储
                        saveLocal(bean);
                    }
                });

                if (!NetConnectManager.getInstance().isConnectStatus()) {
                    return;
                } else {
                    cacheService.getHomeData();
                }

                NetConnectManager.getInstance().registerNetConnectListener(new NetConnectManager.INetConnectListener() {
                    @Override
                    public void onConnected() {
                        cacheService.getHomeData();
                    }

                    @Override
                    public void onDisConnected() {

                    }
                });
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        }, Context.BIND_AUTO_CREATE);
    }

    private void saveLocal(HomeBean bean) {
        //把bean存储到本地
    }


    public HomeBean getHomeData() {
        //从本地获取数据，将数据返回
        return null;
    }

    public void clearCache() {

    }

    public void unregisterListener(IHomeReceivedListener listener) {
        if (iHomeReceivedListeners.contains(listener)) {
            iHomeReceivedListeners.remove(listener);
        }
    }

    public void registerListener(IHomeReceivedListener listener) {
        if (iHomeReceivedListeners.contains(listener)) {
            return;
        } else {
            iHomeReceivedListeners.add(listener);
        }
    }

    public interface IHomeReceivedListener{
        void onHomeDataReceived(HomeBean homeBean);
    }

    private void getRunningMemory() {
        Log.d("LQS: max mem = ", Runtime.getRuntime().maxMemory() / (1024*1024) + "");
    }
}
