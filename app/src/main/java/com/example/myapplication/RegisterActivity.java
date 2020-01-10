package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class RegisterActivity extends AppCompatActivity {

    private EditText nombre;
    private EditText apellidoPaterno;
    private EditText password1;
    private EditText password2;
    private EditText correo;
    private EditText rut1;
    private EditText rut2;
    private TextView txtResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();

        nombre = (EditText)findViewById(R.id.RegisterActivity_Nombre);
        apellidoPaterno = (EditText)findViewById(R.id.RegisterActivity_ApellidoPaterno);
        password1 = (EditText)findViewById(R.id.RegisterActivity_Contraseña1);
        password2 = (EditText)findViewById(R.id.RegisterActivity_Contrasena2);
        correo = (EditText)findViewById(R.id.RegisterActivity_Email);
        rut1 = (EditText)findViewById(R.id.RegisterActivity_Rut1);
        rut2 = (EditText)findViewById(R.id.RegisterActivity_Rut2);
        txtResultado = findViewById(R.id.textView);

    }

    public void Registrar(View view){

        TareaWSInsercion tarea = new TareaWSInsercion();
        tarea.execute();

/*
        if(!str_nombre.isEmpty() && !str_apellidoPaterno.isEmpty()
                && !str_apellidoMaterno.isEmpty() && !str_username.isEmpty()
                && !str_password1.isEmpty() && !str_password2.isEmpty()
                && !str_correo.isEmpty() && !str_telefono.isEmpty()
                && !str_rut1.isEmpty() && !str_rut2.isEmpty()){
            if((hombre.isChecked() || mujer.isChecked()) && (conductor.isChecked() || pasajero.isChecked())){
                if(str_password1.compareTo(str_password2)==0){
                    ContentValues values = new ContentValues();
                    if(conductor.isChecked()) {
                        values.put("username", str_username);
                        values.put("nombre", str_nombre + " " + str_apellidoPaterno + " " + str_apellidoMaterno);
                        values.put("password", str_password1);
                        values.put("correo", str_correo);
                        values.put("telefono", str_telefono);
                        values.put("rut", str_rut1 + "-" + str_rut2);
                        if(hombre.isChecked())values.put("sexo", "Masculino");
                        else values.put("sexo", "Femenino");

                        if(!DBQueries.isConductorRegistrado(str_username, this)){
                            db.insert("conductor", null, values);
                            ContentValues v = new ContentValues();
                            v.put("username", str_username );
                            db.insert("vehiculo", null, v);
                            Toast.makeText(this, "Registro Exitoso", Toast.LENGTH_LONG).show();
                            this.finish();
                        }
                        else Toast.makeText(this, "Nombre de usuario no disponible", Toast.LENGTH_LONG).show();
                        db.close();
                    }
                    else{
                        values.put("username", str_username);
                        values.put("nombre", str_nombre + " " + str_apellidoPaterno + " " + str_apellidoMaterno);
                        values.put("password", str_password1);
                        values.put("correo", str_correo);
                        values.put("telefono", str_telefono);
                        values.put("rut", str_rut1 + "-" + str_rut2);
                        if(hombre.isChecked())values.put("sexo", "Masculino");
                        else values.put("sexo", "Femenino");

                        if(!DBQueries.isPasajeroRegistrado(str_username, this)){
                            db.insert("pasajero", null, values);
                            Toast.makeText(this, "Registro Exitoso", Toast.LENGTH_LONG).show();
                            this.finish();
                        }
                        else Toast.makeText(this, "Nombre de usuario no disponible", Toast.LENGTH_LONG).show();
                        db.close();
                    }
                }
                else Toast.makeText(this,"Las contraseñas no coinciden", Toast.LENGTH_LONG).show();
            }
            else Toast.makeText(this, "Seleccione las casillas", Toast.LENGTH_LONG).show();
        }
        else Toast.makeText(this, "Hay campos sin rellenar", Toast.LENGTH_LONG).show();
*/
    }

    private class TareaWSInsercion extends AsyncTask<String,Integer,Boolean> {

        private Cliente[] listaClientes;

        @SuppressLint("WrongThread")
        protected Boolean doInBackground(String... params) {

            boolean resul = true;

            final String NAMESPACE = "http://sgoliver.net/";
            final String URL="https://webapplication-tb6.conveyor.cloud/WebService1.asmx\"";
            final String METHOD_NAME = "RegistroUsuario";
            final String SOAP_ACTION = "http://tempuri.org/RegistroUsuario";

            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

            request.addProperty("rut", rut1.getText().toString());
            request.addProperty("nombre", nombre.getText().toString());
            request.addProperty("apellido", apellidoPaterno.getText().toString());
            request.addProperty("mail", correo.getText().toString());
            request.addProperty("contraseña", password1.getText().toString());

            SoapSerializationEnvelope envelope =
                    new SoapSerializationEnvelope(SoapEnvelope.VER11);

            envelope.dotNet = true;

            envelope.setOutputSoapObject(request);

            HttpTransportSE transporte = new HttpTransportSE(URL);

            try
            {
                transporte.call(SOAP_ACTION, envelope);

                SoapPrimitive resultado_xml =(SoapPrimitive)envelope.getResponse();
                String res = resultado_xml.toString();

                if(!res.equals("1"))
                    resul = false;
            }
            catch (Exception e)
            {
                resul = false;
            }

            return resul;
        }

        protected void onPostExecute(Boolean result) {
            if (result)
                txtResultado.setText("Insertado OK");
            else
                txtResultado.setText("Error!");
        }
    }
}
