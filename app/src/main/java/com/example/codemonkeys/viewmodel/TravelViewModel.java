package com.example.codemonkeys.viewmodel;


import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import com.example.codemonkeys.model.Model;
import com.example.codemonkeys.model.Player;
import com.example.codemonkeys.model.PlayerInteractor;
import com.example.codemonkeys.model.SolarSystem;
import com.example.codemonkeys.model.Universe;


import java.util.List;

public class TravelViewModel extends AndroidViewModel {
    private final PlayerInteractor interactor;

    public TravelViewModel(@NonNull Application application) {
        super(application);
        interactor = Model.getInstance().getPlayerInteractor();
    }

    public List<SolarSystem> getSolarSystems() {
        return Universe.getInstance().getPlanets();
    }

}