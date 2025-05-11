package model;

import java.util.Objects;

public class Staff {
    private String id;
    private String name;
    private String gender;
    private String contact;
    private String position;
    private String address;

    public Staff(String id, String name, String gender, String contact, String position, String address) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.contact = contact;
        this.position = position;
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
