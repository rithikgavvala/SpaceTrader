package com.example.codemonkeys.model;


import android.util.Log;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * Provide operations associated with Courses
 *
 * Basically provides an interface between the UI (view model) and the Model classes
 */
public class MarketInteractor {

    private final Repository myRepository;

    public MarketInteractor(Repository repo) {
        myRepository = repo;
    }

    public List<TradeGood> getBuyTradeGoods() {
        Player p = myRepository.getPlayer();
        Log.d("PLAYER FIREBASE: ", p.toString());
        SolarSystem s = p.getSystem();
        Log.d("SOLARSYSTEM FIREBASE: ", s.getSystemName());
        return s.getListofResources();
    }

    public List<TradeGood> getSellTradeGoods() {
        Player p = myRepository.getPlayer();
        Spaceship s = p.getSpaceship();
        Deque<TradeGood> r = new LinkedList<TradeGood>(s.getActualCargoList().values());
        SolarSystem system = p.getSystem();
        LinkedList<TradeGood> i = new LinkedList<TradeGood>();
        for (TradeGood t : system.findGoodsAvailabletoBuy()) {
            if (t.getMTLU() <= p.getSystem().getTechLevel().getRank() && r.contains(t)) {
                i.add(t);
            }
        }
        return i;
    }

    public List<TradeGood> getAllHoldGoods() {
        Player p = myRepository.getPlayer();
        Spaceship s = p.getSpaceship();
        return new LinkedList<TradeGood>(s.getActualCargoList().values());
    }


}
