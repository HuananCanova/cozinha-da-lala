package com.ufsm.csi.cozinhaapi.service;

import com.ufsm.csi.cozinhaapi.model.Cardapio;
import com.ufsm.csi.cozinhaapi.repo.CardapioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CardapioService {
    @Autowired
    private CardapioRepository cardapioRepository;

    public List<Cardapio> findAll() {
        return cardapioRepository.findAll();
    }

    public Optional<Cardapio> findById(Long id) {
        return cardapioRepository.findById(id);
    }

    public Cardapio save(Cardapio cardapio) {
        return cardapioRepository.save(cardapio);
    }

    public void deleteById(Long id) {
        cardapioRepository.deleteById(id);
    }
}

