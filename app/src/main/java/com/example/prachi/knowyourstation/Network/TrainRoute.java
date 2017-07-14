package com.example.prachi.knowyourstation.Network;

import java.util.ArrayList;

/**
 * Created by prachi on 12/7/17.
 */

public class TrainRoute {
    public String getSourceName() {
        return SourceName;
    }

    public void setSourceName(String sourceName) {
        SourceName = sourceName;
    }

    String SourceName;

    public ArrayList<StationInfo> getStations() {
        return stations;
    }

    public void setStations(ArrayList<StationInfo> stations) {
        this.stations = stations;
    }

    ArrayList<StationInfo> stations;
}
