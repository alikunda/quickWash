package com.example.quickwash;

public class payement {

    private int id;
    private String account_holder;
    private int acc_number;
    private int MMYY;
    private int CVV;

    public payement(int ID, String name, int accNum, int MY, int cvv){
        id =ID;
        account_holder = name;
        acc_number = accNum;
        MMYY = MY;
        CVV = cvv;
    }

    public int getID(){
        return id;
    }
    public String getAccount_holder(){
        return account_holder;
    }
    public int getAcc_num(){
        return acc_number;
    }
    public int getMMYY(){
        return MMYY;
    }
    public int getCVV(){
        return CVV;
    }


}
