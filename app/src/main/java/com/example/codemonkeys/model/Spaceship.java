package com.example.codemonkeys.model;

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
    private TradeGood[] cargoList;

    private Spaceship(int parsecs, int cargoMax){
        this.parsecs = parsecs;
        this.cargoMax = cargoMax;
        cargoList = new TradeGood[cargoMax];
    }

    public TradeGood[] getCargoList(){
        return cargoList;
    }




}
