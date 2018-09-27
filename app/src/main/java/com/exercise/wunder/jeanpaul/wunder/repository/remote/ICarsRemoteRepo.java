package com.exercise.wunder.jeanpaul.wunder.repository.remote;

import com.exercise.wunder.jeanpaul.wunder.model.CarInfo;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by jean paul on 9/20/18.
 */
public interface ICarsRemoteRepo {
    Observable<List<CarInfo>> getAllCarsInfo();
}
