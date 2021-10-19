package com.example.quickwash;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class register extends AppCompatActivity {
    private DatabaseManager dbManager;


    //RadioButton rb;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_user);
        dbManager = new DatabaseManager(this);
    }
/**
    public void checkUserType(View v){
        int radioId = userTypeRG.getCheckedRadioButtonId();
        rb = findViewById(radioId);

        Toast.makeText(this, "Selected: " + rb.getText(), Toast.LENGTH_LONG).show();

    }*/

    public void submit(View v) {
        //email
        EditText email = findViewById(R.id.email);
        String emailString = email.getText().toString();

        //first name
        EditText fname = findViewById(R.id.fname);
        String fnameString = fname.getText().toString();

        //last name
        EditText lname = findViewById(R.id.lname);
        String lnameString = lname.getText().toString();

        //password
        EditText password = findViewById(R.id.reg_Password);
        String passwordString = password.getText().toString();


        RadioGroup userTypeRG = findViewById(R.id.radioUserTypeReg);
        RadioButton rb;

        int radioId = userTypeRG.getCheckedRadioButtonId();
        rb = findViewById(radioId);

        Toast.makeText(this, "Selected: " + rb.getText(), Toast.LENGTH_LONG).show();

        //EditText user = findViewById(R.id.userType);
        //String UserString  = user.getText().toString();

        if (emailString.equalsIgnoreCase(" ") || fnameString.equalsIgnoreCase(" ") ||
                lnameString.equalsIgnoreCase(" ") || passwordString.equalsIgnoreCase("")) {
            Toast.makeText(this, "Fields are empty", Toast.LENGTH_LONG).show();
        }
        if (rb.isChecked()) {


            userRegisteration myUserRegisteration = new userRegisteration(0, emailString,
                    fnameString, lnameString, passwordString, rb.getText().toString());
            boolean checkUser = dbManager.userCheck(myUserRegisteration, myUserRegisteration.getEmail(), myUserRegisteration.getUserType());
            if (checkUser) {
                Toast.makeText(this, emailString + " Already exists ", Toast.LENGTH_LONG).show();
            } else {
                dbManager.insertUser(myUserRegisteration);
                Toast.makeText(this, emailString + " account has created ", Toast.LENGTH_LONG).show();
                email.setText("");
                fname.setText("");
                lname.setText("");
                password.setText("");
                userTypeRG.clearCheck();
            }
        } else {
            Toast.makeText(this, " Invalid user type, try again ", Toast.LENGTH_LONG).show();
        }
    }

    public void goBack(View v) {
        this.finish();

    }

}
