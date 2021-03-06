package com.example.codemonkeys.model;

import android.util.Log;

import java.util.Enumeration;
import java.util.Random;

public enum TradeGoodEnum {
    Water(0, 0, 2, 30, 3, 4),
    Furs(0, 0, 0, 250, 10, 10),
    Food(1, 0, 1, 100, 5, 5),
    Ore(2, 2, 3, 350, 20, 10),
    Games(3, 1, 6, 250, -10, 5),
    Firearms(3, 1, 5, 1250, -75, 100),
    Medicine(4, 1, 6, 650, -20, 10),
    Machines(4, 3, 5, 900, -30, 5),
    Narcotics(5, 0, 5, 3500, -125, 150),
    Robots(6, 4, 7, 5000, -150, 100);

    private final double basePrice;
    private final int MTLP;
    private final int MTLU;
    private final int TTP;
    private final int IPL;
    private final int var;
    private boolean IE;
    private boolean CR;
    private boolean ER;
    private int MTL;
    private int MTH;
    private double currentPrice;

    TradeGoodEnum(int MTLP, int MTLU, int TTP, double basePrice, int IPL, int Var) {
        this.MTLP = MTLP;
        this.MTLU = MTLU;
        this.TTP = TTP;
        this.basePrice = basePrice;
        this.IPL = IPL;
        this.var = Var;
    }

    public int getMTLP() {
        return MTLP;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public int getIPL() {
        return IPL;
    }

    public int getTTP() {
        return TTP;
    }

    public int getMTLU() {
        return MTLU;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public void generatePrice(SolarSystem s) {
        currentPrice = basePrice + (IPL * (s.getTechLevel().getRank() - MTLP)) + getVariance();
        Log.i("SYSTEM INFO", "System Name: " + s.getSystemName() + " " + currentPrice + " " + this.name());

    }

    private double getVariance() {
        double number = basePrice * (((double) (new Random().nextInt(var)) / 100));
        int random = new Random().nextInt(2);
        if (random == 0) {
            return -1 * number;
        }
        return number;
    }


    public int getVar() {
        return var;
    }
}
