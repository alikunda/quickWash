package com.example.quickwash.Garment;

import java.text.DecimalFormat;

public class Garment implements CleaningMethod {
    //private int id;
    private String garmentName;
    private double price;
    private GarmentType garmentType;

    private boolean Laundry;
    private boolean dryClean;

    private boolean ready;

    //private String owner;

    public final DecimalFormat MONEY = new DecimalFormat("$#,##0.00");

    public Garment() {
        price = this.getPrice();
    }

    public void setPrice(double newPrice) {
        if (newPrice >= 0.0)
            price = newPrice;
    }

    public double getPrice() {
        return price;
    }


    public GarmentType getGarmentType() {
        return garmentType;
    }


    public String getGarmentName() {
        return garmentName;
    }

    public void setGarmentName(String garmentName) {
        this.garmentName = this.garmentType.toString();
    }


    @Override
    public void noStarch() {
    }

    @Override
    public void lightStarch() {

    }

    @Override
    public void mediumStarch() {

    }

    @Override
    public void heavyStarch() {

    }

    @Override
    public void DC() {

    }

    @Override
    public void pressOnly() {

    }

    @Override
    public String getCleaningMethod() {
        return null;
    }
}


