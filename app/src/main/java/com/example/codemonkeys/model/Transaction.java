package com.example.codemonkeys.model;

import java.util.LinkedList;
import java.util.List;

public class Transaction {


    public int buy(Player p, TradeGood t, int quantity) {
        if (!p.getSpaceship().canBuy(quantity)) {
            // create Toast, can't buy
            return -1;
        }
        if (!(p.getMoney() > t.getCurrentPrice() * quantity)) {
            //create Toast, can't buy
            return -2;
        }
        p.setMoney(p.getMoney() - t.getCurrentPrice());
        for(int i = 1; i <= quantity; i++){
            p.getSpaceship().addToList(t);
        }
        return 1;
    }

    public int sell(Player p, TradeGood t, int quantity) {
        if ((t.getMTLU() > p.getSystem().getTechLevel().getRank())) { //TODO: Check this
            //create Toast, can't sell
            return -1;
        }
        if (!(quantity > p.getSpaceship().getSizeCargoList())) {
            //create Toast, can't sell
            return -2;
        }
        List<TradeGood> newList = p.getSpaceship().getCargoList();
        for(int i = 0; i < quantity; i++){
            newList.remove(t);
            p.setMoney(p.getMoney() + t.getCurrentPrice());
        }
        p.getSpaceship().setCargoLoad(newList);
        return 1;
    }
}
