package com.example.retrofitdemo.model;

public class Statistic {
    private long id;
    private String name;
    private int time;
    private long quantityTesting;
    private long quantityNotSubmit;
    private long quantityCorrect;
    private long quantityIncorrect;
    private long quantityTime;

    public Statistic(long id, String name, int time, long quantityTesting, long quantityNotSubmit, long quantityCorrect, long quantityIncorrect, long quantityTime) {
        this.id = id;
        this.name = name;
        this.time = time;
        this.quantityTesting = quantityTesting;
        this.quantityNotSubmit = quantityNotSubmit;
        this.quantityCorrect = quantityCorrect;
        this.quantityIncorrect = quantityIncorrect;
        this.quantityTime = quantityTime;
    }

    public Statistic(long quantityTesting, long quantityNotSubmit) {
        this.quantityTesting = quantityTesting;
        this.quantityNotSubmit = quantityNotSubmit;
    }

    public Statistic() {
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

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public long getQuantityTesting() {
        return quantityTesting;
    }

    public void setQuantityTesting(long quantityTesting) {
        this.quantityTesting = quantityTesting;
    }

    public long getQuantityNotSubmit() {
        return quantityNotSubmit;
    }

    public void setQuantityNotSubmit(long quantityNotSubmit) {
        this.quantityNotSubmit = quantityNotSubmit;
    }

    public long getQuantityCorrect() {
        return quantityCorrect;
    }

    public void setQuantityCorrect(long quantityCorrect) {
        this.quantityCorrect = quantityCorrect;
    }

    public long getQuantityIncorrect() {
        return quantityIncorrect;
    }

    public void setQuantityIncorrect(long quantityIncorrect) {
        this.quantityIncorrect = quantityIncorrect;
    }

    public long getQuantityTime() {
        return quantityTime;
    }

    public void setQuantityTime(long quantityTime) {
        this.quantityTime = quantityTime;
    }
}
