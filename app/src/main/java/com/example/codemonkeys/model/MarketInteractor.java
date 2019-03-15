package com.example.codemonkeys.model;


import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Provide operations associated with Courses
 *
 * Basically provides an interface between the UI (view model) and the Model classes
 */
public class MarketInteractor {

    private Repository myRepository;

    public MarketInteractor(Repository repo) {
        myRepository = repo;
    }

    public List<TradeGood> getBuyTradeGoods() {
        Player p = myRepository.getPlayer();
        SolarSystem s = p.getSystem();
        return s.getListofResources();
    }

    public List<TradeGood> getSellTradeGoods() {
        Player p = myRepository.getPlayer();
        Spaceship s = p.getSpaceship();
        LinkedList<TradeGood> r = new LinkedList<>(s.getCargoList().keySet());
        LinkedList<TradeGood> i = new LinkedList<TradeGood>();
        for (TradeGood t : r) {
            if (t.getMTLU() <= p.getSystem().getTechLevel().getRank()) {
                i.add(t);
            }
        }
        return i;
    }

    public List<TradeGood> getAllHoldGoods() {
        Player p = myRepository.getPlayer();
        Spaceship s = p.getSpaceship();
        return new LinkedList<TradeGood>(s.getCargoList().keySet());
    }


}
