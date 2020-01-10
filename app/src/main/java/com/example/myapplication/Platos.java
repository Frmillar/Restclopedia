package com.example.myapplication;
import java.util.Hashtable;
import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

public class Platos implements KvmSerializable {
    public int id_plato;
    public int id_restaurante;
    public int valor;
    public String nombre;
    public String descripcion;
    public String tipo;


    public Platos() {
        id_plato =0;
        id_restaurante = 0;
        valor = 0;
        nombre = "";
        descripcion = "";
        tipo ="";
    }

    public Platos(int id_plato, int id_restaurante, int valor, String nombre, String descripcion, String tipo) {
        this.id_plato = id_plato;
        this.id_restaurante = id_restaurante;
        this.valor = valor;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tipo = tipo;
    }

    @Override
    public Object getProperty(int arg0) {
        switch (arg0) {
            case 0:
                return id_plato;
            case 1:
                return id_restaurante;
            case 2:
                return valor;
            case 3:
                return nombre;
            case 4:
                return descripcion;
            case 5:
                return tipo;
        }

        return null;
    }

    @Override
    public int getPropertyCount() {
        return 3;
    }

    @Override
    public void getPropertyInfo(int ind, Hashtable ht, PropertyInfo info) {
        switch (ind) {
            case 0:
                info.type = PropertyInfo.INTEGER_CLASS;
                info.name = "id_plato";
                break;
            case 1:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "id_restaurante";
                break;
            case 2:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "valor";
                break;
            case 3:
                info.type = PropertyInfo.INTEGER_CLASS;
                info.name = "nombre";
                break;
            case 4:
                info.type = PropertyInfo.INTEGER_CLASS;
                info.name = "descripcion";
                break;
            case 5:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "tipo";
                break;
        }
    }

    @Override
    public void setProperty(int ind, Object val) {
        switch (ind) {
            case 0:
                id_plato = Integer.parseInt(val.toString());
                break;
            case 1:
                id_restaurante = Integer.parseInt(val.toString());
                break;
            case 2:
                valor = Integer.parseInt(val.toString());
                break;
            case 3:
                nombre = val.toString();
                break;
            case 4:
                descripcion = val.toString();
                break;
            case 5:
                tipo = val.toString();
                break;

            default:
                break;
        }
    }
}
