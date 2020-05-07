package com.example.myapplication.net.api;

import java.util.HashMap;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface NetApi {
    @GET()
    Observable<ResponseBody> get(@Url String url);

    @GET()
    Observable<ResponseBody> get(@Url String url, @QueryMap HashMap<String,String> queryMap);

    @POST()
    Observable<ResponseBody> post(@Url String url);

    @POST()
    Observable<ResponseBody> post(@Url String url, @QueryMap HashMap<String,String> queryMap);
}
