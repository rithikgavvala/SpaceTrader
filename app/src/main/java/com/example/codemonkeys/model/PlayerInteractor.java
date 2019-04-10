package com.example.codemonkeys.model;

import java.io.Serializable;
import java.util.List;

public class PlayerInteractor implements Serializable {
    private final Repository myRepository;


    public PlayerInteractor(Repository repo) {
        myRepository = repo;
    }
    public void updatePlayer(Player p) {
        myRepository.updateCurrentPlayer(p);
    }

    public Player getPlayer() {
        return myRepository.getPlayer();
    }
    public void sendSolarSystemObject(SolarSystem s){
        myRepository.getPlayer().updateSolarSystem(s);
    }
    public List<SolarSystem> getPlayerSystems(){
        return Universe.getInstance().getPlanets();
    }


}
