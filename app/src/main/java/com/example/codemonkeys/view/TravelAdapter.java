package com.example.codemonkeys.view;

import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import com.example.codemonkeys.R;
import com.example.codemonkeys.model.Player;
import com.example.codemonkeys.model.SolarSystem;
import com.example.codemonkeys.model.Universe;
import com.example.codemonkeys.viewmodel.ConfigurationViewModel;

public class TravelAdapter extends RecyclerView.Adapter<TravelAdapter.SolarSystemViewHolder> {

    private List<SolarSystem> SolarSystemList;

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

        SolarSystem SolarSystem = SolarSystemList.get(position);



        Log.d("APP", "Binding: " + position + " " + SolarSystemList.get(position));

        holder.SolarSystemName.setText(SolarSystem.getSystemName());
        holder.SolarSystemTechLevel.setText(SolarSystem.getTechLevel().toString());


    }

    @Override
    public int getItemCount() {
        return SolarSystemList.size();
    }

    public void setSolarSystemList(List<SolarSystem> list) {
        SolarSystemList = list;
        notifyDataSetChanged();
    }

    public void setOnSolarSystemClickListener(OnSolarSystemClickListener listener) {
        this.listener = listener;
    }

    public interface OnSolarSystemClickListener {
        void onSolarSystemClicked(SolarSystem SolarSystem);
    }

    class SolarSystemViewHolder extends RecyclerView.ViewHolder {
        private TextView SolarSystemName;
        private TextView SolarSystemTechLevel;



        public SolarSystemViewHolder(@NonNull View itemView) {
            super(itemView);
            SolarSystemName = itemView.findViewById(R.id.solarSystemName);
            SolarSystemTechLevel = itemView.findViewById(R.id.techLevel);

            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();

                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onSolarSystemClicked(SolarSystemList.get(position));
                    }
                }
            });

        }
    }
}