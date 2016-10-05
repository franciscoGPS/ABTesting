
package com.fcode.core;

import android.util.Log;

import com.fcode.BootstrapApplication;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.squareup.otto.Bus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Bootstrap API service
 */
public class BootstrapService {

    private Retrofit retrofitAdapter;

    protected static final String FLASH_SHARED_PREFS = "Flash-u";

    ArrayList<ServiceOrder> serviceOrdersList = null;

    ArrayList<String> makes = null;

    @Inject
    Bus bus;

    @Inject @Named("current_user")
    User user;

    @Inject
    Gson gson;

    /**
     * Create bootstrap service
     * Default CTOR
     */
    public BootstrapService() {

    }

    /**
     * Create bootstrap service
     *
     * @param retrofit The RestAdapter that allows HTTP Communication.
     */
    public BootstrapService(Retrofit retrofit) {
        this.retrofitAdapter = retrofit;
        BootstrapApplication.component().inject(this);
    }

    private Retrofit getRestAdapter() {
        return retrofitAdapter;
    }

    private UserService getUserService() {
        return getRestAdapter().create(UserService.class);
    }

    private ServiceOrdersService getServiceOrderService(){
        return getRestAdapter().create(ServiceOrdersService.class);
    }

    private VehicleService getVehicleService(){
        return getRestAdapter().create(VehicleService.class);
    }

    private NewsService getNewsService() {
        return getRestAdapter().create(NewsService.class);
    }

    private CheckInService getCheckInService() {
        return getRestAdapter().create(CheckInService.class);
    }

    /**
     * Get all bootstrap News that exists on Parse.com
     */
    public List<News> getNews() {
        //return getNewsService().getNews().getResults();
        return null;
    }

    /**
     * Get all bootstrap Users that exist on Parse.com
     */
    public List<User> getUsers() {
        return getUserService().getUsers().getResults();
    }

    /**
     * Get all bootstrap Checkins that exists on Parse.com
     */
    public List<CheckIn> getCheckIns() {
       //return getCheckInService().getCheckIns().getResults();
        return null;
    }


    public void authenticate(String email, String password) throws IOException {

        UserService userService = getUserService();


        //To make this work, change back to Call<User> in UserService Interface
        //Call<User> userCall = userService.authenticate(email, password);
        //userCall.enqueue(userCallback);

        Observable<Response<ResponseBody>> observable = userService.authenticate(email, password);
        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<Response<ResponseBody>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("FCM", "ERROR: "+ e.getMessage());
                        System.out.println(e.getMessage());
                    }

