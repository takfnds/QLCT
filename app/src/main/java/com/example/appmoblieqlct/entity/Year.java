package com.example.appmoblieqlct.entity;

public class Year {
    int id;
    String year;

    public Year() {
    }

    public Year(int id, String year) {
        this.id = id;
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public String getYear() {
        return year;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
