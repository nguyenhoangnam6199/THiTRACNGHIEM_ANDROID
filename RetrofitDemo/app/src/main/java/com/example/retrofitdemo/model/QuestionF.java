package com.example.retrofitdemo.model;

import java.util.List;

public class QuestionF {
    private long id;
    private String content;
    private String answer;
    private String description;
    private int level;
    private String photo;
    private Type type;
    private List<DetailF> details;

    public QuestionF() {
    }

    public QuestionF(long id, String content, String answer, String description, int level, String photo, Type type, List<DetailF>  details) {
        this.id = id;
        this.content = content;
        this.answer = answer;
        this.description = description;
        this.level = level;
        this.photo = photo;
        this.type = type;
        this.details = details;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public List<DetailF>  getDetails() {
        return details;
    }

    public void setDetails(List<DetailF>  details) {
        this.details = details;
    }
}
