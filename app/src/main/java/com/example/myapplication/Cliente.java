package com.example.myapplication;

import java.util.Hashtable;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

public class Cliente implements KvmSerializable {
    public int id;
    public String nombre;
    public String  telefono;

    public Cliente()
    {
        id = 0;
        nombre = "";
        telefono = "";
    }

    public Cliente(int id, String nombre, String  telefono)
    {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
    }

    @Override
    public Object getProperty(int arg0) {

        switch(arg0)
        {
            case 0:
                return id;
            case 1:
                return nombre;
            case 2:
                return telefono;
        }

        return null;
    }

    @Override
    public int getPropertyCount() {
        return 3;
    }

    @Override
    public void getPropertyInfo(int ind, Hashtable ht, PropertyInfo info) {
        switch(ind)
        {
            case 0:
                info.type = PropertyInfo.INTEGER_CLASS;
                info.name = "Id";
                break;
            case 1:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "Nombre";
                break;
            case 2:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "Telefono";
                break;
            default:break;
        }
    }

    @Override
    public void setProperty(int ind, Object val) {
        switch(ind)
        {
            case 0:
                id = Integer.parseInt(val.toString());
                break;
            case 1:
                nombre = val.toString();
                break;
            case 2:
                telefono = val.toString();
                break;
            default:
                break;
        }
    }
}