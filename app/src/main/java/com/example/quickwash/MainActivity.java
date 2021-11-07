package com.example.quickwash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
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

            /*
            EditText userType = findViewById(R.id.user_type);
            String userTypeString = userType.getText().toString();*/

            if(UserNameString.equalsIgnoreCase(" ") || passwordString.equalsIgnoreCase(" ")){
                Toast.makeText(this, "Fields are empty",Toast.LENGTH_LONG).show();
            }else {
             myUser = new User(0, UserNameString, passwordString,rb.getText().toString());
            String uType = dbManager.checkingUser(UserNameString, passwordString, rb.getText().toString() ); //check user auth



                if(uType.equalsIgnoreCase("success")) {
                    String fname = dbManager.fName(UserNameString);
                    myUser.setFname(fname);
                    String lname = dbManager.lName(UserNameString);
                    myUser.setLname(lname);
                    Toast.makeText(this, "Success", Toast.LENGTH_LONG).show();
                       homePage(rb.getText().toString(),myUser);
                       userName.setText("");
                       password.setText("");
                       userTypeRG.clearCheck();

                }
                else{
                    Toast.makeText(this, "Wrong credentials", Toast.LENGTH_LONG).show();
                }
            }
        }


        public void homePage(String userType, User myUser) {
        if(userType.equalsIgnoreCase("admin")){
            Intent myIntent = new Intent(this, adminPage.class);
            startActivity(myIntent);
           // this.finish();
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