package com.company.Models;

import java.io.StringReader;

public class Admin {
    private int adminID;
    private String username;
    private String password;

    public Admin(String username, String password) {
        setUsername(username);
        setPassword(password);
    }

    public Admin(int id, String username, String password) {
        this(username, password);
        this.adminID = id;
    }

    public int getAdminID() {
        return adminID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
