package com.example.codemonkeys.model;

import android.util.Log;

public class Repository {
    private Player currentPlayer;

    public Repository() {
        currentPlayer = new Player("Default Name", 5,5,5,1, "EASY");
    }

    public Player getPlayer() {
        return currentPlayer;
    }

    public void updateCurrentPlayer(Player p) {

        currentPlayer = p;
        Log.d("Repository", "Created Player in Repository: "
                + currentPlayer);
    }
}
