package com.example.quickwash.Garment;

import java.text.DecimalFormat;

public class Garment extends GarmentType {
    private int id;
    private double price;
    private GarmentType garmentType;
    private String owner;

    public final DecimalFormat MONEY = new DecimalFormat("$#,##0.00");

    public Garment(int newId, GarmentType garmentType, int newPrice, String newOwner) {
        setId(newId);
        setGarmentType(garmentType);
        setPrice(newPrice);
        setOwner(newOwner);
    }

    //public Garment()

    public void setId(int newId) {
        id = newId;
    }

    public void setPrice(double newPrice) {
        if (newPrice >= 0.0)
            price = newPrice;
    }

    public void setOwner(String newOwner) {
        owner = newOwner;
    }

    public int getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    public String getOwner() {
        return owner;
    }

    public void setGarmentType(GarmentType garmentType) {
        this.garmentType = garmentType;
    }

    public GarmentType getGarmentType() {
        return garmentType;
    }

    public String toString() {
        return (owner + "'s " + garmentType + ": " + MONEY.format(price));
    }
}


