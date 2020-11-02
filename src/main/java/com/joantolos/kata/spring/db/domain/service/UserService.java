package com.joantolos.kata.spring.db.domain.service;

import com.joantolos.kata.spring.db.domain.dao.User;
import com.joantolos.kata.spring.db.domain.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {
        return this.userRepository.findAllUsers();
    }

    public User getUser(String username) {
        return this.userRepository.findUser(username);
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
