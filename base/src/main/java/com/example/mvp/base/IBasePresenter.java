package com.example.mvp.base;

//获取数据,各个页面都可以调用该接口获取数据
public interface IBasePresenter<T> {
    //requestCode来区分不同的网络请求
    void doHttpRequest(int requestCode);
    void doHttpPostRequest(int requestCode);
    void attachView(IBaseView<T> iBaseView);
    void detachView();
}
