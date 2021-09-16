package com.api.kruger.service;

import com.api.kruger.entity.Empleado;

import java.util.List;

public interface IEmpleadoService {

    public List<Empleado> findAll();

    public Empleado save(Empleado empleado);

    public void delete(Integer id);

    public Empleado findById(Integer id);
}
