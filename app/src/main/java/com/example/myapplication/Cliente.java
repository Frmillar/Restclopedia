package com.example.myapplication;

import java.util.Hashtable;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

public class Cliente implements KvmSerializable {
    public String rut;
    public String nombre;
    public String  apellido;
    public String  mail;
    public String  contraseña;

    public Cliente()
    {
        rut = "";
        nombre = "";
        apellido = "";
        mail = "";
        contraseña = "";
    }

    public Cliente(String rut, String nombre, String  apellido, String mail, String contraseña)
    {
        this.rut = rut;
        this.nombre = nombre;
        this.apellido = apellido;
        this.mail = mail;
        this.contraseña = contraseña;
    }

    @Override
    public Object getProperty(int arg0) {

        switch(arg0)
        {
            case 0:
                return rut;
            case 1:
                return nombre;
            case 2:
                return apellido;
        }

        return null;
    }

    @Override
    public int getPropertyCount() {
        return 5;
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
                info.name = "apellido";
                break;
            default:break;
        }
    }

    @Override
    public void setProperty(int ind, Object val) {
        switch(ind)
        {
            case 0:
                rut = val.toString();
                break;
            case 1:
                nombre = val.toString();
                break;
            case 2:
                apellido = val.toString();
                break;
            default:
                break;
        }
    }
}