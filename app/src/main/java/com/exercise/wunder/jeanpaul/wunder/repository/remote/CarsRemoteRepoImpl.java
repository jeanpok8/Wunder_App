package com.exercise.wunder.jeanpaul.wunder.repository.remote;

import com.exercise.wunder.jeanpaul.wunder.model.CarInfo;
import com.exercise.wunder.jeanpaul.wunder.model.LocationsInfo;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

/**
 * Created by jean paul on 9/20/18.
 */
public class CarsRemoteRepoImpl extends BaseRemote implements ICarsRemoteRepo {
    @Override
    public Observable<List<CarInfo>> getAllCarsInfo() {
        return create(CarInfoServices.class, RemoteConfiguration.BASE_URL).getCarsInfo().map(locationsInfo -> locationsInfo.getPlacemarks());
    }
}
