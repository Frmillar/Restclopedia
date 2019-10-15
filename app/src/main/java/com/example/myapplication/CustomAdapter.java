package com.example.myapplication;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

// Clase utilizada para guardar los datos de los restaurantes y mostrar su información relevante a través de la interfaz
public class CustomAdapter extends ArrayAdapter<ViewRestaurant> {
    private final Context context;
    private final ArrayList<ViewRestaurant> values;

    ViewRestaurant item;
    private View v;

    public CustomAdapter(Context context, ArrayList<ViewRestaurant> datos) {
        super(context, R.layout.custom, datos);
        this.context = context;
        this.values = datos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View rowView = convertView;
        ViewHolder holder = new ViewHolder(rowView);

        if (rowView == null) {

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.custom, parent, false);
            holder.lblNombre = rowView.findViewById(R.id.nombre);
            holder.lblDireccion = rowView.findViewById(R.id.direccion);
            holder.lblTelefono = rowView.findViewById(R.id.telefono);
            holder.lblDiscapacitados = rowView.findViewById(R.id.discapacitados);

            rowView.setTag(holder);

        } else {
            holder = (ViewHolder) rowView.getTag();
        }

        item = values.get(position);

        String discap ="";

        if(item.getDiscapacitados() == 0)
        {
            discap = "Acceso discapacitados: No";
        }
        else
        {
            discap = "Acceso discapacitados: Sí";
        }

        holder.lblNombre.setText(item.getNombre());
        holder.lblDireccion.setText(item.getdireccion());
        holder.lblTelefono.setText(String.valueOf(item.getTelefono()));
        holder.lblDiscapacitados.setText(discap);

        return rowView;
    }

    static class ViewHolder {
        TextView lblNombre;
        TextView lblDireccion;
        TextView lblTelefono;
        TextView lblDiscapacitados;
        View v;
        public ViewHolder(View v) {
            this.v = v;
        }
    }




/*
    private class GetImage extends AsyncTask<String, Integer, Bitmap> {

        ImageView img;

        public GetImage(ImageView holderImg) {
            this.img = holderImg;
        }

        protected Bitmap doInBackground(String... url) {
            String newUrl = url[0]; //1 url/parametro...
            Bitmap bitmapImg = null;

            try {
                InputStream datosImg = new java.net.URL(newUrl).openStream();
                bitmapImg = BitmapFactory.decodeStream(datosImg); //stream de datos tipo bitmap
            } catch (Exception e) {

                Toast toast1 =
                        Toast.makeText(context.getApplicationContext(), e.toString(), Toast.LENGTH_SHORT);
                toast1.show();
            }
            return bitmapImg;
        }

        protected void onPostExecute(Bitmap bitmapImg) {

            img.setImageBitmap(bitmapImg);

        }
    }
    */
}

