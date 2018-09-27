package com.exercise.wunder.jeanpaul.wunder.repository.main;

import com.exercise.wunder.jeanpaul.wunder.model.CarInfo;
import com.exercise.wunder.jeanpaul.wunder.model.LocationsInfo;
import com.exercise.wunder.jeanpaul.wunder.repository.local.CarsLocalRepo;
import com.exercise.wunder.jeanpaul.wunder.repository.remote.ICarsRemoteRepo;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by jean paul on 9/21/18.
 */
public class CarsInfoRepoImpl implements CarsInfoRepo {
    ICarsRemoteRepo remoteCarInfoRepo;
    CarsLocalRepo localCarInfoRepo;

    public CarsInfoRepoImpl(ICarsRemoteRepo remoteRepo,
                            CarsLocalRepo localRepo) {
        this.remoteCarInfoRepo = remoteRepo;
        this.localCarInfoRepo = localRepo;
    }

    @Override
    public Observable<List<CarInfo>> getAllCarsInfo() {

        return Observable.mergeDelayError(remoteCarInfoRepo.getAllCarsInfo().doOnNext(carsInfo -> {
            System.out.print(carsInfo);
            localCarInfoRepo.addCars(carsInfo);
        }).subscribeOn(Schedulers.io()), localCarInfoRepo.getAllCars().subscribeOn(Schedulers.io()));
    }
}
