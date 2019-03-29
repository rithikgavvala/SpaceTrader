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
        return new LinkedList<TradeGood>(s.getCargoList().keySet());
    }


}
