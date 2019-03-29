package com.example.codemonkeys.model;

import java.util.List;

public class PlayerInteractor {
    private Repository myRepository;


    public PlayerInteractor(Repository repo) {
        myRepository = repo;
    }
    public void updatePlayer(Player p) {
        myRepository.updateCurrentPlayer(p);
    }

    public Player getPlayer() {
        return myRepository.getPlayer();
    }
    public List<SolarSystem> getPlayerUniverse() {return myRepository.getSolarSystems(); }

    public void sendSolarSystemObject(SolarSystem s){
        myRepository.getPlayer().updateSolarSystem(s);
    }


}
