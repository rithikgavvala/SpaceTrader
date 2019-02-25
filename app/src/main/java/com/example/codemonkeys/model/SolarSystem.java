package com.example.codemonkeys.model;

class SolarSystem {
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

}

