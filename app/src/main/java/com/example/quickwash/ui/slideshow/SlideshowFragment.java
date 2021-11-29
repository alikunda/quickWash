package com.example.quickwash.ui.slideshow;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.example.quickwash.databinding.FragmentSlideshowBinding;

public class SlideshowFragment extends Fragment {

    private SlideshowViewModel slideshowViewModel;
    private FragmentSlideshowBinding binding;
    private DatabaseManager dbManager;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                new ViewModelProvider(this).get(SlideshowViewModel.class);
        dbManager = new DatabaseManager(getActivity());

        binding = FragmentSlideshowBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String feedb = binding.fback.getText().toString();
            if(feedb.isEmpty()){
                    Toast.makeText(getActivity(), "No input detected", Toast.LENGTH_SHORT).show();
                }else {
                    dbManager.insertFeedback(feedb,MainActivity.myUser.getEmail());
                Log.w("Feedback","******"+MainActivity.myUser.getEmail());
                    Toast.makeText(getActivity(), "Feedback Submitted", Toast.LENGTH_SHORT).show();
                    binding.fback.setText("");
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