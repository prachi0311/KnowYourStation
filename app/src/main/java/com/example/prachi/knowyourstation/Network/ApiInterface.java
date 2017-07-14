package com.example.prachi.knowyourstation.Network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * Created by prachi on 12/7/17.
 */

public interface ApiInterface {

    @Headers({"X-Mashape-Key:HRTH0PRHnvmshsqn186QbSlmuRnAp1LkD3wjsnH5UQtrJVXKo2", "Accept: application/json"})
    @GET("trainSchedule.php")
    Call<TrainRoute> gettrainroute(@Query("train_no") String train_no);

    @Headers({"X-Mashape-Key:HRTH0PRHnvmshsqn186QbSlmuRnAp1LkD3wjsnH5UQtrJVXKo2", "Accept: application/json"})
    @GET("findstations.php")
    Call<LiveStationResponse> getstationcode(@Query("station") String station);

    @Headers({"X-Mashape-Key:HRTH0PRHnvmshsqn186QbSlmuRnAp1LkD3wjsnH5UQtrJVXKo2", "Accept: application/json"})
    @GET("liveStation.php?in_next_hour=2")
    Call<TrainsAtStation> gettrainsatstation(@Query("journey_station") String journey_station);


}
