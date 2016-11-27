package com.a3vam.tiempociudad.services;

//import com.a3vam.tiempociudad.services.callbacks;
import android.app.ProgressDialog;
import android.location.Geocoder;
import android.util.Log;

import com.a3vam.tiempociudad.Contanst;
import com.a3vam.tiempociudad.activities.MainActivity;
import com.a3vam.tiempociudad.model.RootObject;
import com.a3vam.tiempociudad.services.callbacks.CallbackBase;
import com.a3vam.tiempociudad.services.callbacks.CallbackOpenMap;
import com.a3vam.tiempociudad.services.callbacks.Callbackpronostico;

import com.google.gson.JsonObject;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**

 * @author Rony Diaz
 * @version %I, %G
 * @since 1.0
 */

public class ServiceManager {

    /*public static void getLogin(String username, String password, final CallbackLogin callback){
       // ServiceHelper.getInterface().
    }*/

    public static RootObject getCiudades(double lat, double lon, final CallbackOpenMap arraycallback){
        ServiceInterface apiService = ServiceHelper.getInterface();
        Map<String, String> params = new HashMap<String, String>();
        params.put("lat", String.valueOf( lat));
        params.put("lon", String.valueOf( lon));
        params.put("cnt", "25");
        params.put("units","metric");
        params.put("lang","es");
        params.put("APPID", Contanst.APIOPENWEATHER);
        final RootObject[] respuesta = new RootObject[1];
        Call<RootObject> call =  apiService.getCiudades(params);


        call.enqueue(new Callback<RootObject>() {
            @Override
            public void onResponse(Call<RootObject> call, Response<RootObject> response) {
                respuesta[0] = response.body();
                if(response.body()==null)
                    arraycallback.onError(1,"SIn resultados");
                else {
                    Log.v(getClass().getName(),"Response exitoso ");
                    arraycallback.onSuccess(respuesta[0]);
                }






            }

            @Override
            public void onFailure(Call<RootObject> call, Throwable t) {

                Log.e(getClass().getName(),"error en onFailure " + t.getMessage());

            }
        });

        return respuesta[0];

    }

    public static JsonObject getPronosticoCiudad(String id, final Callbackpronostico arraycallback){

        ServiceInterface apiService = ServiceHelper.getInterface();
        Map<String, String> params = new HashMap<String, String>();
        params.put("id", id);
       // params.put("lon", String.valueOf( lon));
        params.put("units","metric");
        params.put("lang","es");
        params.put("APPID", Contanst.APIOPENWEATHER);
        final JsonObject[] respuesta = new JsonObject[1];// = new JsonObject[1];// = new RootObject[1];
        Call<JsonObject> call =  apiService.getPronosticoCiudad(params);


        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                respuesta[0] = response.body();
                if(response.body()==null)
                    arraycallback.onError(1,"SIn resultados");


                Log.v(getClass().getName(),"Response exitoso ");
                arraycallback.onSuccess(respuesta[0]);



            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.e(getClass().getName(),"error en onFailure " + t.getMessage());
            }


        });

        return respuesta[0];


    }


}
