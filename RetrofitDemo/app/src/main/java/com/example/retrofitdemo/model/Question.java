package com.example.retrofitdemo.model;

import java.util.List;

public class Question {
    public long id;
    public String description;
    public String content;
    public String photo;
    public int level;
    public Type type;
    public List<Detail> detail;

    public Question(long id, String description, String content, String photo, int level, Type type, List<Detail> detail) {
        this.id = id;
        this.description = description;
        this.content = content;
        this.photo = photo;
        this.level = level;
        this.type = type;
        this.detail = detail;
    }

    public Question() {
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

    public List<Detail> getDetail() {
        return detail;
    }

    public void setDetail(List<Detail> detail) {
        this.detail = detail;
    }
}
