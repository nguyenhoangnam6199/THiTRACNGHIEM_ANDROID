package com.example.retrofitdemo.model;

import java.util.List;

public class EndExam {
    private long id;
    private List<ListQuestion> listQuestion;

    public EndExam(long id, List<ListQuestion> listQuestion) {
        this.id = id;
        this.listQuestion = listQuestion;
    }

    public EndExam() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<ListQuestion> getListQuestion() {
        return listQuestion;
    }

    public void setListQuestion(List<ListQuestion> listQuestion) {
        this.listQuestion = listQuestion;
    }
}
