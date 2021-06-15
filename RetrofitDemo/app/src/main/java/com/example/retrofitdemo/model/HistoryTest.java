package com.example.retrofitdemo.model;

public class HistoryTest {
    private long id;
    private String creationDateTime;
    private int time;
    private String submitDateTime;
    private String result;
    private String name;

    public HistoryTest() {
    }

    public HistoryTest(long id, String creationDateTime, int time, String submitDateTime, String result, String name) {
        this.id = id;
        this.creationDateTime = creationDateTime;
        this.time = time;
        this.submitDateTime = submitDateTime;
        this.result = result;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCreationDateTime() {
        return creationDateTime;
    }

    public void setCreationDateTime(String creationDateTime) {
        this.creationDateTime = creationDateTime;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getSubmitDateTime() {
        return submitDateTime;
    }

    public void setSubmitDateTime(String submitDateTime) {
        this.submitDateTime = submitDateTime;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
