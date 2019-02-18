package com.example.codemonkeys.model;

public class Player {
    private String characterName;
    private int pilot;
    private int fighter;
    private int trader;
    private int engineer;
    private String difficulty;
    private int creds;
    // gnat spaceship needs to be created,
    public Player(String name, int pil, int fight, int trad, int engine, String diff) {
        characterName = name;
        pilot = pil;
        fighter = fight;
        trader = trad;
        engineer = engine;
        difficulty = diff;
        creds = 1000;
    }
}