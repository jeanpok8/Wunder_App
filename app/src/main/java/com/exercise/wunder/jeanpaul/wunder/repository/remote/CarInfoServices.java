package com.exercise.wunder.jeanpaul.wunder.repository.remote;

import com.exercise.wunder.jeanpaul.wunder.model.CarInfo;
import com.exercise.wunder.jeanpaul.wunder.model.LocationsInfo;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by jean paul on 9/20/18.
 */
interface CarInfoServices {

    @GET("locations.json")
    Observable<LocationsInfo> getCarsInfo();
}
