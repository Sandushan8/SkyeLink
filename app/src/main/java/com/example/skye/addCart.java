package com.example.skye;

public class addCart {
    Integer count;
    String ID;
    String name;

    public addCart(String ID, String name) {
        this.ID = ID;
        this.name = name;
    }

    public addCart(Integer count, String ID, String name) {
        this.count = count;
        this.ID = ID;
        this.name = name;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
