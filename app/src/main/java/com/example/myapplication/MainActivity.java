package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.*;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.view.*;
import org.ksoap2.SoapEnvelope;
import android.os.AsyncTask;
import org.ksoap2.serialization.*;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private EditText txtNombre;
    private EditText txtTelefono;
    private TextView txtResultado;
    private Button btnEnviar;
    private Button btnEnviar2;
    private Button btnConsultar;
    private ListView lstClientes;
    private ListView listaRestaurant1;
    private TextView HeaderPasajero_username;
    private TextView HeaderPasajero_nombre;
    private ImageView HeaderPasajero_foto;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (Build.VERSION.SDK_INT < 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        View headView = navigationView.getHeaderView(0);

        HeaderPasajero_nombre = (TextView)headView.findViewById(R.id.HeaderUser_username);
        HeaderPasajero_username = (TextView)headView.findViewById(R.id.HeaderUser_nombre);
        HeaderPasajero_foto = (ImageView)headView.findViewById(R.id.HeaderUser_foto);
        HeaderPasajero_username.setText("Usuario");
        HeaderPasajero_nombre.setText("Nombre");
        HeaderPasajero_foto.setImageResource(R.drawable.user);

        listaRestaurant1 = (ListView) findViewById(R.id.customListView);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        lstClientes = findViewById(R.id.customListView);



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

    }

    @Override
    public void onBackPressed() {
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

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.salir){
            Intent LoginActivity = new Intent(this, LoginActivity.class);
            startActivity(LoginActivity);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private class TareaWSConsulta extends AsyncTask<String,Integer,Boolean> {

        private Restaurant[] listaRestaurant;

        protected Boolean doInBackground(String... params) {



            boolean resul = true;

            try
            {

            final String NAMESPACE = "http://tempuri.org/";
            //final String URL="https://webapplication-tb6.conveyor.cloud/WebService1.asmx";
            final String URL="http://192.168.0.2:8083/WebService1.asmx";
            final String METHOD_NAME = "ListaRestaurant";
            final String SOAP_ACTION = "http://tempuri.org/ListaRestaurant";

            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

            SoapSerializationEnvelope envelope =
                    new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;

            envelope.setOutputSoapObject(request);

            HttpTransportSE transporte = new HttpTransportSE(URL);


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

