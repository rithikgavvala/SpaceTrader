package com.example.codemonkeys.view;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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

import java.util.Locale;

public class    BuyExtraActivity extends AppCompatActivity {
    private final int BUY = 1;
    private final int SELL = 0;
    private int transactionType = 1;
    private TextView itemProgress;
    private int amountProgress;
    private Transaction t;
    private TradeGood item;
    private ConfigurationViewModel viewModel;


    private Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buy_extra_activity);
        viewModel = ViewModelProviders.of(this).get(ConfigurationViewModel.class);
        item = (TradeGood) getIntent().getSerializableExtra("ITEM");
        transactionType = getIntent().getIntExtra("TRANSACTION", 1);
        TextView itemTitle = findViewById(R.id.itemTitle);
        Button transactionButton = findViewById(R.id.transactionButton);
        itemProgress = findViewById(R.id.buyAmount);
        SeekBar amountBar = findViewById(R.id.amountBar);
        SeekBar.OnSeekBarChangeListener mlistener = new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int id = seekBar.getId();
                String p = String.format(Locale.US, "%d",progress);
                itemProgress.setText(p);
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
        itemTitle.setText(item.getName());
        t = new Transaction();
        if (transactionType == BUY) {
            String b = "Buy";
            transactionButton.setText(b);
        } else {
            String s = "Sell";
            transactionButton.setText(s);
        }


        transactionButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                int purchase_amount = amountProgress;
                if (transactionType == BUY) {
                    Log.i("System Info", "CURRENT SYSTEM: " + viewModel.getPlayer().getSystem().getSystemName());
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

            private void makeToast(View v, CharSequence s) {
                Toast toast = Toast.makeText(v.getContext(),
                        s, Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 600);
                toast.show();
            }
        });

    }
}
