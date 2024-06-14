package com.ufsm.csi.cozinhaapi.repo;

import com.ufsm.csi.cozinhaapi.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
