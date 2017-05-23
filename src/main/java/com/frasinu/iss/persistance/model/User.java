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
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "username", unique = true)
    private String username;
    @Column(name = "password")
    private String password;

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "user")
    private List<Author> authors;

    @OneToMany(mappedBy = "user")
    private List<Reviewer> reviewers;

    public User() {
    }

    public static UserBuilder builder() {
        return new UserBuilder();
    }

    User(Integer id, String name, String username, String password) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
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

    public List<Reviewer> getReviewers() {
        return reviewers;
    }
}
