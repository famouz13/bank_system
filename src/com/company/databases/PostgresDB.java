package com.company.databases;

import com.company.databases.interfaces.IDB;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;


public class PostgresDB implements IDB {
    public PostgresDB() {
    }

    private static class PostgressDBSingleton {
        public static final PostgresDB db = new PostgresDB();
    }

    public static PostgresDB getInstance() {
        return PostgressDBSingleton.db;
    }

    @Override
    public Connection getConnection() throws SQLException, ClassNotFoundException {
        //path to database
        String connectionUrl = "jdbc:postgresql://localhost:1433/HW5";
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(connectionUrl, "postgres", "123");

        } catch (Exception e) {
            System.out.println(e);
            throw e;
        }
    }



}

