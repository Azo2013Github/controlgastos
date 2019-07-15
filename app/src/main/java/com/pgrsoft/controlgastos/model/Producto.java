package com.pgrsoft.controlgastos.model;

import java.util.Date;

public class Producto {

    private int codigo; //codigo del producto que se autoincrementa PK.
    private int tipoProducto; // gastos puntuales o gastpos de casa que son alquiler o comida o bebida etc...
    private String descripcionProducto; // nombre del producto...
    private double precio;

    public Producto() {
    }

    public Producto(int tipoProducto, String descripcionProducto, double precio) {
        this.tipoProducto = tipoProducto;
        this.descripcionProducto = descripcionProducto;
        this.precio = precio;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getTipoProducto() {
        return tipoProducto;
    }

    public void setTipoProducto(int tipoProducto) {
        this.tipoProducto = tipoProducto;
    }

    public String getDescripcionProducto() {
        return descripcionProducto;
    }

    public void setDescripcionProducto(String descripcionProducto) {
        this.descripcionProducto = descripcionProducto;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "codigo=" + codigo +
                ", tipoProducto=" + tipoProducto +
                ", descripcionProducto='" + descripcionProducto + '\'' +
                ", precio=" + precio +
                '}';
    }
}
