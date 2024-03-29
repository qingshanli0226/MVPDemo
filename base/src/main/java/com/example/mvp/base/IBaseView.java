package com.example.mvp.base;

import com.example.mvp.common.P2PError;

import java.util.List;

//它是被presenter回调的.presenter拿到数据后，通过view回调方法，这个接口就是view的通用回调接口。进行显示
//因为它支持不同的Activity Fragment，所以要使用泛型
public interface IBaseView<T> {
    void onHttpRequestDataSuccess(int requestCode, T data);
    void onHttpRequestDataListSuccess(int requestCode, List<T> data);//返回列表的回调
    void onHttpRequestDataFailed(int requestCode, P2PError error);
    void showLoading(int requestCode);//开始请求数据时，后显示加载页面
    void hideLoading(int requestCode);//请求数据结束时，关闭显示加载页面.
}
