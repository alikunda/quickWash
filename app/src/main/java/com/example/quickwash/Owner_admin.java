package com.example.quickwash;

import android.graphics.Point;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Owner_admin  extends AppCompatActivity {
    private DatabaseManager dbManager;
    public static User myUser;

    ArrayList<userRegisteration> myUserRegisterations;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbManager = new DatabaseManager(this);
        updateView();

    }
    public void updateView() {
        int j = 0;

        // Get all candies form the db table
        myUserRegisterations = dbManager.selectAllOwners();
        if (myUserRegisterations.size() > 0) {
            // create ScrollView and GridLayout
            ScrollView scrollView = new ScrollView(this);
            // Use gridLayout
            GridLayout grid = new GridLayout(this);
            // set columns and rows
            grid.setRowCount(myUserRegisterations.size()+1);
            grid.setColumnCount(3);
            // create arrays of components
            TextView[] ids = new TextView[myUserRegisterations.size()];
            TextView[] name = new TextView[myUserRegisterations.size()];
            TextView[] emails = new TextView[myUserRegisterations.size()];

            TextView id  = new TextView(this);
            id.setGravity(Gravity.CENTER);
            id.setText(" ");

            TextView fullName = new TextView(this);
            fullName.setText("Full name");
            fullName.setTextColor(getResources().getColor(R.color.red));
            fullName.setTextSize(30);

            TextView emailOwner = new TextView(this);
            emailOwner.setText("Email");
            emailOwner.setTextColor(getResources().getColor(R.color.red));
            emailOwner.setTextSize(30);

            // retrieve width of screen
            Point size = new Point();
            getWindowManager().getDefaultDisplay().getSize(size);
            int width = size.x;
            grid.addView(id, width / 5, ViewGroup.LayoutParams.WRAP_CONTENT);
            grid.addView(fullName, (int) (width * 0.40),
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            grid.addView(emailOwner, (int) (width * 0.40),
                    ViewGroup.LayoutParams.WRAP_CONTENT);

            for (userRegisteration myUser : myUserRegisterations) {


                ids[j] = new TextView(this);
                ids[j].setGravity(Gravity.CENTER);
                ids[j].setText("   " + (j + 1));

                name[j] = new TextView(this);
                name[j].setText(myUser.getfName()+" "+myUser.getlName());
                name[j].setId(10 * myUser.getId());
                //emails
                emails[j]= new TextView(this);
                emails[j].setText(myUser.getEmail());
                emails[j].setId(10 * myUser.getId());


                // add the elements to grid
                grid.addView(ids[j], width / 15, ViewGroup.LayoutParams.WRAP_CONTENT);
                grid.addView(name[j], (int) (width * 0.35),
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                grid.addView(emails[j], (int) (width * 0.35),
                        ViewGroup.LayoutParams.WRAP_CONTENT);

                j++;
            }
            // Add views
            scrollView.addView(grid);
            setContentView(scrollView);
        }
    }
}
