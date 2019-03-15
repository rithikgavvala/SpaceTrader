package com.example.codemonkeys.model;

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
        p.setMoney(p.getMoney() - (t.getCurrentPrice() * quantity));
        p.getSpaceship().addToList(t, quantity);
        return 1;
    }

    public int sell(Player p, TradeGood t, int quantity) {
        if ((p.getSystem().getTechLevel().getRank() < t.getMTLU())) { //TODO: Check this
            //create Toast, can't sell
            return -1;
        }
        if ((quantity > p.getSpaceship().getQuantity())) {
            //create Toast, can't sell
            return -2;
        }
        p.getSpaceship().remove(t, quantity);
        p.setMoney(p.getMoney() + (t.getCurrentPrice() * quantity));
        return 1;
    }
}
