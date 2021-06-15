package com.example.retrofitdemo.model;

public class Type {
    public long id;
    public String name;

    public Type(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Type() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
