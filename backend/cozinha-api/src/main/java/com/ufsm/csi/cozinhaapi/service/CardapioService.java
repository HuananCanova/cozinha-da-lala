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

    public List<Cardapio> listarTodos() {
        return cardapioRepository.findAll();
    }

    public Optional<Cardapio> buscarPorId(Long id) {
        return cardapioRepository.findById(id);
    }

    public Cardapio salvar(Cardapio cardapio) {
        cardapioRepository.save(cardapio);
        cardapio.setDiaSemana(cardapio.obterNomeDiaDaSemana());
        cardapioRepository.save(cardapio);
        return cardapio;
    }

    public void deletarPorId(Long id) {
        cardapioRepository.deleteById(id);
    }
}
