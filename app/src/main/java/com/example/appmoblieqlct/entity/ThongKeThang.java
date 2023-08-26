package com.example.appmoblieqlct.entity;

public class ThongKeThang {
    String month;
    int money;

    public ThongKeThang() {
    }

    public ThongKeThang(String month, int money) {
        this.month = month;
        this.money = money;
    }

    public String getMonth() {
        return month;
    }

    public int getMoney() {
        return money;
    }
}
