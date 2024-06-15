package com.ufsm.csi.cozinhaapi.repo;

import com.ufsm.csi.cozinhaapi.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
