package com.fcode.core;

/**
 * Created by fcorde on 01/10/16.
 */

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface VehicleService {


/**
 * The {@link retrofit2} values will be transform into query string paramters
 * via Retrofit
 *
 * @param q String: The query param to search for in car makes
 * @return Call<ResponseBody></></>.
 */
@GET(Constants.Http.API_URL+Constants.Http.URL_GET_CAR_MAKES)
Call<ResponseBody> carMakes(@Query(Constants.Http.QUERY_PARAM) String q);
}


