package com.example.restclopedia.ui.restaurantes;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RestaurantesViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public RestaurantesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Aquí van los restaurantes");
    }

    public LiveData<String> getText() {
        return mText;
    }
}