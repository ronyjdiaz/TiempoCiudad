package com.a3vam.tiempociudad.services;

import com.a3vam.tiempociudad.model.RootObject;
import com.google.gson.JsonObject;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**

 * @author Rony Diaz
 * @version %I, %G
 * @since 1.0
 */

public interface ServiceInterface {

    @GET("find?")
    Call<RootObject> getCiudades(@QueryMap Map<String, String> options);
    @GET("forecast?")
    Call<JsonObject> getPronosticoCiudad(@QueryMap Map<String, String> options);

}
