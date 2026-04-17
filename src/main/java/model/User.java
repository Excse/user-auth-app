package model;

import java.sql.Timestamp;
import java.util.Locale;

public class User {

    private Timestamp lastAccessed;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private Locale locale;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private int id;

    public User(int id, String username, String password, String firstName, String lastName, String email,
            Locale locale, Timestamp createdAt, Timestamp updatedAt, Timestamp lastAccessed) {
        this.lastAccessed = lastAccessed;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.locale = locale;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.id = id;
    }

    public void setLastAccessed(Timestamp lastAccessed) {
        this.lastAccessed = lastAccessed;
    }

    public Timestamp getLastAccessed() {
        return lastAccessed;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

}
