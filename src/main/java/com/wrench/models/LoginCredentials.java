package com.wrench.models;

import java.io.Serializable;

public class LoginCredentials implements Serializable {
    private String username, password;

    public LoginCredentials() {}

    public LoginCredentials(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
