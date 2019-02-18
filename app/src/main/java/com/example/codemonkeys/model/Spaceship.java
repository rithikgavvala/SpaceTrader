package com.example.codemonkeys.model;

public enum Spaceship {
    Flea(20),
    Gnat(15),
    Firefly(17),
    Mosquito(13),
    Bumblebee(15),
    Beetle(14),
    Hornet(16),
    Grasshopper(15),
    Termite(13),
    Wasp(14);

    private int fuelTankSize;

    private Spaceship(int fuelTankSize){
        this.fuelTankSize = fuelTankSize;
    }


}
