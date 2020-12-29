package com.project.login.domain.service;

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
    private UsuarioRepository usuarioRepository;

    public List<Usuario> findAll(){
        return usuarioRepository.findAll();
    }

    public ResponseEntity<Usuario> findOne(Long codigo) {
        Optional<Usuario> usuarioOp = usuarioRepository.findById(codigo);

        if (usuarioOp.isPresent()) {
            Usuario usuario = usuarioOp.get();
            return ResponseEntity.ok(usuario);
        }

        return ResponseEntity.notFound().build();
    }

    public Usuario save(Usuario usuario){
        Optional<Usuario> usuarioExistente = usuarioRepository.findByEmail(usuario.getEmail());

        if(usuarioExistente.isPresent() && !usuarioExistente.equals(usuario)){
            throw new NegocioException("Já existe um usuário cadastrado com este email.");
        }

        return usuarioRepository.save(usuario);
    }

    public ResponseEntity<Usuario> update(Usuario usuario, Long codigo) {
        if (!usuarioRepository.existsById(codigo)) {
            return ResponseEntity.notFound().build();
        }

        usuario.setCodigo(codigo);
        Usuario usuarioS = usuarioRepository.save(usuario);

        return ResponseEntity.ok(usuarioS);
    }

    public ResponseEntity<Void> delete(Long codigo) {
        if (!usuarioRepository.existsById(codigo)) {
            return ResponseEntity.notFound().build();
        }
        usuarioRepository.deleteById(codigo);

        return ResponseEntity.noContent().build();
    }
}
