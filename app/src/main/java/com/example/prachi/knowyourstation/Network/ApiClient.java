package com.example.prachi.knowyourstation.Network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by prachi on 12/7/17.
 */

public class ApiClient {
    static ApiInterface apiinterface;

    public static ApiInterface getApiinterface(){
        if(apiinterface==null){

            Retrofit retrofit= new Retrofit.Builder().baseUrl("https://devru-ntes-national-train-enquiry-system-v1.p.mashape.com/")
                    .addConverterFactory(GsonConverterFactory.create()).build();
            apiinterface=retrofit.create(ApiInterface.class);
        }
        return apiinterface;
    }

}