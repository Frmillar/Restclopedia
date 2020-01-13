package com.example.myapplication;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class PlatosAdapter extends ArrayAdapter<ViewPlatos> {
    private final Context context;
    private final ArrayList<ViewPlatos> values;

    ViewPlatos item;
    private View v;
    String varID;

    public PlatosAdapter(Context context, ArrayList<ViewPlatos> datos) {
        super(context, R.layout.custom_platos, datos);
        this.context = context;
        this.values = datos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        View rowView = convertView;
        ViewHolder holder = new ViewHolder(rowView);

        if (rowView == null) {

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.custom_platos, parent, false);
            holder.lbl_id_plato = rowView.findViewById(R.id.id_plato_plato);
            holder.lbl_id_restaurante = rowView.findViewById(R.id.id_restaurante_plato);
            holder.lbl_valor = rowView.findViewById(R.id.valor_plato);
            holder.lbl_nombre = rowView.findViewById(R.id.nombre_plato);
            holder.lbl_descripcion = rowView.findViewById(R.id.descripcion_plato);
            holder.lbl_tipo = rowView.findViewById(R.id.tipo_plato);

            rowView.setTag(holder);

        } else {
            holder = (ViewHolder) rowView.getTag();
        }

        item = values.get(position);

        holder.lbl_id_plato.setText(String.valueOf(item.getid_plato()));
        holder.lbl_id_restaurante.setText(String.valueOf(item.getid_restaurante()));
        holder.lbl_valor.setText(String.valueOf(item.getValor()));
        holder.lbl_nombre.setText(item.getNombre());
        holder.lbl_descripcion.setText(String.valueOf(item.getDescripcion()));
        holder.lbl_tipo.setText(item.getTipo());

        //varID = item.getid_plato();



//*********************

        holder.lbl_descripcion.setTag(holder);

        holder.lbl_descripcion.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {



                  try
                {
/*
                    Intent intent = new Intent(context,  NutricionalActivity.class);
                    intent.putExtra("id_plato", varID.toString());
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);

 */


                    ViewHolder holder2 = (ViewHolder)v.getTag();
                    varID = holder2.lbl_id_plato.getText().toString();

                    Intent i = new Intent(v.getContext(),NutricionalActivity.class);
                    i.putExtra("id_plato", varID);

                    // i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    v.getContext().startActivity(i);

                }
                catch (Exception e)
                {

                    Toast toast1 =

                            Toast.makeText(context.getApplicationContext(),
                                    e.toString(), Toast.LENGTH_LONG);
                    toast1.show();

                }

            }

        });



        return rowView;
    }

    static class ViewHolder {
        TextView lbl_id_plato;
        TextView lbl_id_restaurante;
        TextView lbl_valor;
        TextView lbl_nombre;
        TextView lbl_descripcion;
        TextView lbl_tipo;

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

