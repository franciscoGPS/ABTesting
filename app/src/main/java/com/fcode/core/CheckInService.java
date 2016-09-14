package com.fcode.core;


import retrofit2.http.GET;

public interface CheckInService {

    @GET(Constants.Http.URL_CHECKINS)
    CheckInWrapper getCheckIns();
}
