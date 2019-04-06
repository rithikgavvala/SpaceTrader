package com.example.codemonkeys.model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public enum Spaceship {
    Flea(20, 10),
    Gnat(15, 15),
    Firefly(17, 20),
    Mosquito(13, 15),
    Bumblebee(15, 25),
    Beetle(14, 50),
    Hornet(16, 20),
    Grasshopper(15, 30),
    Termite(13, 60),
    Wasp(14, 35);

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

    private int parsecs;
    private int cargoMax;
    private Integer quantity;
    private HashMap<TradeGood, Integer> cargoList;
    private int sizeCargoList;

    Spaceship(int parsecs, int cargoMax){
        this.parsecs = parsecs;
        this.cargoMax = cargoMax;
        sizeCargoList = 0;
        quantity = 0;
        cargoList = new HashMap<TradeGood, Integer>();
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
