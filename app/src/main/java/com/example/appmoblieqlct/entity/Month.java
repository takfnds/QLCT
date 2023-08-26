package com.example.appmoblieqlct.entity;

public class Month {
    int id;
    String month;

    public Month() {
    }

    public Month(int id, String month) {
        this.id = id;
        this.month = month;
    }

    public int getId() {
        return id;
    }

    public String getMonth() {
        return month;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMonth(String month) {
        this.month = month;
    }
}
