package com.ufsm.csi.cozinhaapi.repo;

import com.ufsm.csi.cozinhaapi.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
