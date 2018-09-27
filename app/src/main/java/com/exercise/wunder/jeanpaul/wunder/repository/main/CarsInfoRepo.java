package com.exercise.wunder.jeanpaul.wunder.repository.main;

import com.exercise.wunder.jeanpaul.wunder.model.CarInfo;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by jean paul on 9/21/18.
 */
public interface CarsInfoRepo {

    Observable<List<CarInfo>> getAllCarsInfo();
}
