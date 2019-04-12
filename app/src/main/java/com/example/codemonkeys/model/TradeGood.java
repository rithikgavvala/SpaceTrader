package com.example.codemonkeys.model;

import android.util.Log;

import java.io.Serializable;
import java.util.Enumeration;
import java.util.Random;

public class TradeGood implements Serializable {

    private final double basePrice;
    private final int MTLP;
    private final int MTLU;
    private final int TTP;
    private final int IPL;
    private final int var;
    private String name;
    private boolean IE;
    private boolean CR;
    private boolean ER;
    private int MTL;
    private int MTH;
    private double currentPrice;

    TradeGood(int MTLP, int MTLU, int TTP, double basePrice, int IPL, int Var) {
        this.MTLP = MTLP;
        this.MTLU = MTLU;
        this.TTP = TTP;
        this.basePrice = basePrice;
        this.IPL = IPL;
        this.var = Var;
    }

    TradeGood(TradeGoodEnum te) {
        this.MTLP = te.getMTLP();
        this.MTLU = te.getMTLU();
        this.TTP = te.getTTP();
        this.basePrice = te.getBasePrice();
        this.IPL = te.getIPL();
        this.var = te.getVar();
        this.name = te.name();
    }

    public TradeGood() {
        this(0, 0, 0, 0, 0, 0);

    }

    public int getMTLP(){
        return MTLP;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public int getIPL(){
        return IPL;
    }

    public int getTTP(){
        return TTP;
    }

    public int getMTLU(){
        return MTLU;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public void generatePrice(SolarSystem s) {
        currentPrice = basePrice + (IPL * (s.getTechLevel().getRank() - MTLP)) + getVariance();
//        Log.i("SYSTEM INFO","System Name: " + s.getSystemName() + " " + currentPrice + " " + this.name());

    }

    private double getVariance(){
        double number = basePrice * (((double)(new Random().nextInt(var))/100));
        int random = new Random().nextInt(2);
        if(random == 0){
            return -1 * number;
        }
        return number;
    }


    public String getName() {
        return name;
    }

    public boolean equals(Object o) {
        if (!(o instanceof TradeGood))
            return false;
        if (o == this)
            return true;
        return this.name.equals(((TradeGood) o).name);
    }

    public int hashCode() {
        int hashCode = 0;
        hashCode += (31 * (MTLP + MTLU + TTP + IPL + var + name.hashCode() + MTL + MTH));
        return hashCode;
    }




}
