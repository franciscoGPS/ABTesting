package com.fcode.core;

import java.util.List;

import retrofit2.Callback;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;


/**
 * Created by fcorde on 11/09/16.
 */
public interface ServiceOrdersService {
    @GET(Constants.Http.API_URL+Constants.Http.URL_SERVICE_ORDERS)
    List<ServiceOrder> getServiceOrders();

    /**
     * The {@link retrofit2.http} values will be transform into query string paramters
     * via Retrofit
     *
     * @param email The users email
     * @param password The users password
     * @return A login response.
     */
    @FormUrlEncoded
    @POST(Constants.Http.URL_AUTH)
    void authenticate(@Field(Constants.Http.PARAM_USERNAME) String email,
                      @Field(Constants.Http.PARAM_PASSWORD) String password, Callback<User> userCallback);
}
