package com.example.quickwash.Garment;

import com.example.quickwash.Garment.Garments.Shirt;
import com.example.quickwash.Garment.Garments.Trouser;

public class GarmentFactory {

    public GarmentFactory(){

    }


    public Garment getGarment(String garmentType, String cleaningMethod){
        if (garmentType.equalsIgnoreCase("shirt")){
            return new Shirt(cleaningMethod);
        }
        if (garmentType.equalsIgnoreCase("trouser")){
            return new Trouser(cleaningMethod);
        }
        else
            return null;
    }
}
