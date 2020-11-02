package com.joantolos.kata.spring.db.domain.repository;

import com.joantolos.kata.spring.db.domain.dao.User;
import com.joantolos.kata.spring.db.domain.dao.UserId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserRepository extends JpaRepository<User, UserId> {

    @Query(value = "SELECT * FROM users;", nativeQuery = true)
    List<User> findAllUsers();

    @Query(value = "SELECT * FROM users WHERE username = :username", nativeQuery = true)
    User findUser(@Param("username") String username);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO users (username, password) VALUES(:username, :password);", nativeQuery = true)
    void addUser(String username, String password);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM users WHERE username = :username", nativeQuery = true)
    void deleteUser(String username);

    @Modifying
    @Transactional
    @Query(value = "UPDATE users SET password = :password WHERE username = :username", nativeQuery = true)
    void updateUser(String username, String password);
}
