package com.pgrsoft.controlgastos.model;

import java.io.Serializable;
import java.util.Date;

public class Movimiento implements Serializable {

    private Long codigo; // PK
    private double importe; // precio del producto
    private Producto producto; // El nombre del producto en el que nos hemos gastado el dinero
    private String descripcion; // descripcion del movimeinto
    private Date fecha;

    public Movimiento() {
    }

    public Movimiento(double importe, String descripcion, Date fecha,Producto producto) {
        this.importe = importe;
        this.producto = producto;
        this.descripcion = descripcion;
        this.fecha = fecha;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Movimiento{" +
                "codigo=" + codigo +
                ", importe=" + importe +
                ", producto=" + producto +
                ", descripcion='" + descripcion + '\'' +
                ", fecha=" + fecha +
                '}';
    }
}
