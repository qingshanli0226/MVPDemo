package com.example.mvp.myapplication;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.example.mvp.base.BaseActivity;
import com.example.mvp.base.IBasePresenter;
import com.example.mvp.base.IBaseView;
import com.example.mvp.base.CacheManager;
import com.example.mvp.common.P2PError;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

//多次网络请求，所以返回值使用Object类型
public class MainActivity extends BaseActivity implements IBaseView<Object> {

    private IBasePresenter iHomePresenter;//声明一个接口
    private IBasePresenter iSplashPresenter;//声明一个接口

    private final int HOME_REQUEST_CODE = 100;
    private final int SPLASH_REQUEST_CODE = 200;

    private int count = 0;

    List<Bitmap> bitmapList = new ArrayList<>();

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.d("LQS", "onConfigurationChanged");

        //函数可以监听屏幕的切换事件

        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Log.d("LQS", "当前屏幕切换到横屏");

        } else if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            Log.d("LQS", "当前屏幕切换到竖屏");
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        findViewById(R.id.btnGet).setOnClickListener(new View.OnClickListener() {
                                                         @Override
                                                         public void onClick(View v) {
                                                             iHomePresenter.doHttpRequest(HOME_REQUEST_CODE);
                                                         }
                                                     });

        findViewById(R.id.btnPost).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iSplashPresenter.doHttpPostRequest(SPLASH_REQUEST_CODE);
            }
        });
        findViewById(R.id.btnTestMem).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MemActivity.class));
            }
        });

        findViewById(R.id.btnHandlerMem).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, HandlerActivity.class));
            }
        });
        findViewById(R.id.btnThreadMem).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ThreadActivity.class));
            }
        });
        findViewById(R.id.btnStaticMem).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, StaticActivity.class));
            }
        });
        findViewById(R.id.btnTransferMem).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TransferActivity.class));
            }
        });
        findViewById(R.id.btnScroll).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ScrollActivity.class));
            }
        });

        new Thread(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.P)
            @Override
            public void run() {
                /**
                 * 当前版本号
                 *
                 * @return
                 */

                    long version = 0;
                    PackageManager manager = getPackageManager();
                    try {
                        PackageInfo packageInfo = manager.getPackageInfo(getPackageName(), 0);
                        version = packageInfo.versionCode;
                    } catch (PackageManager.NameNotFoundException e) {
                        //e.printStackTrace(); //如果找不到对应的应用包信息, 就返回"未知版本"
                    }
                    Log.d("LQS: version:", version+"");
                }

        }).start();

        Log.d("LQS", count + "");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("LQS: ", "onStart 是否在后台 = " + isApplicationBroughtToBackground(this));
    }


    @Override
    protected void onStop() {
        super.onStop();
        Log.d("LQS: ", "onStop 是否在后台 = " + isApplicationBroughtToBackground(this));
    }

    @Override
    protected void iniTitle() {
        setTitle(getResources().getString(R.string.test));
        setLeftImg(R.mipmap.search);
        setLeftText(getString(R.string.search));
        setRightImg(R.mipmap.menu);
        setRightText(getString(R.string.menu));
        showLeftTv();
        showRightImg();
    }

    @Override
    public void onLeftImgClick() {
        Toast.makeText(this,  getString(R.string.please_click_left_button), Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void initData() {

        //获取网络数据之前，先判断当前网络是否连接，如果没有连接则提示用户，就不再进行网络请求了
        if (!isConnected()) {
            Toast.makeText(this, "当前网络没有连接", Toast.LENGTH_SHORT).show();
            return;
        }

        Toast.makeText(this, "当前网络连接正常，获取数据", Toast.LENGTH_SHORT).show();

        HashMap<String, String> paramMap = new HashMap<>();
        paramMap.put("stage_id", "1");
        paramMap.put("limit", "20");
        paramMap.put("page", "1");
        iHomePresenter = new HomePresenter(paramMap);
        iHomePresenter.attachView(this);
        iSplashPresenter = new SplashPresenter();
        iSplashPresenter.attachView(this);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int heightPixels = dm.heightPixels;
        int widthPixels = dm.widthPixels;
        float density = dm.density;
        float heightDP = heightPixels / density;
        float widthDP = widthPixels / density;
        float smallestWidthDP;
        if(widthDP < heightDP) {
            smallestWidthDP = widthDP;
        }else {
            smallestWidthDP = heightDP;
        }

        Log.d("LQS:small = ", smallestWidthDP+"");
    }

    @Override
    public void onConnected() {
        initData();//如果监听网络再次连接成功，则直接获取数据
    }

    @Override
    public void onDisConnected() {
        Toast.makeText(this, "当前网络连接已经断开", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onHttpRequestDataSuccess(int requestCode, Object data) {
        if (requestCode == SPLASH_REQUEST_CODE) {
            SplashDataBean splashDataBean = (SplashDataBean)data;
            Log.d("LQS POST---", splashDataBean.getRetCode()+"" + splashDataBean.getRequestId());
        }
    }

    @Override
    public void onHttpRequestDataListSuccess(int requestCode,List<Object> data) {
        if (requestCode == HOME_REQUEST_CODE) {//表示确定那个网络请求，例如本次是home数据网络请求
            //做类型转换，转换成我们需要的类型
            List<HomeBean> homeBeanList = Arrays.asList(data.toArray(new HomeBean[0]));

            for(HomeBean bean:homeBeanList) {
                Log.d("LQS---", bean.getFood_str());
            }
            Toast.makeText(MainActivity.this, getString(R.string.get_data_success), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onHttpRequestDataFailed(int requestCode,P2PError error) {
        Toast.makeText(MainActivity.this, error.getErrorMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading(int requestCode) {
        //显示加载页面
    }

    @Override
    public void hideLoading(int requestCode) {
        //关闭加载页面
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        iHomePresenter.detachView();
        iSplashPresenter.detachView();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        CacheManager.getInstance().clearCache();
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        CacheManager.getInstance().clearCache();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.d("LQS:", "dispatchTouchEvent...");
        return super.dispatchTouchEvent(ev);
    }

    //判断当前应用是否处于后台
    public boolean isApplicationBroughtToBackground(final Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> tasks = am.getRunningTasks(1);
        if (!tasks.isEmpty()) {
            ComponentName topActivity = tasks.get(0).topActivity;
            if (!topActivity.getPackageName().equals(context.getPackageName())) {
                return true;
            }
        }
        return false;
    }
}
