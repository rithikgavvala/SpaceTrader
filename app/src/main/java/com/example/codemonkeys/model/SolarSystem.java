package com.example.codemonkeys.model;

import java.util.List;

public class SolarSystem {
    String systemName;
    private Location location;
    private TechLevel techLevel;
    private List<TradeGood> listofResources;

    public SolarSystem(String systemName, Location loc, TechLevel tl) {
        this.location = loc;
        this.techLevel = tl;
        this.location = loc;
        listofResources = findGoodsAvailabletoSell();
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public TechLevel getTechLevel() {
        return techLevel;
    }

    public void setTechLevel(TechLevel techLevel) {
        this.techLevel = techLevel;
    }

    private List<TradeGood> findGoodsAvailabletoSell(){

    }


    //TODO put a method here and add to constructor which allows you to list what items can be sold
}

