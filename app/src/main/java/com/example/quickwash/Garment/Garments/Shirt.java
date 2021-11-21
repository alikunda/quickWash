package com.example.quickwash.Garment.Garments;

public class Shirt extends GarmentType {
    double laundryPrice = 2.49; // no starch, light starch +$.10, med starch +$.20
    double dryCleanPrice = 3.59;

    public Shirt() {
        setPrice(laundryPrice);

    }
}
