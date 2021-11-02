package com.example.quickwash.ui.checkOrder;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.quickwash.databinding.FragmentCheckOrderBinding;
import com.example.quickwash.databinding.FragmentGalleryBinding;

public class checkOrderFragment extends Fragment {

    private checkOrderViewModel checkOrderViewModel;
    private @NonNull FragmentCheckOrderBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        checkOrderViewModel =
                new ViewModelProvider(this).get(checkOrderViewModel.class);

        binding = FragmentCheckOrderBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

//        final TextView textView = binding.textGallery;
//        galleryViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}