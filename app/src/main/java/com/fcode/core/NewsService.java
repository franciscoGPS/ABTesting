package com.fcode.core;


import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Interface for defining the news service to communicate with Parse.com
 */
public interface NewsService {

    @GET(Constants.Http.URL_NEWS)
    Observable<Response<ResponseBody>> getNews();

}
