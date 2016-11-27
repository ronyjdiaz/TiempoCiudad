package com.a3vam.tiempociudad.services;



import com.a3vam.tiempociudad.Contanst;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**

 * @author Rony Diaz
 * @version %I, %G
 * @since 1.0
 */

public class ServiceHelper {
    private Retrofit mRetrofit;
    private ServiceInterface mService;
    private static ServiceHelper mInstance;

    public static ServiceInterface getInterface(){
        if (mInstance==null){
            mInstance = new ServiceHelper();
        }
        return mInstance.mService;
    }

    private ServiceHelper(){

        final OkHttpClient mokHttpClient = new OkHttpClient.Builder()
                .readTimeout(120, TimeUnit.SECONDS)
                .connectTimeout(120, TimeUnit.SECONDS)
                .build();
        mRetrofit = new Retrofit.Builder()
                .baseUrl(Contanst.URL_OPENweather)
                .addConverterFactory(GsonConverterFactory.create())
                .client(mokHttpClient)
                .build();
        mService = mRetrofit.create(ServiceInterface.class);

    }

}
