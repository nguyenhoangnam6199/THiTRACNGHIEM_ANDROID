package com.example.retrofitdemo.model;

import java.util.List;

public class TestCreation {
    private String name;
    private int time;
    private List<TestElement> details;

    public TestCreation(String name, int time, List<TestElement> details) {
        this.name = name;
        this.time = time;
        this.details = details;
    }

    public TestCreation() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public List<TestElement> getDetails() {
        return details;
    }

    public void setDetails(List<TestElement> details) {
        this.details = details;
    }
}
