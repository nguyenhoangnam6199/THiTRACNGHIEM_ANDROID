package com.example.retrofitdemo.model;

import java.util.List;

public class Question2 {
    public long idQuestion;
    public String description;
    public String content;
    public String photo;
    public int level;
    public Type type;
    public long idQuestionDetail;
    public String descriptionDetail;
    public String contentDetail;
    public String a;
    public String b;
    public String c;
    public String d;

    public Question2(long idQuestion, String description, String content, String photo, int level, Type type, long idQuestionDetail, String descriptionDetail, String contentDetail, String a, String b, String c, String d) {
        this.idQuestion = idQuestion;
        this.description = description;
        this.content = content;
        this.photo = photo;
        this.level = level;
        this.type = type;
        this.idQuestionDetail = idQuestionDetail;
        this.descriptionDetail = descriptionDetail;
        this.contentDetail = contentDetail;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
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

    public long getIdQuestion() {
        return idQuestion;
    }

    public void setIdQuestion(long idQuestion) {
        this.idQuestion = idQuestion;
    }

    public long getIdQuestionDetail() {
        return idQuestionDetail;
    }

    public void setIdQuestionDetail(long idQuestionDetail) {
        this.idQuestionDetail = idQuestionDetail;
    }

    public String getDescriptionDetail() {
        return descriptionDetail;
    }

    public void setDescriptionDetail(String descriptionDetail) {
        this.descriptionDetail = descriptionDetail;
    }

    public String getContentDetail() {
        return contentDetail;
    }

    public void setContentDetail(String contentDetail) {
        this.contentDetail = contentDetail;
    }

    public Question2() {
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


}
