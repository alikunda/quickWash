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
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class displayAllOrdersAdmin extends AppCompatActivity {

    private DatabaseManager dbManager;
    private DatabaseManager2 dbManager1;
    public static User myUser;
    public final DecimalFormat rate_prec = new DecimalFormat("$0.00");
    ArrayList<Order> myOrders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dbManager = new DatabaseManager(this);
        dbManager1 = new DatabaseManager2(this);
        updateView();
    }
    public void updateView() {
        myOrders = dbManager1.selectAllOrdersDone();
        if (myOrders.size() > 0) {
            // create ScrollView and GridLayout
            ScrollView scrollView = new ScrollView(this);
            // Use gridLayout
            GridLayout grid = new GridLayout(this);
            // set columns and rows
            grid.setRowCount(myOrders.size() + 1);
            grid.setColumnCount(2);


            // create arrays of components

            TextView quote = new TextView(this);
            quote.setText("Admins request for account");
            quote.setGravity(Gravity.CENTER);
            quote.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            TextView[] ids = new TextView[myOrders.size()];
            TextView IDS[] = new TextView[myOrders.size()];
            TextView[] emails = new TextView[myOrders.size()];
            EditText[] updateStatus = new EditText[myOrders.size()];
            Button[] updateStatusBtn = new Button[myOrders.size()];

            // ButtonHandler approveBtn = new ButtonHandler();
            // ButtonHandler1 rejectBtn = new ButtonHandler1();


            TextView id = new TextView(this);
            id.setGravity(Gravity.CENTER);
            id.setText(" ");
            id.setTextSize(20);
            TextView id1 = new TextView(this);
            id1.setGravity(Gravity.CENTER);
            id1.setText(" ");
            TextView id2 = new TextView(this);
            id2.setGravity(Gravity.CENTER);
            id2.setText(" ");
            TextView id3 = new TextView(this);
            id3.setGravity(Gravity.CENTER);
            id3.setText(" ");


            TextView emailOwner = new TextView(this);
            emailOwner.setText("Orders Completed ");
            emailOwner.setTextColor(getResources().getColor(R.color.red));
            emailOwner.setTextSize(20);
            // retrieve width of screen
            Point size = new Point();
            getWindowManager().getDefaultDisplay().getSize(size);
            int width = size.x;
            int i = 0;

            grid.addView(id, (int) (width * 0.12), ViewGroup.LayoutParams.WRAP_CONTENT);
            grid.addView(emailOwner, (int) (width * 0.75),
                    ViewGroup.LayoutParams.WRAP_CONTENT);



            for (Order myOrder : myOrders) {
                ids[i] = new TextView(this);
                ids[i].setText("    ");
                // create the two EditTexts for the candy's name and price
                // dreate EditText for both name and price
                emails[i] = new TextView(this);
                double D1 = Double.parseDouble(myOrder.getPRICE());
                double D2 = Double.parseDouble(myOrder.getQUANTITY());
                double tax = D1 * 0.0825;
                double total = D1 + tax;

                emails[i].setText(+(i + 1) + " Garment:  " + myOrder.getGARMENT_TYPE() + "\n Cleaning Method: " + myOrder.getCLEANING_METHOD() + "\n Price: " + rate_prec.format(D1) + " (" + rate_prec.format(D1 / D2) + " EA)" + "\n Tax:" + rate_prec.format(tax) + "\n Total: " + rate_prec.format(total) + "\n QTY: " + myOrder.getQUANTITY() + "\n Status: " + myOrder.getSTATUS() + "\n Recieved on " + myOrder.getRECEIVED() + "\n Customer email: " + myOrder.getCUSTOMER_EMAIL() + "\n Reciept number: " + myOrder.getRECIEPTNUMBER());
                emails[i].setText("Garment: " + myOrder.getGARMENT_TYPE() + "\nCleaning Method: " +
                        myOrder.getCLEANING_METHOD() + "\nPrice: " + rate_prec.format(D1) + "\nTax: " + rate_prec.format(tax) + "\nTotal: " + rate_prec.format(total) + "\nQTY: " +
                        myOrder.getQUANTITY() + "\nStatus: " + myOrder.getSTATUS() + "\nRecieved on " +
                        myOrder.getRECEIVED()+"\nDelivered: "+myOrder.getDELIVERED() + "\nCustomer email: " + myOrder.getCUSTOMER_EMAIL() +
                        "\nReciept number: " + myOrder.getID());
                emails[i].setTextSize(18);
                Log.w("AdminUpdate", "****" + myOrder.getCUSTOMER_EMAIL() + myOrder.getCLEANING_METHOD());

                IDS[i] = new TextView(this);
                IDS[i].setText(" " + (i + 1));

                // add the elements to grid
                grid.addView(IDS[i], (int) (width * 0.09),
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                grid.addView(emails[i], (int) (width * 0.79),
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                grid.setUseDefaultMargins(true);
                grid.setBackground(getResources().getDrawable(R.drawable.border));
                i++;
            }

            // Add views
            scrollView.addView(grid);
            setContentView(scrollView);
        }
    }


}
