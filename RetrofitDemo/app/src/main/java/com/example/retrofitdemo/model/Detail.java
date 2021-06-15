package com.example.retrofitdemo.model;

public class Detail {
    public long id;
    public String description;
    public String content;
    public String a;
    public String b;
    public String c;
    public String d;

    public Detail(long id, String description, String content, String a, String b, String c, String d) {
        this.id = id;
        this.description = description;
        this.content = content;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    public Detail() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public String getD() {
        return d;
    }

    public void setD(String d) {
        this.d = d;
    }
}
