package com.frasinu.iss.persistance.model;

import java.util.stream.Stream;

/**
 * Created by bjz on 6/5/2017.
 */
public enum Bid {
    I_WISH("wish"),
    I_COULD("could"),
    I_REFUSE("refuse");

    private String name;

    public String getName() {
        return name;
    }

    Bid(String name) {
        this.name = name;
    }

    public static Bid getByName(String name) {
        return Stream.of(values())
                .filter(bid -> bid.name.equals(name))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("No such Bid type!" + name));
    }

}
