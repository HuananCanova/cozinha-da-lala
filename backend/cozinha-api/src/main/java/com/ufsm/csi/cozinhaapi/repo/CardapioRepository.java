package com.ufsm.csi.cozinhaapi.repo;

import com.ufsm.csi.cozinhaapi.model.Cardapio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface CardapioRepository extends JpaRepository<Cardapio, Long> {
}
