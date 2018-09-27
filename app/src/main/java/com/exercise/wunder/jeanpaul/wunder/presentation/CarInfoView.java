package com.exercise.wunder.jeanpaul.wunder.presentation;

import com.exercise.wunder.jeanpaul.wunder.model.CarInfo;
import com.exercise.wunder.jeanpaul.wunder.presentation.base.MvpView;

import java.util.List;

/**
 * Created by jean paul on 9/21/18.
 */
public interface CarInfoView extends MvpView {

    void showLoading();
    void hideLoading();
    void showError(String error);
}
