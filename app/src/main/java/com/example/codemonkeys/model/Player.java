package com.example.codemonkeys.model;

import android.util.Log;
import android.widget.Space;

import java.util.ArrayList;
import java.util.List;

public class Player {
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

    private int fuelLevel;

    public Player(String name, int pil, int fight, int trad, int engine, String diff) {
        characterName = name;
        pilotSkill = pil;
        fighterSkill = fight;
        traderSkill = trad;
        engineerSkill = engine;
        difficulty = diff;
        credits = 1000;
        this.ship = Spaceship.Gnat;
        money = 1000;
        fuelLevel = ship.getParsecs();
    }

    public int getFuelLevel() {
        return fuelLevel;
    }



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

    private SolarSystem system;
    private double money;

    public void setFuelLevel(int fuelLevel) {
        this.fuelLevel = fuelLevel;
    }
    public void updateSolarSystem(SolarSystem s){
        Log.d("Solar System Player",s.getSystemName());
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

    public List<SolarSystem> getTravelList(){
        List<SolarSystem> travelList = new ArrayList<>();
        Universe u = Universe.getInstance();
        List<SolarSystem> currPlanets = new ArrayList<>();
        currPlanets = u.getUniverse();

        for(int i = 0; i < currPlanets.size(); i++){
            if(calcDistance(currPlanets.get(i), system)  < 5 * ship.getParsecs()
                    && fuelLevel  > calcDistance(currPlanets.get(i), system) / 6){
                travelList.add(currPlanets.get(i));
            }
        }
        return travelList;
    }

    public int calcDistance(SolarSystem sys1, SolarSystem sys2){
        int difX = sys1.getLocation().getX() - sys2.getLocation().getX();
        int difY = sys1.getLocation().getY() - sys2.getLocation().getY();

        double xSquare = Math.pow(difX, 2);
        double ySquare = Math.pow(difY, 2);

        double distance = Math.pow(xSquare + ySquare, 0.5);
        return Math.abs((int) distance);

    }
}