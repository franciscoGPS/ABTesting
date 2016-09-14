package com.fcode.core;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;

/**
 * Created by fcorde on 12/09/16.
 */
public class ResponseInterceptor implements Interceptor{

    @Override
    public okhttp3.Response intercept(Interceptor.Chain chain) throws IOException {
        Request newRequest = chain.request().newBuilder().addHeader("User-Agent", "Retrofit-Sample-App").build();
        return chain.proceed(newRequest);
    }
};



