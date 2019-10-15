package com.example.myapplication;

import android.annotation.SuppressLint;
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

//Pantalla principal de la aplicación, en este prototipo tiene la función de mostrar la información de los restaurantes registrados en la base de datos.
public class MainActivity extends AppCompatActivity {

    private EditText txtNombre;
    private EditText txtTelefono;
    private TextView txtResultado;
    private Button btnEnviar;
    private Button btnEnviar2;
    private Button btnConsultar;
    private ListView lstClientes;
    private ListView listaRestaurant1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listaRestaurant1 = (ListView) findViewById(R.id.customListView);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });



        lstClientes = findViewById(R.id.customListView);

        btnEnviar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                TareaWSInsercion1 tarea = new TareaWSInsercion1();
                tarea.execute();
            }
        });

        TareaWSConsulta tarea = new TareaWSConsulta();
        tarea.execute();
/*
        btnEnviar2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                TareaWSInsercion2 tarea = new TareaWSInsercion2();
                tarea.execute();
            }
        });
*/
        btnConsultar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

            }
        });
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


    // Clase interna de la Activity que hace las consultas al Webservice para extraer los datos de los restaurantes registrados.
    private class TareaWSConsulta extends AsyncTask<String,Integer,Boolean> {
        // Se crea una lista que tendrá objetos de clase restaurante
        private Restaurant[] listaRestaurant;

        protected Boolean doInBackground(String... params) {

            boolean resul = true;
            // Se fijan los parámetros del webservice
            final String NAMESPACE = "http://tempuri.org/";
            final String URL="https://webapplication2-qj7.conveyor.cloud/WebService1.asmx";
            final String METHOD_NAME = "ListaRestaurant";
            final String SOAP_ACTION = "http://tempuri.org/ListaRestaurant";

            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

            SoapSerializationEnvelope envelope =
                    new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;

            envelope.setOutputSoapObject(request);

            HttpTransportSE transporte = new HttpTransportSE(URL);
            // Si se recibe una respuesta, se guardan los datos de los restaurantes en la lista
            try
            {
                transporte.call(SOAP_ACTION, envelope);

                SoapObject resSoap =(SoapObject)envelope.getResponse();

                listaRestaurant = new Restaurant[resSoap.getPropertyCount()];

                for (int i = 0; i < listaRestaurant.length; i++)
                {
                    SoapObject ic = (SoapObject)resSoap.getProperty(i);

                    Restaurant re = new Restaurant();
                    re.id_restaurante = Integer.parseInt(ic.getProperty(0).toString());
                    re.nombre = ic.getProperty(1).toString();
                    re.direccion = ic.getProperty(2).toString();
                    re.telefono = Integer.parseInt(ic.getProperty(3).toString());
                    re.valoracion_r = Integer.parseInt(ic.getProperty(4).toString());
                    re.tipo_r = ic.getProperty(5).toString();
                    re.foto = ic.getProperty(6).toString();
                    re.rut_a = ic.getProperty(7).toString();
                    re.id_horario = Integer.parseInt(ic.getProperty(8).toString());
                    re.discapacitados = Integer.parseInt(ic.getProperty(9).toString());

                    listaRestaurant[i] = re;
                }
            }
            catch (Exception e)
            {
                final String re = e.toString();

                runOnUiThread(new Runnable(){

                    @Override
                    public void run(){
                        Toast.makeText(getApplicationContext(),re,Toast.LENGTH_LONG).show();
                    }
                });


                resul = false;
            }

            return resul;
        }
        // Luego de recibir los datos se pasan a objetos ViewRestaurant y estos se guardan en un ArrayList, que luego será usado por un CustomAdapter para mostrarlos como una lista en la interfaz
        protected void onPostExecute(Boolean result) {

            if (result)
            {

                ArrayList<ViewRestaurant> datos = new ArrayList<ViewRestaurant>(listaRestaurant.length);

                for (int i = 0; i < listaRestaurant.length; i++)
                {
                    datos.add(new ViewRestaurant(listaRestaurant[i].id_restaurante, listaRestaurant[i].nombre, listaRestaurant[i].direccion, listaRestaurant[i].telefono, listaRestaurant[i].valoracion_r, listaRestaurant[i].tipo_r, listaRestaurant[i].foto, listaRestaurant[i].rut_a, listaRestaurant[i].id_horario, listaRestaurant[i].discapacitados));
                }


                CustomAdapter adaptador =
                        new CustomAdapter(MainActivity.this, datos);

                listaRestaurant1 = (ListView) findViewById(R.id.customListView);
                listaRestaurant1.setAdapter(adaptador);

                /*
                //Rellenamos la lista con los nombres de los clientes
                final String[] datos = new String[listaRestaurant.length];

                for(int i=0; i<listaRestaurant.length; i++)
                    datos[i] = listaRestaurant[i].nombre;

                ArrayAdapter<String> adaptador =
                        new ArrayAdapter<String>(MainActivity.this,
                                android.R.layout.simple_list_item_1, datos);

                lstClientes.setAdapter(adaptador);

                 */
            }
            else
            {
                txtResultado.setText("Error!");
            }
        }
    }

















    /////////////


    private class TareaWSInsercion1 extends AsyncTask<String,Integer,Boolean> {

        private Cliente[] listaClientes;

        @SuppressLint("WrongThread")
        protected Boolean doInBackground(String... params) {

            boolean resul = true;

            final String NAMESPACE = "http://sgoliver.net/";
            final String URL="https://192.168.0.5:44394/WebService1.asmx";
            final String METHOD_NAME = "NuevoClienteSimple";
            final String SOAP_ACTION = "http://sgoliver.net/NuevoClienteSimple";

            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

            request.addProperty("nombre", txtNombre.getText().toString());
            request.addProperty("telefono", txtTelefono.getText().toString());

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











    //Tarea Asíncrona para llamar al WS de consulta en segundo plano
    private class TareaWSInsercion2 extends AsyncTask<String,Integer,Boolean> {

        private Cliente[] listaClientes;

        @SuppressLint("WrongThread")
        protected Boolean doInBackground(String... params) {

            boolean resul = true;

            final String NAMESPACE = "http://sgoliver.net/";
            final String URL="https://192.168.0.5:44394/WebService1.asmx";
            final String METHOD_NAME = "NuevoClienteObjeto";
            final String SOAP_ACTION = "http://sgoliver.net/NuevoClienteObjeto";

            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

            Cliente cli = new Cliente();
            cli.nombre = txtNombre.getText().toString();
            cli.telefono = txtTelefono.getText().toString();

            PropertyInfo pi = new PropertyInfo();
            pi.setName("cliente");
            pi.setValue(cli);
            pi.setType(cli.getClass());

            request.addProperty(pi);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;

            envelope.setOutputSoapObject(request);

            envelope.addMapping(NAMESPACE, "Cliente", cli.getClass());

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

