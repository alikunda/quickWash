package com.example.quickwash;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

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

        boolean isOwner = dbManager.returnOwnerStatus(MainActivity.myUser.getEmail());

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_delete) {
            if(isOwner) {
                Intent deleteIntent = new Intent(this, DeleteActivity.class);
                this.startActivity(deleteIntent);
                return true;
            }
            else{
                Toast.makeText(this, "Access denied", Toast.LENGTH_LONG).show();
            }
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
        else if(id == R.id.action_access) {
            if (isOwner) {
                Intent profile = new Intent(this,OwnerAdmin.class);
                this.startActivity(profile);
                return true;
            } else{
                    Toast.makeText(this, "Access denied", Toast.LENGTH_LONG).show();
            }
        }
        else if(id == R.id.action_admins){
            if(isOwner) {
                Intent myIntent = new Intent(this, displayAllAdmins.class);
                startActivity(myIntent);
            }
            else{
                Toast.makeText(this, "Access denied", Toast.LENGTH_LONG).show();
            }
        }
        else if(id == R.id.action_logout){
            Intent myIntent = new Intent(this, MainActivity.class);
            startActivity(myIntent);
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

}