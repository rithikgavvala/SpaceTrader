package com.example.codemonkeys.model;

import java.util.List;

public class Player {
    private String characterName;
    private int pilotSkill;
    private int fighterSkill;
    private int traderSkill;
    private int engineerSkill;
    private String difficulty;
    private int credits;
    private Spaceship ship;


    public Player(String name, int pil, int fight, int trad, int engine, String diff) {
        characterName = name;
        pilotSkill = pil;
        fighterSkill = fight;
        traderSkill = trad;
        engineerSkill = engine;
        difficulty = diff;
        credits = 1000;
        this.ship = Spaceship.Gnat;
    }

    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public String toString() {
        return "Player " + characterName + " with pilot skill of " + pilotSkill
                + ", fighter skill of " + fighterSkill
                + ", trader skill of " + traderSkill
                + ", and engineer skill of " + engineerSkill;
    }
}