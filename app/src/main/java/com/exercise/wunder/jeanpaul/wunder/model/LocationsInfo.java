package com.exercise.wunder.jeanpaul.wunder.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jean paul on 9/22/18.
 */
public class LocationsInfo {


    List<CarInfo> placemarks = new ArrayList<>();

    public List<CarInfo> getPlacemarks() {
        return placemarks;
    }

    public void setPlacemarks(List<CarInfo> placemarks) {
        this.placemarks = placemarks;
    }
}
