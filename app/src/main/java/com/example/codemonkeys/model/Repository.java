package com.example.codemonkeys.model;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class Repository {
    private Player currentPlayer;
    private List<TradeGood> allTradeGoods;

    public Repository() {

        currentPlayer = new Player("Default Name", 5,5,5,1, "EASY");
        allTradeGoods = new ArrayList<>();
    }

    public List<TradeGood> getAllTradeGoods() { return allTradeGoods;}

    public List<TradeGood> getTradeGoodsForSolarSystem(SolarSystem s) {
        return s.findGoodsAvailabletoBuy();
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
