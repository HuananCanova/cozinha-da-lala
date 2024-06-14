package com.ufsm.csi.cozinhaapi.controller;


import com.ufsm.csi.cozinhaapi.model.Cardapio;
import com.ufsm.csi.cozinhaapi.service.CardapioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cardapios")
public class CardapioController {
    @Autowired
    private CardapioService cardapioService;

    @GetMapping
    public ResponseEntity<List<Cardapio>> getAllCardapios() {
        List<Cardapio> cardapios = cardapioService.findAll();
        return new ResponseEntity<>(cardapios, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Cardapio> createCardapio(@RequestBody Cardapio cardapio) {
        Cardapio newCardapio = cardapioService.save(cardapio);
        return new ResponseEntity<>(newCardapio, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cardapio> getCardapioById(@PathVariable Long id) {
        Optional<Cardapio> cardapio = cardapioService.findById(id);
        if (cardapio.isPresent()) {
            return new ResponseEntity<>(cardapio.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCardapioById(@PathVariable Long id) {
        if (cardapioService.findById(id).isPresent()) {
            cardapioService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}