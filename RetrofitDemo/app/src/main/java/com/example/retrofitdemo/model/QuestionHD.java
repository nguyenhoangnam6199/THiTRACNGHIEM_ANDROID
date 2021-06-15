package com.example.retrofitdemo.model;

import java.util.List;

public class QuestionHD {
    private long id	;
    private String answer;
    private  String description	;
    private String content;
    private  String photo;
    private int level;
    private Type type;
    private String userAnswer;
    private List<DetailHD> details;

    public QuestionHD() {
    }

    public QuestionHD(long id, String answer, String description, String content, String photo, int level, Type type, String userAnswer, List<DetailHD> details) {
        this.id = id;
        this.answer = answer;
        this.description = description;
        this.content = content;
        this.photo = photo;
        this.level = level;
        this.type = type;
        this.userAnswer = userAnswer;
        this.details = details;
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

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(String userAnswer) {
        this.userAnswer = userAnswer;
    }

    public List<DetailHD> getDetails() {
        return details;
    }

    public void setDetails(List<DetailHD> details) {
        this.details = details;
    }
}
