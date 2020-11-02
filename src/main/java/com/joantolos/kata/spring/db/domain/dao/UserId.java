package com.joantolos.kata.spring.db.domain.dao;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Objects;

public class UserId implements Serializable {

    @Column(name = "username")
    private String username;

    public UserId() {}

    public UserId(String username) {
        this.username = username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserId)) return false;
        UserId userId = (UserId) o;
        return Objects.equals(username, userId.username) &&
                Objects.equals(username, userId.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }
}
