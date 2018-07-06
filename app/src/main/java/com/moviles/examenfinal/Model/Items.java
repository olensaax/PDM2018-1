package com.moviles.examenfinal.Model;

/**
 * Created by joel on 7/6/18.
 */

public class Items {

    private String nombre;
    private String valor;

    public Items(){

    }

    public Items(String nombre, String valor) {
        this.nombre = nombre;
        this.valor = valor;
    }

    public String getNombre() {
        return nombre;
    }

    public String getValor() {
        return valor;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
}
