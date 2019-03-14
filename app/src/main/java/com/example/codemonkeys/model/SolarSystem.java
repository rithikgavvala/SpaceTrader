package com.example.codemonkeys.model;

import java.util.ArrayList;
import java.util.List;

public class SolarSystem {
    String systemName;
    private Location location;
    private TechLevel techLevel;
    private Resources resources;
    private List<TradeGood> listofResources;

    public SolarSystem(String systemName, Location loc, TechLevel tl, Resources pol) {
        this.location = loc;
        this.techLevel = tl;
        this.resources = pol;
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

    public Resources getResources() {
        return resources;
    }

    public void setResources(Resources resources) {
        this.resources = resources;
    }
    private List<TradeGood> findGoodsAvailabletoSell(){
        return new ArrayList<>();
    }

    //TODO put a method here and add to constructor which allows you to list what items can be sold
}

