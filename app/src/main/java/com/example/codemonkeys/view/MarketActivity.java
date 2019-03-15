package com.example.codemonkeys.view;


import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.codemonkeys.R;
import com.example.codemonkeys.model.TradeGood;
import com.example.codemonkeys.view.TransactionAdapter;
import com.example.codemonkeys.viewmodel.MarketViewModel;


public class MarketActivity extends AppCompatActivity {

    /** reference to the course whose details this is */

    /**
     * our view model
     */
    private MarketViewModel viewModel;

    /**
     * the adapter for translating List of student into display in recycler view
     */
    private TransactionAdapter adapter;
    private Intent intent = getIntent();
    private final int BUY = 1;
    private final int SELL = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.transaction_activity);

        //check if a course was passed in, correct behavior is that a course always is passed

        //set up the button to register students - NOT IMPLEMENTED YET!!!


//        setTitle(String.format("Students Registered for: %s %s", course.getSchool(),course.getNumber()));

        /*
           Setup our recycler view by grabbing widget reference, setting layout and then setting
           the adapter.
         */
        RecyclerView recyclerView = findViewById(R.id.sell_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        adapter = new TransactionAdapter();
        recyclerView.setAdapter(adapter);

        //grab our view model
        viewModel = ViewModelProviders.of(this).get(MarketViewModel.class);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (intent.getIntExtra("TRANSACTION", BUY) == BUY) {
            adapter.setTradeGoodsList(viewModel.getBuyTradeGoods());
        } else {
            adapter.setTradeGoodsList(viewModel.getSellTradeGoods());
        }

        adapter.setOnStudentClickListener(new TransactionAdapter.OnItemClickListener() {
            @Override
            public void onItemClicked(TradeGood student) {
                Intent intent = new Intent(MarketActivity.this, BuyExtraActivity.class);
                startActivityForResult(intent, 1);
            }
        });
    }
}
