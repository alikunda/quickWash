package com.example.quickwash.ui.checkOrder;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quickwash.DatabaseManager2;
import com.example.quickwash.MainActivity;
import com.example.quickwash.Order;
import com.example.quickwash.R;

import java.util.ArrayList;

public class CheckOrderActivity extends AppCompatActivity {

    private checkOrderViewModel checkOrderViewModel;
    //private @NonNull FragmentCheckOrderBinding binding;
    private DatabaseManager2 db = new DatabaseManager2(this);
    ArrayList<Order> orders;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_order);
        updateView();

    }

    public void updateView(){
        orders = db.checkStatus(MainActivity.myUser.getEmail());

        RelativeLayout relativeLayout = new RelativeLayout(this);
        ScrollView scrollView = new ScrollView(this);

        RadioGroup group = new RadioGroup(this);


        for (Order order: orders){
            RadioButton rb = new RadioButton(this);
            rb.setId(Integer.parseInt(order.getRECIEPTNUMBER()));
            rb.setText(order.toString());
            if(order.getSTATUS().equalsIgnoreCase("recieved")){
                rb.setBackgroundColor(Color.YELLOW);
            }
            else if(order.getSTATUS().equalsIgnoreCase("delivered")){
                rb.setBackgroundColor(Color.GREEN);
            }
            group.addView(rb);
        }

        scrollView.addView(group);
        relativeLayout.addView(scrollView);

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        params.addRule(RelativeLayout.CENTER_HORIZONTAL);
        params.setMargins(0, 0, 0, 50);

        setContentView(relativeLayout);
    }

}