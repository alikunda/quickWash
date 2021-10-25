package com.example.quickwash;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class nav_header extends AppCompatActivity {
  private DatabaseManager dbManager;
//    String Name, Email;
//
//  public nav_header(String name, String email){
//      setName(name);
//      setEmail(email);
//  }
//  public void setName(String name){
//      Name = name;
//  }
//    public void setEmail(String email){
//        Email = email;
//    }
//    public String getName(){
//      return Name;
//    }
//    public String getEmail(){
//      return Email;
//    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_header_side_menu);
        dbManager = new DatabaseManager(this);
        updateView();

    }
    public void updateView(){

        TextView user_name = findViewById(R.id.name);
        user_name.setText("ali");
        TextView email = findViewById(R.id.textView);
        email.setText("email");

    }
}