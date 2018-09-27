package com.exercise.wunder.jeanpaul.wunder.presenter;

import com.exercise.wunder.jeanpaul.wunder.presentation.CarInfoView;
import com.exercise.wunder.jeanpaul.wunder.presenter.base.BasePresenter;

/**
 * Created by jean paul on 9/21/18.
 */
public abstract class CarInfoPresenter extends BasePresenter<CarInfoView> {
    public abstract void getCarsInfo();
}
