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


public class PlatosActivity extends AppCompatActivity {

    private EditText txtNombre;
    private EditText txtTelefono;
    private TextView txtResultado;
    private Button btnEnviar;
    private Button btnEnviar2;
    private Button btnConsultar;
    private ListView lstPlatos;
    private ListView listaPlatos1;

    Bundle bundle;
    Integer id_restaurante;
    String resp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plato4);


        listaPlatos1 = (ListView) findViewById(R.id.platosListView);

        start();

    }

    public void start()
    {



            bundle = this.getIntent().getExtras();
            resp = bundle.getString("id_restaurante");
            //resp = "1";

            Toast toast1 =
                    Toast.makeText(getApplicationContext(), resp, Toast.LENGTH_LONG);
            toast1.show();

            ConsultaPlatos tarea = new ConsultaPlatos();
            tarea.execute();

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


    private class ConsultaPlatos extends AsyncTask<String,Integer,Boolean> {

        private Platos[] listaPlatos;

        protected Boolean doInBackground(String... params) {

            boolean resul = true;

            final String NAMESPACE = "http://tempuri.org/";
            //final String URL="https://webapplication2-qj7.conveyor.cloud/WebService1.asmx";
            final String URL="http://192.168.0.2:8083/WebService1.asmx";
            final String METHOD_NAME = "Platos";
            final String SOAP_ACTION = "http://tempuri.org/Platos";

            try {

                SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

                request.addProperty("id_restaurante", resp);

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

                if (result)
                {

                    ArrayList<ViewPlatos> datos = new ArrayList<ViewPlatos>(listaPlatos.length);

                    for (int i = 0; i < listaPlatos.length; i++)
                    {
                        datos.add(new ViewPlatos(listaPlatos[i].id_plato, listaPlatos[i].id_restaurante, listaPlatos[i].valor, listaPlatos[i].nombre, listaPlatos[i].descripcion, listaPlatos[i].tipo));
                    }

                    PlatosAdapter adaptador =
                            new PlatosAdapter(PlatosActivity.this, datos);

                    listaPlatos1 = (ListView) findViewById(R.id.platosListView);
                    listaPlatos1.setAdapter(adaptador);

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

