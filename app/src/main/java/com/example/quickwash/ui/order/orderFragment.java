package com.example.quickwash.ui.order;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.quickwash.databinding.FragmentOrderBinding;
import android.widget.TextView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

public class orderFragment extends Fragment {

    private orderViewModel myorderViewModel;
    private FragmentOrderBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        myorderViewModel = new ViewModelProvider(this).get(orderViewModel.class);

        binding = FragmentOrderBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

       // final TextView textView = binding.textOrder;
      //  myorderViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
        //    @Override
          //  public void onChanged(@Nullable String s) {
            //    textView.setText(s);
           // }
        //});
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}

