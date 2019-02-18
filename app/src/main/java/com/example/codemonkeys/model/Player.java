package com.example.codemonkeys.model;

import java.util.List;

public class Player {
    private String characterName;
    private int pilot;
    private int fighter;
    private int trader;
    private int engineer;
    private String difficulty;
    private int credits;
    private Spaceship type;


    public Player(String name, int pil, int fight, int trad, int engine, String diff) {
        characterName = name;
        pilot = pil;
        fighter = fight;
        trader = trad;
        engineer = engine;
        difficulty = diff;
        credits = 1000;
        this.type = Spaceship.Gnat;
    }

    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public String toString() {
        return "Player " + characterName + " with pilot skill of " + pilot
                + ", fighter skill of " + fighter
                + ", trader skill of " + trader
                + ", and engineer skill of " + engineer;
    }
}