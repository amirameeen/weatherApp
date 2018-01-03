package com.rajawali.weatherapps.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.rajawali.weatherapps.Constant.API_URL;

/**
 * Created by macbookultimate on 5/2/17.
 */

public class APIService {
    public Retrofit BaseURLAPI()
    {
        OkHttpClient okHttpClient = new OkHttpClient();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL).client(okHttpClient.newBuilder()
                        .connectTimeout(60, TimeUnit.SECONDS)
                        .readTimeout(60, TimeUnit.SECONDS)
                        .writeTimeout(60, TimeUnit.SECONDS).build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }

    public APIInterface getInterfaceService(){
        OkHttpClient okHttpClient = new OkHttpClient();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL).client(okHttpClient.newBuilder()
                        .connectTimeout(60, TimeUnit.SECONDS)
                        .readTimeout(60, TimeUnit.SECONDS)
                        .writeTimeout(60, TimeUnit.SECONDS).build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIInterface mInterfaceService = retrofit.create(APIInterface.class);
        return mInterfaceService;
    }


}
