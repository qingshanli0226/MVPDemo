package com.example.mvp.base.util;

import com.example.mvp.common.P2PError;
import com.google.gson.JsonSyntaxException;

import java.net.SocketTimeoutException;

import retrofit2.HttpException;

public class ErrorUtil {
    public static P2PError handleError(Throwable e) {
        if (e instanceof SocketTimeoutException) { //肯定是网络请求协议错误.
            return P2PError.HTTP_SOCKET_TIME_OUT_ERROR;
        } else if (e instanceof HttpException) {
            return P2PError.HTTP_ERROR;
        } else if (e instanceof JsonSyntaxException) { //json 解析错误.
            return P2PError.JSON_ERROR;
        } else { //业务错误.
            return P2PError.OTHER_ERROR;
        }
    }
}
