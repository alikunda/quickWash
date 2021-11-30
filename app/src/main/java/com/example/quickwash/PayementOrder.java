package com.example.quickwash;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;


public class PayementOrder extends AppCompatActivity {

    private DatabaseManager dbManager;
    private DatabaseManager2 dbManager2;
    ArrayList<Order> orders;
    private int tracker = 0;
    public int count= 100000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payement);
        dbManager = new DatabaseManager(this);
        dbManager2 = new DatabaseManager2(this);
        updateView();
    }
    public void updateView(){

    String checking  = dbManager.is_user_exist(MainActivity.myUser.getEmail());
        if(checking == "true"){
        String Name = dbManager.sendName(MainActivity.myUser.getEmail());
        String AC_num = dbManager.sendacc(MainActivity.myUser.getEmail());
        String my = dbManager.sendMMYY(MainActivity.myUser.getEmail());
        String cv = dbManager.sendCVV(MainActivity.myUser.getEmail());
        //set the field to tha information already stored in the database for credit card
            EditText et1 = findViewById(R.id.name_holder);
            EditText et2 = findViewById(R.id.account_number);
            EditText et3 = findViewById(R.id.mmyy);
            EditText et4 = findViewById(R.id.account_cvv);


        et1.setText(Name);
        et2.setText(AC_num);
        et3.setText(my);
        et4.setText(cv);
    }

        Button add = findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        public void onClick(View view) {
            EditText nameHolder = findViewById(R.id.name_holder);
            EditText accountNumber = findViewById(R.id.account_number);
            EditText mmyy = findViewById(R.id.mmyy);
            EditText accountCvv = findViewById(R.id.account_cvv);
            String name = nameHolder.getText().toString();
            String AC = accountNumber.getText().toString();
            String month_year = mmyy.getText().toString();
            String cvv = accountCvv.getText().toString();
            String email = MainActivity.myUser.getEmail();

            if (name.isEmpty() || AC.isEmpty() || month_year.isEmpty()|| cvv.isEmpty()) {
                Toast.makeText(PayementOrder.this, "Some of the fields are empty!", Toast.LENGTH_SHORT).show();
            } else if (AC.length() < 16 || month_year.length() < 4 || cvv.length() < 3) {
                Toast.makeText(PayementOrder.this, "Invalid input!,\nmay missing a number?", Toast.LENGTH_SHORT).show();
            } else {
                long accountNum = Long.parseLong(AC);
                int MMYY = Integer.parseInt(month_year);
                int CVV = Integer.parseInt(cvv);
                Log.w("MainActivity", "******* "+email);
                String checking_for_dup = dbManager.check_if_exist(name, accountNum, MMYY, CVV, email);
                Log.w("Gallery","******"+checking_for_dup);
                if (checking_for_dup =="true") {
                    ProgressDialog progressDialog;
                    progressDialog = new ProgressDialog(PayementOrder.this);
                    progressDialog.setMax(100);
                    progressDialog.setMessage("Please wait...");
                    progressDialog.setTitle("Processing Payment");
                    progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    progressDialog.show();
                    final Handler handle = new Handler() {
                        @Override
                        public void handleMessage(Message msg) {
                            super.handleMessage(msg);
                            progressDialog.incrementProgressBy(1);
                        }
                    };
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                while (progressDialog.getProgress() <= progressDialog
                                        .getMax()) {
                                    Thread.sleep(30);
                                    handle.sendMessage(handle.obtainMessage());
                                    if (progressDialog.getProgress() == progressDialog
                                            .getMax()) {
                                        progressDialog.dismiss();
                                    }

                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                    Toast.makeText(PayementOrder.this, "Payement made Sucessfully!", Toast.LENGTH_SHORT).show();
                    orders = dbManager2.selectAllPendingOrders(MainActivity.myUser.getEmail());

                    count = Integer.parseInt(orders.get(0).getRECIEPTNUMBER())+1;
                    for ( Order myOrder : orders) {
                        //  dbManager2.insertGarment(newGarment, quantity, "in cart", recieptNumber, MainActivity.myUser.getEmail());//cleaning method
                        //(String garmentName, String cleaningMethod, int price, int quantity, String status, int recieptNum, String email)

                        double price = Double.parseDouble(myOrder.getPRICE());
                        double qty = Double.parseDouble(myOrder.getQUANTITY());
                        dbManager2.insertGarmentInOrder(myOrder.getGARMENT_TYPE(),myOrder.getCLEANING_METHOD(),price, qty,"recieved",count,myOrder.getCUSTOMER_EMAIL());
                        Log.w("Payment print ","*****"+myOrder.getGARMENT_TYPE()+" "+myOrder.getCLEANING_METHOD()+" "+price+" "+qty+" "+count+" "+myOrder.getCUSTOMER_EMAIL());
                        //count++;
                        dbManager2.deleteItem(myOrder.getCLEANING_METHOD(),myOrder.getGARMENT_TYPE(),myOrder.getQUANTITY(),myOrder.getRECEIVED(), myOrder.getRECIEPTNUMBER());
                    }

                    Intent myIntent = new Intent(PayementOrder.this, OrderPlacedActivty.class);
                    startActivity(myIntent);
                    finish();

                } else {
                    ProgressDialog progressDialog;
                    progressDialog = new ProgressDialog(PayementOrder.this);
                    progressDialog.setMax(100);
                    progressDialog.setMessage("Please wait...");
                    progressDialog.setTitle("Processing Payment");
                    progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    progressDialog.show();
                    final Handler handle = new Handler() {
                        @Override
                        public void handleMessage(Message msg) {
                            super.handleMessage(msg);
                            progressDialog.incrementProgressBy(1);
                        }
                    };
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                while (progressDialog.getProgress() <= progressDialog
                                        .getMax()) {
                                    Thread.sleep(300);
                                    handle.sendMessage(handle.obtainMessage());
                                    if (progressDialog.getProgress() == progressDialog
                                            .getMax()) {
                                        progressDialog.dismiss();
                                    }

                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();

                    dbManager.insertPayement(name, accountNum,MMYY, CVV,email);  //inserting payment method in DB
                    orders = dbManager2.selectAllPendingOrders(MainActivity.myUser.getEmail());

                    count = Integer.parseInt(orders.get(0).getRECIEPTNUMBER())+1;
                    for ( Order myOrder : orders) {

                      //  dbManager2.insertGarment(newGarment, quantity, "in cart", recieptNumber, MainActivity.myUser.getEmail());//cleaning method
                        //(String garmentName, String cleaningMethod, int price, int quantity, String status, int recieptNum, String email)
                        double price = Double.parseDouble(myOrder.getPRICE());
                        double qty = Double.parseDouble(myOrder.getQUANTITY());
                        dbManager2.insertGarmentInOrder(myOrder.getGARMENT_TYPE(),myOrder.getCLEANING_METHOD(),price, qty,"recieved",count,myOrder.getCUSTOMER_EMAIL());
                        //count++;
                        dbManager2.deleteItem(myOrder.getCLEANING_METHOD(),myOrder.getGARMENT_TYPE(),myOrder.getQUANTITY(),myOrder.getRECEIVED(), myOrder.getRECIEPTNUMBER());
                    }
                    count++;

                    Intent myIntent = new Intent(PayementOrder.this, OrderPlacedActivty.class);
                    startActivity(myIntent);
                    finish();
                }
            }
        }
    });
    //deleting payement info
        Button delete = findViewById(R.id.delete);
        delete.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            EditText nameHolder = findViewById(R.id.name_holder);
            EditText accountNumber = findViewById(R.id.account_number);
            EditText mmyy = findViewById(R.id.mmyy);
            EditText accountCvv = findViewById(R.id.account_cvv);
            String name1 = nameHolder.getText().toString();
            String AC1 = accountNumber.getText().toString();
            String month_year1 = mmyy.getText().toString();
            String cvv1 = accountCvv.getText().toString();

            if (name1.isEmpty() || AC1.isEmpty() || month_year1.isEmpty() || cvv1.isEmpty()) {
                Toast.makeText(PayementOrder.this, "Some of the fields are empty!", Toast.LENGTH_SHORT).show();
            } else {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(PayementOrder.this);
                builder1.setMessage("Are you sure you want to delete the payment information");
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                String email = MainActivity.myUser.getEmail();
                                dbManager.DeletePayment(email);
                                Toast.makeText(PayementOrder.this, "Payement info deleted!", Toast.LENGTH_LONG).show();
                                EditText nameHolder = findViewById(R.id.name_holder);
                                EditText accountNumber = findViewById(R.id.account_number);
                                EditText mmyy = findViewById(R.id.mmyy);
                                EditText accountCvv = findViewById(R.id.account_cvv);
                                nameHolder.setText("");
                                accountNumber.setText("");
                                mmyy.setText("");
                                accountCvv.setText("");
                            }
                        });
                builder1.setNegativeButton(
                        "No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert11 = builder1.create();
                alert11.show();
            }
        }
    });
}

}
