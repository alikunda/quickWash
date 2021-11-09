package com.example.quickwash;

public class User {
    private DatabaseManager dbManager;

    private int id;
    private String fname, lname;

    private String email;
    private String password;
    private String User_Type;


    public  User(int id, String email, String password, String UserType){
        setId(id);
        setEmail(email);
        setPassword(password);
        setUser(UserType);
    }

    public void setFname(String first){
        fname = first;
    }
    public void setLname(String last){
        lname = last;
    }
    public void setId(int newId){
        id = newId;
    }
    public void setEmail(String newEmail){
        email = newEmail;
    }
    public void setPassword(String newPass){
        password = newPass;
    }
    public void setUser(String newUser){
        User_Type = newUser;
    }
    public int getId( ) {
        return id;
    }

    public String getFname(){
        return fname;
    }
    public String getLname(){
        return lname;
    }
    public String getName(){
        return fname+" "+lname;
    }
    public String getEmail( ) {
        return email;
    }
    public String getPassword( ) {
        return password;
    }
    public String getUser_Type(){
        return User_Type;
    }
    public String toString(){
        return ("User Name: "+email+" User Type: "+User_Type );
    }



}
