package com.example.pract10_2;

public class DBEntry
{
    private final int id;
    private final String name;
    private final String weight;
    private final String price;
    private final int amount;

    public DBEntry(int id, String name, String weight, String price, int amount)
    {
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.price = price;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getWeight() {
        return weight;
    }

    public String getPrice() {
        return price;
    }

    public int getAmount() {
        return amount;
    }
}