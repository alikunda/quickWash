package com.example.quickwash;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {
    private DatabaseManager dbManager;
    public static User myUser;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbManager = new DatabaseManager(this);
        setContentView(R.layout.admin_profile);
        updateView();

        EditText fnameET = findViewById(R.id.Editfname);
        String temp1 = dbManager.fName(MainActivity.myUser.getEmail());
        fnameET.setText(temp1);

        EditText lnameET = findViewById(R.id.Editlname);
        String temp2 = dbManager.lName(MainActivity.myUser.getEmail());
        lnameET.setText(temp2);

        TextView emailET = findViewById(R.id.editEmail);
        emailET.setText(MainActivity.myUser.getEmail());

        EditText passwordET = findViewById(R.id.Editpass);
        String temp3 = dbManager.password(MainActivity.myUser.getEmail());
        passwordET.setText(temp3);

        Button myButton = findViewById(R.id.back);
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(ProfileActivity.this, adminPage.class);
                startActivity(myIntent);
            }
        });
    }

    // Build a View dynamically with all the candies
    @SuppressWarnings("deprecation")

    public void updateView() {


        Button myButton = findViewById(R.id.update);

        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText fnameET = findViewById(R.id.Editfname);
                EditText lnameET = findViewById(R.id.Editlname);
                TextView email = findViewById(R.id.editEmail);
                EditText passwordET = findViewById(R.id.Editpass);
                String fname = fnameET.getText().toString();
                String lname = lnameET.getText().toString();
                email.setText(MainActivity.myUser.getEmail());
                String password = passwordET.getText().toString();

                if(passwordET.length()<8){
                    Toast.makeText(ProfileActivity.this,"Password must be atleast 8 characters",Toast.LENGTH_LONG).show();
                }else {
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(ProfileActivity.this);
                    builder1.setMessage("Are you sure you want to make changes?");
                    builder1.setCancelable(true);
                    builder1.setPositiveButton(
                            "Yes",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    // update candy in database
                                    try {
                                        MainActivity.myUser.setFname(fname);
                                        dbManager.updateByIdAdmin(fname, lname, MainActivity.myUser.getEmail(), password);
                                        MainActivity.myUser.setLname(lname);
                                        MainActivity.myUser.setPassword(password);

                                        Toast.makeText(ProfileActivity.this, "Credentials updated", Toast.LENGTH_LONG).show();
                                        updateView();

                                        // update screen

                                    } catch (NumberFormatException nfe) {
                                        Toast.makeText(ProfileActivity.this, " error", Toast.LENGTH_LONG).show();
                                    }
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




