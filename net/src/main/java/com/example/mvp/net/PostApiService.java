package com.example.mvp.net;

import java.util.HashMap;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

//该类是用来测试Post请求用的
public interface PostApiService {
//ResponseBody 是一个通用的返回类型，如果不清楚服务端返回的json数据，可以通过它来获取
    @POST("{path}")
    @FormUrlEncoded
    Observable<ResponseBody> postData(@FieldMap HashMap<String, String> headers, @Path("path") String path, @FieldMap HashMap<String, String> params);
}
