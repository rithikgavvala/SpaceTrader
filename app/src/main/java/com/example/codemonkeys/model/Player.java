package com.example.codemonkeys.model;

import java.io.Serializable;

public class Player implements Serializable {
    public void setSystem(SolarSystem system) {
        this.system = system;
    }

    public int getPilotSkill() {
        return pilotSkill;
    }

    public void setPilotSkill(int pilotSkill) {
        this.pilotSkill = pilotSkill;
    }

    public int getFighterSkill() {
        return fighterSkill;
    }

    public void setFighterSkill(int fighterSkill) {
        this.fighterSkill = fighterSkill;
    }

    public int getTraderSkill() {
        return traderSkill;
    }

    public void setTraderSkill(int traderSkill) {
        this.traderSkill = traderSkill;
    }

    public int getEngineerSkill() {
        return engineerSkill;
    }

    private String characterName;
    private int pilotSkill;
    private int fighterSkill;
    private int traderSkill;
    private int engineerSkill;
    private String difficulty;
    private int credits;
    private Spaceship ship;
    private Universe universe;
    private SolarSystem system;

    private int fuelLevel;

    public boolean isLoaded() {
        return loaded;
    }

    public void setLoaded(boolean loaded) {
        this.loaded = loaded;
    }

    private boolean loaded;

    public Player() {

    }
    public Player(String name, int pil, int fight, int trad, int engine, String diff) {
        characterName = name;
        pilotSkill = pil;
        fighterSkill = fight;
        traderSkill = trad;
        engineerSkill = engine;
        difficulty = diff;
        credits = 1000;
        this.ship = new Spaceship(SpaceshipEnum.Gnat);
        money = 1000;
        fuelLevel = ship.getParsecs();
        loaded = false;
    }

    public int getFuelLevel() {
        return fuelLevel;
    }

//    public Universe getUniverse() {
//        return universe;
//    }

//    public void setUniverse(Universe universe) {
//        this.universe = universe;
//    }

    public void setEngineerSkill(int engineerSkill) {
        this.engineerSkill = engineerSkill;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public Spaceship getShip() {
        return ship;
    }

    public void setShip(Spaceship ship) {
        this.ship = ship;
    }

    private double money;

    public void setFuelLevel(int fuelLevel) {
        this.fuelLevel = fuelLevel;
    }
    public void updateSolarSystem(SolarSystem s){
//        Log.d("Solar System Player",s.getSystemName());
        this.system = s;
    }

    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public Spaceship getSpaceship(){
        return ship;
    }


    public double getMoney(){
        return money;
    }

    public void setMoney(double m){
        money = m;
    }

    public SolarSystem getSystem(){
        return system;
    }

    public String toString() {
        return "Player " + characterName + " with pilot skill of " + pilotSkill
                + ", fighter skill of " + fighterSkill
                + ", trader skill of " + traderSkill
                + ", and engineer skill of " + engineerSkill;
    }




}