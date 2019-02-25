package com.example.codemonkeys.model;

class SolarSystem {
    String systemName;
    private Location location;
    private TechLevel techLevel;
    private PoliticalSystem politicalSystem;

    public SolarSystem(String systemName, Location loc, TechLevel tl, PoliticalSystem pol) {
        this.location = loc;
        this.techLevel = tl;
        this.politicalSystem = pol;
        this.location = loc;
    }

}

