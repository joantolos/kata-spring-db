package com.joantolos.kata.spring.db.domain.dao;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Objects;

public class UserId implements Serializable {

    @Column(name = "username")
    private String username;

    public UserId() {}
}
