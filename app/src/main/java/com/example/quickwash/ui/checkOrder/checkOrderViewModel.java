package com.example.quickwash.ui.checkOrder;

import android.widget.EditText;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class checkOrderViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public checkOrderViewModel() {
        mText = new MutableLiveData<>();



    }

    public LiveData<String> getText() {
        return mText;
    }
}