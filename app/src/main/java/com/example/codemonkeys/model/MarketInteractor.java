package com.example.codemonkeys.model;

import java.util.List;
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

    public List<TradeGood> getAllTradeGoods() {
        Player p = myRepository.getPlayer();
        SolarSystem s = p.getSystem();
        return s.findGoodsAvailabletoSell();
    }

}
