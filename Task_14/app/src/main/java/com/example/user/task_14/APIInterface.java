package com.example.user.task_14;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

interface APIInterface {
    @Headers("X-CMC_PRO_API_KEY:516d8dbd-e8f5-417e-ba9e-86b25ade1280")
    @GET("/v1/cryptocurrency/listings/latest?")
    Call<CryptoList> doGetUserList(@Query("limit") String page);
}
