package com.exercise.wunder.jeanpaul.wunder.repository.local;

import com.exercise.wunder.jeanpaul.wunder.model.CarInfo;

import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Observable;

/**
 * Created by jean paul on 9/20/18.
 */
public class CarsLocalRepoImpl implements CarsLocalRepo {

    CarsInfoDao carsInfoLocalDao;

    public CarsLocalRepoImpl(CarsInfoDao carsInfoLocalDao) {
        this.carsInfoLocalDao = carsInfoLocalDao;
    }

    @Override
    public Observable<List<CarInfo>> getAllCars() {
        return Observable.fromCallable(() -> carsInfoLocalDao.getAll());
    }

    @Override
    public void addCars(List<CarInfo> carsInfo) {
        carsInfoLocalDao.insertAll(carsInfo);
    }
}
