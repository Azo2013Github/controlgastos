package com.pgrsoft.controlgastos.model;

import java.util.Date;

public class Gasto {

    private int codigo; // PK
    private double precioGastos; // precio del producto
    private Producto producto; // El nombre del producto en el que nos hemos gastado el dinero
    private String descripion; //codigo postal de donde hemos gastado
    private Date fecha;

    public Gasto() {
    }

    public Gasto(double precioGastos, Producto producto, String descripion, Date fecha) {
        this.precioGastos = precioGastos;
        this.producto = producto;
        this.descripion = descripion;
        this.fecha = fecha;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public double getPrecioGastos() {
        return precioGastos;
    }

    public void setPrecioGastos(double precioGastos) {
        this.precioGastos = precioGastos;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public String getDescripion() {
        return descripion;
    }

    public void setDescripion(String descripion) {
        this.descripion = descripion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Gasto{" +
                "codigo=" + codigo +
                ", precioGastos=" + precioGastos +
                ", producto=" + producto +
                ", descripion='" + descripion + '\'' +
                ", fecha=" + fecha +
                '}';
    }
}
