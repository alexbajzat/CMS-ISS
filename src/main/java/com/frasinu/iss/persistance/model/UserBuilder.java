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

    public User build() {
        return new User(id, name, username, password);
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

}
