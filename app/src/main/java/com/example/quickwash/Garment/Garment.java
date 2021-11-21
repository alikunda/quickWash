package com.example.quickwash.Garment;

import com.google.type.DateTime;

import java.text.DecimalFormat;

public class Garment {
    //private int id;
    private String garmentName;
    private double price;
    //private GarmentType garmentType;

    private String cleaningMethod;
    private DateTime received, delivered;


    private boolean ready;

    //private String owner;

    public final DecimalFormat MONEY = new DecimalFormat("$#,##0.00");

    public Garment(String cm) {

        cleaningMethod = cm;
        received = DateTime.getDefaultInstance();


        price = this.getPrice();
    }

    public void setPrice(double newPrice) {
        if (newPrice >= 0.0)
            price = newPrice;
    }

    public double getPrice() {
        return price;
    }




    public String getGarmentName() {
        return garmentName;
    }


    public void setCleaningMethod(String cleaningMethod) {
        this.cleaningMethod = cleaningMethod;
    }

    public String getCleaningMethod() {
        return cleaningMethod;
    }
}


