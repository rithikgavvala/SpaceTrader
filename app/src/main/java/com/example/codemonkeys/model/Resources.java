package com.example.codemonkeys.model;

import java.io.Serializable;

public enum Resources implements Serializable {
    NOSPECIALRESOURCES,
    MINERALRICH,
    MINERALPOOR,
    DESERT,
    LOTSOFWATER,
    RICHSOIL,
    POORSOIL,
    RICHFAUNA,
    LIFELESS,
    WEIRDMUSHROOMS,
    LOTSOFHERBS,
    ARTISTIC,
    WARLIKE;

    public String getName() {
        return name();
    }
}
