package com.project.login.domain.services;

import com.project.login.domain.exception.NegocioException;
import com.project.login.domain.model.Usuario;
import com.project.login.domain.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository userRepository;


    public List<Usuario> findAll() {

        return userRepository.findAll();
    };

    public ResponseEntity<Usuario> findOne( Long id) {

        Optional<Usuario> usuarioop = userRepository.findById(id);

        if (usuarioop.isPresent()) {
            Usuario usuario = usuarioop.get();
            return ResponseEntity.ok(usuario);
        }

        return ResponseEntity.notFound().build();
    }


    public Usuario create(Usuario usuario) {

        Usuario usuarioExistente = userRepository.findByEmail(usuario.getEmail());

        if (usuarioExistente != null && !usuarioExistente.equals(usuario)) {
            throw new NegocioException("Já existe um usuário cadastrado com esse e-mail");
        }
        return userRepository.save(usuario);
    }


    public ResponseEntity<Usuario> update( Usuario usuario, Long id) {

        if (!userRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        usuario.setId(id);
        Usuario usuarioS = userRepository.save(usuario);

        return ResponseEntity.ok(usuarioS);
    }


    public ResponseEntity<Void> delete( Long id) {

        if (!userRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        userRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}