package com.api.kruger.service;

import com.api.kruger.entity.Empleado;
import com.api.kruger.entity.Usuario;

public interface IUsuarioService {

	public Usuario findByUsername(String username);

	public Usuario findByEmpleado(Empleado empleado);
}
