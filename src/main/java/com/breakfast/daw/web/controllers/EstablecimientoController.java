package com.breakfast.daw.web.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.breakfast.daw.persintence.entities.Establecimiento;
import com.breakfast.daw.services.EstablecimientoService;

@RestController
@RequestMapping("/establecimiento")
public class EstablecimientoController {

    @Autowired
    private EstablecimientoService establecimientoService;

    @GetMapping
    public ResponseEntity<List<Establecimiento>> getEstablecimientos() {
        return ResponseEntity.ok(establecimientoService.getAllEstablecimientos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Establecimiento> getEstablecimientoById(@PathVariable int id) {
        Optional<Establecimiento> establecimiento = establecimientoService.getEstablecimientoById(id);
        if (establecimiento.isPresent()) {
            return ResponseEntity.ok(establecimiento.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Establecimiento> createEstablecimiento(@RequestBody Establecimiento establecimiento) {
        return ResponseEntity.ok(establecimientoService.createEstablecimiento(establecimiento));
    }
}
