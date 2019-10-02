package com.example.restclopedia.ui.buscar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.restclopedia.R;

public class BuscarFragment extends Fragment {

    private BuscarViewModel buscarViewModel;

    private EditText busqueda;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        buscarViewModel =
                ViewModelProviders.of(this).get(BuscarViewModel.class);
        View root = inflater.inflate(R.layout.fragment_buscar, container, false);
        //final TextView textView = root.findViewById(R.id.text_notifications);
        /*buscarViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/

        busqueda = (EditText)root.findViewById(R.id.fragment_buscar_busqueda);
        return root;
    }
}