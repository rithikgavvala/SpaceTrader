package com.example.codemonkeys.model;

public class SolarSystem {
    String systemName;
    private Location location;
    private TechLevel techLevel;
    private Resources resources;

    public SolarSystem(String systemName, Location loc, TechLevel tl, Resources pol) {
        this.location = loc;
        this.techLevel = tl;
        this.resources = pol;
        this.location = loc;
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
}

