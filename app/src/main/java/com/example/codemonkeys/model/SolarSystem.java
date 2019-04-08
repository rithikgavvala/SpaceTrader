package com.example.codemonkeys.model;

import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SolarSystem implements Serializable {
    private String systemName;
    private Location location;
    private TechLevel techLevel;
    private Resources resources;
    private List<TradeGood> listofResources;


    public SolarSystem(String systemName, Location loc, TechLevel tl, Resources pol) {
        this.systemName = systemName;
        this.location = loc;
        this.techLevel = tl;
        this.resources = pol;
        this.location = loc;
        this.listofResources = findGoodsAvailabletoBuy();

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

    public String getSystemName(){
        return systemName;
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

    public List<TradeGood> getListofResources() {
        return listofResources;
    }
    public List<TradeGood> findGoodsAvailabletoBuy() {
        List<TradeGood> list = new ArrayList<TradeGood>();
        for (TradeGoodEnum t : TradeGoodEnum.values()) {
            if(techLevel.getRank() >= t.getMTLP()){
                TradeGood nt = new TradeGood(t);
                nt.generatePrice(this);
                Log.i("SYSTEM INFO", "Rank: " + this.techLevel.getRank());
                list.add(nt);
            }
        }
        return list;
    }

    //TODO put a method here and add to constructor which allows you to list what items can be sold
}

