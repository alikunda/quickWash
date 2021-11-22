package com.example.quickwash.ui.gallery;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.quickwash.DatabaseManager;
import com.example.quickwash.MainActivity;
import com.example.quickwash.R;
import com.example.quickwash.databinding.FragmentGalleryBinding;
import com.example.quickwash.register;

import java.util.zip.Inflater;

public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;
    private FragmentGalleryBinding binding;
    private DatabaseManager dbManager;
    private int tracker = 0;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);
        dbManager = new DatabaseManager(getActivity());

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;

    }
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String name  = getArguments().getString("my_key");

        tracker= Integer.parseInt(name);
        if(tracker ==1){
            Button payButton = binding.add.findViewById(R.id.add);
            payButton.setText("Pay");
            tracker = 0;
        }
        String checking  = dbManager.is_user_exist(MainActivity.myUser.getEmail());
        if(checking == "true"){
            String Name = dbManager.sendName(MainActivity.myUser.getEmail());
            String AC_num = dbManager.sendacc(MainActivity.myUser.getEmail());
            String my = dbManager.sendMMYY(MainActivity.myUser.getEmail());
            String cv = dbManager.sendCVV(MainActivity.myUser.getEmail());
            //set the field to tha information already stored in the database for credit card
            binding.nameHolder.setText(Name);
            binding.accountNumber.setText(AC_num);
            binding.mmyy.setText(my);
            binding.accountCvv.setText(cv);
        }

        binding.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = binding.nameHolder.getText().toString();
                String AC = binding.accountNumber.getText().toString();
                String month_year = binding.mmyy.getText().toString();
                String cvv = binding.accountCvv.getText().toString();
                String email = MainActivity.myUser.getEmail();

                if (name.isEmpty() || AC.isEmpty() || month_year.isEmpty()|| cvv.isEmpty()) {
                    Toast.makeText(getActivity(), "Some of the fields are empty!", Toast.LENGTH_SHORT).show();
                } else if (AC.length() < 16 || month_year.length() < 4 || cvv.length() < 3) {
                    Toast.makeText(getActivity(), "Invalid input!,\nmay missing a number?", Toast.LENGTH_SHORT).show();
                } else {
                    long accountNum = Long.parseLong(AC);
                    int MMYY = Integer.parseInt(month_year);
                    int CVV = Integer.parseInt(cvv);
                    Log.w("MainActivity", "******* "+email);
                   String checking_for_dup = dbManager.check_if_exist(name, accountNum, MMYY, CVV, email);
                   Log.w("Gallery","******"+checking_for_dup);
                    if (checking_for_dup =="true") {
                        Toast.makeText(getActivity(), "Payement Info Already Exists", Toast.LENGTH_SHORT).show();
                    } else {
                        dbManager.insertPayement(name, accountNum,MMYY, CVV,email);  //inserting payment method in DB
                        Toast.makeText(getActivity(), "Payement Added Sucessfully!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        //deleting payement info
        binding.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name1 = binding.nameHolder.getText().toString();
                String AC1 = binding.accountNumber.getText().toString();
                 String month_year1 = binding.mmyy.getText().toString();
                 String cvv1 = binding.accountCvv.getText().toString();

                if (name1.isEmpty() || AC1.isEmpty() || month_year1.isEmpty() || cvv1.isEmpty()) {
                    Toast.makeText(getActivity(), "Some of the fields are empty!", Toast.LENGTH_SHORT).show();
                } else {
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(getContext());
                    builder1.setMessage("Are you sure you want to delete the payment information");
                    builder1.setCancelable(true);

                    builder1.setPositiveButton(
                            "Yes",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    String email = MainActivity.myUser.getEmail();
                                    dbManager.DeletePayment(email);
                                    Toast.makeText(getActivity(), "Payement info deleted!", Toast.LENGTH_LONG).show();
                                    binding.nameHolder.setText("");
                                    binding.accountNumber.setText("");
                                    binding.mmyy.setText("");
                                    binding.accountCvv.setText("");
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
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}