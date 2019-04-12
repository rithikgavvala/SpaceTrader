package com.example.codemonkeys.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import com.example.codemonkeys.R;
import com.example.codemonkeys.model.SolarSystem;
import com.example.codemonkeys.viewmodel.ConfigurationViewModel;

public class TravelAdapter extends RecyclerView.Adapter<TravelAdapter.SolarSystemViewHolder> {

    private List<SolarSystem> solarSystemList;

    private OnSolarSystemClickListener listener;

    private ConfigurationViewModel viewModel;



    @NonNull
    @Override
    public SolarSystemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.travel_item, parent, false);

        return new SolarSystemViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull SolarSystemViewHolder holder, int position) {

        SolarSystem SolarSystem = solarSystemList.get(position);



        Log.d("APP", "Binding: " + position + " " + solarSystemList.get(position));

        holder.SolarSystemName.setText(SolarSystem.getSystemName());
        holder.SolarSystemTechLevel.setText(SolarSystem.getTechLevel().toString());


    }

    @Override
    public int getItemCount() {
        return solarSystemList.size();
    }

    public void setSolarSystemList(List<SolarSystem> list) {
        solarSystemList = list;
        notifyDataSetChanged();
    }

    public void setOnSolarSystemClickListener(OnSolarSystemClickListener listener) {
        this.listener = listener;
    }

    public interface OnSolarSystemClickListener {
        void onSolarSystemClicked(SolarSystem SolarSystem);
    }

    class SolarSystemViewHolder extends RecyclerView.ViewHolder {
        private final TextView SolarSystemName;
        private final TextView SolarSystemTechLevel;



        public SolarSystemViewHolder(@NonNull View itemView) {
            super(itemView);
            SolarSystemName = itemView.findViewById(R.id.solarSystemName);
            SolarSystemTechLevel = itemView.findViewById(R.id.techLevel);

            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();

                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onSolarSystemClicked(solarSystemList.get(position));
                    }
                }
            });

        }
    }
}