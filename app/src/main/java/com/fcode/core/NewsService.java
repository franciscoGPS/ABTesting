package com.fcode.core;


import retrofit2.http.GET;

/**
 * Interface for defining the news service to communicate with Parse.com
 */
public interface NewsService {

    @GET(Constants.Http.URL_NEWS)
    NewsWrapper getNews();

}
