package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.view.*;
import org.ksoap2.SoapEnvelope;
import android.os.AsyncTask;
import android.widget.TextView;
import android.widget.Toast;

import org.ksoap2.serialization.*;
import org.ksoap2.transport.HttpTransportSE;
import java.util.ArrayList;

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

    @Override
    public void onBackPressed() {

    }


    private class ConsultaPlatos extends AsyncTask<String,Integer,Boolean> {

        private Platos[] listaPlatos;

        protected Boolean doInBackground(String... params) {

            boolean resul = true;

            final String NAMESPACE = "http://tempuri.org/";
            // final String URL="https://webapplication-tb6.conveyor.cloud/WebService1.asmx";
            final String URL="http://192.168.0.2:8083/WebService1.asmx";
            final String METHOD_NAME = "Login";
            final String SOAP_ACTION = "http://tempuri.org/Login";

            try {

                SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

               // request.addProperty("id_restaurante", resp);

                SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.dotNet = true;

                envelope.setOutputSoapObject(request);

                HttpTransportSE transporte = new HttpTransportSE(URL);

                transporte.call(SOAP_ACTION, envelope);

                SoapObject resSoap = (SoapObject) envelope.getResponse();

                listaPlatos = new Platos[resSoap.getPropertyCount()];

                for (int i = 0; i < listaPlatos.length; i++) {

                    SoapObject ic = (SoapObject) resSoap.getProperty(i);

                    Platos pla = new Platos();

                    pla.id_plato = Integer.parseInt(ic.getProperty(0).toString());
                    pla.id_restaurante = Integer.parseInt(ic.getProperty(1).toString());
                    pla.valor = Integer.parseInt(ic.getProperty(2).toString());
                    pla.nombre = ic.getProperty(3).toString();
                    pla.descripcion = ic.getProperty(4).toString();
                    pla.tipo = ic.getProperty(5).toString();

                    listaPlatos[i] = pla;

                }

            }
            catch (Exception e)
            {
                resul = false;
                Toast toast1 =
                        Toast.makeText(getApplicationContext(),
                                e.toString(), Toast.LENGTH_LONG);
                toast1.show();
            }

            return resul;
        }


        protected void onPostExecute(Boolean result) {

            try {

                if (result) {

                    ArrayList<ViewPlatos> datos = new ArrayList<ViewPlatos>(listaPlatos.length);

                    for (int i = 0; i < listaPlatos.length; i++) {
                        datos.add(new ViewPlatos(listaPlatos[i].id_plato, listaPlatos[i].id_restaurante, listaPlatos[i].valor, listaPlatos[i].nombre, listaPlatos[i].descripcion, listaPlatos[i].tipo));
                    }

                    PlatosAdapter adaptador =
                            new PlatosAdapter(LoginActivity.this, datos);

                    //   listaPlatos1 = (ListView) findViewById(R.id.platosListView);
                    //  listaPlatos1.setAdapter(adaptador);



                }
                else
                {
                    Toast toast1 =
                            Toast.makeText(getApplicationContext(),
                                    "Error: "+result.toString(), Toast.LENGTH_SHORT);
                    toast1.show();
                }
            }
            catch (Exception e) {

                Toast toast1 =
                        Toast.makeText(getApplicationContext(),
                                e.toString(), Toast.LENGTH_LONG);
                toast1.show();
            }
        }
    }


}
