package com.wrench.models;

import java.io.Serializable;
import java.util.HashMap;

public class UsersStorage implements Serializable {
    private HashMap<String, UserAccount> users = new HashMap<>();

    public UserAccount get(String userName) {
        return users.get(userName);
    }

    public void put(String userName, UserAccount userAccount) {
        users.put(userName, userAccount);
    }

    public boolean has(String userName) {
        return users.containsKey(userName);
    }

    @Override
    public String toString() {
        return "UsersStorage{" +
                "users=" + users +
                '}';
    }
}
