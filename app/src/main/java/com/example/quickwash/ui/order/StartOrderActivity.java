package com.example.quickwash.ui.order;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quickwash.DatabaseManager2;
import com.example.quickwash.Garment.Garment;
import com.example.quickwash.Garment.GarmentFactory;
import com.example.quickwash.R;
import com.google.android.material.textfield.TextInputLayout;

public class StartOrderActivity extends AppCompatActivity  {

    private orderViewModel myorderViewModel;
    private DatabaseManager2 dbManager2;
    private Garment garment;
    private GarmentFactory gf;
    //private ScriptGroup.Binding binding = new ScriptGroup.Binding()


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_order);

        dbManager2 = new DatabaseManager2(this);



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

        EditText quantityET = findViewById(R.id.garment_quantity_ET);
        int quantity = Integer.parseInt(quantityET.getText().toString());

        //radio group
        RadioGroup cleaningMethodRG = findViewById(R.id.radio_cleaning_method);
        int radioID = cleaningMethodRG.getCheckedRadioButtonId();

        RadioButton cleaningMethodRB = findViewById(radioID);
        String cleaningMethodString = cleaningMethodRB.getText().toString();


        //gf = new GarmentFactory();

        //Garment newGarment = gf.getGarment(garmentTypeString);//cleaning method


        //dbManager2.insertGarment(newGarment, quantity);//cleaning method



        Toast.makeText(this,  quantity + " " + garmentTypeString + ":  " +
                cleaningMethodString + " added to order", Toast.LENGTH_LONG).show();

    }

    public void pay(View v ) {

        Toast.makeText(this, "payment button is clicked", Toast.LENGTH_LONG).show();

        //Intent payIntent = new Intent(this, payment.class);
        //startActivity(payIntent);

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

