package com.example.quickwash;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quickwash.ui.checkOrder.CheckOrderActivity;

public class OrderPlacedActivty extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_placed);
        Toast.makeText(this, "Payement made Sucessfully!", Toast.LENGTH_SHORT).show();

    }
    public void trackOrder(View v){
        Intent myIntent = new Intent(this, CheckOrderActivity.class);
        startActivity(myIntent);
        this.finish();
    }
}
