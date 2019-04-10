package com.example.codemonkeys.model;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class SolarSystemTest {

    @Test
    public void findGoodsAvailabletoBuy() {

        /*T and F is taken separately here- if was false then TradeGood was not added to list. This
        is why only*/
        SolarSystem s = new SolarSystem("TestSystem", new Location(0, 0), TechLevel.PREAGRICULTURE, Resources.ARTISTIC);
        List<TradeGood> zeroTechLevelList = s.findGoodsAvailabletoBuy();
        assertEquals(2, zeroTechLevelList.size());
        assertEquals("Water", zeroTechLevelList.get(0).getName());
        assertEquals("Furs", zeroTechLevelList.get(1).getName());

        s = new SolarSystem("TestSystem", new Location(0, 0), TechLevel.AGRICULTURE, Resources.ARTISTIC);
        List<TradeGood> oneTechLevelList = s.findGoodsAvailabletoBuy();
        assertEquals(3, oneTechLevelList.size());
        assertEquals("Water", oneTechLevelList.get(0).getName());
        assertEquals("Furs", oneTechLevelList.get(1).getName());
        assertEquals("Food", oneTechLevelList.get(2).getName());

        s = new SolarSystem("TestSystem", new Location(0, 0), TechLevel.Medieval, Resources.ARTISTIC);
        List<TradeGood> twoTechLevelList = s.findGoodsAvailabletoBuy();
        assertEquals(4, twoTechLevelList.size());
        assertEquals("Water", twoTechLevelList.get(0).getName());
        assertEquals("Furs", twoTechLevelList.get(1).getName());
        assertEquals("Food", twoTechLevelList.get(2).getName());
        assertEquals("Ore", twoTechLevelList.get(3).getName());

        s = new SolarSystem("TestSystem", new Location(0, 0), TechLevel.Renaissance, Resources.ARTISTIC);
        List<TradeGood> threeTechLevelList = s.findGoodsAvailabletoBuy();
        assertEquals(6, threeTechLevelList.size());
        assertEquals("Water", threeTechLevelList.get(0).getName());
        assertEquals("Furs", threeTechLevelList.get(1).getName());
        assertEquals("Food", threeTechLevelList.get(2).getName());
        assertEquals("Ore", threeTechLevelList.get(3).getName());
        assertEquals("Games", threeTechLevelList.get(4).getName());
        assertEquals("Firearms", threeTechLevelList.get(5).getName());


    }
}