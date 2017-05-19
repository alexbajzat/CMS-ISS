package com.frasinu.iss.persistance.model;

import java.util.List;

/**
 * Created by bjz on 5/7/2017.
 */
public class UserBuilder {
    private Integer id;
    private String name;
    private String username;
    private String password;
    private List<Author> authors;

    public User build() {
        return new User(id, name, username, password, authors);
    }

    public UserBuilder setId(Integer id) {
        this.id = id;
        return this;
    }

    public UserBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public UserBuilder setUsername(String username) {
        this.username = username;
        return this;
    }

    public UserBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    public UserBuilder setAuthors(List<Author> authors) {
        this.authors = authors;
        return this;
    }
}
