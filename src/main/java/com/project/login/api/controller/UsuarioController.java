package com.project.login.api.controller;

import com.project.login.domain.model.Usuario;
import com.project.login.domain.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    };

//    @GetMapping("/{id}")
//    public Optional<Usuario> findOne(@PathVariable Long id) {
//        return usuarioRepository.findById(id);
//    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> findOne(@PathVariable Long id) {

        Optional<Usuario> usuarioop = usuarioRepository.findById(id);

        if (usuarioop.isPresent()) {
            Usuario usuario = usuarioop.get();
            return ResponseEntity.ok(usuario);
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public Usuario create(@RequestBody Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> update(@RequestBody Usuario usuario, @PathVariable Long id) {

        if (!usuarioRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        usuario.setId(id);
        Usuario usuarioS = usuarioRepository.save(usuario);

        return ResponseEntity.ok(usuarioS);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        if (!usuarioRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        usuarioRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}