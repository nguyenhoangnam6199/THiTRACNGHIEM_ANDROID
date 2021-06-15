package com.example.retrofitdemo.model;

public class CauHoi {
    public int id;
    public long idCauHoi;
    public int loai;
    public String content;
    public String description;

    public CauHoi(int id,long idCauHoi, int loai, String content, String description) {
        this.id = id;
        this.idCauHoi=idCauHoi;
        this.loai = loai;
        this.content = content;
        this.description=description;
    }
}
