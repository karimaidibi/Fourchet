package com.fourchet.users;

import org.bson.Document;
import org.bson.types.Binary;

public class User {

    private Binary picture;
    private String username;

    private String email;

    private String password;
    private String role;

    private String address;


    public User(String username, String email, String password, String role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public User(Document document) {
        this.username = (String) document.get("username");
        this.email = (String) document.get("email");
        this.password = (String) document.get("password");
        this.role = (String) document.get("role");
        this.picture = (Binary) document.get("picture", Binary.class);
        this.address = (String) document.get("address");
    }

    public User(){}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Binary getPicture() {
        return picture;
    }

    public void setPicture(Binary picture) {
        this.picture = picture;
    }

    public String getAddress() {
        return address;
    }
}