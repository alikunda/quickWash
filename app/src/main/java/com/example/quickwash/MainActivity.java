package com.example.quickwash;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

/**
 * Login and register user page - class: user.java, userRegistration.java,
 * MainActivity.java, register.java activity_main.xml, register.xml, DatabaseManage.java
 */

public class MainActivity extends AppCompatActivity {
    private DatabaseManager dbManager;
    public static User myUser;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbManager = new DatabaseManager(this);
        boolean adminCheck = dbManager.checkIfAdminExist();  //*******//
        if(!adminCheck){ //*******//
            dbManager.insertUserManually("admin@admin.com","admin","owner","admin","admin","owner");

            //Toast.makeText(this, "admin created",Toast.LENGTH_LONG).show();  //*******//
            Log.w("testing main activity","****Created"); //*******//
        }
    }
    public void checkUser(View v){
        //username
        EditText userName = findViewById(R.id.user_name);
        String UserNameString = userName.getText().toString();

        //password
        EditText password = findViewById(R.id.password);
        String passwordString = password.getText().toString();
        //user type

        RadioGroup userTypeRG = findViewById(R.id.radioUserTypeHome);
        RadioButton rb;
        int radioId = userTypeRG.getCheckedRadioButtonId();
        rb = findViewById(radioId);

        Log.w("MainActivity.java","*****:"+UserNameString+"  "+passwordString);
        String adminStatus = dbManager.checkStat(UserNameString);


            /*
            EditText userType = findViewById(R.id.user_type);
            String userTypeString = userType.getText().toString();*/
        if(UserNameString.isEmpty()|| passwordString.isEmpty()){
            Toast.makeText(this, "Fields are empty",Toast.LENGTH_LONG).show();
        }
//            else if(UserNameString !="admin@admin.com" && rb.getText().toString().equals("Admin")){ //*******//
//                Toast.makeText(this, "Access denied!\nApproval pending.",Toast.LENGTH_LONG).show(); //*******//
//                Log.w("admin caps","*****"+rb.getText().toString()); //*******//
//            }
        else{

            boolean isDenied = dbManager.deniedEmail(UserNameString);
            myUser = new User(0, UserNameString, passwordString,rb.getText().toString());
            String uType = dbManager.checkingUser(UserNameString, passwordString, rb.getText().toString() ); //check user auth
            if(uType.equalsIgnoreCase("success")) {
                String fname = dbManager.fName(UserNameString);
                myUser.setFname(fname);
                String lname = dbManager.lName(UserNameString);
                myUser.setLname(lname);
                if(isDenied){

                }else {

                }
                if(uType.equalsIgnoreCase("success")) {
                    ProgressDialog progressDialog;
                    progressDialog = new ProgressDialog(MainActivity.this);
                    progressDialog.setMax(100);
                    progressDialog.setMessage("Please wait...");
                    progressDialog.setTitle("Logging in");
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
                }

                homePage(rb.getText().toString(),myUser);
                userName.setText("");
                password.setText("");
            }
            else{
                boolean checkTemp = dbManager.doesexist(myUser.getEmail());
                if(checkTemp == false){
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
                    builder1.setMessage("Account does not exists!\nDo you want to register?");
                    builder1.setCancelable(true);

                    builder1.setPositiveButton(
                            "Yes",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    Intent myIntent = new Intent(MainActivity.this, register.class);
                                    startActivity(myIntent);
                                    finish();
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
                else {
                    Toast.makeText(this, "Wrong credentials", Toast.LENGTH_LONG).show();
                }
            }
        }
    }


    public void homePage(String userType, User myUser) {
        if(userType.equalsIgnoreCase("admin")){

            if(myUser.getEmail().equals("admin@admin.com")){
                Intent myIntent = new Intent(this, adminPage.class);
                startActivity(myIntent);
                this.finish();
            }
            else {
                boolean temp = dbManager.deniedEmail(myUser.getEmail());
                Log.w("this", "^^^^^^^" + temp + " d");
                if (temp) {
                    Toast.makeText(this, "Approval Pending!", Toast.LENGTH_LONG).show();
                } else {
                    Intent myIntent = new Intent(this, adminPage.class);
                    startActivity(myIntent);
                    this.finish();
                }
            }
        }
        else {
            Intent myIntent = new Intent(this, side_menu.class);
            myIntent.putExtra("Name", myUser.getName());
            myIntent.putExtra("Email", myUser.getEmail());
            startActivity(myIntent);
            //this.finish();
        }
    }
    public void registerPage(View view){

        startActivity(new Intent(this, register.class));

    }

    public void reset(View view){
        //username
        EditText userName = findViewById(R.id.user_name);
        userName.setText("");

        //password
        EditText password = findViewById(R.id.password);
        password.setText("");
        RadioButton userTypeRG = findViewById(R.id.userButton);
        userTypeRG.setChecked(true);

        Toast.makeText(this, "Fields are cleared",Toast.LENGTH_SHORT).show(); //show toast message when fields are clears
    }

}