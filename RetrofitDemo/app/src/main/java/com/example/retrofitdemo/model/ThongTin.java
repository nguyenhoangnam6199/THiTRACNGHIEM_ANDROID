package com.example.retrofitdemo.model;

public class ThongTin {
    String stt;
    String hoten;
    String masv;

    public ThongTin(String stt, String hoten, String masv) {
        this.stt = stt;
        this.hoten = hoten;
        this.masv = masv;
    }

    public String getStt() {
        return stt;
    }

    public void setStt(String stt) {
        this.stt = stt;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getMasv() {
        return masv;
    }

    public void setMasv(String masv) {
        this.masv = masv;
    }
}
