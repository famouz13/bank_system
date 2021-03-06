package com.company.databases;

import com.company.databases.interfaces.IDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class PostgresDB implements IDB {


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

