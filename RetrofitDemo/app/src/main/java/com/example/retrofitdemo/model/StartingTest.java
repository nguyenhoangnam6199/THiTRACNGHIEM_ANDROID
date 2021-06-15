package com.example.retrofitdemo.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;

public class StartingTest implements Parcelable {
    public long id;
    public String creationDateTime;
    public String expirationDateTime;
    public Boolean isExpired;
    public List<Question> listQuestion;

    public StartingTest(long id, String creationDateTime, String expirationDateTime, Boolean isExpired, List<Question> listQuestion) {
        this.id = id;
        this.creationDateTime = creationDateTime;
        this.expirationDateTime = expirationDateTime;
        this.isExpired = isExpired;
        this.listQuestion = listQuestion;
    }

    public StartingTest() {
    }

    protected StartingTest(Parcel in) {
        id = in.readLong();
        creationDateTime = in.readString();
        expirationDateTime = in.readString();
        byte tmpIsExpired = in.readByte();
        isExpired = tmpIsExpired == 0 ? null : tmpIsExpired == 1;
    }

    public static final Creator<StartingTest> CREATOR = new Creator<StartingTest>() {
        @Override
        public StartingTest createFromParcel(Parcel in) {
            return new StartingTest(in);
        }

        @Override
        public StartingTest[] newArray(int size) {
            return new StartingTest[size];
        }
    };

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCreationDateTime() {
        return creationDateTime;
    }

    public void setCreationDateTime(String creationDateTime) {
        this.creationDateTime = creationDateTime;
    }

    public String getExpirationDateTime() {
        return expirationDateTime;
    }

    public void setExpirationDateTime(String expirationDateTime) {
        this.expirationDateTime = expirationDateTime;
    }

    public Boolean getExpired() {
        return isExpired;
    }

    public void setExpired(Boolean expired) {
        isExpired = expired;
    }

    public List<Question> getListQuestion() {
        return listQuestion;
    }

    public void setListQuestion(List<Question> listQuestion) {
        this.listQuestion = listQuestion;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(creationDateTime);
        dest.writeString(expirationDateTime);
        dest.writeByte((byte) (isExpired == null ? 0 : isExpired ? 1 : 2));
    }
}
