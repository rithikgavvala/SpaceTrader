package com.example.codemonkeys.model;

import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Repository implements Serializable {
    private Player currentPlayer;
    private final List<TradeGood> allTradeGoods;
    private List<SolarSystem> solarSystems;

    public Repository() {

        currentPlayer = new Player("Default Name", 5,5,5,1, "EASY");
        allTradeGoods = new ArrayList<>();
    }

    public List<TradeGood> getAllTradeGoods() { return allTradeGoods;}

    public List<SolarSystem> getSolarSystems() { return solarSystems;}





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

    public void updateCurrentSolarSystem(SolarSystem s) {

    }

}