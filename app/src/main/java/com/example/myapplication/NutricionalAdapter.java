package com.example.myapplication;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class NutricionalAdapter extends ArrayAdapter<ViewNutricional> {
    private final Context context;
    private final ArrayList<ViewNutricional> values;

    ViewNutricional item;
    private View v;

    public NutricionalAdapter(Context context, ArrayList<ViewNutricional> datos) {
        super(context, R.layout.custom_nutri, datos);
        this.context = context;
        this.values = datos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        View rowView = convertView;
        ViewHolder holder = new ViewHolder(rowView);

        if (rowView == null) {

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.custom_nutri, parent, false);
            holder.lbl_id_infon = rowView.findViewById(R.id.id_infon);
            holder.lbl_kcal = rowView.findViewById(R.id.kcal);
            holder.lbl_g_totales = rowView.findViewById(R.id.g_totales);
            holder.lbl_g_saturadas = rowView.findViewById(R.id.g_saturadas);
            holder.lbl_proteina = rowView.findViewById(R.id.proteina);
            holder.lbl_sal = rowView.findViewById(R.id.sal);
            holder.lbl_h_carbono = rowView.findViewById(R.id.h_carbono);
            holder.lbl_azucar = rowView.findViewById(R.id.azucar);


            rowView.setTag(holder);

        } else {
            holder = (ViewHolder) rowView.getTag();
        }

        item = values.get(position);

        String kcal = "Kal: " + item.getkcal();
        String gtot = "Grasas totales: " + item.getg_totales();
        String gsat = "Grasas saturadas: " + item.getg_saturadas();
        String prot = "Protenias: " + item.getproteina();
        String sal =  "Sal: " + item.getsal();
        String hcar = "Hidratos de carbono: " + item.geth_carbono();
        String azuc = "Azucar: " + item.getazucar();

        holder.lbl_id_infon.setText(String.valueOf(item.getid_infon()));
        holder.lbl_kcal.setText(kcal);
        holder.lbl_g_totales.setText(gtot);
        holder.lbl_g_saturadas.setText(gsat);
        holder.lbl_proteina.setText(prot);
        holder.lbl_sal.setText(sal);
        holder.lbl_h_carbono.setText(hcar);
        holder.lbl_azucar.setText(azuc);


        return rowView;
    }

    static class ViewHolder {
        TextView lbl_id_infon;
        TextView lbl_kcal;
        TextView lbl_g_totales;
        TextView lbl_g_saturadas;
        TextView lbl_proteina;
        TextView lbl_sal;
        TextView lbl_h_carbono;
        TextView lbl_azucar;

        TextView lbl_nombre;
        TextView lbl_nombre_plato;

        private TextView button;

        View v;
        public ViewHolder(View v) {
            this.v = v;
        }
        public TextView getButton()
        {
            if (button == null)
            {
                button = (TextView) v.findViewById(R.id.nombre);
            }
            return (button);
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

