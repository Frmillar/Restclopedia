package com.example.myapplication;

public class ViewPlatos {

    public int id_plato;
    public int id_restaurante;
    public int valor;
    public String nombre;
    public String descripcion;
    public String tipo;


    public ViewPlatos(int id_plato, int id_restaurante, int valor, String nombre, String descripcion, String tipo) {
        this.id_plato = id_plato;
        this.id_restaurante = id_restaurante;
        this.valor = valor;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tipo = tipo;
    }

    public ViewPlatos() {
        // TODO Auto-generated constructor stub
    }

    public Integer getid_plato() {
        return id_plato;
    }
    public Integer getid_restaurante() {
        return id_restaurante;
    }
    public Integer getValor() {
        return id_plato;
    }
    public String getNombre() { return nombre; }
    public String getDescripcion() {
        return descripcion;
    }
    public String getTipo() {
        return tipo;
    }





}
