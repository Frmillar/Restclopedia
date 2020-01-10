package com.example.myapplication;

public class ViewNutricional {

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

    public ViewNutricional(int id_infon, int kcal, int g_totales, int g_saturadas, int proteina, int sal, int h_carbono, int azucar, String nombre, String tipo, String nombre_plato) {
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

    public ViewNutricional() {
        // TODO Auto-generated constructor stub
    }

    public Integer getid_infon() {
        return id_infon;
    }
    public Integer getkcal() {
        return kcal;
    }
    public Integer getg_totales() { return g_totales; }
    public Integer getg_saturadas() { return g_saturadas; }
    public Integer getproteina() {
        return proteina;
    }
    public Integer getsal() {
        return sal;
    }
    public Integer geth_carbono() {
        return h_carbono;
    }
    public Integer getazucar() { return azucar; }

    public String getNombre() { return nombre; }
    public String getTipo() {
        return tipo;
    }
    public String getNombre_plato() {
        return nombre_plato;
    }


}