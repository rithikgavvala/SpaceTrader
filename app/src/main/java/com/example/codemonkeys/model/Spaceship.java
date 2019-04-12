package com.example.codemonkeys.model;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;

public class Spaceship {


    private String name;
    private int hullStrength;
    private int parsecs;
    private int cargoMax;
    private int quantity;
    private HashMap<String, Integer> cargoList;

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public HashMap<String, TradeGood> getActualCargoList() {
        return actualCargoList;
    }

    public void setActualCargoList(HashMap<String, TradeGood> actualCargoList) {
        this.actualCargoList = actualCargoList;
    }

    private HashMap<String, TradeGood> actualCargoList;
    private int sizeCargoList;




    Spaceship(String name, int parsecs, int cargoMax, int hullStrength){
        this.name = name;
        this.parsecs = parsecs;
        this.cargoMax = cargoMax;
        this.hullStrength = hullStrength;
        sizeCargoList = 0;
        quantity = 0;
        cargoList = new HashMap<String, Integer>();
    }

    Spaceship(SpaceshipEnum se){
        this.name = se.getName();
        this.parsecs = se.getParsecs();
        this.cargoMax = se.getCargoMax();
        this.hullStrength = se.getHullStrength();
        sizeCargoList = 0;
        cargoList = new HashMap<String, Integer>();
        actualCargoList = new HashMap<>();


    }

    Spaceship() {

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

    public Map<String, Integer> getCargoList() {
        return cargoList;
    }

    public void remove(TradeGood t, int quantity) {
        int currQuantity = cargoList.get(t.getName());
        if (quantity >= currQuantity) {
            actualCargoList.remove(t.getName());
            cargoList.remove(t.getName());

        } else {
            cargoList.put(t.getName(), currQuantity - quantity);

        }
        this.quantity -= quantity;
    }

    public void setParsecs(int parsecs) {
        this.parsecs = parsecs;
    }
    public void setCargoLoad(HashMap<String, Integer> map) {
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
        cargoList.put(t.getName(), quantity);
        actualCargoList.put(t.getName(), t);
    }
    public int getCargoMax() {
        return cargoMax;
    }

    public void setCargoMax(int cargoMax) {
        this.cargoMax = cargoMax;
    }

//    public void setQuantity(Integer quantity) {
//        this.quantity = quantity;
//    }

    public void setCargoList(HashMap<String, Integer> cargoList) {
        this.cargoList = cargoList;
    }

    public int getSizeCargoList() {
        return sizeCargoList;
    }

    public int getParsecs(){
        return parsecs;
    }






}
