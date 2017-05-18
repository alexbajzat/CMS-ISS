package com.frasinu.persistance.model;

import javax.persistence.*;

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

    public User(){
        this.id = 0;
        this.name = "";
        this.username = "";
        this.password = "";
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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
