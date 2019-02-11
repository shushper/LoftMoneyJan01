package com.shushper.loftmoneyjan01;

public class Item {

    public static final String TYPE_INCOME = "income";
    public static final String TYPE_EXPENSE = "expense";

    private String name;
    private Double price;
    private String type;


    public Item(String name, Double price, String type) {
        this.name = name;
        this.price = price;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public String getType() {
        return type;
    }
}

