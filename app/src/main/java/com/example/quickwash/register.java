package com.example.quickwash;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.regex.Pattern;

public class register extends AppCompatActivity {
    private DatabaseManager dbManager;


    //RadioButton rb;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_user);
        dbManager = new DatabaseManager(this);

        Button myButton = findViewById(R.id.reset);

        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //username
        EditText email = findViewById(R.id.email);
        email.setText("");
        //first name
        EditText fname = findViewById(R.id.fname);
        fname.setText("");
        //last name
        EditText lname = findViewById(R.id.lname);
        lname.setText("");
        //password
        EditText password = findViewById(R.id.reg_Password);
        password.setText("");
        RadioButton userTypeRG = findViewById(R.id.userButton);
        userTypeRG.setChecked(true);
        Toast.makeText(register.this, "Fields are cleared",Toast.LENGTH_SHORT).show(); //show toast message when fields are clears
            }
        });
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


        //EditText user = findViewById(R.id.userType);
        //String UserString  = user.getText().toString();

        if (emailString.equalsIgnoreCase(" ") || fnameString.equalsIgnoreCase(" ") ||
                lnameString.equalsIgnoreCase(" ") || passwordString.equalsIgnoreCase("")) {
            Toast.makeText(this, "Fields are empty", Toast.LENGTH_LONG).show();
        }
        else if(rb.isChecked()) {
            if (passwordString.length() < 8) {
                Toast.makeText(this, "Password should be atleast 8 charaters", Toast.LENGTH_LONG).show();
            } else {
                boolean email_validation = isValidEmailId(emailString.trim());
                if (email_validation) {
                    userRegisteration myUserRegisteration = new userRegisteration(0, emailString,
                            fnameString, lnameString, passwordString, rb.getText().toString());
                    boolean checkUser = dbManager.userCheck(myUserRegisteration, myUserRegisteration.getEmail(), myUserRegisteration.getUserType());
                    if (checkUser) {
                        Toast.makeText(this, emailString + " Already exists ", Toast.LENGTH_LONG).show();
                    } else {
                        dbManager.insertUser(myUserRegisteration);
                        Toast.makeText(this, "Selected: " + rb.getText() + " & " + emailString + " has been registered ", Toast.LENGTH_LONG).show();
                        email.setText("");
                        fname.setText("");
                        lname.setText("");
                        password.setText("");
                        userTypeRG.clearCheck();
                    }
                    Intent myIntent = new Intent(this, MainActivity.class);
                    startActivity(myIntent);
                }
                else{
                    Toast.makeText(this, " Invalid Email! ", Toast.LENGTH_LONG).show();
                }
            }
            } else{
                Toast.makeText(this, " Invalid user type, try again ", Toast.LENGTH_LONG).show();
            }


    }
    private boolean isValidEmailId(String email){

        return Pattern.compile("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$").matcher(email).matches();
    }

    public void goBack(View v) {
        this.finish();

    }

}
