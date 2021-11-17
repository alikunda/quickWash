package com.example.quickwash;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.quickwash.DatabaseManager;

import java.util.ArrayList;

/**
 * This activity  allows admin to delete the user from database
 */

public class DeleteActivity extends AppCompatActivity {

    private DatabaseManager dbManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbManager = new DatabaseManager(this);
        updateView();
    }
    // Build a View dynamically with all the candies
    public void updateView() {

        ArrayList<userRegisteration> myUser = dbManager.selectAll();

        RelativeLayout layout = new RelativeLayout((this));
        ScrollView scrollView = new ScrollView(this);

        RadioGroup group = new RadioGroup((this));
        for(userRegisteration Users: myUser){
            RadioButton rb = new RadioButton(this);
            rb.setId(Users.getId());
            rb.setText(Users.toString());
            group.addView(rb);
        }

        // set up event handling
        RadioButtonHandler rbh = new RadioButtonHandler();
        group.setOnCheckedChangeListener(rbh);

        // create a back button
        Button backButton = new Button(this);
        backButton.setText("Back");

        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                DeleteActivity.this.finish();
            }
        });
        scrollView.addView(group);
        layout.addView(scrollView);

        // add view here


        // add back button at bottom
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        params.addRule(RelativeLayout.CENTER_HORIZONTAL);
        params.setMargins(0, 0, 0, 50);
        layout.addView(backButton, params);

        setContentView(layout);
    }

    private class RadioButtonHandler implements RadioGroup.OnCheckedChangeListener {
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            AlertDialog.Builder builder1 = new AlertDialog.Builder(DeleteActivity.this);
            builder1.setMessage("Are you Sure you want to delete user?");
            builder1.setCancelable(true);

            builder1.setPositiveButton(
                    "Yes",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            //delete candy from database
                            dbManager.deleteById(checkedId);
                            Toast.makeText(DeleteActivity.this, "User Deleted", Toast.LENGTH_SHORT).show();

                            updateView();
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

}
