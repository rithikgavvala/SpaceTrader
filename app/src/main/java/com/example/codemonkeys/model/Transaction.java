package com.example.codemonkeys.model;

import java.util.LinkedList;

public class Transaction {


    public void buy(Player p, TradeGood t, int quantity){
        if(p.getSpaceship().canBuy(quantity)){
            // create Toast
        }
        if(p.getMoney() > t.generatePrice(p.getSystem()) * quantity){
            //create Toast
        }
        for(int i = 1; i <= quantity; i++){
            p.getSpaceship().addToList(t);
            p.setMoney(p.getMoney() - t.generatePrice(p.getSystem()));
        }
    }

    private void sell(Player p, TradeGood t, int quantity){
        LinkedList<TradeGood> newList = p.getSpaceship().getCargoList();
        for(int i = 0; i < quantity; i++){
            newList.remove(t);
            p.setMoney(p.getMoney() + t.generatePrice(p.getSystem()));
        }
        p.getSpaceship().setCargoLoad(newList);
    }
}
