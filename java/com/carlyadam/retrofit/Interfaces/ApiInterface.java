package com.carlyadam.retrofit.Interfaces;

import com.carlyadam.retrofit.Data.ApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    //api url
    String URL = "https://api.myjson.com/bins/";


    //Get request
    @GET("q6hnk")
    Call <ApiResponse> getBooks();
}
