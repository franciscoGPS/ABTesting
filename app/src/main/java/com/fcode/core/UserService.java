package com.fcode.core;


import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;

/**
 * User service for connecting the the REST API and
 * getting the users.
 */
public interface UserService {

    @GET(Constants.Http.URL_AUTH)
    UsersWrapper getUsers();

    /**
     * The {@link retrofit2} values will be transform into query string paramters
     * via Retrofit
     *
     * @param email The users email
     * @param password The users password
     * @return A login response.
     */
    @FormUrlEncoded
    @POST(Constants.Http.URL_AUTH)
    Observable<Response<ResponseBody>> authenticate(@Field(Constants.Http.PARAM_USERNAME) String email,
                               @Field(Constants.Http.PARAM_PASSWORD) String password);


}
