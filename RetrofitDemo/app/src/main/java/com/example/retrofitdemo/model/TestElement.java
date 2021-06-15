package com.example.retrofitdemo.model;

public class TestElement {
    private long typeid;
    private int level;
    private int quantity;

    public TestElement(long typeid, int level, int quantity) {
        this.typeid = typeid;
        this.level = level;
        this.quantity = quantity;
    }

    public TestElement() {
    }

    public long getTypeid() {
        return typeid;
    }

    public void setTypeid(long typeid) {
        this.typeid = typeid;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
