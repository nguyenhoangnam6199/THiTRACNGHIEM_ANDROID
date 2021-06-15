package com.example.retrofitdemo.model;

import android.os.Parcel;
import android.os.Parcelable;

public class BoDe implements Parcelable {
    private int id;
    private String name;
    private int quantity;
    private int time;

    public BoDe(int id, String name, int quantity, int time) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.time = time;
    }

    protected BoDe(Parcel in) {
        id = in.readInt();
        name = in.readString();
        quantity = in.readInt();
        time = in.readInt();
    }

    public static final Parcelable.Creator<BoDe> CREATOR = new Parcelable.Creator<BoDe>() {
        @Override
        public BoDe createFromParcel(Parcel in) {
            return new BoDe(in);
        }

        @Override
        public BoDe[] newArray(int size) {
            return new BoDe[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeInt(quantity);
        dest.writeInt(time);
    }
}
