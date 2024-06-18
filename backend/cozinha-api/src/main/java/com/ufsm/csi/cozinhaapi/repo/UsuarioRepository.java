package com.ufsm.csi.cozinhaapi.repo;

import com.ufsm.csi.cozinhaapi.model.Role;
import com.ufsm.csi.cozinhaapi.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    public Optional<Usuario> findByEmail(String email);
    boolean existsByRole(Role role);
}
