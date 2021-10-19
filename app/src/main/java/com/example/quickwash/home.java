package com.example.quickwash;

import android.os.Bundle;
import android.view.View;
/**
 User Home page after login in(home.xml)
 */
import androidx.appcompat.app.AppCompatActivity;

public class home extends AppCompatActivity {
    private DatabaseManager dbManager;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        dbManager = new DatabaseManager(this);
    }
    public void goBack(View v){
        this.finish();

    }
}
