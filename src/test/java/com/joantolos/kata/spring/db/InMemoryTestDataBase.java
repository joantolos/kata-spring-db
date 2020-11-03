package com.joantolos.kata.spring.db;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class InMemoryTestDataBase {

    @Autowired
    private EntityManager entityManager;

    public void executeStatement(String sqlScript) throws IOException {
        String sqlStatement = this.toString(this.getClass().getResourceAsStream(sqlScript));
        entityManager.createNativeQuery(sqlStatement).executeUpdate();
    }

    private String toString(InputStream stream) throws IOException {
        StringBuilder sb = new StringBuilder();
        String line;
        BufferedReader br = new BufferedReader(new InputStreamReader(stream));
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        return sb.toString();
    }
}
