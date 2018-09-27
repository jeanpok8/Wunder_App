package com.exercise.wunder.jeanpaul.wunder.repository.local;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by jean paul on 9/21/18.
 */
public class Converters {

    @TypeConverter
    public String fromCoordinatesList(List<Double> coordinates) {
        if (coordinates == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<Double>>() {
        }.getType();
        String json = gson.toJson(coordinates, type);
        return json;
    }

    @TypeConverter
    public List<Double> toCoordinatesList(String coordinatesString) {
        if (coordinatesString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<Double>>() {
        }.getType();
        return gson.fromJson(coordinatesString, type);
    }
}
