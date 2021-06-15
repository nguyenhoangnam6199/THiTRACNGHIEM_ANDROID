package com.example.retrofitdemo.model;

public class DetailHD {
    private long id;
    private String answer;
    private String description;
    private String content;
    private String a;
    private String b;
    private String c;
    private String d;
    private String userAnswer;

    public DetailHD() {
    }

    public DetailHD(long id, String answer, String description, String content, String a, String b, String c, String d, String userAnswer) {
        this.id = id;
        this.answer = answer;
        this.description = description;
        this.content = content;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.userAnswer = userAnswer;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
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

    public String getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(String userAnswer) {
        this.userAnswer = userAnswer;
    }
}
