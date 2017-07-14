package com.example.prachi.knowyourstation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.prachi.knowyourstation.Network.ApiClient;
import com.example.prachi.knowyourstation.Network.ApiInterface;
import com.example.prachi.knowyourstation.Network.IndianRailwayApiCLient;
import com.example.prachi.knowyourstation.Network.LiveStationInfo;
import com.example.prachi.knowyourstation.Network.LiveStationResponse;
import com.example.prachi.knowyourstation.Network.StationCodes;
import com.example.prachi.knowyourstation.Network.TrainsAtStation;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by prachi on 14/7/17.
 */

public class LiveStationFragment extends Fragment{
    EditText stationcode;
    String station_code;
    ArrayList<StationCodes> stationlist;
    ArrayList<LiveStationInfo> trainlist;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.live_station_fragment,container,false);
        stationcode=(EditText) view.findViewById(R.id.livestation);
        Button search=(Button) view.findViewById(R.id.search);
        stationlist=new ArrayList<>();
        trainlist=new ArrayList<>();
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                station_code=stationcode.getText().toString();
                fetchstationcode(station_code);
            }
        });
        return view;
    }

    private void fetchstationcode(String station_code) {
        ApiInterface apiinterface=IndianRailwayApiCLient.getApiinterface();
        Call<LiveStationResponse> call=apiinterface.getstationcode(station_code);
        call.enqueue(new Callback<LiveStationResponse>() {
            @Override
            public void onResponse(Call<LiveStationResponse> call, Response<LiveStationResponse> response) {
                if(response.isSuccessful()){
                    stationlist.addAll(response.body().getStations());
                    Log.i("Live station",stationlist.get(0).getStationName());
                }
                fetchlivetrains(stationlist.get(0).getStationCode());
            }

            @Override
            public void onFailure(Call<LiveStationResponse> call, Throwable t) {
                Log.i("Live station",t.toString());
            }
        });


    }

    private void fetchlivetrains(String stationCode) {
        ApiInterface apiinterface= ApiClient.getApiinterface();
        Call<TrainsAtStation> call=apiinterface.gettrainsatstation(stationCode);
        call.enqueue(new Callback<TrainsAtStation>() {
            @Override
            public void onResponse(Call<TrainsAtStation> call, Response<TrainsAtStation> response) {
                if(response.isSuccessful()){
                    trainlist.addAll(response.body().getTrainsAtStation());
                    Log.i("trainsatstation",trainlist.get(0).getDestinationName());
                }
            }

            @Override
            public void onFailure(Call<TrainsAtStation> call, Throwable t) {

            }
        });
    }
}
