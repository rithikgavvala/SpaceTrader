package com.example.codemonkeys.viewmodel;


import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import com.example.codemonkeys.model.Model;
import com.example.codemonkeys.model.Player;
import com.example.codemonkeys.model.PlayerInteractor;
import com.example.codemonkeys.model.SolarSystem;

public class ConfigurationViewModel  extends AndroidViewModel {

    private final PlayerInteractor interactor;
    public ConfigurationViewModel(@NonNull Application application) {
        super(application);
        interactor = Model.getInstance().getPlayerInteractor();
    }

    public void updatePlayer(Player p) {
        interactor.updatePlayer(p);
    }

    public void generatePlayerSolarSystem(SolarSystem s){
        interactor.sendSolarSystemObject(s);
    }

    public Player getPlayer() {
        return interactor.getPlayer();
    }
}
