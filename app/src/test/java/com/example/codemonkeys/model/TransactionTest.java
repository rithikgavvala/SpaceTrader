package com.example.codemonkeys.model;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class TransactionTest {
    private Player p;
    private Transaction t;
    private TradeGood good;
    @Before
    public void setUp() {
        Player p = new Player("Vamsi", 16, 0, 0, 0, "easy");
        this.p =p;
        Transaction t = new Transaction();
        this.t = t;
        TradeGood good = new TradeGood(TradeGoodEnum.Robots);
        this.good = good;
        SolarSystem s = new SolarSystem("VamsiPlanet", new Location(30,40),TechLevel.AGRICULTURE,Resources.NOSPECIALRESOURCES);
        good.generatePrice(s);
    }
    @Test
    public void testQuantityError() {
        assertEquals("Unable to account for buying too much - do not have enough space in cargo",-1,t.buy(p,good,16));
    }
    @Test
    public void noMoneyError(){
        System.out.println(good.getCurrentPrice());
        assertEquals("Unable to account for buying a good too expensive - not enough money",-2,t.buy(p,good,15));
    }
    @Test
    public void legalBuy(){
        assertEquals("Unable to implement buy method properly -- cannot buy good even though allowed",0,t.buy(p,good,5));
    }
}