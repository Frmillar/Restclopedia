package com.example.myapplication;
import java.util.Hashtable;
import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

public class Nutricional implements KvmSerializable {
    public int id_infon;
    public int kcal;
    public int g_totales;
    public int g_saturadas;
    public int proteina;
    public int sal;
    public int h_carbono;
    public int azucar;

    public String nombre;
    public String tipo;
    public String nombre_plato;

    public Nutricional() {
        id_infon =0;
        kcal = 0;
        g_totales = 0;
        g_saturadas = 0;
        proteina = 0;
        sal = 0;
        h_carbono = 0;
        azucar = 0;

        nombre = "";
        tipo = "";
        nombre_plato ="";
    }

    public Nutricional(int id_infon, int kcal, int g_totales,  int g_saturadas, int proteina, int sal, int h_carbono, int azucar, String nombre, String tipo, String nombre_plato)
   {
        this.id_infon = id_infon;
        this.kcal = kcal;
        this.g_totales = g_totales;
        this.g_saturadas = g_saturadas;
        this.proteina = proteina;
        this.sal = sal;
        this.h_carbono = h_carbono;
        this.azucar = azucar;

       this.nombre = nombre;
       this.tipo = tipo;
       this.nombre_plato = nombre_plato;
    }

    @Override
    public Object getProperty(int arg0) {
        switch (arg0) {
            case 0:
                return id_infon;
            case 1:
                return kcal;
            case 2:
                return g_totales;
            case 3:
                return g_saturadas;
            case 4:
                return proteina;
            case 5:
                return sal;
            case 6:
                return h_carbono;
            case 7:
                return azucar;
            case 8:
                return nombre;
            case 9:
                return tipo;
            case 10:
                return nombre_plato;
        }

        return null;
    }

    @Override
    public int getPropertyCount() {
        return 10;
    }

    @Override
    public void getPropertyInfo(int ind, Hashtable ht, PropertyInfo info) {
        switch (ind) {
            case 0:
                info.type = PropertyInfo.INTEGER_CLASS;
                info.name = "id_infon";
                break;
            case 1:
                info.type = PropertyInfo.INTEGER_CLASS;
                info.name = "kcal";
                break;
            case 2:
                info.type = PropertyInfo.INTEGER_CLASS;
                info.name = "g_totales";
                break;
            case 3:
                info.type = PropertyInfo.INTEGER_CLASS;
                info.name = "g_saturadas";
                break;
            case 4:
                info.type = PropertyInfo.INTEGER_CLASS;
                info.name = "proteina";
                break;
            case 5:
                info.type = PropertyInfo.INTEGER_CLASS;
                info.name = "sal";
                break;
            case 6:
                info.type = PropertyInfo.INTEGER_CLASS;
                info.name = "h_carbono";
                break;
            case 7:
                info.type = PropertyInfo.INTEGER_CLASS;
                info.name = "azucar";
                break;
            case 8:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "nombre";
                break;
            case 9:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "tipo";
                break;
            case 10:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "nombre_plato";
                break;
        }
    }

    @Override
    public void setProperty(int ind, Object val) {
        switch (ind) {
            case 0:
                id_infon = Integer.parseInt(val.toString());
                break;
            case 1:
                kcal = Integer.parseInt(val.toString());
                break;
            case 2:
                g_totales = Integer.parseInt(val.toString());
                break;
            case 3:
                g_saturadas = Integer.parseInt(val.toString());
                break;
            case 4:
                proteina = Integer.parseInt(val.toString());
                break;
            case 5:
                sal = Integer.parseInt(val.toString());
                break;
            case 6:
                h_carbono = Integer.parseInt(val.toString());
                break;
            case 7:
                azucar = Integer.parseInt(val.toString());
                break;
            case 8:
                nombre = val.toString();
                break;
            case 9:
                tipo = val.toString();
                break;
            case 10:
                nombre_plato = val.toString();
                break;


            default:
                break;
        }
    }
}
