package com.example.mvp.myapplication;

import com.example.mvp.base.BasePresenter;
import com.example.mvp.net.ResEntity;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;

public class SplashPresenter extends BasePresenter {
    @Override
    public String getPath() {
        return "splashList";
    }

    @Override
    public HashMap<String, String> getParmas() {
        HashMap<String, String> paramMap = new HashMap<>();
        paramMap.put("platform", "2");
        paramMap.put("appVersion", "1.7.0");
        paramMap.put("osType", "2");
        return paramMap;
    }

    @Override
    public Type getBeanType() {
        return new TypeToken<SplashDataBean>(){}.getType();
    }
}
