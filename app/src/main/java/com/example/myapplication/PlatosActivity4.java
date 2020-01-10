package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class PlatosActivity4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try
        {

            setContentView(R.layout.activity_platos);

        }
        catch (Exception e)
        {

            Toast toast1 =

                    Toast.makeText(getApplicationContext(),
                            e.toString(), Toast.LENGTH_LONG);
            toast1.show();

        }


        getSupportActionBar().hide();
    }


}
