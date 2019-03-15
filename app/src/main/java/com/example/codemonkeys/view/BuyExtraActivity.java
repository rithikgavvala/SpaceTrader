package com.example.codemonkeys.view;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.codemonkeys.R;
import com.example.codemonkeys.model.TradeGood;
import com.example.codemonkeys.model.Transaction;
import com.example.codemonkeys.viewmodel.ConfigurationViewModel;

public class BuyExtraActivity extends AppCompatActivity {
    private final int BUY = 1;
    private final int SELL = 0;
    private int transactionType = 1;
    private TextView itemTitle;
    private Button transactionButton;
    private TextView itemProgress;
    private SeekBar.OnSeekBarChangeListener mlistener;
    private SeekBar amountBar;
    private int amountProgress;
    private Transaction t;
    private TradeGood item;
    private ConfigurationViewModel viewModel;


    private Bundle extras;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buy_extra_activity);
        viewModel = ViewModelProviders.of(this).get(ConfigurationViewModel.class);
        item = (TradeGood) getIntent().getSerializableExtra("ITEM");
        transactionType = getIntent().getIntExtra("TRANSACTION", 1);
        itemTitle = (TextView) findViewById(R.id.itemTitle);
        transactionButton = (Button) findViewById(R.id.transactionButton);
        itemProgress = (TextView) findViewById(R.id.buyAmount);
        amountBar = (SeekBar) findViewById(R.id.amountBar);
        mlistener = new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int id = seekBar.getId();
                itemProgress.setText("" + progress);
                amountProgress = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        };
        amountBar.setOnSeekBarChangeListener(mlistener);
        itemTitle.setText(item.toString());
        t = new Transaction();
        if (transactionType == BUY) {
            transactionButton.setText("Buy");
        } else {
            transactionButton.setText("Sell");
        }


        transactionButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                int purchase_amount = amountProgress;
                if (transactionType == BUY) {
                    int result = t.buy(viewModel.getPlayer(), item, purchase_amount);
                    if (result == 1) {
                        makeToast(v, "Buy Complete. You now have "
                                + viewModel.getPlayer().getMoney() + " credits");
                        finish();
                    } else if (result == -1) {
                        makeToast(v, "You do not have enough cargo bays!");
                    } else {
                        makeToast(v, "You do not have enough credits!");
                    }
                } else {
                    int result = t.sell(viewModel.getPlayer(), item, purchase_amount);
                    if (result == 1) {
                        makeToast(v, "Sell Complete. You now have "
                                + viewModel.getPlayer().getMoney() + " credits");
                        finish();
                    } else if (result == -1) {
                        makeToast(v, "The Minimum Tech-Level of this item is greater " +
                                "than the tech level of the system");
                    } else {
                        makeToast(v, "You are selling more than what you have!");
                    }
                }


            }

            private void makeToast(View v, String s) {
                Toast toast = Toast.makeText(v.getContext(),
                        s, Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 600);
                toast.show();
            }
        });

    }
}
