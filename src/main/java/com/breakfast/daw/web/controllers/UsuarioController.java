package com.breakfast.daw.web.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.breakfast.daw.persintence.entities.Usuario;
import com.breakfast.daw.services.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<Usuario>> getUsuarios() {
        return ResponseEntity.ok(usuarioService.getAllUsuarios());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Usuario>> getUsuariosByCliente(@PathVariable int id) {
        if (this.usuarioService.usuarioIsPresent(id)) {
            return ResponseEntity.ok(usuarioService.getUsuarioById(id));
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Usuario> createUsuario(@RequestBody Usuario usuario) {
        return ResponseEntity.ok(usuarioService.createUsuario(usuario));
    }

    @PutMapping
    public ResponseEntity<Usuario> updateUsuario(@PathVariable int id, @RequestBody Usuario usuario) {
        if (usuario.getId() != id) {
            return ResponseEntity.badRequest().build();
        }
        if (this.usuarioService.usuarioIsPresent(id)) {
            return ResponseEntity.ok(usuarioService.updateUsuario(usuario));
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping
    public ResponseEntity<Usuario> deleteUsuario(@PathVariable int id) {
        if(this.usuarioService.usuarioIsPresent(id)){
            return ResponseEntity.ok(this.usuarioService.deleteUsuario(id));
        }
        return ResponseEntity.notFound().build();
    }

    
    /*
    @PutMapping("/{id},{newPassword}")
    public ResponseEntity<Usuario> changePassword(@PathVariable int id, @PathVariable String newPassword) {
        if (this.usuarioService.usuarioIsPresent(id)) {
            return ResponseEntity.ok(usuarioService.actualizarContrasenna(id, newPassword));
        }
        return ResponseEntity.notFound().build();
    }
*/



}
