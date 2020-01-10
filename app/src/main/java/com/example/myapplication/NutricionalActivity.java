package com.example.myapplication;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.widget.*;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.*;
import org.ksoap2.SoapEnvelope;
import android.os.AsyncTask;
import org.ksoap2.serialization.*;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;


public class NutricionalActivity extends AppCompatActivity {

    private EditText txtNombre;
    private EditText txtTelefono;
    private TextView txtResultado;
    private Button btnEnviar;
    private Button btnEnviar2;
    private Button btnConsultar;
    private ListView lstPlatos;
    private ListView listaNutri1;

    Bundle bundle;
    Integer id_restaurante;
    String resp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plato4);


        listaNutri1 = (ListView) findViewById(R.id.platosListView);

        start();

    }

    public void start()
    {
        try
        {
            bundle = this.getIntent().getExtras();
            // resp = bundle.getString("id_plato");
            resp = "1";

            ConsultaNutri tarea = new ConsultaNutri();
            tarea.execute();
        }
        catch (Exception e)
        {
            Toast toast1 =
                    Toast.makeText(getApplicationContext(),
                            e.toString(), Toast.LENGTH_SHORT);
            toast1.show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private class ConsultaNutri extends AsyncTask<String,Integer,Boolean> {

        private Nutricional[] listaNutricional;

        protected Boolean doInBackground(String... params) {

            boolean resul = true;

            final String NAMESPACE = "http://tempuri.org/";
            //final String URL="https://webapplication2-qj7.conveyor.cloud/WebService1.asmx";
            final String URL="http://192.168.0.2:8083/WebService1.asmx";
            final String METHOD_NAME = "Nutricional";
            final String SOAP_ACTION = "http://tempuri.org/Nutricional";

            try {

                SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

                request.addProperty("id_plato", resp);

                SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.dotNet = true;

                envelope.setOutputSoapObject(request);

                HttpTransportSE transporte = new HttpTransportSE(URL);

                transporte.call(SOAP_ACTION, envelope);

                SoapObject resSoap = (SoapObject) envelope.getResponse();

                listaNutricional = new Nutricional[resSoap.getPropertyCount()];

                for (int i = 0; i < listaNutricional.length; i++) {

                    SoapObject ic = (SoapObject) resSoap.getProperty(i);

                    Nutricional nutri = new Nutricional();

                    nutri.id_infon = Integer.parseInt(ic.getProperty(0).toString());
                    nutri.kcal = Integer.parseInt(ic.getProperty(1).toString());
                    nutri.g_totales = Integer.parseInt(ic.getProperty(2).toString());
                    nutri.g_saturadas = Integer.parseInt(ic.getProperty(3).toString());
                    nutri.proteina = Integer.parseInt(ic.getProperty(4).toString());
                    nutri.sal = Integer.parseInt(ic.getProperty(5).toString());
                    nutri.h_carbono = Integer.parseInt(ic.getProperty(6).toString());
                    nutri.azucar = Integer.parseInt(ic.getProperty(7).toString());
                    nutri.nombre = ic.getProperty(8).toString();
                    nutri.tipo = ic.getProperty(9).toString();
                    nutri.nombre_plato = ic.getProperty(10).toString();

                    listaNutricional[i] = nutri;

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

                if (result)
                {

                    ArrayList<ViewNutricional> datos = new ArrayList<ViewNutricional>(listaNutricional.length);

                   // List<String> ingList = new ArrayList<String>();

                    String ingredientes ="";

                    for (int i = 0; i < listaNutricional.length; i++)
                    {
                        datos.add(new ViewNutricional(listaNutricional[i].id_infon, listaNutricional[i].kcal, listaNutricional[i].g_totales, listaNutricional[i].g_saturadas, listaNutricional[i].proteina, listaNutricional[i].sal,listaNutricional[i].h_carbono,listaNutricional[i].azucar,listaNutricional[i].nombre,listaNutricional[i].tipo,listaNutricional[i].nombre_plato));
                       // ingList.add(listaNutricional[i].nombre);
                        ingredientes = listaNutricional[i].nombre;
                    }

                    NutricionalAdapter adaptador =
                            new NutricionalAdapter(NutricionalActivity.this, datos);

                    //StringUtils.join(ingList, ",");

                    listaNutri1 = (ListView) findViewById(R.id.nutriListView);
                    listaNutri1.setAdapter(adaptador);

                    /*
                    String s = "";
                    for (int i = 0; i < ingList.size(); i++) {
                        s += ingList.get(i) + ", ";
                    }
                    */


                    TextView tv = (TextView) findViewById(R.id.ListIngredientes);
                   // tv.setText(s);



                    Toast toast1 =
                            Toast.makeText(getApplicationContext(),
                                    ingredientes.toString(), Toast.LENGTH_LONG);
                    toast1.show();




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

