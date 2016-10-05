package com.fcode.core;


import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.GET;
import rx.Observable;

public interface CheckInService {

    @GET(Constants.Http.URL_CHECKINS)
    Observable<Response<ResponseBody>> getCheckIns();
}
