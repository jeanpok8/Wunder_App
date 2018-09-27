package com.exercise.wunder.jeanpaul.wunder.presenter;

import com.exercise.wunder.jeanpaul.wunder.model.CarInfo;
import com.exercise.wunder.jeanpaul.wunder.repository.main.CarsInfoRepo;

import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by jean paul on 9/21/18.
 */
public class CarInfoPresenterImpl extends CarInfoPresenter {

    private Scheduler scheduler;

    private Disposable disposable;

    private CarsInfoRepo carsInfoRepo;


    public CarInfoPresenterImpl(Scheduler scheduler, CarsInfoRepo carsInfoRepo) {
        super();
        this.carsInfoRepo = carsInfoRepo;
        this.scheduler = scheduler;
    }

    @Override
    public void getCarsInfo() {

        if (!isViewAttached())
            return;

        getView().showLoading();

        disposable = carsInfoRepo.getAllCarsInfo().observeOn(AndroidSchedulers.mainThread()).subscribe(this::handleResponse, this::handleError);
    }

    private void handleError(Throwable throwable) {
        if (!isViewAttached())
            return;
        getView().showError(throwable.getLocalizedMessage());

    }

    private void handleResponse(List<CarInfo> carInfos) {
        if (!isViewAttached())
            return;

        getView().showCarsInfo(carInfos);
    }


    @Override
    public void onDetach() {
        super.onDetach();
        disposable.dispose();

    }
}
