package com.example.quickwash;

public class Order {
    private int id;
    private String username;
    private int tshirtQty;
    private int pantQty;
    final private int tshirtPrice = 4;
    final private int pantPrice = 5;
    private int total;

    public Order(int ID, String name, int sQTY, int pQTY){
        id = ID;
        username = name;
        tshirtQty = sQTY;
        pantQty = pQTY;
    }

    public int getId( ) {
        return id;
    }
    public String getUserName(){return username;}
    public int getsQTY(){ return tshirtQty;}
    public int getpQTY(){ return pantQty;}
    public int getTshirtPrice(){return tshirtPrice;}
    public int getPantPrice(){return pantPrice;}

    public int total(){

        return total = (tshirtPrice*tshirtQty) + (pantPrice*pantQty);
    }





















}
