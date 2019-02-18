package com.shushper.loftmoneyjan01;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Api {


    @GET("auth")
    Call<AuthResponse> auth(@Query("social_user_id") String userId);


    @GET("items")
    Call<List<Item>> getItems(@Query("type") String type, @Query("auth-token") String token);


    @POST("items/add")
    Call<Object> addItem(@Body AddItemRequest request, @Query("auth-token") String token);

    @POST("items/remove")
    Call<Object> removeItem(@Query("id") Long id, @Query("auth-token") String token);

}
