package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

// Actividad que servirá de pantalla inicial para verificar la identificación del usuario que quiere ingresar a la aplicación, por ahora solo muestra los campos a rellenar y un boton para ingresar pero no se verifica el usuario.
public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
    }

    public void Login(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void Registrar(View view){
        Intent RegistroActivity = new Intent(this, RegisterActivity.class);
        startActivity(RegistroActivity);
    }
}
