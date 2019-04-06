package com.example.codemonkeys.model;

import android.util.Log;
import android.widget.Space;

import java.io.Serializable;
import java.util.List;

public class Player implements Serializable {
    private String characterName;
    private int pilotSkill;
    private int fighterSkill;
    private int traderSkill;
    private int engineerSkill;
    private String difficulty;
    private int credits;
    private Spaceship ship;
    private SolarSystem system;
    private double money;
    private String firebaseUserID;
    private static int inc = 0;
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
        firebaseUserID = Integer.toString(inc);
        inc++;
    }
    public void updateSolarSystem(SolarSystem s){
        Log.d("Solar System Player",s.getSystemName());
        this.system = s;
    }
    public String getFirebaseUserID(){
        return firebaseUserID;
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