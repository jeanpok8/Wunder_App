package com.exercise.wunder.jeanpaul.wunder.presentation.base;

import android.support.v4.app.Fragment;

import com.exercise.wunder.jeanpaul.wunder.model.CarInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jean paul on 9/22/18.
 */
public abstract class BaseFragment extends Fragment implements MvpView{

    List<CarInfo> carsInfo=new ArrayList<>();


    public void setCarsInfo(List<CarInfo> carsInfo){
        setItemsInAdapter(carsInfo);
    }

    protected abstract void setItemsInAdapter(List<CarInfo> carsInfo);

    public void showCarsInfo(List<CarInfo> carsInfo){
        this.setItemsInAdapter(carsInfo);
    };
}
