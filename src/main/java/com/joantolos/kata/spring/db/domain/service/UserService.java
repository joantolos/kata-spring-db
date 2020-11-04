package com.joantolos.kata.spring.db.domain.service;

import com.joantolos.kata.spring.db.domain.dao.User;
import com.joantolos.kata.spring.db.domain.dao.UserId;
import com.joantolos.kata.spring.db.domain.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {
        return this.userRepository.findAll();
    }

    public User getUser(String username) {
        return this.userRepository.findUser(username);
    }

    public Optional<User> findById(String username) {
        return this.userRepository.findById(new UserId(username));
    }

    public void addUser(User user) {
        this.userRepository.addUser(user.getUsername(), user.getPassword());
    }

    public void deleteUser(User user) {
        this.userRepository.deleteUser(user.getUsername());
    }

    public void updateUser(User user) {
        this.userRepository.updateUser(user.getUsername(), user.getPassword());
    }
}
