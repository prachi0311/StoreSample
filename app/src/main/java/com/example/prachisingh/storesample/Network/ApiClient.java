package com.example.prachisingh.storesample.Network;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by prachisingh on 21/09/18.
 */

public class ApiClient {
    static private ApiInterface apiInterface;

    public static ApiInterface getAuthorizedApiInterface(){
        if(apiInterface ==null){

            Retrofit retrofit= new Retrofit.Builder().baseUrl("https://shopicruit.myshopify.com/admin/")
                    .client(new OkHttpClient.Builder().build())
                    .addConverterFactory(GsonConverterFactory.create()).build();
            apiInterface =retrofit.create(com.example.prachisingh.storesample.Network.ApiInterface.class);
        }
        return apiInterface;
    }
}
