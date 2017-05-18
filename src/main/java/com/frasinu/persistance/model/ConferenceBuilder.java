package com.frasinu.persistance.model;

/**
 * Created by bjz on 5/9/2017.
 */
public class ConferenceBuilder {
    private Integer id;
    private String name;
    private String webpage;

    ConferenceBuilder() {
    }

    public Conference build() {
        return new Conference(id, name, webpage);
    }

    public ConferenceBuilder setId(Integer id) {
        this.id = id;
        return this;
    }

    public ConferenceBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public ConferenceBuilder setWebpage(String webpage) {
        this.webpage = webpage;
        return this;
    }
}
