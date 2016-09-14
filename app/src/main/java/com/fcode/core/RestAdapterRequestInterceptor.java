package com.fcode.core;


import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;


public class RestAdapterRequestInterceptor implements Interceptor {

    private UserAgentProvider userAgentProvider;


    public RestAdapterRequestInterceptor(UserAgentProvider userAgentProvider) {
        this.userAgentProvider = userAgentProvider;

    }





        //request.addHeader("Content-Type", "application/json;charset=utf-8");
        //request.addHeader("Accept", "application/json");


        // Add auth info for PARSE, normally this is where you'd add your auth info for this request (if needed).


        // Add the user agent to the request.
        //context = mContextReference.get();
        //request.addHeader("User-Agent", userAgentProvider.get());

//        sharedPreferences = context.getSharedPreferences(Constants.Http.FLASH_SHARED_PREFS, Context.MODE_PRIVATE);


/*
        request.addHeader(Constants.Http.AUTH_HEADER_ACCESS_TOKEN, user.getToken());
        request.addHeader(Constants.Http.AUTH_HEADER_CLIENT, user.getClient());
        request.addHeader(Constants.Http.AUTH_HEADER_TOKEN_TYPE, user.getTokenType());
        request.addHeader(Constants.Http.AUTH_HEADER_EXPIRY, user.getExpiry());
        request.addHeader(Constants.Http.AUTH_HEADER_UID, user.getUid());
*/


    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        Request authRequest = request.newBuilder()
                .addHeader(Constants.Http.AUTH_HEADER_ACCESS_TOKEN, userAgentProvider.getToken())
                .addHeader(Constants.Http.AUTH_HEADER_CLIENT, userAgentProvider.getClient())
                .addHeader(Constants.Http.AUTH_HEADER_TOKEN_TYPE, userAgentProvider.getTokenType())
                .addHeader(Constants.Http.AUTH_HEADER_EXPIRY, userAgentProvider.getExpiry())
                .addHeader(Constants.Http.AUTH_HEADER_UID, userAgentProvider.getUid()).build();


        Response response = chain.proceed(authRequest);

        userAgentProvider.setToken(response.header(Constants.Http.AUTH_HEADER_ACCESS_TOKEN));
        userAgentProvider.setTokenType(response.header(Constants.Http.AUTH_HEADER_TOKEN_TYPE));
        userAgentProvider.setClient(response.header(Constants.Http.AUTH_HEADER_CLIENT));
        userAgentProvider.setExpiry(response.header(Constants.Http.AUTH_HEADER_EXPIRY));
        userAgentProvider.setUid(response.header(Constants.Http.AUTH_HEADER_UID));

        return response;
    }
}
