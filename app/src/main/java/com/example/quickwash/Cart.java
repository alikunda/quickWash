package com.example.quickwash;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.example.quickwash.ui.gallery.GalleryFragment;

import java.util.ArrayList;

/**
 * Admin control page
 */

public class Cart extends AppCompatActivity {


    private DatabaseManager2 dbManager1;
    public static User myUser;
    ArrayList<Order> orders;
    private int recieptNumber = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        dbManager1 = new DatabaseManager2(this);
        updateView();
    }

    
    public void updateView() {
        orders = dbManager1.selectAllPendingOrders(MainActivity.myUser.getEmail());
        if (orders.size() > 0) {
            RelativeLayout layout = new RelativeLayout((this));
            // create ScrollView and GridLayout
            ScrollView scrollView = new ScrollView(this);
            // Use gridLayout
            GridLayout grid = new GridLayout(this);
            // set columns and rows
            grid.setRowCount(orders.size()+1);
            grid.setColumnCount(3);


            // create arrays of components

            TextView quote= new TextView(this);
            quote.setText("Admins request for account");
            quote.setGravity(Gravity.CENTER);
            quote.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            TextView[] ids = new TextView[orders.size()];
            TextView[] orderItems = new TextView[orders.size()];
            Button[] remove = new Button[orders.size()];
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
            emailOwner.setText("Items ");
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


            for ( Order myOrder : orders) {

                // create the TextView for the candy's id
                ids[i] = new TextView(this);
                ids[i].setGravity(Gravity.CENTER);
                ids[i].setText("" + (i + 1));

                // create the two EditTexts for the candy's name and price
                // dreate EditText for both name and price
                orderItems[i]= new TextView(this);
                orderItems[i].setText("Garment: "+myOrder.getGARMENT_TYPE()+"\nCleaning Method: "+myOrder.getCLEANING_METHOD()+"\nPrice: "+myOrder.getPRICE()+"\nQTY: "+myOrder.getQUANTITY()+"\nContact information: "+myOrder.getCUSTOMER_EMAIL()+"\nReciept number: "+myOrder.getRECIEPTNUMBER());
                      //  setText("Garment: "+myOrder.getGARMENT_TYPE()+"\nQTY:"+myOrder.getQUANTITY()+"\n"+"Cleaning Method: "+myOrder.getCLEANING_METHOD() +"\nPrice:$"+myOrder.getPRICE());
                // create the approve button


                // create the deny button
                remove[i] = new Button(this);
                remove[i].setText("Delete");
                remove[i].setBackgroundColor(getResources().getColor(android.R.color.holo_red_dark));
                //   remove[i].setId(friend.getId()+1);
                // set up event handling
                remove[i].setPadding(20,20,20,20);

                // add the elements to grid
                grid.addView(ids[i], width / 15, ViewGroup.LayoutParams.WRAP_CONTENT);
                grid.addView(orderItems[i], (int) (width * 0.55),
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                grid.addView(remove[i], (int) (width * 0.25),
                        ViewGroup.LayoutParams.WRAP_CONTENT);

                grid.setUseDefaultMargins(true);
                grid.setPadding(1,30,1,90);

                //remove admins
                remove[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                     dbManager1.deleteItem(myOrder.getCLEANING_METHOD(),myOrder.getGARMENT_TYPE(),myOrder.getQUANTITY(),myOrder.getRECEIVED(), myOrder.getRECIEPTNUMBER());
                     Toast.makeText(Cart.this, "Item deleted!!!",Toast.LENGTH_SHORT).show();
                     updateView();
                        if(orders.isEmpty()) {
                            Intent myIntent = new Intent(Cart.this, Cart.class);
                            startActivity(myIntent);
                            finish();
                    }
                    }

                });

                i++;
            }

            // create a back button
            Button Pay = new Button(this);
            Pay.setText("Pay");
            Pay.setBackgroundColor(getResources().getColor(R.color.green));
            Pay.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent myIntent = new Intent(Cart.this, PayementOrder.class);
                    startActivity(myIntent);
                    finish();

                }
            });
            // Add views
            scrollView.addView(grid);
            layout.addView(scrollView);
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT);
            params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
            params.addRule(RelativeLayout.CENTER_HORIZONTAL);
            params.setMargins(0, 0, 0, 50);
            layout.addView(Pay, params);

            setContentView(layout);
        }
    }
    //menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.side_menu, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_userProfile) {
            Intent deleteIntent = new Intent(this,userProfileActivity.class);
            this.startActivity(deleteIntent);
            return true;
        }
        else if (id == R.id.action_cart) {
           Toast.makeText(this,"You are in Cart!!!",Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}