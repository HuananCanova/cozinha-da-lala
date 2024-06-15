package com.ufsm.csi.cozinhaapi.service;

import com.ufsm.csi.cozinhaapi.model.Usuario;
import com.ufsm.csi.cozinhaapi.repo.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario criarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> buscarUsuarioPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    public Usuario atualizarUsuario(Long id, Usuario usuarioAtualizado) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if (usuario.isPresent()) {
            usuarioAtualizado.setId(id);
            return usuarioRepository.save(usuarioAtualizado);
        } else {
            return null;
        }
    }

    public void deletarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }
}
