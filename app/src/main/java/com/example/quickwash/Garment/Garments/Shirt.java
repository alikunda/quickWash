package com.example.quickwash.Garment.Garments;

import com.example.quickwash.Garment.GarmentType;

public class Shirt extends GarmentType {
    double laundryPrice = 2.49;
    double dryCleanPrice = 3.59;

    public Shirt() {
        setPrice(laundryPrice);

    }
}
