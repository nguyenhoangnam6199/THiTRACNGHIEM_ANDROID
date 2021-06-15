package com.example.retrofitdemo.model;

public class Account {
    private long id;
    private String username;
    private String name;
    private String photo;
    private Boolean isAdmin;

    public Account(long id, String username, String name, String photo, Boolean isAdmin) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.photo = photo;
    }

    public Boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
