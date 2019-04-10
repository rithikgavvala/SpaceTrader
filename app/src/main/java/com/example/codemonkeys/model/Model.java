package com.example.codemonkeys.model;

import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.codemonkeys.viewmodel.ConfigurationViewModel;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public final class Model {
    private final Repository myRepository;
    private final Map<String, Object> interactorMap;

    private static final Model instance = new Model();

    private final PlayerInteractor inter;

    public static Model getInstance() {
        return instance;
    }


    private Model() {
        myRepository = new Repository();
        interactorMap = new HashMap<>();
        inter = new PlayerInteractor(myRepository);
        registerInteractors();
    }

    private void registerInteractors() {
        interactorMap.put("Player", new PlayerInteractor(myRepository));
        interactorMap.put("Market", new MarketInteractor(myRepository));
        interactorMap.put("Solar System", new PlayerInteractor(myRepository));
    }

    public PlayerInteractor getPlayerInteractor() {
        return (PlayerInteractor) interactorMap.get("Player");
    }

    public MarketInteractor getMarketInteractor() {
        return (MarketInteractor) interactorMap.get("Market");
    }

    public boolean savePlayer() {
        boolean success = true;
        try {

            final FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference ref = database.getReference("players");
            Map<String, Player> map = new HashMap<>();
            map.put("playerChild", myRepository.getPlayer());
            ref.child("Player").setValue(map);
            //DatabaseReference push = ref.push();
            //push.setValue(map);
        } catch (Exception e) {
            System.out.println(e);
            Log.i("Firebase", "EXCEPTION: " + e);
            success = false;
        }

        Log.i("Firebase", "SUCCESS: " + success);
        return success;
    }

    public boolean load(DataCallback dataCallback) {
        boolean success = false;
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        try {
            DatabaseReference ref = database.getReference("players");
            // Read from the database
            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Player p = dataSnapshot.child("Player/playerChild").getValue(Player.class);
                    Log.i("callback", "data-change player: " + p.toString());
                    myRepository.updateCurrentPlayer(p);
                    Universe u = dataSnapshot.child("Universe/universeChild").getValue(Universe.class);
                    Universe myUniverse = Universe.getInstance();
                    myUniverse.setUniverse(u);
                    dataCallback.onDataCallback(p, u);

                }
                @Override
                public void onCancelled(DatabaseError error) {
                    // Failed to read value
                }
            });
        } catch (Exception e) {
            System.out.println(e);
            success = false;
        }
        return success;
    }

    public boolean saveUniverse() {
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference universeRef = database.getReference("players");
        HashMap<String, Universe> map = new HashMap<String, Universe>();

        map.put("universeChild", Universe.getInstance());
        universeRef.child("Universe").setValue(map);
        //DatabaseReference push = universeRef.push();
        //push.setValue(map);
        return true;


    }

/*    public boolean loadUniverse(DataCallback dataCallback) {
        boolean success = true;
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference universeRef = database.getReference("universe");
        if (Universe.getInstance().getSystems() == null) {
            universeRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    Universe u = dataSnapshot.getValue(Universe.class);
                    Universe myUniverse = Universe.getInstance();
                    myUniverse.setUniverse(u);
                    dataCallback.onUniverseCallback(u);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                }
            });
        }
        return success;

    }*/

    public PlayerInteractor getTravelInteractor() {
        return (PlayerInteractor) interactorMap.get("Solar System");
    }


}