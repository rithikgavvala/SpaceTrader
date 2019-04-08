package com.example.codemonkeys.model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public enum Spaceship {
    Flea("Flea", 20, 10, 2),
    Gnat("Gnat", 15, 15, 7),
    Firefly("Firefly", 17, 20, 7),
    Mosquito("Mosquito", 13, 15, 25),
    Bumblebee("Bumblebee", 15, 25, 10),
    Beetle("Beetle", 14, 50, 5),
    Hornet("Hornet", 16, 20, 16),
    Grasshopper("Grasshopper", 15, 30, 12),
    Termite("Termite", 13, 60, 20),
    Wasp("Wasp", 14, 35, 20);

    public int getCargoMax() {
        return cargoMax;
    }

    public void setCargoMax(int cargoMax) {
        this.cargoMax = cargoMax;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setCargoList(HashMap<TradeGood, Integer> cargoList) {
        this.cargoList = cargoList;
    }

    public int getSizeCargoList() {
        return sizeCargoList;
    }

    public int getParsecs(){
        return parsecs;
    }

    private String name;
    private int hullStrength;
    private int parsecs;
    private int cargoMax;
    private Integer quantity;
    private HashMap<TradeGood, Integer> cargoList;
    private int sizeCargoList;

    Spaceship(String name, int parsecs, int cargoMax, int hullStrength){
        this.name = name;
        this.parsecs = parsecs;
        this.cargoMax = cargoMax;
        this.hullStrength = hullStrength;
        sizeCargoList = 0;
        quantity = 0;
        cargoList = new HashMap<TradeGood, Integer>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHullStrength() {
        return hullStrength;
    }

    public void setHullStrength(int hullStrength) {
        this.hullStrength = hullStrength;
    }

    public HashMap<TradeGood, Integer> getCargoList() {
        return cargoList;
    }

    public void remove(TradeGood t, int quantity) {
        int currQuantity = cargoList.get(t);
        if (quantity >= currQuantity) {
            cargoList.remove(t);
        } else {
            cargoList.put(t, currQuantity - quantity);
        }
        this.quantity -= quantity;
    }

    public void setParsecs(int parsecs) {
        this.parsecs = parsecs;
    }
    public void setCargoLoad(HashMap<TradeGood, Integer> map) {
        cargoList = map;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setSizeCargoList(int size){
        sizeCargoList = size;
    }

    public boolean canBuy(int quantity){

        return sizeCargoList + quantity <= cargoMax;
    }

    public void addToList(TradeGood t, int quantity) {
        this.quantity += quantity;
        cargoList.put(t, quantity);
    }
}
