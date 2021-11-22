package com.example.quickwash.Garment;

public class GarmentFactory {

    public GarmentFactory(){

    }


    public Garment getGarment(String garmentType, String cleaningMethod, double price){

        return new Garment(garmentType, cleaningMethod, price);

        /*
        if (garmentType.equalsIgnoreCase("shirt")){
            return new Shirt(cleaningMethod, price);
        }
        if (garmentType.equalsIgnoreCase("trouser")){
            return new Trouser();
        }
        else
            return null;*/
    }
}
