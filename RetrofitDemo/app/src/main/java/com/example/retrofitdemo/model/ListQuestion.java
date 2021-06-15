package com.example.retrofitdemo.model;

public class ListQuestion {
    private long idQuestion;
    private long idQuestionDetail;
    private String answer;

    public ListQuestion() {
    }

    public ListQuestion(long idQuestion, long idQuestionDetail, String answer) {
        this.idQuestion = idQuestion;
        this.idQuestionDetail = idQuestionDetail;
        this.answer = answer;
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

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
