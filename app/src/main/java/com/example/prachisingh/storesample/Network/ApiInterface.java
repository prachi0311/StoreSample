package com.example.prachisingh.storesample.Network;

import com.example.prachisingh.storesample.ApiResponses.ProductItemsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by prachisingh on 21/09/18.
 */

public interface ApiInterface {
    @GET("products.json")
    Call<ProductItemsResponse> getProduct(@Query("page") int pageNumber,@Query("access_token") String accessToken);
}
