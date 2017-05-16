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
    private final Integer id;
    @Column(name = "name")
    private final String name;
    @Column(name = "website")
    private final String webpage;

    public Conference() {
        this.id = 0;
        this.name = "";
        this.webpage = "";
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

    @Override
    public String toString() {
        return "Conference{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", webpage='" + webpage + '\'' +
                '}';
    }
}
