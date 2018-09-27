package com.exercise.wunder.jeanpaul.wunder.presentation.base;

import com.exercise.wunder.jeanpaul.wunder.model.CarInfo;

import java.util.List;

/**
 * Created by jean paul on 9/21/18.
 */
public interface MvpView {
    void showCarsInfo(List<CarInfo> carsInfo);
}