                    @Override
                    public void onNext(Response<ResponseBody> respose) {


                        String line;
                        String JSONResponse = "";
                        BufferedReader br=new BufferedReader(new InputStreamReader(respose.body().byteStream()));
                        try {
                            while ((line=br.readLine()) != null) {
                                JSONResponse+=line;
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        if(JSONResponse != ""){
                            Map<String,Object> userHash = null;
                            GsonBuilder builder = new GsonBuilder();
                            gson = builder.create();

                            Log.d("FCM", "JSONResponse " + JSONResponse);


                            Map<String,Object> data = new Gson().fromJson(JSONResponse, Map.class);

                            try {
                                userHash = (Map<String,Object>)data.get("data");
                                String s_id = userHash.get("id").toString();
                                Double d_id = Double.parseDouble(s_id);

                                user.setId(d_id.intValue());
                                user.setName(userHash.get("name").toString());
                                user.setEmail(userHash.get("email").toString());
                                String phone = userHash.get("phone") != null ? userHash.get("phone").toString() : "";
                                user.setPhone(phone);
                                user.setProvider(userHash.get("provider").toString());
                                String role_id = userHash.get("role_id").toString();
                                Double d_role_id = Double.parseDouble(role_id);
                                user.setRole_id(d_role_id.intValue());
                                user.setUid(userHash.get("uid").toString());
                                user.setAdmin(Boolean.parseBoolean(userHash.get("admin").toString()));
                                user.setSuperadmin(Boolean.parseBoolean(userHash.get("superadmin").toString()));

                                //user.setDeleted_at(userHash.get("deleted_at").toString());


                            }catch (ClassCastException e){
                                Log.e("FCM", e.getMessage());
                            }

                        }


                        bus.post(user);

                    }
                });



    }

    public ArrayList<ServiceOrder> getServiceOrders(){

        try {
        Response<ResponseBody> response = getServiceOrderService().getServiceOrders().execute();

            serviceOrdersList =(ArrayList<ServiceOrder>) parseResult(response, ServiceOrder.class, true);


            /*
            String line;
            String JSONResponse = "";



            if (response.errorBody() == null) {
                try {
                    BufferedReader br = new BufferedReader(new InputStreamReader(response.body().byteStream()));


                    try {
                        while ((line = br.readLine()) != null) {
                            JSONResponse += line;
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    if (JSONResponse != "") {

                        serviceOrdersList = gson.fromJson(JSONResponse,
                                new TypeToken<List<ServiceOrder>>() {}.getType());


                        //bus.post(serviceOrdersList);
                    }

                } catch (Exception e) {
                    Timber.d(e.getMessage());
                }
            }else{

            }*/




        }catch (IOException e){
            e.printStackTrace();
        }


        /*Observable<Response<ResponseBody>> observable = getServiceOrderService().getServiceOrders();
        observable.observeOn(AndroidSchedulers.mainThread())
                //.subscribeOn(Schedulers.io())
                .subscribe(responseSubscriber);*/

        return serviceOrdersList;
    }




    public ServiceOrder createServiceOrder(ServiceOrder serviceOrder){
        ServiceOrder newServiceOrder = null;
        try {
        Response<ResponseBody> response = getServiceOrderService().newServiceOrder(serviceOrder).execute();
            newServiceOrder = (ServiceOrder) parseResult(response, ServiceOrder.class, false);
        }catch (IOException e){
            e.printStackTrace();
        }

        return newServiceOrder;
    }

    public ServiceOrder updateServiceOrder(ServiceOrder serviceOrder){
        ServiceOrder newServiceOrder = null;
        try {
            Response<ResponseBody> response = getServiceOrderService().updateServiceOrder(serviceOrder.getId(), serviceOrder).execute();
            newServiceOrder = (ServiceOrder) parseResult(response, ServiceOrder.class, false);
        }catch (IOException e){
            e.printStackTrace();
        }

        return newServiceOrder;
    }

    public ArrayList<String> getMakes(String query){
        try {
            Call<ResponseBody> callCarMakes = getVehicleService().carMakes(query);

            Response<ResponseBody> response = callCarMakes.execute();

            makes = (ArrayList<String>) parseResult(response, String.class ,true);
        } catch (IOException e) {
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }


        return makes;

    }

    private Object parseResult(Response<ResponseBody> response, Object o, Boolean list){
        String line, JSONResponse = "";

        Class clazz =  (Class) o;
        Type type = null;
        if( !(o instanceof ArrayList) && !list) {
            clazz = o.getClass();
        }else if(o == String.class && list ){
            type = new TypeToken<ArrayList<String>>() {
            }.getType();
        }else if((o == ServiceOrder.class && list )){
            type = new TypeToken<ArrayList<ServiceOrder>>() {
            }.getType();
        }



            if (response.errorBody() == null) {
                try {
                    BufferedReader br = new BufferedReader(new InputStreamReader(response.body().byteStream()));


                    try {
                        while ((line = br.readLine()) != null) {
                            JSONResponse += line;
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    if (JSONResponse != "") {


                        if(list) {
                            return gson.fromJson(JSONResponse, type);
                        }else {
                            return gson.fromJson(JSONResponse, clazz);
                        }
                        //bus.post(serviceOrdersList);
                    }

                } catch (Exception e) {
                    Timber.d(e.getMessage());
                }
            }else{

            }

        return null;


    }


}