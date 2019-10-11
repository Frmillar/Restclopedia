package com.example.myapplication;
import java.util.Hashtable;
import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

public class Restaurant implements KvmSerializable {
    public int id_restaurante;
    public String nombre;
    public String direccion;
    public int telefono;
    public int valoracion_r;
    public String tipo_r;
    public String foto;
    public String rut_a;
    public int id_horario;

    public Restaurant() {
        id_restaurante = 0;
        nombre = "";
        direccion = "";
        telefono =0;
        valoracion_r=0;
        tipo_r="";
        foto = "";
        rut_a ="";
        id_horario=0;
    }

    public Restaurant(int id_restaurante, String nombre, String direccion,int telefono,int valoracion_r,String tipo_r, String foto,String rut_a,int id_horario) {
        this.id_restaurante = id_restaurante;
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
        this.valoracion_r = valoracion_r;
        this.tipo_r = tipo_r;
        this.rut_a = rut_a;
        this.id_horario = id_horario;
    }

    @Override
    public Object getProperty(int arg0) {
        switch (arg0) {
            case 0:
                return id_restaurante;
            case 1:
                return nombre;
            case 2:
                return direccion;
            case 3:
                return telefono;
            case 4:
                return valoracion_r;
            case 5:
                return tipo_r;
            case 6:
                return foto;
            case 7:
                return rut_a;
            case 8:
                return id_horario;
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
                info.name = "id_restaurante";
                break;
            case 1:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "nombre";
                break;
            case 2:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "direccion";
                break;
            case 3:
                info.type = PropertyInfo.INTEGER_CLASS;
                info.name = "telefono";
                break;
            case 4:
                info.type = PropertyInfo.INTEGER_CLASS;
                info.name = "valoracion_r";
                break;
            case 5:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "tipo_r";
                break;
            case 6:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "foto";
                break;
            case 7:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "rut_a";
                break;
            case 8:
                info.type = PropertyInfo.INTEGER_CLASS;
                info.name = "id_horario";
                break;
            default:
                break;
        }
    }

    @Override
    public void setProperty(int ind, Object val) {
        switch (ind) {
            case 0:
                id_restaurante = Integer.parseInt(val.toString());
                break;
            case 1:
                nombre = val.toString();
                break;
            case 2:
                direccion = val.toString();
                break;
            case 3:
                telefono = Integer.parseInt(val.toString());
                break;
            case 4:
                valoracion_r = Integer.parseInt(val.toString());
                break;
            case 5:
                tipo_r = val.toString();
                break;
            case 6:
                foto = val.toString();
                break;
            case 7:
                rut_a = val.toString();
                break;
            case 8:
                id_horario = Integer.parseInt(val.toString());
                break;

            default:
                break;
        }
    }
}
