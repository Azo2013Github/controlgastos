package com.pgrsoft.controlgastos.services;

import com.pgrsoft.controlgastos.model.Movimiento;

import java.util.List;

public interface MovimientoServices {

    public Movimiento create(Movimiento movimiento);
    public Movimiento read(Long codigo);

    public List<Movimiento> getAll();



}
