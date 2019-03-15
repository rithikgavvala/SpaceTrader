package com.example.codemonkeys.model;

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
}
