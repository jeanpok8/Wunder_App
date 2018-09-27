package com.exercise.wunder.jeanpaul.wunder.repository.local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.exercise.wunder.jeanpaul.wunder.model.CarInfo;

import java.util.List;

/**
 * Created by jean paul on 9/20/18.
 */
@Dao
public interface CarsInfoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<CarInfo> carsInfo);

    @Query("SELECT * FROM " + DBConstant.CARINFO_TABLE_NAME)
    List<CarInfo> getAll();
}
