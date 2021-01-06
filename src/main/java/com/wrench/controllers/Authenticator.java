package com.wrench.controllers;

import com.wrench.models.LoginCredentials;
import com.wrench.models.UserAccount;
import com.wrench.models.UsersStorage;

import java.io.*;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.Period;
import java.util.HashMap;

public class Authenticator implements Serializable {
    transient private static String storageLocation = Paths.get(".").toString() + "/data.bin";
    transient private static boolean loaded = false;

    public static UsersStorage users = new UsersStorage();

    private Authenticator() {}

    public static UserAccount getUser(String userName) {
        return users.get(userName);
    }

    public static ValidationObject validate(LoginCredentials user) {
        String username = user.getUsername();
        String password = user.getPassword();

        if (username.equals("") && password.equals("")) {
            return new ValidationObject(false, "Username and Password fields are empty.");
        } else if (username.equals("")) {
            return new ValidationObject(false, "Username field is empty.");
        } else if (password.equals("")) {
            return new ValidationObject(false, "Password field is empty.");
        } else if (users.get(username) == null) {
            return new ValidationObject(false, "That is not a recognized Username. Please sign Up!");
        } else if (!users.get(username).checkPassword(password)) {
            return new ValidationObject(false, "That password is incorrect.");
        }

        return new ValidationObject(true, "Successful Login!");
    }

    public static ValidationObject validateRegistration(String firstname, String lastname, int age, String username, String password, LocalDate dateOfBirth) {
        long calcedAge = Period.between(dateOfBirth, LocalDate.now()).getYears();

        if(firstname.equals("") || lastname.equals("")) return new ValidationObject(false, "Invalid First Name and/or Last Name.");
        if(username.equals("") || password.equals("")) return new ValidationObject(false, "Invalid Username and/or Password.");
        if(age != calcedAge) return new ValidationObject(false, "Your age and your date of birth do not match.");
        if(calcedAge < 18) return new ValidationObject(false, "You are not old enough to be on this site :)");
        if(users.has(username)) return new ValidationObject(false, "That Username is already in use.");

        users.put(username, new UserAccount(new LoginCredentials(username, password), firstname, lastname, age, dateOfBirth));
        return new ValidationObject(true, "Successful Sign Up!");
    }

    public static void save() {
        File out = new File(storageLocation);
        try (var objectOut = new ObjectOutputStream(new FileOutputStream(storageLocation))) {
            out.createNewFile();
            objectOut.writeObject(users);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void load() {
        if (loaded) return;
        try (var objectIn = new ObjectInputStream(new FileInputStream(storageLocation))) {
            users = (UsersStorage) objectIn.readObject();
            objectIn.close();
            loaded = true;
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    public static class ValidationObject {
        private boolean isValid;
        private StringBuilder message;

        public ValidationObject() {
            message = new StringBuilder();
        }

        public ValidationObject(boolean isValid, String message) {
            this();
            this.isValid = isValid;
            this.message.append(message);
        }

        public void addMessage(String toAppend) {
            message.append("\n" + toAppend);
        }

        public boolean isValid() {
            return isValid;
        }

        public String getMessage() {
            return message.toString();
        }

        public void setValid(boolean isValid) {
            this.isValid = isValid;
        }

        public void setMessage(String message) {
            this.message.setLength(0);
            this.message.append(message);
        }

    }
}
