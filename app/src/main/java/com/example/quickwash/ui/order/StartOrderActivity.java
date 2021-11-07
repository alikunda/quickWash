package com.example.quickwash.ui.order;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quickwash.DatabaseManager;
import com.example.quickwash.R;
import com.google.android.material.textfield.TextInputLayout;

public class StartOrderActivity extends AppCompatActivity  {

    private orderViewModel myorderViewModel;
    private DatabaseManager dbManager2;
    //private ScriptGroup.Binding binding = new ScriptGroup.Binding()


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_order);

        TextInputLayout textInputLayout = findViewById(R.id.garment_type_dropdown);
        AutoCompleteTextView autoCompleteTextView = findViewById(R.id.garment_type_items);

        String [] garmentTypes = getResources().getStringArray(R.array.garment_type);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(StartOrderActivity.this,
                R.layout.dropdown_item, garmentTypes);
        autoCompleteTextView.setAdapter(arrayAdapter);
        /*
        List<String> garmentTypes = Arrays.asList(getResources().getStringArray(R.array.garment_type));
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(StartOrderActivity.this,
                R.layout.dropdown_item, garmentTypes);
        arrayAdapter.setDropDownViewResource(R.layout);*/

    }


    public void addOrder(View v) {
        AutoCompleteTextView garmentTV = findViewById(R.id.garment_type_items);
        String garmentTypeString = garmentTV.getText().toString();

        Toast.makeText(this, garmentTypeString+ ":  " + " added to order", Toast.LENGTH_LONG).show();

    }








    /*
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding.procced.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tshirtQty = binding.tQty.getText().toString();
                String pantQty = binding.pQty.getText().toString();
                //   String tshirtPrice = binding.tPrice.getText().toString();
                //   String pantPrice = binding.pQty.getText().toString();
                //     int tQty = Integer.parseInt(tshirtQty);
                //   int pQty = Integer.parseInt(pantQty);
                if(tshirtQty == "" || pantQty ==""){
                    Toast.makeText(this, "No Input Detected", Toast.LENGTH_SHORT).show();
                }
                //  int tPrice = Integer.parseInt(tshirtPrice);
                // int pPrice = Integer.parseInt(pantPrice);
             //
                Intent myIntent = new Intent(getActivity(), MainActivity.class);
                startActivity(myIntent);
            }
        });
    }
    @Override
    public void onDestroyView() {
        super.onDestroy();
        binding = null;
    }*/
}

