package com.ufsm.csi.cozinhaapi.controller;


import com.ufsm.csi.cozinhaapi.config.security.TokenService;
import com.ufsm.csi.cozinhaapi.dto.LoginDTO;
import com.ufsm.csi.cozinhaapi.dto.RegisterRequestDTO;
import com.ufsm.csi.cozinhaapi.dto.ResponseDTO;
import com.ufsm.csi.cozinhaapi.model.Role;
import com.ufsm.csi.cozinhaapi.model.Usuario;
import com.ufsm.csi.cozinhaapi.repo.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthController {

    @Autowired
    private final UsuarioRepository usuarioRepository;
    @Autowired
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private final TokenService tokenService;


    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginDTO loginDTO) {
        Usuario usuario = this.usuarioRepository.findByEmail(loginDTO.email()).orElseThrow(() ->new RuntimeException("usuario nao encontrado"));
        if(passwordEncoder.matches(loginDTO.senha(), usuario.getSenha())){
            String token = this.tokenService.generateToken(usuario);
            return ResponseEntity.ok(new ResponseDTO(usuario.getNome(), token, usuario.getRole()));
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterRequestDTO registerRequestDTO) {
        Optional<Usuario> usuario = this.usuarioRepository.findByEmail(registerRequestDTO.email());
        if (usuario.isEmpty()) {
            Role role = Role.CLIENTE;

            if (!this.usuarioRepository.existsByRole(Role.ADMIN)) {
                role = Role.ADMIN;
            }

            Usuario newUsuario = new Usuario();
            newUsuario.setEmail(registerRequestDTO.email());
            newUsuario.setSenha(passwordEncoder.encode(registerRequestDTO.senha()));
            newUsuario.setNome(registerRequestDTO.nome());
            newUsuario.setTelefone(registerRequestDTO.telefone());
            newUsuario.setEndereco(registerRequestDTO.endereco());
            newUsuario.setRole(role);
            this.usuarioRepository.save(newUsuario);

            String token = this.tokenService.generateToken(newUsuario);
            return ResponseEntity.ok(new ResponseDTO(newUsuario.getNome(), token, newUsuario.getRole()));
        }

        return ResponseEntity.badRequest().build();
    }

}
