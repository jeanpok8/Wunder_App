package com.exercise.wunder.jeanpaul.wunder.repository.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.exercise.wunder.jeanpaul.wunder.model.CarInfo;

/**
 * Created by jean paul on 9/20/18.
 */
@Database(entities = {CarInfo.class}, version = 1, exportSchema = false)
public abstract class WunderDatabase extends RoomDatabase {

    public abstract CarsInfoDao carInfoDao();
}
