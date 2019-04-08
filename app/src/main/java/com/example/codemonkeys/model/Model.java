package com.example.codemonkeys.model;

import android.arch.lifecycle.ViewModelProviders;
import android.util.Log;

import com.example.codemonkeys.viewmodel.ConfigurationViewModel;
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

    public boolean savePlayer() {
       boolean success = true;
       try {
           ByteArrayOutputStream bo = new ByteArrayOutputStream();
           ObjectOutputStream so = new ObjectOutputStream(bo);
           so.writeObject(inter);
           so.flush();
           String serializedObject = bo.toString();
           Log.d("player serial:",serializedObject);
           final FirebaseDatabase database = FirebaseDatabase.getInstance();
           DatabaseReference ref = database.getReference("players");
           ref.push().setValue(serializedObject);
        } catch (Exception e) {
             System.out.println(e);
             success = false;
        }
        return success;
    }

    public boolean loadPlayer() {
        boolean success = true;
        try {
            final FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference ref = database.getReference("players");
            // Read from the database
            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.
                    String serializedObject = dataSnapshot.getValue(String.class);
                    Log.d("player serial:",serializedObject);
                    byte b[] = serializedObject.getBytes();
                    ByteArrayInputStream bi = new ByteArrayInputStream(b);
                    ObjectInputStream si = null;
                    try {
                        si = new ObjectInputStream(bi);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        PlayerInteractor obj = (PlayerInteractor) si.readObject();
                        interactorMap.put("Player", obj);
                        interactorMap.put("Solar System", obj);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    // Failed to read value
                    Log.w("serial", "Failed to read value.", error.toException());
                }
            });
        } catch (Exception e) {
            System.out.println(e);
            success = false;
        }
        return success;
    }

    public boolean saveUniverse() {
        boolean success = true;
        try {
            ByteArrayOutputStream bo = new ByteArrayOutputStream();
            ObjectOutputStream so = new ObjectOutputStream(bo);
            so.writeObject(Universe.getInstance());
            so.flush();
            String serializedObject = bo.toString();
            Log.d("universe serial:",serializedObject);
            final FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference ref = database.getReference("universe");
            ref.push().setValue(serializedObject);
        } catch (Exception e) {
            System.out.println(e);
            success = false;
        }
        return success;
    }

    public boolean loadUniverse() {
        boolean success = true;
        try {
            final FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference ref = database.getReference("universe");
            // Read from the database
            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.
                    String serializedObject = dataSnapshot.getValue(String.class);
                    Log.d("universe serial:",serializedObject);
                    byte b[] = serializedObject.getBytes();
                    ByteArrayInputStream bi = new ByteArrayInputStream(b);
                    ObjectInputStream si = null;
                    try {
                        si = new ObjectInputStream(bi);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        Universe obj = (Universe) si.readObject();
                        Universe myUniverse = Universe.getInstance();
                        myUniverse.setSystems(obj.getSystems());
                        myUniverse.setLocations(obj.getLocations());
                        myUniverse.setResources(obj.getResources());
                        myUniverse.setTechLevel(obj.getTechLevel());
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    // Failed to read value
                    Log.w("serial", "Failed to read value.", error.toException());
                }
            });
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