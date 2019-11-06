package com.example.mvp.base;

import com.example.mvp.base.IBaseView;
import com.example.mvp.common.P2PError;

import java.util.List;

public class BaseView<T> implements IBaseView<T> {
    @Override
    public void onHttpRequestDataSuccess(int requestCode, T data) {

    }

    @Override
    public void onHttpRequestDataListSuccess(int requestCode, List<T> data) {

    }

    @Override
    public void onHttpRequestDataFailed(int requestCode, P2PError error) {

    }

    @Override
    public void showLoading(int requestCode) {

    }

    @Override
    public void hideLoading(int requestCode) {

    }
}
