package com.example.codemonkeys.viewmodel;


import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import com.example.codemonkeys.model.Model;
import com.example.codemonkeys.model.TradeGood;
import com.example.codemonkeys.model.MarketInteractor;
import com.example.codemonkeys.model.SolarSystem;

import java.util.List;

public class MarketViewModel  extends AndroidViewModel {
    private MarketInteractor interactor;

    public MarketViewModel(@NonNull Application application) {
        super(application);
        interactor = Model.getInstance().getMarketInteractor();
    }

    public List<TradeGood> getBuyTradeGoods() {
        return interactor.getBuyTradeGoods();
    }

    public List<TradeGood> getSellTradeGoods() {
        return interactor.getSellTradeGoods();
    }
}