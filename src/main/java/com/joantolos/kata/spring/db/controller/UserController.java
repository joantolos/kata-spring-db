package com.joantolos.kata.spring.db.controller;

import com.joantolos.kata.spring.db.domain.dao.User;
import com.joantolos.kata.spring.db.domain.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private final Logger log = LoggerFactory.getLogger(com.joantolos.kata.spring.db.controller.UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ResponseEntity getUsers(@RequestParam(defaultValue = "all") String username) {
        log.info("### GET /users endpoint called");
        if (username.equals("all")) {
            return ResponseEntity.status(HttpStatus.OK).body(this.userService.getUsers());
        }
        return ResponseEntity.status(HttpStatus.OK).body(this.userService.getUser(username));
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public ResponseEntity addUser(@RequestBody User user) {
        log.info("### POST /users endpoint called");
        this.userService.addUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @RequestMapping(value = "/users", method = RequestMethod.DELETE)
    public ResponseEntity deleteUser(@RequestBody User user) {
        log.info("### DELETE /users endpoint called");
        this.userService.deleteUser(user);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @RequestMapping(value = "/users", method = RequestMethod.PUT)
    public ResponseEntity updateUser(@RequestBody User user) {
        log.info("### PUT /users endpoint called");
        this.userService.updateUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
