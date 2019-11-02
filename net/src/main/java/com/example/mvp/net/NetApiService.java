package com.example.mvp.net;

import java.util.HashMap;


import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.*;


public interface NetApiService {
//ResponseBody 是一个通用的返回类型，如果不清楚服务端返回的json数据，可以通过它来获取
    @GET("{path}")
    Observable<ResponseBody> getData(@HeaderMap HashMap<String, String> headers, @Path("path") String path, @QueryMap HashMap<String, String> params);
}
