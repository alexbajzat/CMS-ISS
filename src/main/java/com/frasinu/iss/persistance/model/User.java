package com.frasinu.iss.persistance.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by bjz on 5/7/2017.
 */
@Entity
@Table(name = "user_app")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private final Integer id;
    @Column(name = "name")
    private final String name;
    @Column(name = "username")
    private final String username;
    @Column(name = "password")
    private final String password;

    @OneToMany(mappedBy = "user")
    private List<Author> authors;

    public User() {
        this.id = 0;
        this.name = "";
        this.username = "";
        this.password = "";
    }

    public static UserBuilder builder() {
        return new UserBuilder();
    }

    User(Integer id, String name, String username, String password, List<Author> authors) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.authors = authors;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public List<Author> getAuthors() {
        return authors;
    }
}
