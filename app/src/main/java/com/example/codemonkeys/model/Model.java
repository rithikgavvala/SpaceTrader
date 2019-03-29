package com.example.codemonkeys.model;

import java.util.HashMap;
import java.util.Map;

public class Model {
    private Repository myRepository;
    private Map<String, Object> interactorMap;

    private static Model instance = new Model();

    public static Model getInstance() { return instance; }

    private Model() {
        myRepository = new Repository();
        interactorMap = new HashMap<>();
        registerInteractors();
    }

    private void registerInteractors() {
        interactorMap.put("Player", new PlayerInteractor(myRepository));
        interactorMap.put("Market", new MarketInteractor(myRepository));
        interactorMap.put("Solar System", new PlayerInteractor(myRepository));
    }

    public PlayerInteractor getPlayerInteractor() {
        return (PlayerInteractor) interactorMap.get("Player");
    }

    public MarketInteractor getMarketInteractor() {
        return (MarketInteractor) interactorMap.get("Market");
    }

    public PlayerInteractor getTravelInteractor() {
        return (PlayerInteractor) interactorMap.get("Solar System");
    }

}