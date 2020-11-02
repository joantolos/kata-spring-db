package com.joantolos.kata.spring.db.domain.dao;

import javax.persistence.*;

@Entity(name = "users")
@IdClass(UserId.class)
public class User {

    @Id
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;

    protected User() {

    }

    public User(String username) {
        this.username = username;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

}
