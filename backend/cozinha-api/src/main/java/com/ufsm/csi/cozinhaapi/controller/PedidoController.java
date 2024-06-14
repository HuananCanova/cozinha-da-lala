package com.ufsm.csi.cozinhaapi.controller;

import com.ufsm.csi.cozinhaapi.model.Cardapio;
import com.ufsm.csi.cozinhaapi.model.Cliente;
import com.ufsm.csi.cozinhaapi.model.Pedido;
import com.ufsm.csi.cozinhaapi.service.CardapioService;
import com.ufsm.csi.cozinhaapi.service.ClienteService;
import com.ufsm.csi.cozinhaapi.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
    @Autowired
    private PedidoService pedidoService;
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private CardapioService cardapioService;

    @GetMapping
    public ResponseEntity<List<Pedido>> listarPedidos() {
        List<Pedido> pedidos = pedidoService.listarTodos();
        return new ResponseEntity<>(pedidos, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Pedido> criarPedido(@RequestBody Pedido pedido) {
        // Buscar o cliente pelo ID
        Optional<Cliente> clienteOptional = clienteService.buscarClientePorId(pedido.getCliente().getId());
        // Verificar se o cliente existe
        if (clienteOptional.isPresent()) {
            Cliente cliente = clienteOptional.get();
            pedido.setCliente(cliente);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        // Buscar o cardápio pelo ID
        Optional<Cardapio> cardapioOptional = cardapioService.buscarPorId(pedido.getCardapio().getId());
        // Verificar se o cardápio existe
        if (cardapioOptional.isPresent()) {
            Cardapio cardapio = cardapioOptional.get();
            pedido.setCardapio(cardapio);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        // Calcular o total a pagar com base no preço do cardápio e na quantidade
        double precoCardapio = pedido.getCardapio().getPreco();
        int quantidade = pedido.getQuantidade();
        double totalAPagar = precoCardapio * quantidade;
        pedido.setTotalAPagar(totalAPagar);

        // Salvar o pedido
        Pedido novoPedido = pedidoService.salvar(pedido);
        return new ResponseEntity<>(novoPedido, HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Pedido> buscarPedidoPorId(@PathVariable Long id) {
        Optional<Pedido> pedido = pedidoService.buscarPorId(id);
        if (pedido.isPresent()) {
            return new ResponseEntity<>(pedido.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pedido> atualizarPedido(@PathVariable Long id, @RequestBody Pedido pedidoAtualizado) {
        Pedido pedido = pedidoService.atualizar(id, pedidoAtualizado);
        if (pedido != null) {
            return new ResponseEntity<>(pedido, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPedidoPorId(@PathVariable Long id) {
        if (pedidoService.buscarPorId(id).isPresent()) {
            pedidoService.deletarPorId(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
