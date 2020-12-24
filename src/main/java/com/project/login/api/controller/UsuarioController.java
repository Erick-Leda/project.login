package com.project.login.api.controller;

import com.project.login.domain.model.Usuario;
import com.project.login.domain.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public List<Usuario> findAll() {
        return usuarioService.findAll();
    }


    @GetMapping("/{id}")
    public ResponseEntity<Usuario> findOne(@PathVariable Long id) {
        return usuarioService.findOne(id);
    }

    @PostMapping
    public Usuario create(@Valid @RequestBody Usuario usuario) {
        return usuarioService.create(usuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> update(@Valid @RequestBody Usuario usuario, @PathVariable Long id) {
        return usuarioService.update(usuario, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return usuarioService.delete(id);
    }
}