package com.example.quickwash;

import android.content.Intent;
import android.graphics.Point;
import android.os.Build;
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

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Admin control page
 */

public class adminPage extends AppCompatActivity {

    private DatabaseManager dbManager;
    private DatabaseManager2 dbManager1;
    public static User myUser;
    public final DecimalFormat rate_prec = new DecimalFormat("$0.00");
    ArrayList<Order> myOrders;
    String ETUpdateSatus = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.admin_page);
        dbManager = new DatabaseManager(this);
        dbManager1 = new DatabaseManager2(this);
        updateView();
    }
    public void updateView() {
        myOrders = dbManager1.selectAllNewOrders();
        if (myOrders.size() > 0) {
            // create ScrollView and GridLayout
            ScrollView scrollView = new ScrollView(this);
            // Use gridLayout
            GridLayout grid = new GridLayout(this);
            // set columns and rows
            grid.setRowCount(myOrders.size()+1);
            grid.setColumnCount(3);


            // create arrays of components

            TextView quote= new TextView(this);
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
            id3.setText(" ");
            TextView id4  = new TextView(this);
            id4.setGravity(Gravity.CENTER);
            id4.setText(" ");
            TextView id5  = new TextView(this);
            id5.setGravity(Gravity.CENTER);
            id5.setText(" ");
            TextView id6  = new TextView(this);
            id6.setGravity(Gravity.CENTER);
            id6.setText(" ");

            TextView emailOwner = new TextView(this);
            emailOwner.setText("Pending orders ");
            emailOwner.setTextColor(getResources().getColor(R.color.red));
            emailOwner.setTextSize(20);
            // retrieve width of screen
            Point size = new Point();
            getWindowManager().getDefaultDisplay().getSize(size);
            int width = size.x;
            int i = 0;


            grid.addView(emailOwner, (int) (width * 0.35),
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            grid.addView(id3, (int) (width * 0.10), ViewGroup.LayoutParams.WRAP_CONTENT);
            grid.addView(id, (int) (width * 0.10), ViewGroup.LayoutParams.WRAP_CONTENT);


            for ( Order myOrder : myOrders) {

                // create the two EditTexts for the candy's name and price
                // dreate EditText for both name and price
                emails[i]= new TextView(this);
                double D1 = Double.parseDouble(myOrder.getPRICE());
                double D2 = Double.parseDouble(myOrder.getQUANTITY());
                double tax = D1 * 0.0825;
                double total  = D1+tax;

                emails[i].setText(+(i+1)+" Garment:  "+myOrder.getGARMENT_TYPE()+"\n Cleaning Method: "+myOrder.getCLEANING_METHOD()+"\n Price: "+rate_prec.format(D1)+" ("+rate_prec.format(D1/D2)+" EA)"+"\n Tax:"+rate_prec.format(tax)+"\n Total: "+rate_prec.format(total)+"\n QTY: "+myOrder.getQUANTITY()+"\n Status: "+myOrder.getSTATUS()+"\n Recieved on "+myOrder.getRECEIVED()+"\n Customer email: "+myOrder.getCUSTOMER_EMAIL()+"\n Reciept number: "+myOrder.getRECIEPTNUMBER());
                emails[i].setText("Garment: "+myOrder.getGARMENT_TYPE()+"\nCleaning Method: "+
                        myOrder.getCLEANING_METHOD()+"\nPrice: "+D1+"\nTax: "+tax+"\nTotal: "+total+"\nQTY: "+
                        myOrder.getQUANTITY()+"\nStatus: "+myOrder.getSTATUS()+"\nRecieved on "+
                        myOrder.getRECEIVED()+"\n Customer email: "+myOrder.getCUSTOMER_EMAIL()+
                        "\n Reciept number: "+myOrder.getID());
                Log.w("AdminUpdate","****"+myOrder.getCUSTOMER_EMAIL()+myOrder.getCLEANING_METHOD());

                IDS[i] = new TextView(this);
                IDS[i].setText(" ");

                updateStatus[i]=new EditText(this);
                updateStatus[i].setHint("Update Status");
                updateStatus[i].setTextSize(12);



                updateStatusBtn[i] = new Button(this);
                updateStatusBtn[i].setBackground(getResources().getDrawable(R.drawable.ic_updating_foreground));
                int finalI = i;
                updateStatusBtn[i].setOnClickListener(new View.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void onClick(View view) {
                        ETUpdateSatus = updateStatus[finalI].getText().toString();
                      if(ETUpdateSatus.isEmpty()){

                          Toast.makeText(adminPage.this,"Field is empty",Toast.LENGTH_SHORT).show();
                      }
                      else if(myOrders.isEmpty()){
                          Intent myIntent = new Intent(adminPage.this, adminPage.class);
                          startActivity(myIntent);
                      }
                      else{
                          dbManager1.updateStatus(myOrder.getCUSTOMER_EMAIL(),ETUpdateSatus,myOrder.getRECEIVED(),myOrder.getGARMENT_TYPE());
                          Toast.makeText(adminPage.this,"Updated",Toast.LENGTH_SHORT).show();
                      }
                        updateView();
                    }
                });
                // add the elements to grid
                grid.addView(emails[i], (int) (width * 0.49),
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                grid.addView(updateStatus[i], (int) (width * 0.29),
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                grid.addView(updateStatusBtn[i], (int) (width * 0.19),
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
        else if(id == R.id.action_orders_done){
            Intent myIntent = new Intent(this, displayAllOrdersAdmin.class);
            startActivity(myIntent);
            this.finish();
        }
        else if(id == R.id.action_logout){
            Intent myIntent = new Intent(this, MainActivity.class);
            startActivity(myIntent);
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

}