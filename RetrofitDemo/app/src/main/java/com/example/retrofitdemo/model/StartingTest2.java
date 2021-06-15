package com.example.retrofitdemo.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;

public class StartingTest2 implements Parcelable {
    public long id;
    public String creationDateTime;
    public String expirationDateTime;
    public Boolean isExpired;
    public List<Question2> listQuestion;

    public StartingTest2(long id, String creationDateTime, String expirationDateTime, Boolean isExpired, List<Question2> listQuestion) {
        this.id = id;
        this.creationDateTime = creationDateTime;
        this.expirationDateTime = expirationDateTime;
        this.isExpired = isExpired;
        this.listQuestion = listQuestion;
    }

    public StartingTest2() {
    }

    protected StartingTest2(Parcel in) {
        id = in.readLong();
        creationDateTime = in.readString();
        expirationDateTime = in.readString();
        byte tmpIsExpired = in.readByte();
        isExpired = tmpIsExpired == 0 ? null : tmpIsExpired == 1;
    }

    public static final Creator<StartingTest2> CREATOR = new Creator<StartingTest2>() {
        @Override
        public StartingTest2 createFromParcel(Parcel in) {
            return new StartingTest2(in);
        }

        @Override
        public StartingTest2[] newArray(int size) {
            return new StartingTest2[size];
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

    public List<Question2> getListQuestion() {
        return listQuestion;
    }

    public void setListQuestion(List<Question2> listQuestion) {
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
