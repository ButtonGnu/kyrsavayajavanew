package com.example.kyrsavayajava;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public interface DAO<T> {

    String     dbURL = "jdbc:postgresql://localhost:5432/SPARES3";
    String     user  = "postgres";
    String     pass  = "2001Eliz!!";
    default Connection buildConnection(){
        try {
            return DriverManager.getConnection(dbURL, user, pass);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    void create(T entity);

    void update(T entity);

    T findById(Long id);

    List<T> findAll();
}
