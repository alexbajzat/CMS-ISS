package com.frasinu.model;


import javax.persistence.*;

/**
 * Created by bjz on 5/9/2017.
 */
@Entity
@Table(name = "conference")
public class Conference {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "website")
    private String webpage;

    Conference() {
    }

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
