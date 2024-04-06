package com.example.readsms.api;

import com.example.readsms.models.Response;
import com.example.readsms.models.Transaction;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {
    @GET("/web/auth/get")
    Call<Response> get();

    @POST("/sendMail")
    Call<Response> postRequest(@Body Transaction transaction);
}
