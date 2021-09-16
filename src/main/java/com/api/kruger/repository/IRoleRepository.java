package com.api.kruger.repository;

import com.api.kruger.entity.Role;
import org.springframework.data.repository.CrudRepository;

public interface IRoleRepository extends CrudRepository<Role, Long> {
    public Role findByNombre(String nombre);
}
