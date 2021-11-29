package com.example.quickwash;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class feedbackActivity extends AppCompatActivity {

    private DatabaseManager dbManager;
    public int i = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbManager = new DatabaseManager(this);
        updateView();
    }

    public void updateView() {

        ArrayList<RegisterFeed> myUsers = dbManager.showAllFeedback();

        RelativeLayout layout = new RelativeLayout((this));
        ScrollView scrollView = new ScrollView(this);


        TableLayout linearLayout = new TableLayout(this);
        for(RegisterFeed Users: myUsers) {
            TextView quote = new TextView(this);
            quote.setId(Users.getID());
            quote.setText(i + ".  Email: " + Users.getEmail()+"\n      FeedBack: "+Users.getFeedback()+"\n");
            quote.setTextSize(23);
            quote.setGravity(Gravity.CENTER);

            quote.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            linearLayout.addView(quote);
            i++;
        }

        // create a back button
        Button backButton = new Button(this);
        backButton.setText("Back");

        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                feedbackActivity.this.finish();
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
