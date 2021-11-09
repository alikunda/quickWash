package com.example.quickwash;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.quickwash.DatabaseManager;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * This activity  allows admin to delete the user from database
 */

public class display_customers extends AppCompatActivity {

    private DatabaseManager dbManager;
    public int i = 1;

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


      TableLayout linearLayout = new TableLayout(this);
        for(userRegisteration Users: myUser) {
            TextView quote= new TextView(this);
            quote.setId(Users.getId());
            quote.setText(i + ". " + Users.toString()+"\n");
            quote.setTextSize(18);
            quote.setBackgroundColor(getResources().getColor(R.color.yellow));
            quote.setGravity(Gravity.CENTER);
            quote.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
             linearLayout.addView(quote);
            i++;
        }
//            String temp;
//        for(userRegisteration Users: myUser){
//            TextView textView = new TextView(this);
//            textView.setId(Users.getId());
//            textView.setText(+i+". "+Users.toString());
//
//        }

        // create a back button
        Button backButton = new Button(this);
        backButton.setText("Back");

        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                display_customers.this.finish();
            }
        });
        scrollView.addView(linearLayout);
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


}

