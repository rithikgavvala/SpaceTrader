package com.example.codemonkeys.model;

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

    private int parsecs;
    private int cargoMax;
    private List<TradeGood> cargoList;
    private int sizeCargoList;

    Spaceship(int parsecs, int cargoMax){
        this.parsecs = parsecs;
        this.cargoMax = cargoMax;
        sizeCargoList = 0;
        cargoList = new LinkedList<>();
    }

    public List<TradeGood> getCargoList() {
        return cargoList;
    }

    public void setCargoLoad(List<TradeGood> arr) {
        cargoList = arr;
    }

    public int getSizeCargoList(){
        return sizeCargoList;
    }

    public void setSizeCargoList(int size){
        sizeCargoList = size;
    }

    public boolean canBuy(int quantity){
        return sizeCargoList + quantity <= cargoMax;
    }

    public void addToList(TradeGood t){
       cargoList.add(t);
    }
}
