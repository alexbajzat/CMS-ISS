package com.frasinu.iss.persistance.model;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by bjz on 6/5/2017.
 */
public enum Bid {
    STRONG_ACCEPT("strong_accept"),
    ACCEPT("accept"),
    WEAK_ACCEPT("weak_accept"),
    BORDERLINE_ACCEPT("borderline_accept"),
    WEAK_REJECT("weak_reject"),
    REJECT("reject"),
    STRONG_REJECT("strong_reject");

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
