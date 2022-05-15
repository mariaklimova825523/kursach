package com.example.submon;

public class Subscription {
    private int ID;
    private String name;
    private String description;
    private Float price;
    private Integer day;

    public Subscription(int id,String name, String description, Float price, Integer day) {
        this.ID = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.day = day;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }
}
