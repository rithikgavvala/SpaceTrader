package com.example.codemonkeys.model;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
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

public class Model {
    private Repository myRepository;
    private Map<String, Object> interactorMap;

    private static Model instance = new Model();

    private PlayerInteractor inter;

    public static Model getInstance() { return instance; }


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
    public boolean save() {
       boolean success = true;
       try {
           ByteArrayOutputStream bo = new ByteArrayOutputStream();
           ObjectOutputStream so = new ObjectOutputStream(bo);
           so.writeObject(inter);
           so.flush();
           String serializedObject = bo.toString();
           Log.d("serial:",serializedObject);
           final FirebaseDatabase database = FirebaseDatabase.getInstance();
           DatabaseReference ref = database.getReference("players");
           ref.push().setValue(serializedObject);
        } catch (Exception e) {
             System.out.println(e);
             success = false;
        }
        return success;
    }

    public boolean load() {
        boolean success = true;
        try {
            String serializedObject = "";
            final FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference ref = database.getReference("players");
            // Read from the database
            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.
                    serializedObject = dataSnapshot.getValue(String.class);
                    Log.d("serial:",serializedObject);
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    // Failed to read value
                    Log.w("serial", "Failed to read value.", error.toException());
                }
            });
            byte b[] = serializedObject.getBytes();
            ByteArrayInputStream bi = new ByteArrayInputStream(b);
            ObjectInputStream si = new ObjectInputStream(bi);
            PlayerInteractor obj = (PlayerInteractor) si.readObject();
        } catch (Exception e) {
            System.out.println(e);
            success = false;
        }
        return success;
    }

    public PlayerInteractor getTravelInteractor() {
        return (PlayerInteractor) interactorMap.get("Solar System");
    }

}