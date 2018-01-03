package com.rajawali.weatherapps.api;

import com.rajawali.weatherapps.model.WeatherResponse;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Url;

/**
 * Created by macbookultimate on 5/2/17.
 */

public interface APIInterface {
    @GET
    Call<WeatherResponse> getWeather(@Url String Url);
}
