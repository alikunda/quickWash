package com.example.quickwash.ui.order;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.quickwash.Cart;
import com.example.quickwash.DatabaseManager2;
import com.example.quickwash.Garment.Garment;
import com.example.quickwash.Garment.GarmentFactory;
import com.example.quickwash.MainActivity;
import com.example.quickwash.Order;
import com.example.quickwash.R;
import com.example.quickwash.userProfileActivity;
import com.google.android.material.textfield.TextInputLayout;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

public class StartOrderActivity extends AppCompatActivity  {

    private orderViewModel myorderViewModel;
    private DatabaseManager2 dbManager2;
    private Garment garment;
    private GarmentFactory gf;
    //private ScriptGroup.Binding binding = new ScriptGroup.Binding()
    NumberFormat nf = NumberFormat.getCurrencyInstance();
    public final DecimalFormat rate_prec = new DecimalFormat("$0.00");
    double runningTotal;
    private int recieptNumber = 100001;
    ArrayList<Order> orders;
    public int react= 0;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_order);



        dbManager2 = new DatabaseManager2(this);
        gf = new GarmentFactory();



        TextView totalView = findViewById(R.id.total_tv);
        totalView.setText(nf.format(runningTotal));

        Button dryCleanButton = findViewById(R.id.dry_clean_button);
        Button laundryButton = findViewById(R.id.laundry_button);

        View laundryView = findViewById(R.id.laundry_layout);
        View paymentView = findViewById(R.id.payment_view);
        View garmentSelectView = findViewById(R.id.garment_select_view);

        //radio group
        RadioGroup cleaningMethodRG = findViewById(R.id.radio_cleaning_method);
        //int radioID = cleaningMethodRG.getCheckedRadioButtonId();


        TextInputLayout textInputLayout = findViewById(R.id.garment_type_dropdown);
        AutoCompleteTextView garmentTV = findViewById(R.id.garment_type_items);

        String [] garmentTypesDC = getResources().getStringArray(R.array.garment_type_DC);
        String [] garmentTypesLaundry = getResources().getStringArray(R.array.garment_type_Laundry);
        updateView();



        dryCleanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                garmentTV.setText(" ");

                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(StartOrderActivity.this,
                        R.layout.dropdown_item, garmentTypesDC);
                garmentTV.setAdapter(arrayAdapter);

                cleaningMethodRG.check(-1);

                laundryView.setVisibility(View.GONE);

                garmentSelectView.setVisibility(View.VISIBLE);

                paymentView.setVisibility(View.VISIBLE);
                react = 1;


            }
        });

        laundryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                garmentTV.setText(" ");
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(StartOrderActivity.this,
                        R.layout.dropdown_item, garmentTypesLaundry);
                garmentTV.setAdapter(arrayAdapter);

                cleaningMethodRG.check(R.id.l_starch);


                laundryView.setVisibility(View.VISIBLE);

                garmentSelectView.setVisibility(View.VISIBLE);
                paymentView.setVisibility(View.VISIBLE);

            }
        });

        garmentTV.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(StartOrderActivity.this, "SELECT CLEANING METHOD ABOVE", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(StartOrderActivity.this, "SELECT CLEANING METHOD ABOVE", Toast.LENGTH_LONG).show();
            }
        });




        /*
        List<String> garmentTypes = Arrays.asList(getResources().getStringArray(R.array.garment_type));
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(StartOrderActivity.this,
                R.layout.dropdown_item, garmentTypes);
        arrayAdapter.setDropDownViewResource(R.layout);*/

        //radio buttons dynamically rendered

        //String garmentTypeString = garmentTV.getText().toString();


    }


    public void updateView() {
//        TextView totalView = findViewById(R.id.total_tv);
//        totalView.setText(nf.format(runningTotal));

        AutoCompleteTextView garmentTV = findViewById(R.id.garment_type_items);
        garmentTV.clearListSelection();

        EditText quantityET = findViewById(R.id.garment_quantity_ET);
        quantityET.setText("1");

        //radio group
        RadioGroup cleaningMethodRG = findViewById(R.id.radio_cleaning_method);
        cleaningMethodRG.clearCheck();

        View paymentView = findViewById(R.id.payment_view);
        View garmentSelectView = findViewById(R.id.garment_select_view);

        paymentView.setVisibility(View.GONE);
        garmentSelectView.setVisibility(View.GONE);

        TextView totalView = findViewById(R.id.total_tv);
        totalView.setText(nf.format(runningTotal));




    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void addOrder(View v) {
        AutoCompleteTextView garmentTV = findViewById(R.id.garment_type_items);
        String[] garmentSelectArray = garmentTV.getText().toString().split(" - ");
        String garmentTypeString = garmentSelectArray[0];
        if(garmentTypeString.isEmpty()|| garmentTypeString == null || garmentTypeString.equals(" ")){
            Toast.makeText(this, "Select Garment type", Toast.LENGTH_SHORT).show();
        }
        else {
            String garmentPriceString = garmentSelectArray[1];
            double garmentPrice = Double.parseDouble(garmentPriceString.substring(1));


            EditText quantityET = findViewById(R.id.garment_quantity_ET);
            int quantity = Integer.parseInt(quantityET.getText().toString());

            //radio group
            RadioGroup cleaningMethodRG = findViewById(R.id.radio_cleaning_method);
            int radioID = cleaningMethodRG.getCheckedRadioButtonId();

            String cleaningMethodString;
            RadioButton cleaningMethodRB = findViewById(radioID);
            String garmentTVString = garmentTV.getText().toString();

            if (radioID == -1) {
                cleaningMethodString = "dry clean";

                Garment newGarment = gf.getGarment(garmentTypeString, cleaningMethodString, garmentPrice);//cleaning method

                runningTotal += newGarment.getPrice() * quantity;


                dbManager2.insertGarment(newGarment, quantity, "in cart", recieptNumber, MainActivity.myUser.getEmail());//cleaning method

                Toast.makeText(StartOrderActivity.this, quantity + " " + cleaningMethodString +
                        " " + garmentTypeString + ":  $" + rate_prec.format(garmentPrice) + " added to Cart", Toast.LENGTH_LONG).show();
                react=0;
            }
            else {

                cleaningMethodString = cleaningMethodRB.getText().toString().split(" ")[0];


                if(cleaningMethodString.equalsIgnoreCase("light")){
                    Garment newGarment = gf.getGarment(garmentTypeString, cleaningMethodString, garmentPrice);//cleaning method
                    garmentPrice = newGarment.getPrice()+.10;//light starch price
                    newGarment.setPrice(garmentPrice);
                    runningTotal += garmentPrice *quantity;
                    dbManager2.insertGarment(newGarment, quantity, "in cart", recieptNumber, MainActivity.myUser.getEmail());//cleaning method
                }
                else if(cleaningMethodString.equalsIgnoreCase("medium")){
                    Garment newGarment = gf.getGarment(garmentTypeString, cleaningMethodString, garmentPrice);//cleaning method
                    garmentPrice = newGarment.getPrice()+.20;//med starch price
                    newGarment.setPrice(garmentPrice);
                    runningTotal += garmentPrice *quantity;
                    dbManager2.insertGarment(newGarment, quantity, "in cart", recieptNumber, MainActivity.myUser.getEmail());//cleaning method
                }
                else if(cleaningMethodString.equalsIgnoreCase("heavy")){
                    Garment newGarment = gf.getGarment(garmentTypeString, cleaningMethodString, garmentPrice);//cleaning method
                    garmentPrice = newGarment.getPrice()+.30;//heavy starch price
                    newGarment.setPrice(garmentPrice);
                    runningTotal += garmentPrice *quantity;
                    dbManager2.insertGarment(newGarment, quantity, "in cart", recieptNumber, MainActivity.myUser.getEmail());//cleaning method
                }

                //runningTotal += newGarment.getPrice() * quantity;

                //dbManager2.insertGarment(newGarment, quantity, "in cart", recieptNumber, MainActivity.myUser.getEmail());//cleaning method

                Toast.makeText(StartOrderActivity.this, quantity + " " + cleaningMethodString +
                        " " + garmentTypeString + ":  $" + rate_prec.format(garmentPrice) + " added to Cart", Toast.LENGTH_LONG).show();

            }

            updateView();
        }
    }
    public void Checkout(View v ) {
        Intent myIntent = new Intent(this, Cart.class);
        startActivity(myIntent);
        recieptNumber++;

//        FragmentManager fragmentManager = getFragmentManager();
//
//        Fragment fragment = new Fragment(R.layout.fragment_gallery);
//        Bundle bundle = new Bundle();
//
//        fragment.setArguments(bundle);
//        fragmentManager.beginTransaction().replace(R.id.container, R.layout).commit();
//        Bundle bundle = new Bundle();
//        bundle.putString("my_key", "1");
//        GalleryFragment myFrag = new GalleryFragment();
//        myFrag.setArguments(bundle);,ne

//        Intent payIntent = new Intent(StartOrderActivity.this, GalleryFragment.class);
//        startActivity(payIntent);
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

    //menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.side_menu, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_userProfile) {
            Intent deleteIntent = new Intent(this, userProfileActivity.class);
            this.startActivity(deleteIntent);
            return true;
        }
        else if (id == R.id.action_cart) {
            Intent myIntent = new Intent(this,Cart.class);
            this.startActivity(myIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

