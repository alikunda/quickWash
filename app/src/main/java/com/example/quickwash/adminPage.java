package com.example.quickwash;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

/**
 * Admin control page
 */

public class adminPage extends AppCompatActivity {

    private DatabaseManager dbManager;
    private DatabaseManager2 dbManager1;
    public static User myUser;
    ArrayList<Order> myOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.admin_page);
        dbManager = new DatabaseManager(this);
        dbManager1 = new DatabaseManager2(this);
        updateView();
    }
    public void updateView() {
        myOrder = dbManager1.selectAllPendingOrders();
        if (myOrder.size() > 0) {
            // create ScrollView and GridLayout
            ScrollView scrollView = new ScrollView(this);
            // Use gridLayout
            GridLayout grid = new GridLayout(this);
            // set columns and rows
            grid.setRowCount(myOrder.size()+1);
            grid.setColumnCount(4);


            // create arrays of components

            TextView quote= new TextView(this);
            quote.setText("Admins request for account");
            quote.setGravity(Gravity.CENTER);
            quote.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            TextView[] ids = new TextView[myOrder.size()];
            TextView[] emails = new TextView[myOrder.size()];
            Button[] approve = new Button[myOrder.size()];
            Button[] reject = new Button[myOrder.size()];
            // ButtonHandler approveBtn = new ButtonHandler();
            // ButtonHandler1 rejectBtn = new ButtonHandler1();


            TextView id  = new TextView(this);
            id.setGravity(Gravity.CENTER);
            id.setText(" ");
            id.setTextSize(20);
            TextView id1  = new TextView(this);
            id1.setGravity(Gravity.CENTER);
            id1.setText(" ");
            TextView id2  = new TextView(this);
            id2.setGravity(Gravity.CENTER);
            id2.setText(" ");

            TextView emailOwner = new TextView(this);
            emailOwner.setText("Garment Type ");
            emailOwner.setTextColor(getResources().getColor(R.color.red));
            emailOwner.setTextSize(20);
            // retrieve width of screen
            Point size = new Point();
            getWindowManager().getDefaultDisplay().getSize(size);
            int width = size.x;
            int i = 0;

            grid.addView(id, (int) (width * 0.10), ViewGroup.LayoutParams.WRAP_CONTENT);
            grid.addView(emailOwner, (int) (width * 0.35),
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            grid.addView(id1, (int) (width * 0.10),
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            grid.addView(id2, (int) (width * 0.10),
                    ViewGroup.LayoutParams.WRAP_CONTENT);

            for ( Order myOrder : myOrder) {

                // create the TextView for the candy's id
                ids[i] = new TextView(this);
                ids[i].setGravity(Gravity.CENTER);
                ids[i].setText("" + (i + 1));

                // create the two EditTexts for the candy's name and price
                // dreate EditText for both name and price
                emails[i]= new TextView(this);
                emails[i].setText(myOrder.getGARMENT_TYPE()+", "+myOrder.getCLEANING_METHOD());
                Log.w("AdminUpdate","****"+myOrder.getCUSTOMER_EMAIL()+myOrder.getCLEANING_METHOD());
                // create the approve button
                approve[i] = new Button(this);
                approve[i].setText("Approve");
                approve[i].setBackgroundColor(getResources().getColor(android.R.color.holo_green_dark));
                //approve[i].setId(friend.getId());
                // set up event handling


                // create the deny button
                reject[i] = new Button(this);
                reject[i].setText("Deny");
                reject[i].setBackgroundColor(getResources().getColor(android.R.color.holo_red_dark));
                //   reject[i].setId(friend.getId()+1);
                // set up event handling
                reject[i].setPadding(20,20,20,20);

                // add the elements to grid
                grid.addView(ids[i], width / 15, ViewGroup.LayoutParams.WRAP_CONTENT);
                grid.addView(emails[i], (int) (width * 0.35),
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                grid.addView(approve[i], (int) (width * 0.20),
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                grid.addView(reject[i], (int) (width * 0.15),
                        ViewGroup.LayoutParams.WRAP_CONTENT);

                grid.setPadding(1,30,1,90);

                //aprove admins
                approve[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
//                        dbManager.requestAdminAC(myOrder.getEmail(),"approve");
//                        updateView();
//                        Toast.makeText(adminAcPermission.this, "Account Approved",Toast.LENGTH_LONG).show();
//                        if(Admin_Update_Order.this.myOrder.isEmpty()){
//                            Intent myIntent = new Intent(adminAcPermission.this, adminPage.class);
//                            startActivity(myIntent);
//                        }
                    }

                });
                //reject admins
                reject[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
//                        dbManager.deleteAdminByAdmin(myOrder.getEmail());
//                        updateView();
//                        Toast.makeText(adminAcPermission.this, "Account rejected and deleted",Toast.LENGTH_LONG).show();
//                        if(Admin_Update_Order.this.myOrder.isEmpty()){
//                            Intent myIntent = new Intent(adminAcPermission.this, adminPage.class);
//                            startActivity(myIntent);
//                        }
                    }

                });
                i++;
            }

            // Add views
            scrollView.addView(grid);
            setContentView(scrollView);
        }
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
                Intent profile = new Intent(this, adminAcPermission.class);
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
        else if(id == R.id.action_owner_access_list){
            if(isOwner){
                Intent myIntent = new Intent(this, Owner_admin.class);
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