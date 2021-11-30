package com.example.quickwash.ui.history;

import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quickwash.DatabaseManager2;
import com.example.quickwash.MainActivity;
import com.example.quickwash.Order;
import com.example.quickwash.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {

    private historyViewModel historyViewModel;
   // private @NonNull FragmentHistoryBinding binding;
    private ArrayList<Order> orders;
    private DatabaseManager2 db;
    public final DecimalFormat rate_prec = new DecimalFormat("$0.00");



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);

        db = new DatabaseManager2(this);
        updateView();
    }

    public void updateView(){


        orders = db.selectUserOrderHistory(MainActivity.myUser.getEmail());
        if (orders.size() > 0) {
            // create ScrollView and GridLayout
            ScrollView scrollView = new ScrollView(this);
            // Use gridLayout
            GridLayout grid = new GridLayout(this);
            // set columns and rows
            grid.setRowCount(orders.size() + 1);
            grid.setColumnCount(2);


            // create arrays of components

            TextView quote = new TextView(this);
            quote.setText("Admins request for account");
            quote.setGravity(Gravity.CENTER);
            quote.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            TextView[] ids = new TextView[orders.size()];
            TextView[] emails = new TextView[orders.size()];
            Button[] approve = new Button[orders.size()];
            Button[] reject = new Button[orders.size()];
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

            TextView emailOwner = new TextView(this);
            emailOwner.setText("Order History");
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

            for (Order myOrder : orders) {

                // create the TextView for the candy's id
                ids[i] = new TextView(this);
                ids[i].setGravity(Gravity.CENTER);
                ids[i].setText("" + (i + 1));

                // create the two EditTexts for the candy's name and price
                // dreate EditText for both name and price
                emails[i] = new TextView(this);
                                double D1 = Double.parseDouble(myOrder.getPRICE());
                double D2 = Double.parseDouble(myOrder.getQUANTITY());
                double tax = D1 * 0.0825;
                double total  = D1+tax;

             //   emails[i].setText(+(i+1)+" Garment:  "+myOrder.getGARMENT_TYPE()+"\n Cleaning Method: "+myOrder.getCLEANING_METHOD()+"\n Price: "+rate_prec.format(D1)+" ("+rate_prec.format(D1/D2)+" EA)"+"\n Tax:"+rate_prec.format(tax)+"\n Total: "+rate_prec.format(total)+"\n QTY: "+myOrder.getQUANTITY()+"\n Status: "+myOrder.getSTATUS()+"\n Recieved on "+myOrder.getRECEIVED()+"\n Customer email: "+myOrder.getCUSTOMER_EMAIL()+"\n Reciept number: "+myOrder.getRECIEPTNUMBER());
                if(myOrder.getCLEANING_METHOD().equals("Light")) {
                    emails[i].setText("Garment: " + myOrder.getGARMENT_TYPE() + "\nCleaning Method: " + myOrder.getCLEANING_METHOD() + "\nPrice: " +myOrder.getPRICE() + " (" + rate_prec.format((D1 / D2)-0.10) + " EA + 0.10)" +"\nExtra Charge for light starch: $0.10"+"\nTAX: " + rate_prec.format(tax) + "\nTotal: " + rate_prec.format(total) + "\nQTY: " + myOrder.getQUANTITY()+ "\nStatus: " + myOrder.getSTATUS() + "\nRecieved on " + myOrder.getRECEIVED()+"\nDelivered: "+ myOrder.getDELIVERED() + "\nCustomer email: " + myOrder.getCUSTOMER_EMAIL() +"\nReciept number: " + myOrder.getID());
                } else if(myOrder.getCLEANING_METHOD().equals("Medium")){
                    emails[i].setText("Garment: " + myOrder.getGARMENT_TYPE() + "\nCleaning Method: " + myOrder.getCLEANING_METHOD() + "\nPrice: " +myOrder.getPRICE()  + " (" + rate_prec.format((D1 / D2)-0.20) + " EA + 0.20)" +"\nExtra Charge for medium starch: $0.20"+"\nTAX: " + rate_prec.format(tax) + "\nTotal: " + rate_prec.format(total) + "\nQTY: " + myOrder.getQUANTITY()+ myOrder.getQUANTITY()+ "\nStatus: " + myOrder.getSTATUS() + "\nRecieved on " + myOrder.getRECEIVED()+"\nDelivered: "+ myOrder.getDELIVERED() + "\nCustomer email: " + myOrder.getCUSTOMER_EMAIL() +"\nReciept number: " + myOrder.getID());
                }
                else if(myOrder.getCLEANING_METHOD().equals("Heavy")){
                    emails[i].setText("Garment: " + myOrder.getGARMENT_TYPE() + "\nCleaning Method: " + myOrder.getCLEANING_METHOD() + "\nPrice: "+myOrder.getPRICE()  + " (" + rate_prec.format((D1 / D2)-0.30) + " EA + 0.30)" +"\nExtra Charge for heavy starch: $0.30"+"\nTAX: " + rate_prec.format(tax) + "\nTotal: " + rate_prec.format(total) + "\nQTY: " + myOrder.getQUANTITY() + myOrder.getQUANTITY()+ "\nStatus: " + myOrder.getSTATUS() + "\nRecieved on " + myOrder.getRECEIVED()+"\nDelivered: "+ myOrder.getDELIVERED() + "\nCustomer email: " + myOrder.getCUSTOMER_EMAIL() +"\nReciept number: " + myOrder.getID());
                }
                else {
                    emails[i].setText("Garment: " + myOrder.getGARMENT_TYPE() + "\nCleaning Method: " + myOrder.getCLEANING_METHOD() + "\nPrice: " + myOrder.getPRICE() + " (" + rate_prec.format(D1 / D2) + " EA)" + "\nTAX: " + rate_prec.format(tax) + "\nTotal: " + rate_prec.format(total) + "\nQTY: " + myOrder.getQUANTITY() + myOrder.getQUANTITY()+ "\nStatus: " + myOrder.getSTATUS() + "\nRecieved on " + myOrder.getRECEIVED()+"\nDelivered: "+ myOrder.getDELIVERED() + "\nCustomer email: " + myOrder.getCUSTOMER_EMAIL() +"\nReciept number: " + myOrder.getID());
                }
//                emails[i].setText("Garment: " + myOrder.getGARMENT_TYPE() + "\nCleaning Method: " +
//                        myOrder.getCLEANING_METHOD() + "\nPrice: " + myOrder.getPRICE() + "\nQTY: " +
//                        myOrder.getQUANTITY() + "\nStatus: " + myOrder.getSTATUS() + "\nRecieved on " + myOrder.getRECEIVED()+"\nDelivered: "+ myOrder.getDELIVERED() + "\nCustomer email: " + myOrder.getCUSTOMER_EMAIL() +
//                        "\nReciept number: " + myOrder.getID());
                Log.w("AdminUpdate", "****" + myOrder.getCUSTOMER_EMAIL() + myOrder.getCLEANING_METHOD());

                // add the elements to grid
                grid.addView(ids[i], width / 15, ViewGroup.LayoutParams.WRAP_CONTENT);
                grid.addView(emails[i], (int) (width * 0.65),
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                grid.setPadding(1, 30, 1, 90);
                grid.setUseDefaultMargins(true);


                i++;
            }

            // Add views
            scrollView.addView(grid);
            setContentView(scrollView);

//        orders = db.selectAllNewOrders();
//        RelativeLayout layout = new RelativeLayout(this);
//        ScrollView scrollView = new ScrollView(this);
//
//        TableRow row = new TableRow(this);
//        for (Order order: orders){
//            RadioButton rb = new RadioButton(this);
//            rb.setId(Integer.parseInt(order.getRECIEPTNUMBER()));
//            rb.setText(order.toString());
//            row.addView(rb);
//        }
//
//        scrollView.addView(row);
//        layout.addView(scrollView);
        }

    }


}