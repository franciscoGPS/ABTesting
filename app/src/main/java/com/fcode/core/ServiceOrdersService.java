package com.fcode.core;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;


/**
 * Created by fcorde on 11/09/16.
 */
public interface ServiceOrdersService {
    @GET(Constants.Http.API_URL+Constants.Http.URL_SERVICE_ORDERS)
    Call<ResponseBody> getServiceOrders();

    @POST(Constants.Http.API_URL+ Constants.Http.URL_SERVICE_ORDERS)
    Call<ResponseBody> newServiceOrder(@Body ServiceOrder serviceOrder);

    /*@POST(Constants.Http.API_URL+ Constants.Http.URL_CUSTOMERS)
    Call<ResponseBody> newCustomer(@Body Customer customer);*/

    @PATCH(Constants.Http.API_URL+Constants.Http.URL_SERVICE_ORDERS+"/{id}")
    Call<ResponseBody> updateServiceOrder(@Path("id") int serviceOrderId,@Body ServiceOrder serviceOrder);

}
