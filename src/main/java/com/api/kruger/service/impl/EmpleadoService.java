package com.api.kruger.service.impl;

import com.api.kruger.entity.Empleado;
import com.api.kruger.entity.Role;
import com.api.kruger.entity.Usuario;
import com.api.kruger.repository.IEmpleadoRepository;
import com.api.kruger.repository.IRoleRepository;
import com.api.kruger.repository.IUsuarioRepository;
import com.api.kruger.service.IEmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmpleadoService implements IEmpleadoService {

    @Autowired // inyeccion de dependencia
    private IEmpleadoRepository empleadoRepository; // repository

    @Autowired // inyeccion de dependencia
    private IUsuarioRepository usuarioRepository; // repository

    @Autowired // inyeccion de dependencia
    private IRoleRepository roleRepository; // repository

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    @Transactional(readOnly = true)
    public List<Empleado> findAll() {
        return (List<Empleado>) empleadoRepository.findAll();
    }

    @Override
    @Transactional
    public Empleado save(Empleado empleado) {
        empleado = empleadoRepository.save(empleado);
        Usuario user = usuarioRepository.findByEmpleado(empleado);
        String username = empleado.getEmail().split("@")[0];
        if (user == null) {
            user = new Usuario();
            user.setUsername(username);
            user.setPassword(generatePassword());
            user.setEmpleado(empleado);
            List<Role> listaRoles = new ArrayList<>();
            Role role_empleado = roleRepository.findByNombre("ROLE_EMPLEADO");
            listaRoles.add(role_empleado);//ROLE_EMPLEADO
            user.setRoles(listaRoles);
            usuarioRepository.save(user);
        } else if (user.getUsername() != username) {
            user.setUsername(username);
            usuarioRepository.save(user);
        }
        return empleado;
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        empleadoRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Empleado findById(Integer id) {
        return empleadoRepository.findById(id).orElse(null);
    }

    private String generatePassword() {
        String password = "12345";
        return passwordEncoder.encode(password);
    }
}
