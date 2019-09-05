package com.pgrsoft.controlgastos.services;

import com.pgrsoft.controlgastos.model.Movimiento;

import java.util.Date;
import java.util.List;

public interface MovimientoServices {

    public Movimiento create(Movimiento movimiento);
    public Movimiento read(Long codigo);

    public List<Movimiento> getAll();
    public Movimiento update(Movimiento movimiento);       // TODO
    public boolean delete(Long codigo);
    public List<Movimiento> getDateBetween(Date date, Date date1);




}
