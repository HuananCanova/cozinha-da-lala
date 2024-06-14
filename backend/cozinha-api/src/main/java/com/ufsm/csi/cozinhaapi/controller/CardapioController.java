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
    public ResponseEntity<List<Cardapio>> listarCardapios() {
        List<Cardapio> cardapios = cardapioService.listarTodos();
        return new ResponseEntity<>(cardapios, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Cardapio> criarCardapio(@RequestBody Cardapio cardapio) {
        Cardapio novoCardapio = cardapioService.salvar(cardapio);
        return new ResponseEntity<>(novoCardapio, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cardapio> buscarCardapioPorId(@PathVariable Long id) {
        Optional<Cardapio> cardapio = cardapioService.buscarPorId(id);
        if (cardapio.isPresent()) {
            return new ResponseEntity<>(cardapio.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cardapio> atualizarCardapio(@PathVariable Long id, @RequestBody Cardapio cardapioAtualizado) {
        Optional<Cardapio> cardapioExistente = cardapioService.buscarPorId(id);
        if (cardapioExistente.isPresent()) {
            Cardapio cardapio = cardapioExistente.get();
            cardapio.setDescricao(cardapioAtualizado.getDescricao());
            cardapio.setPreco(cardapioAtualizado.getPreco());
            cardapio.setData(cardapioAtualizado.getData());
            Cardapio cardapioAtualizadoSalvo = cardapioService.salvar(cardapio);
            return new ResponseEntity<>(cardapioAtualizadoSalvo, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCardapioPorId(@PathVariable Long id) {
        if (cardapioService.buscarPorId(id).isPresent()) {
            cardapioService.deletarPorId(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
