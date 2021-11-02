package com.example.quickwash.ui.order;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.quickwash.MainActivity;
import com.example.quickwash.databinding.FragmentOrderBinding;

import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

public class orderFragment extends Fragment  {

    private orderViewModel myorderViewModel;
    private FragmentOrderBinding binding;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        myorderViewModel = new ViewModelProvider(this).get(orderViewModel.class);

        binding = FragmentOrderBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
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
                    Toast.makeText(getActivity(), "No Input Detected", Toast.LENGTH_SHORT).show();
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
        super.onDestroyView();
        binding = null;
    }
}

