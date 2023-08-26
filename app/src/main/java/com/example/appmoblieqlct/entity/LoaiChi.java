package com.example.appmoblieqlct.entity;

public class LoaiChi {
    String name,id;
    public LoaiChi(){}

    public LoaiChi(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }
}
