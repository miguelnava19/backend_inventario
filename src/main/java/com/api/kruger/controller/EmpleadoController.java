package com.api.kruger.controller;

import com.api.kruger.entity.Empleado;
import com.api.kruger.service.IEmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class EmpleadoController {
    @Autowired
    private IEmpleadoService empleadoService;

    @Secured("ROLE_ADMIN")
    @GetMapping("/empleados")
    public List<Empleado> index() {
        return empleadoService.findAll();
    }

    @Secured({"ROLE_EMPLEADO", "ROLE_ADMIN"})
    @GetMapping("/empleados/{id}")
    public ResponseEntity show(@PathVariable Integer id) {
        Map<String, Object> response = new HashMap<>();
        Empleado empleadoActual = null;
        try {
            empleadoActual = empleadoService.findById(id);
            if (empleadoActual == null) {
                response.put("mensaje", "No se encontro el empleado con id: ".concat(id.toString()));
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
            }

        } catch (DataAccessException e) {
            response.put("mensaje", "Error al mostrar empleado");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Empleado>(empleadoActual, HttpStatus.OK);
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/empleados")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity create(@Valid @RequestBody Empleado empleado, BindingResult result) {
        Empleado empleadoNuevo = null;
        Map<String, Object> response = new HashMap<>();

        if (result.hasErrors()) {
            List<String> errors = validaErrores(result);
            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        try {
            empleadoNuevo = empleadoService.save(empleado);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al guardar empleado");
            String messageSpecific = e.getMostSpecificCause().getMessage();
            if (messageSpecific.contains("unique")) {
                if (messageSpecific.contains("cedula"))
                    response.put("error", "Cedula " + empleado.getCedula() + " ya se encuentra en uso");
                else if (messageSpecific.contains("email"))
                    response.put("error", "Email " + empleado.getEmail() + " ya se encuentra en uso");
            } else
                response.put("error", e.getMessage().concat(": ").concat(messageSpecific));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "El empleado ha sido creado con éxito!");
        response.put("empleado", empleadoNuevo);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @Secured({"ROLE_EMPLEADO", "ROLE_ADMIN"})
    @PutMapping("/empleados/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity update(@Valid @RequestBody Empleado empleado, BindingResult result, @PathVariable Integer id) {
        Map<String, Object> response = new HashMap<>();
        Empleado empleadoActual = null;
        if (result.hasErrors()) {
            List<String> errors = validaErrores(result);
            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        try {
            empleadoActual = empleadoService.findById(id);
            if (empleadoActual == null) {
                response.put("mensaje", "Error: no se encontro el empleado con id: " + id.toString());
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
            }
            empleadoActual.setNombres(empleado.getNombres());
            empleadoActual.setApellidos(empleado.getApellidos());

            empleadoActual.setCedula(empleado.getCedula());
            empleadoActual.setEmail(empleado.getEmail());
            empleadoActual.setFechaNacimiento(empleado.getFechaNacimiento());
            empleadoActual.setDomicilio(empleado.getDomicilio());
            empleadoActual.setTelefono(empleado.getTelefono());
            empleadoActual.setEstadoVacunacion(empleado.getEstadoVacunacion());
            empleadoActual.setTipoVacuna(empleado.getTipoVacuna());
            empleadoActual.setNumeroDosis(empleado.getNumeroDosis());
            empleadoService.save(empleadoActual);

        } catch (DataAccessException e) {
            response.put("mensaje", "Error al actualizar el empleado");
            String messageSpecific = e.getMostSpecificCause().getMessage();
            if (messageSpecific.contains("unique")) {
                if (messageSpecific.contains("cedula"))
                    response.put("error", "Cedula " + empleado.getCedula() + " ya se encuentra en uso");
                else if (messageSpecific.contains("email"))
                    response.put("error", "Email " + empleado.getEmail() + " ya se encuentra en uso");
            } else
                response.put("error", e.getMessage().concat(": ").concat(messageSpecific));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "Empleado actualizado correctamente");
        response.put("empleado", empleadoActual);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    private List<String> validaErrores(BindingResult result) {
        return result.getFieldErrors()
                .stream()
                .map(e -> "El campo: " + e.getField() + " " + e.getDefaultMessage())
                .collect(Collectors.toList());
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping("/empleados/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity delete(@PathVariable Integer id) {
        Map<String, Object> response = new HashMap<>();
        try {
            empleadoService.delete(id);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al eliminar el empleado");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "El empleado ha sido eliminado correctamente");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }
}
