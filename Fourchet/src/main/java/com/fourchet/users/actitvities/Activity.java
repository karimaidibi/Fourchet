package com.fourchet.users.actitvities;

import org.bson.Document;
import org.bson.types.Binary;

public class Activity {
    private Binary picture;

    private String ownerEmail;

    private String name;

    private String type;

    private String location;

    private String phoneNumber;


    public Activity(String ownerEmail, String name, String type, String location, String phoneNumber) {
        this.ownerEmail = ownerEmail;
        this.name = name;
        this.type = type;
        this.location = location;
        this.phoneNumber = phoneNumber;
    }

    public Activity(Document document) {
        this.ownerEmail = (String) document.get("ownerEmail");
        this.name = (String) document.get("name");
        this.type = (String) document.get("type");
        this.location = (String) document.get("location");
        this.phoneNumber = (String) document.get("phoneNumber");
        this.picture = (Binary) document.get("picture", Binary.class);
    }

    public Binary getPicture() {
        return picture;
    }

    public void setPicture(Binary picture) {
        this.picture = picture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }
}
