package com.frasinu.model;

/**
 * Created by bjz on 5/9/2017.
 */
public class Conference {
    private final Integer id;
    private final String name;
    private final String webpage;

    Conference(Integer id, String name, String webpage) {
        this.id = id;
        this.name = name;
        this.webpage = webpage;
    }

    public static ConferenceBuilder builder() {
        return new ConferenceBuilder();
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getWebpage() {
        return webpage;
    }
}
