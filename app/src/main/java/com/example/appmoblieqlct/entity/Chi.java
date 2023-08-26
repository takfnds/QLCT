package com.example.appmoblieqlct.entity;

public class Chi {
    String tkc,note,lc;
    int money;
    String day,month,year;

    public Chi(){}

    public Chi(String tkc, String note, String lc, int money, String day, String month, String year) {
        this.tkc = tkc;
        this.note = note;
        this.lc = lc;
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

    public String getTkc() {
        return tkc;
    }

    public String getNote() {
        return note;
    }

    public int getMoney() {
        return money;
    }

    public String getLc() {
        return lc;
    }
}
