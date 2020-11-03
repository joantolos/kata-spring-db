package com.joantolos.kata.spring.db.domain.service;

import com.joantolos.kata.spring.db.InMemoryTestDataBase;
import com.joantolos.kata.spring.db.domain.dao.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserServiceTestDataBase extends InMemoryTestDataBase {

    @Autowired
    private UserService userService;

    @Before
    public void setUp() throws IOException {
        this.executeStatement("/user/createUser.sql");
    }

    @After
    public void tearDown() throws IOException {
        this.executeStatement("/user/dropUser.sql");
    }

    @Test
    @Modifying
    @Transactional
    public void shouldGetAllUsers() {
        Assert.assertEquals(3, userService.getUsers().size());
    }

    @Test
    @Modifying
    @Transactional
    public void shouldGetUser() {
        Assert.assertEquals("123", userService.getUser("luke").getPassword());
    }

    @Test
    @Modifying
    @Transactional
    public void shouldAddUser() {
        userService.addUser(new User("leia", "xyz"));
        Assert.assertNotNull(userService.getUser("leia"));
    }

    @Test
    @Modifying
    @Transactional
    public void shouldUpdateUser() {
        userService.updateUser(new User("luke", "XXX"));
        Assert.assertEquals("XXX", userService.getUser("luke").getPassword());
    }

    @Test
    @Modifying
    @Transactional
    public void shouldDeleteUser() {
        userService.deleteUser(new User("luke"));
        Assert.assertNull(userService.getUser("luke"));
    }
}
