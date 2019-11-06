package com.example.mvp.base;

import com.example.mvp.base.util.ErrorUtil;
import com.example.mvp.common.P2PError;
import com.example.mvp.net.ResEntity;
import com.example.mvp.net.RetrofitCreator;
import com.example.mvp.net.RetrofitPostCreator;
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

    //http get网络请求
    @Override
    public void doHttpRequest(final int requestCode) {
        RetrofitCreator.getApiService().getData(getHearerParmas(), getPath(), getParmas())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        //提示用户正在加载，显示加载页
                        iBaseView.showLoading(requestCode);
                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        //关闭显示的加载页面
                        iBaseView.hideLoading(requestCode);
                        try {
                            //如果返回的数据是列表
                            if (isList()) {
                                 ResEntity<List<T>> resEntityList = new Gson().fromJson(responseBody.string(), getBeanType());
                                if (resEntityList.getRet().equals("1")) {
                                    //获取列表数据成功
                                    if (iBaseView!= null) {
                                        iBaseView.onHttpRequestDataListSuccess(requestCode,resEntityList.getData());
                                    }
                                } else {
                                    //获取列表数据失败
                                    if (iBaseView!= null) {
                                        iBaseView.onHttpRequestDataFailed(requestCode,P2PError.BUSINESS_ERROR);
                                    }
                                }
                            } else {
                                ResEntity<T> resEntity = new Gson().fromJson(responseBody.string(), getBeanType());
                                if (resEntity.getRet().equals("1")) {
                                    //获取数据成功
                                    if (iBaseView!= null) {
                                        iBaseView.onHttpRequestDataSuccess(requestCode,resEntity.getData());
                                    }
                                } else {
                                    //获取数据失败
                                    if (iBaseView!= null) {
                                        iBaseView.onHttpRequestDataFailed(requestCode,P2PError.BUSINESS_ERROR);
                                    }
                                }
                            }

                        } catch (IOException e) {
                            throw new RuntimeException("获取数据为空");//扔出异常，让onError函数统一处理
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        //关闭显示的加载页面
                        iBaseView.hideLoading(requestCode);
                        //获取数据失败
                        if (iBaseView!= null) {
                            iBaseView.onHttpRequestDataFailed(requestCode,ErrorUtil.handleError(e));
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    //http post 网络请求
    @Override
    public void doHttpPostRequest(final int requestCode) {
        RetrofitPostCreator.getApiService().postData(getHearerParmas(), getPath(), getParmas())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        //提示用户正在加载，显示加载页
                        iBaseView.showLoading(requestCode);
                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        //关闭显示的加载页面
                        iBaseView.hideLoading(requestCode);
                        try {
                            T resEntity = new Gson().fromJson(responseBody.string(), getBeanType());

                            //获取数据成功
                            if (iBaseView!= null) {
                                iBaseView.onHttpRequestDataSuccess(requestCode,resEntity);
                            }

                        } catch (IOException e) {
                            throw new RuntimeException("获取数据为空");//扔出异常，让onError函数统一处理
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        //关闭显示的加载页面
                        iBaseView.hideLoading(requestCode);
                        //获取数据失败
                        if (iBaseView!= null) {
                            iBaseView.onHttpRequestDataFailed(requestCode,ErrorUtil.handleError(e));
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

    //当页面销毁时，置成空，防止内存泄漏
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
