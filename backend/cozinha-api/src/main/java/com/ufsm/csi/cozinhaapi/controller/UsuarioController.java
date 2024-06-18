package com.ufsm.csi.cozinhaapi.controller;

import com.ufsm.csi.cozinhaapi.config.security.CustomUserDetailsService;
import com.ufsm.csi.cozinhaapi.model.Usuario;
import com.ufsm.csi.cozinhaapi.repo.UsuarioRepository;
import com.ufsm.csi.cozinhaapi.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private UsuarioRepository usuarioRepository;


    @GetMapping("/perfil")
    public ResponseEntity<Usuario> getAuthenticatedUser(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            Usuario usuario = (Usuario) authentication.getPrincipal();

            return new ResponseEntity<>(usuario, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        List<Usuario> usuarios = usuarioService.listarUsuarios();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarUsuarioPorId(@PathVariable Long id) {
        Optional<Usuario> usuario = usuarioService.buscarUsuarioPorId(id);
        if (usuario.isPresent()) {
            return new ResponseEntity<>(usuario.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/perfil")
    public ResponseEntity<Usuario> atualizarPerfilUsuario(@AuthenticationPrincipal Usuario usuarioLogado, @RequestBody Usuario usuarioAtualizado) {
        if (usuarioLogado != null) {
            usuarioLogado.setNome(usuarioAtualizado.getNome());
            usuarioLogado.setEmail(usuarioAtualizado.getEmail());
            usuarioLogado.setTelefone(usuarioAtualizado.getTelefone());
            usuarioLogado.setEndereco(usuarioAtualizado.getEndereco());

            Usuario usuarioAtualizadoNoBanco = usuarioService.atualizarUsuario(usuarioLogado.getId(), usuarioLogado);

            if (usuarioAtualizadoNoBanco != null) {
                return new ResponseEntity<>(usuarioAtualizadoNoBanco, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }


    @DeleteMapping("/perfil")
    public ResponseEntity<Void> excluirUsuario(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            Usuario usuario = (Usuario) authentication.getPrincipal();
            usuarioRepository.delete(usuario);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }




}
