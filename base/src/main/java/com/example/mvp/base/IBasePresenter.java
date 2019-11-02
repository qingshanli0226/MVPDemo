package com.example.mvp.base;

//获取数据,各个页面都可以调用该接口获取数据
public interface IBasePresenter<T> {
    void getData();
    void attachView(IBaseView<T> iBaseView);
    void detachView();
}
