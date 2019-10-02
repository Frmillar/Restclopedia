package com.example.restclopedia.ui.buscar;

import android.widget.EditText;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.restclopedia.R;

public class BuscarViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private EditText busqueda;
    public BuscarViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Aquí va la búsqueda");
    }

    public LiveData<String> getText() {
        return mText;
    }
}