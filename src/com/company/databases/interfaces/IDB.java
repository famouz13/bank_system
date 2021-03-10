package com.company.databases.interfaces;

import jdk.jshell.spi.ExecutionControl;

import java.sql.Connection;
import java.sql.SQLException;

public interface IDB {
    Connection getConnection() throws SQLException, ClassNotFoundException;

}
