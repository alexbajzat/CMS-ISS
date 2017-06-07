package com.frasinu.iss.persistance.model;

import java.util.stream.Stream;

/**
 * Created by Andrei on 06-Jun-17.
 */
public enum Result {
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

    Result(String name) {
        this.name = name;
    }

    public static Result getByName(String name) {
        return Stream.of(values())
                .filter(result -> result.name.equals(name))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("No such Result type!" + name));
    }

}
