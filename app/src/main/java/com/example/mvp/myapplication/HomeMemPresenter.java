package com.example.mvp.myapplication;

import com.example.mvp.base.BaseMemPresenter;
import com.example.mvp.base.BasePresenter;
import com.example.mvp.net.ResEntity;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;

public class HomeMemPresenter extends BaseMemPresenter<HomeBean> {
    @Override
    public String getPath() {
        return "dish_list.php";
    }

    @Override
    public HashMap<String, String> getParmas() {
        HashMap<String, String> paramMap = new HashMap<>();
        paramMap.put("stage_id", "1");
        paramMap.put("limit", "20");
        paramMap.put("page", "1");
        return paramMap;
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

    @Override
    public void doHttpPostRequest(int requestCode) {

    }
}
