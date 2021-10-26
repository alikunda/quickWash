package com.example.quickwash.ui.order;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.quickwash.R;
import com.example.quickwash.databinding.FragmentOrderBinding;

public class orderFragment extends Fragment {

    private orderViewModel myorderViewModel;
    private FragmentOrderBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        myorderViewModel = new ViewModelProvider(this).get(orderViewModel.class);

        binding = FragmentOrderBinding.inflate(inflater, container, false);


        String[] clothing_types = getResources().getStringArray(R.array.clothing_types);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter(requireContext(), R.layout.dropdown_item, clothing_types);
        binding.autoCompleteTextView.setAdapter(arrayAdapter);

       // final TextView textView = binding.textOrder;
      //  myorderViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
        //    @Override
          //  public void onChanged(@Nullable String s) {
            //    textView.setText(s);
           // }
        //});
        View root = binding.getRoot();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}

