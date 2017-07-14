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
import android.widget.Toast;

import com.example.prachi.knowyourstation.Network.ApiClient;
import com.example.prachi.knowyourstation.Network.ApiInterface;
import com.example.prachi.knowyourstation.Network.StationInfo;
import com.example.prachi.knowyourstation.Network.TrainRoute;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by prachi on 12/7/17.
 */

public class TrainRouteFragment extends Fragment {
    EditText trainno;
    String train_number;
    String test;
    ArrayList<StationInfo> route;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_main,container,false);
        trainno=(EditText) view.findViewById(R.id.TrainNo);
        route=new ArrayList<>();
      //  Log.i("train_no",train_number);
        Button search=(Button) view.findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                train_number=trainno.getText().toString();
                Toast.makeText(getContext(),train_number,Toast.LENGTH_SHORT).show();
                fetchtrainroute(train_number);
            }
        });

        return view;

    }

    private void fetchtrainroute(final String train_number) {
        ApiInterface apiinterface= ApiClient.getApiinterface();
        Call<TrainRoute> trainroute=apiinterface.gettrainroute(train_number);
        trainroute.enqueue(new Callback<TrainRoute>() {
            @Override
            public void onResponse(Call<TrainRoute> call, Response<TrainRoute> response) {
                Log.i("response",String.valueOf(response.code()));
                if(response.isSuccessful()){
                    test=response.body().getSourceName();
                    route.addAll(response.body().getStations());
                    Log.i("response",route.get(10).getStationName());
                }
            }

            @Override
            public void onFailure(Call<TrainRoute> call, Throwable t) {
                Log.i("response",t.toString());
            }
        });
    }
}
