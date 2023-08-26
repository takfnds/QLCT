package com.example.appmoblieqlct.entity;

public class Thu2 {
    String tkt,note,lt;
    int money;
    String day,month,year;

    public Thu2(){}

    public Thu2(String tkt, String note, String lt, int money, String day, String month, String year) {
        this.tkt = tkt;
        this.note = note;
        this.lt = lt;
        this.money = money;
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public String getDay() {
        return day;
    }

    public String getMonth() {
        return month;
    }

    public String getYear() {
        return year;
    }

    public String getTkt() {
        return tkt;
    }

    public String getNote() {
        return note;
    }

    public int getMoney() {
        return money;
    }

    public String getLt() {
        return lt;
    }
}
