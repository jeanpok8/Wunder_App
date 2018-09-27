package com.exercise.wunder.jeanpaul.wunder.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import com.exercise.wunder.jeanpaul.wunder.repository.local.Converters;
import com.exercise.wunder.jeanpaul.wunder.repository.local.DBConstant;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jean paul on 9/20/18.
 */

@Entity(tableName = DBConstant.CARINFO_TABLE_NAME, primaryKeys = {"vin"})
public class CarInfo {
    
    @NonNull
    private String vin = "";
    private String address = "";
    @TypeConverters(Converters.class)
    private List<Double> coordinates = new ArrayList<>();
    private String engineType = "";
    private String exterior = "";
    private int fuel;
    private String interior = "";
    private String name = "";


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Double> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<Double> coordinates) {
        this.coordinates = coordinates;
    }

    public String getEngineType() {
        return engineType;
    }

    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }

    public String getExterior() {
        return exterior;
    }

    public void setExterior(String exterior) {
        this.exterior = exterior;
    }

    public int getFuel() {
        return fuel;
    }

    public void setFuel(int fuel) {
        this.fuel = fuel;
    }

    public String getInterior() {
        return interior;
    }

    public void setInterior(String interior) {
        this.interior = interior;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }
}
