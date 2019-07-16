package com.pgrsoft.controlgastos.model;

import java.util.Date;

public class Movimiento {

    private Long codigo; // PK
    private double importe; // precio del producto
    private Producto producto; // El nombre del producto en el que nos hemos gastado el dinero
    private String descripion; // descripcion del movimeinto
    private Date fecha;
    private double saldo;

    public Movimiento() {
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
                ", descripion='" + descripion + '\'' +
                ", fecha=" + fecha +
                ", saldo=" + saldo +
                '}';
    }
}
