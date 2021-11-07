package com.example.quickwash;

import androidx.appcompat.app.AppCompatActivity;

public class userRegisteration extends AppCompatActivity {
    private int id;
    private String email;
    private String fName;
    private String lName;
    private String password;
    private String UserType;

    public userRegisteration(int id, String Email, String First, String Last, String Pass, String Type){
        setId(id);
        setEmail(Email);
        setFname(First);
        setlname(Last);
        setPassword(Pass);
        setUserType(Type);
    }


    public void setId(int newId){
        id = newId;
    }
    public void setEmail(String newEmail){
        email = newEmail;
    }
    public void setFname(String newFname){
        fName = newFname;
    }
    public void setlname(String newLname){
        lName = newLname;
    }
    public void setPassword(String newPass){
        password = newPass;
    }
    public void setUserType(String newUser){
        UserType = newUser;
    }
    public int getId(){
        return id;
    }
    public String getEmail(){
        return email;
    }
    public String getfName(){
        return fName;
    }
    public String getlName(){
        return lName;
    }
    public String getPassword(){
        return password;
    }
    public String getUserType(){
        return UserType;
    }
    public String toString(){
        return ("User Name: "+email+" User Type: "+UserType );
    }
}
