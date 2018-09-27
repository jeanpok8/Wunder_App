package com.exercise.wunder.jeanpaul.wunder.presenter;

import com.exercise.wunder.jeanpaul.wunder.model.CarInfo;
import com.exercise.wunder.jeanpaul.wunder.presentation.base.BaseFragment;
import com.exercise.wunder.jeanpaul.wunder.presentation.base.MvpView;
import com.exercise.wunder.jeanpaul.wunder.repository.local.CarsLocalRepo;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by jean paul on 9/23/18.
 */
public class MapLocationsPresenter {

    private CarsLocalRepo localCarInfoRepo;
    private MvpView view;

    public MapLocationsPresenter(CarsLocalRepo localRepo) {

        this.localCarInfoRepo = localRepo;

    }

    public void getCarsInfo() {

        localCarInfoRepo.getAllCars().observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResponse, this::handleError);

    }

    private void handleError(Throwable throwable) {
    }

    private void handleResponse(List<CarInfo> carInfos) {
        view.showCarsInfo(carInfos);
    }

    public void attachView(BaseFragment fragment) {
        view = fragment;
    }


}
