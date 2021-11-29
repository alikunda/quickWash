package com.example.quickwash;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterFeed extends AppCompatActivity {
    private String feedback;
    private String email;
    private int ID;

    public RegisterFeed(int ID, String Feedback, String Email){
        setEmail(Email);
        setFeed(Feedback);
        setID(ID);

    }


    public void setEmail(String newEmail){
        email = newEmail;
    }
    public void setFeed(String newFeed){
        feedback = newFeed;
    }
    public void setID(int newID){
        ID = newID;
    }
    public int getID(){
        return ID;
    }
    public String getEmail(){
        return email;
    }
    public String getFeedback(){
        return feedback;
    }
}
