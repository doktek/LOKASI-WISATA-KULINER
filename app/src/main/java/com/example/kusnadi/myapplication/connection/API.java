package com.example.kusnadi.myapplication.connection;

import com.example.kusnadi.myapplication.connection.callbacks.CallbackDevice;
import com.example.kusnadi.myapplication.connection.callbacks.CallbackListPlace;
import com.example.kusnadi.myapplication.connection.callbacks.CallbackPlaceDetails;
import com.example.kusnadi.myapplication.model.DeviceInfo;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Kusnadi on 12/10/2016.
 */
public interface API {

    String CACHE = "Cache-Control: max-age=0";
    String AGENT = "User-Agent: Place";

    /* Recipe API transaction ------------------------------- */

    @Headers({CACHE, AGENT})
    @GET("app/services/listPlaces")
    Call<CallbackListPlace> getPlacesByPage(
            @Query("page") int page,
            @Query("count") int count,
            @Query("draft") int draft
    );

    @Headers({CACHE, AGENT})
    @GET("app/services/getPLaceDetails")
    Call<CallbackPlaceDetails> getPlaceDetails(
            @Query("place_id") int place_id
    );

    @Headers({CACHE, AGENT})
    @POST("app/services")
    Call<CallbackDevice> registerDevice(
            @Body DeviceInfo deviceInfo
    );
}
