package com.example.appmoblieqlct.entity;

public class ThongKeNam {
    String year;
    int money;

    public ThongKeNam() {
    }

    public ThongKeNam(String year, int money) {
        this.year = year;
        this.money = money;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}
