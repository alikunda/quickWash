package com.example.quickwash;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Admin control page
 */

public class adminPage extends AppCompatActivity {

    private DatabaseManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_page);
        dbManager = new DatabaseManager(this);
    }
    //menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_admin, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_delete) {
            Intent deleteIntent = new Intent(this,DeleteActivity.class);
            this.startActivity(deleteIntent);
            return true;
        }
        else if(id ==R.id.action_Profile){
            Intent profile = new Intent(this,ProfileActivity.class);
            this.startActivity(profile);
            return true;
        }
        else if(id ==R.id.action_customer){
            Intent profile = new Intent(this,display_customers.class);
            this.startActivity(profile);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void goBack(View v){
        Intent myIntent = new Intent(this, MainActivity.class);
        startActivity(myIntent);
    }


}