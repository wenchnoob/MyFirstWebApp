package com.wrench.models;

import java.io.Serializable;
import java.time.LocalDate;

public class UserAccount implements Serializable {

    private String firstName;
    private String lastName;
    private int age;
    private LocalDate dateOfBirth;
    private LoginCredentials loginCredentials;

    public UserAccount() {}

    public UserAccount(LoginCredentials loginCredentials, String firstName, String lastName, int age, LocalDate dateOfBirth) {
        this.loginCredentials = loginCredentials;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.dateOfBirth = dateOfBirth;
    }

    public boolean checkPassword(String password) {
        if (loginCredentials.getPassword().equals(password)) return true;
        return false;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public LoginCredentials getLoginCredentials() {
        return loginCredentials;
    }

    public void setLoginCredentials(LoginCredentials loginCredentials) {
        this.loginCredentials = loginCredentials;
    }
}
