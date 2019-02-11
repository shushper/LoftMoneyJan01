package com.shushper.loftmoneyjan01;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {

    @GET("items")
    Call<List<Item>> getItems(@Query("type") String type, @Query("auth-token") String token);
}
