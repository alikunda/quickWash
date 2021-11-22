package com.example.quickwash.Garment;

import com.google.type.DateTime;

import java.text.DecimalFormat;

public class Garment {
    //private int id;
    private String garmentName;
    private double price;
    //private GarmentType garmentType;

    //private String name;

    private String cleaningMethod;
    private DateTime received, delivered;


    private boolean ready;

    //private String owner;

    public final DecimalFormat MONEY = new DecimalFormat("$#,##0.00");

    public Garment(String nm, String cm, double p) {

        garmentName = nm;
        cleaningMethod = cm;
        received = DateTime.getDefaultInstance();
        price = p;


    }

    public Garment() {

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

    public String getReceived() {
        return received.toString();
    }

    public void setReceived(DateTime received) {
        this.received = received;
    }

    public DateTime getDelivered() {
        return delivered;
    }

    public void setDelivered(DateTime delivered) {
        this.delivered = delivered;
    }
}


