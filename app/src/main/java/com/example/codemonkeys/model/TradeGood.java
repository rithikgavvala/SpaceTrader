package com.example.codemonkeys.model;

public enum TradeGood {
    Water(0,0,2,30,3,4),
    Furs(0,0,0,250,10,10),
    Food(1,0,1,100,5,5),
    Ore(2,2,3,350,20,10),
    Games(3,1,6,250,-10,5),
    Firearms(3,1,5,1250,-75,100),
    Medicine(4,1,6,650,-20,10),
    Machines(4,3,5,900,-30,5),
    Narcotics(5,0,5,3500,-125,150),
    Robots(6,4,7,5000,-150,100);

    private int basePrice;
    private int MTLP;
    private int MTLU;
    private int TTP;
    private int IPL;
    private int Var;
    private boolean IE;
    private boolean CR;
    private boolean ER;
    private int MTL;
    private int MTH;

    private TradeGood(int MTLP, int MTLU, int TTP, int basePrice, int IPL, int Var) { this.MTLP = MTLP; this.MTLU = MTLU; this.TTP = TTP; this.basePrice = basePrice; this.IPL = IPL; this.Var = Var;}
}
