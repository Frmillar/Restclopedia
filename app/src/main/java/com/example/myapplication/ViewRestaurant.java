package com.example.myapplication;

public class ViewRestaurant {

    public int id_restaurante;
    public String nombre;
    public String direccion;
    public int telefono;
    public int valoracion_r;
    public String tipo_r;
    public String foto;
    public String rut_a;
    public int id_horario;
    public int discapacitados;


    public ViewRestaurant(int id_restaurante, String nombre, String direccion,int telefono,int valoracion_r,String tipo_r, String foto,String rut_a,int id_horario, int discapacitados) {
        this.id_restaurante = id_restaurante;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.valoracion_r = valoracion_r;
        this.tipo_r = tipo_r;
        this.foto = foto;
        this.rut_a = rut_a;
        this.id_horario = id_horario;
        this.discapacitados = discapacitados;
    }

    public ViewRestaurant() {
        // TODO Auto-generated constructor stub
    }

    public Integer getid_restaurante() {
        return id_restaurante;
    }
    public String getNombre() { return nombre; }
    public String getdireccion() {
        return direccion;
    }
    public Integer getTelefono() {
        return telefono;
    }
    public Integer getValoracion_r() {
        return valoracion_r;
    }
    public String getTipo_r() { return tipo_r; }
    public String getfoto() { return foto; }
    public String getRut_a() { return rut_a; }
    public Integer getid_horario() {
        return id_horario;
    }
    public Integer getDiscapacitados() {
        return discapacitados;
    }




}
