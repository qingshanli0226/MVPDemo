package com.example.mvp.myapplication;

import com.example.mvp.base.BasePresenter;
import com.example.mvp.net.ResEntity;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;

public class HomePresenter extends BasePresenter<HomeBean> {
    HashMap<String, String> params;

    public HomePresenter(HashMap<String, String> params) {
        this.params = params;
    }

    @Override
    public String getPath() {
        return "dish_list.php";
    }

    @Override
    public HashMap<String, String> getParmas() {
        return params;
    }


    //提供将json字符串转换成bean类的type类型
    @Override
    public Type getBeanType() {
        return new TypeToken<ResEntity<List<HomeBean>>>(){}.getType();
    }

    @Override
    public boolean isList() {
        return true;
    }
}
