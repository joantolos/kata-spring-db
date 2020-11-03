package com.joantolos.kata.spring.db.controller;

import com.joantolos.kata.spring.db.InMemoryTestDataBase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest extends InMemoryTestDataBase {

    @Autowired
    private MockMvc mockMvc;

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
    public void shouldGetAllUsers() throws Exception {

        this.mockMvc
                .perform(get("/users"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].username").value("luke"));
    }

    @Test
    @Modifying
    @Transactional
    public void shouldGetOneUser() throws Exception {

        this.mockMvc
                .perform(get("/users").param("username", "luke"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("luke"))
                .andExpect(jsonPath("$.password").value("123"));
    }

    @Test
    @Modifying
    @Transactional
    public void shouldAddUser() throws Exception {
        this.mockMvc
                .perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"Sith\", \"password\":\"XXX\" }"))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    @Modifying
    @Transactional
    public void shouldUpdateUser() throws Exception {
        this.mockMvc
                .perform(put("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"luke\", \"password\":\"yyy\"}"))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    @Modifying
    @Transactional
    public void shouldDeleteUser() throws Exception {
        this.mockMvc
                .perform(delete("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"luke\"}"))
                .andDo(print())
                .andExpect(status().isAccepted());
    }

}
