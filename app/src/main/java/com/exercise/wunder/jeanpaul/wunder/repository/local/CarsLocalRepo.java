package com.exercise.wunder.jeanpaul.wunder.repository.local;

import com.exercise.wunder.jeanpaul.wunder.model.CarInfo;
import com.exercise.wunder.jeanpaul.wunder.model.LocationsInfo;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by jean paul on 9/20/18.
 */
public interface CarsLocalRepo {

    Observable<List<CarInfo>> getAllCars();

    void addCars(List<CarInfo> cars);

}
