package com.exercise.wunder.jeanpaul.wunder.presentation.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.exercise.wunder.jeanpaul.wunder.R;
import com.exercise.wunder.jeanpaul.wunder.model.CarInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jean paul on 9/21/18.
 */
public class CarsInfoAdapter extends RecyclerView.Adapter<CarsInfoAdapter.CarInfoViewHolder> implements ICarsInfoAdapter{
    private Context context;
    private List<CarInfo> carsInfo = new ArrayList<>();


    public CarsInfoAdapter(Context context) {
        this.context = context;
        this.carsInfo = carsInfo;

    }

    @NonNull
    @Override
    public CarInfoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.car_info_item, parent, false);
        return new CarInfoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CarInfoViewHolder holder, int position) {
        CarInfo item = carsInfo.get(position);
        holder.car_name.setText(item.getName());
        holder.address.setText(item.getAddress());
        List<Double> coordinates = item.getCoordinates();
        if (coordinates != null && coordinates.size() >= 2)
            holder.cordinates.setText("Latitude:" + coordinates.get(0) + " | " + "Longitude:" + coordinates.get(1));
        holder.vin_nbr.setText(item.getVin());
        holder.fuel.setText(""+item.getFuel());
        holder.interior.setText(item.getInterior());
        holder.engine.setText(item.getEngineType());
        holder.exterior.setText(item.getExterior());

    }

    @Override
    public int getItemCount() {
        return carsInfo.size();
    }

    @Override
    public void setItemInAdapter(List<CarInfo> carInfos) {
        if(carInfos!=null) {
            this.carsInfo.clear();
            this.carsInfo.addAll(carInfos);
            notifyDataSetChanged();
        }
    }

    public static class CarInfoViewHolder extends RecyclerView.ViewHolder {
        TextView car_name, vin_nbr, fuel, engine, address, cordinates, interior, exterior;

        public CarInfoViewHolder(View itemView) {
            super(itemView);
            car_name = itemView.findViewById(R.id.car_name);
            vin_nbr = itemView.findViewById(R.id.vin_nbr);
            fuel = itemView.findViewById(R.id.fuel_value);
            engine = itemView.findViewById(R.id.engine_model);
            address = itemView.findViewById(R.id.address);
            cordinates = itemView.findViewById(R.id.cordinates);
            exterior = itemView.findViewById(R.id.exterior_value);
            interior = itemView.findViewById(R.id.interior_value);

        }
    }

}
