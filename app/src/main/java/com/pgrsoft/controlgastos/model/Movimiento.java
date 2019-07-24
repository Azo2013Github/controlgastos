package com.pgrsoft.controlgastos.model;

import java.util.Date;

public class Movimiento {

    private Long codigo; // PK
    private double importe; // precio del producto
    private Producto producto; // El nombre del producto en el que nos hemos gastado el dinero
    private String descripcion; // descripcion del movimeinto
    private Date fecha;
    private double saldo;

    public Movimiento() {
    }

    public Movimiento(Long codigo, double importe, String descripcion, Date fecha, double saldo, Producto producto) {
        this.codigo = codigo;
        this.importe = importe;
        this.producto = producto;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.saldo = saldo;
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

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    @Override
    public String toString() {
        return "Movimiento{" +
                "codigo=" + codigo +
                ", importe=" + importe +
                ", producto=" + producto +
                ", descripion='" + descripcion + '\'' +
                ", fecha=" + fecha +
                ", saldo=" + saldo +
                '}';
    }
}
