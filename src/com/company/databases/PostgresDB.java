package com.company.databases;

import com.company.databases.interfaces.IDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class PostgresDB implements IDB {
    @Override
    public Connection getConnection() throws SQLException, ClassNotFoundException {
        //path to database
        String connectionUrl = "jdbc:postgresql://localhost:5432/BankDB";
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(connectionUrl, "postgres", "Famouz13");

        } catch (Exception e) {
            System.out.println(e);
            throw e;
        }
    }
}

