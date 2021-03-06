package com.example.quickwash;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Gravity;
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

public class adminAcPermission extends AppCompatActivity {
    private DatabaseManager dbManager;
    public static User myUser;
    int i = 1;
    ArrayList<userRegisteration> myUserRegisterations;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbManager = new DatabaseManager(this);

        updateView();

    }

    public void updateView() {

        // Get all candies form the db table

        myUserRegisterations = dbManager.selectAllPendingAdmins();
        if (myUserRegisterations.size() > 0) {
            // create ScrollView and GridLayout
            ScrollView scrollView = new ScrollView(this);
            // Use gridLayout
            GridLayout grid = new GridLayout(this);
            // set columns and rows
            grid.setRowCount(myUserRegisterations.size()+1);
            grid.setColumnCount(4);


            // create arrays of components

            TextView quote= new TextView(this);
            quote.setText("Admins request for account");
            quote.setGravity(Gravity.CENTER);
            quote.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            TextView[] ids = new TextView[myUserRegisterations.size()];
            TextView[] emails = new TextView[myUserRegisterations.size()];
            TextView[] IDS = new TextView[myUserRegisterations.size()];
            Button[] approve = new Button[myUserRegisterations.size()];
            Button[] reject = new Button[myUserRegisterations.size()];
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
            TextView id3  = new TextView(this);
            id3.setGravity(Gravity.CENTER);

            TextView id4  = new TextView(this);
            id3.setGravity(Gravity.CENTER);
            id3.setText(" ");

            TextView emailOwner = new TextView(this);
            emailOwner.setText("Email");
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

            for (userRegisteration myUser : myUserRegisterations) {


                // create the TextView for the candy's id
                ids[i] = new TextView(this);
                ids[i].setGravity(Gravity.CENTER);
                ids[i].setText("" + (i + 1));

                IDS[i] = new TextView(this);
                IDS[i].setGravity(Gravity.CENTER);
                IDS[i].setText("  " );

                // create the two EditTexts for the candy's name and price
                // dreate EditText for both name and price
                emails[i]= new TextView(this);
                emails[i].setText(myUser.getEmail());
                emails[i].setId(10 * myUser.getId());

                // create the approve button
                approve[i] = new Button(this);
                approve[i].setText("Approve");
                approve[i].setBackgroundColor(getResources().getColor(android.R.color.holo_green_dark));
                approve[i].setHeight(20);
               approve[i].setPadding(20,10,20,10);
                //approve[i].setId(friend.getId());
                // set up event handling


                // create the deny button
                reject[i] = new Button(this);
                reject[i].setText("Deny");
                reject[i].setBackgroundColor(getResources().getColor(android.R.color.holo_red_dark));
                reject[i].setHeight(20);
                //   reject[i].setId(friend.getId()+1);
                // set up event handling
                reject[i].setPadding(20,10,20,10);


                        // add the elements to grid
                        grid.addView(ids[i], width / 15, ViewGroup.LayoutParams.WRAP_CONTENT);
                grid.addView(emails[i], (int) (width * 0.35),
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                grid.addView(approve[i], (int) (width * 0.20),
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                grid.addView(reject[i], (int) (width * 0.15),
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                grid.setUseDefaultMargins(true);



                grid.setPadding(1,30,1,90);

                //aprove admins
                approve[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dbManager.requestAdminAC(myUser.getEmail(),"approve");
                        updateView();
                        Toast.makeText(adminAcPermission.this, "Account Approved",Toast.LENGTH_LONG).show();
                        if(myUserRegisterations.isEmpty()){
                            Intent myIntent = new Intent(adminAcPermission.this, adminPage.class);
                            startActivity(myIntent);
                        }
                    }

                });
                //reject admins
                reject[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                    dbManager.deleteAdminByAdmin(myUser.getEmail());
                    updateView();
                        Toast.makeText(adminAcPermission.this, "Account rejected and deleted",Toast.LENGTH_LONG).show();
                        if(myUserRegisterations.isEmpty()){
                                    Intent myIntent = new Intent(adminAcPermission.this, adminPage.class);
                                    startActivity(myIntent);
                            }
                        }

                });
                i++;
            }

            // Add views
            scrollView.addView(grid);
            setContentView(scrollView);
        }
    }

}