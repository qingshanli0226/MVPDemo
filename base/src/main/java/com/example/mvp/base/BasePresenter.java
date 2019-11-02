package com.example.mvp.base;

import android.annotation.SuppressLint;

import com.example.mvp.base.util.ErrorUtil;
import com.example.mvp.net.ResEntity;
import com.example.mvp.net.RetrofitCreator;
import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

//mvp的presenter的抽象类，实现获取网络数据的业务逻辑
public abstract class BasePresenter<T> implements IBasePresenter {

    private IBaseView<T> iBaseView;


    @Override
    public void getData() {
        RetrofitCreator.getApiService().getData(getHearerParmas(), getPath(), getParmas())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        //提示用户正在加载，显示加载页
                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {
                            //如果返回的数据是列表
                            if (isList()) {
                                 ResEntity<List<T>> resEntityList = new Gson().fromJson(responseBody.string(), getBeanType());
                                if (resEntityList.getRet().equals("1")) {
                                    //获取列表数据成功
                                    if (iBaseView!= null) {
                                        iBaseView.onGetDataListSucess(resEntityList.getData());
                                    }
                                } else {
                                    //获取数据失败
                                    if (iBaseView!= null) {
                                        iBaseView.onGetDataFailed("获取数据失败");
                                    }
                                }
                            } else {
                                ResEntity<T> resEntity = new Gson().fromJson(responseBody.string(), getBeanType());
                                if (resEntity.getRet().equals("1")) {
                                    //获取数据成功
                                    if (iBaseView!= null) {
                                        iBaseView.onGetDataSucess(resEntity.getData());
                                    }
                                } else {
                                    //获取数据失败
                                    if (iBaseView!= null) {
                                        iBaseView.onGetDataFailed("获取数据失败");
                                    }
                                }
                            }

                        } catch (IOException e) {
                            throw new RuntimeException("获取数据为空");//扔出异常，让onError函数统一处理
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        String errorMessage = ErrorUtil.handleError(e);
                        //获取数据失败
                        if (iBaseView!= null) {
                            iBaseView.onGetDataFailed(errorMessage);
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    //Activity 或者Fragment注册回调接口, 在初始化presenter时调用


    @Override
    public void attachView(IBaseView iBaseView) {
        this.iBaseView = iBaseView;
    }

    @Override
    public void detachView() {
        this.iBaseView = null;
    }

    public abstract String getPath();//让子类提供获取网络数据的路径
    public HashMap<String, String> getParmas() {
        return new HashMap<>();
    }//让子类来提供调用网络请求 的参数
    public HashMap<String, String> getHearerParmas(){
        return new HashMap<>();
    }//让子类来提供调用网络请求的头参数, 例如token

    public abstract Type getBeanType();//让子类来提供返回bean的类型

    //默认不是列表数据
    public boolean isList() { return false;}
}
