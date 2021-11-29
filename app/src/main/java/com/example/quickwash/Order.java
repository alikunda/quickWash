package com.example.quickwash;

import com.example.quickwash.Garment.GarmentFactory;

public class Order {
    private GarmentFactory garmentFactory;
    private int ID;
    private User newUser;
    private  String GARMENT_TYPE;
    private  String CLEANING_METHOD;
    private  String QUANTITY;
    private  String PRICE;
    private  String RECEIVED;
    private String DELIVERED;
    private  String STATUS;
    private  String RECIEPTNUMBER;
    private  String CUSTOMER_EMAIL;
    public Order(int id, String garmenType,String Cleaning_method, String quantity, String price,
                 String Rec,String del, String Status, String Rec_num, String email){

        setID(id);
        setGarmentType(garmenType);
        setCleaingMethod(Cleaning_method);
        setQuantity(quantity);
        setPrice(price);
        setReceved(Rec);
        setDelivered(del);
        setStatus(Status);
        setRec_num(Rec_num);
        setEmail(email);
    }

    public void setDelivered(String del){
     DELIVERED = del;
    }
    public  void setRec_num(String rec_num) {
        RECIEPTNUMBER = rec_num;
    }

    public  void setStatus(String status) {
        STATUS = status;
    }

    public  void setPrice(String price) {
        PRICE = price;
    }

    public  void setReceved(String rec) {
        RECEIVED = rec;
    }

    public  void setQuantity(String quantity) {
        QUANTITY = quantity;
    }

    public  void setCleaingMethod(String cleaning_method) {
        CLEANING_METHOD = cleaning_method;
    }

    public  void setGarmentType(String garmenType) {
        GARMENT_TYPE = garmenType;
    }
    public  void setEmail(String Email) {
        CUSTOMER_EMAIL = Email;
    }
    public String getGARMENT_TYPE(){
        return  GARMENT_TYPE;
    }
    public String getCLEANING_METHOD(){
        return CLEANING_METHOD;
    }
    public String getQUANTITY(){
        return QUANTITY;
    }
    public String getPRICE(){
        return PRICE;
    }
    public String getRECEIVED(){
        return RECEIVED;
    }
    public String getSTATUS(){
        return STATUS;
    }
    public String getRECIEPTNUMBER(){
        return RECIEPTNUMBER;
    }
    public String getCUSTOMER_EMAIL(){
        return CUSTOMER_EMAIL;
    }
    public String getDELIVERED(){
        return DELIVERED;
    }


    public User getNewUser() {
        return newUser;
    }

    public void setNewUser(User newUser) {
        this.newUser = newUser;
    }

    public String toString(){
        return "\nQTY: "+QUANTITY + " " +"\nGarment Type"+ GARMENT_TYPE + "\nCleaning Method: " + CLEANING_METHOD + "\nPrice: " + Double.parseDouble(PRICE)
                + "\nStatus:  " + STATUS;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }


















    /*
    private int id;
    private String username;
    private int tshirtQty;
    private int pantQty;
    final private int tshirtPrice = 4;
    final private int pantPrice = 5;
    private int total;

    public Order(int ID, String name, int sQTY, int pQTY) {
        id = ID;
        username = name;
        tshirtQty = sQTY;
        pantQty = pQTY;
    }

    public int getId() {
        return id;
    }

    public String getUserName() {
        return username;
    }

    public int getsQTY() {
        return tshirtQty;
    }

    public int getpQTY() {
        return pantQty;
    }

    public int getTshirtPrice() {
        return tshirtPrice;
    }

    public int getPantPrice() {
        return pantPrice;
    }

    public int total() {

        return total = (tshirtPrice * tshirtQty) + (pantPrice * pantQty);
    }*/
}






















