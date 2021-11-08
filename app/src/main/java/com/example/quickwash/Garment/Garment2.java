package com.example.quickwash.Garment;

import java.text.DecimalFormat;

public abstract class Garment2 implements CleaningMethod {
    //private int id;
    private String garmentName;
    private double price;
    private GarmentType garmentType;
    private CleaningMethod cleaningMethod;

    private boolean ready;

    //private String owner;

    public final DecimalFormat MONEY = new DecimalFormat("$#,##0.00");
}
