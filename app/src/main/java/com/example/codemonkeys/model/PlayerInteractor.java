package com.example.codemonkeys.model;

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
    public void sendSolarSystemObject(SolarSystem s){
        myRepository.getPlayer().updateSolarSystem(s);
    }
}
