package com.rajawali.weatherapps.presenter;

import android.content.Context;

import com.rajawali.weatherapps.api.APIInterface;
import com.rajawali.weatherapps.api.APIService;
import com.rajawali.weatherapps.model.WeatherResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.rajawali.weatherapps.Constant.appid;

/**
 * Created by macbookultimate on 12/27/17.
 */

public class WeatherPresenter {
    Context ApplicationContext;
    APIInterface mApiService;

    WeatherInterface onDone;

    public WeatherPresenter(Context context, WeatherInterface listener){
        this.ApplicationContext = context;
        this.onDone = listener;
        APIService service = new APIService();
        mApiService = service.getInterfaceService();
    }

    public void getDataProcess(String zipCode){
        onDone.onLoadData();
        String remoteUrl = "/data/2.5/forecast?zip="+ zipCode +"&appid=" + appid;

        Call<WeatherResponse> dataService = mApiService.getWeather(remoteUrl);
        dataService.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                int StatusCode = response.code();
                if(StatusCode == 200){
                    onDone.onLoadSuccess(response.body());
                }else{
                    onDone.onLoadFailure();
                }

            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                onDone.onLoadFailure();
            }
        });
    }

    public interface WeatherInterface{
        public void onLoadData();
        public void onLoadSuccess(WeatherResponse data);
        public void onLoadFailure();
    }
}
