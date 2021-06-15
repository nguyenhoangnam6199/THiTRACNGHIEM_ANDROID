package com.example.retrofitdemo.model;

import java.time.Instant;

public class FavoriteQuestion {
    private QuestionF question;
    private String creationDateTime;

    public FavoriteQuestion(QuestionF question, String creationDateTime) {
        this.question = question;
        this.creationDateTime = creationDateTime;
    }

    public FavoriteQuestion() {
    }

    public QuestionF getQuestion() {
        return question;
    }

    public void setQuestion(QuestionF question) {
        this.question = question;
    }

    public String getCreationDateTime() {
        return creationDateTime;
    }

    public void setCreationDateTime(String creationDateTime) {
        this.creationDateTime = creationDateTime;
    }
}
