package com.ufsm.csi.cozinhaapi.service;

import com.ufsm.csi.cozinhaapi.model.Pedido;
import com.ufsm.csi.cozinhaapi.repo.PedidoRepository;
import com.ufsm.csi.cozinhaapi.repo.CardapioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private CardapioRepository cardapioRepository;

    public List<Pedido> listarTodos() {
        return pedidoRepository.findAll();
    }

    public Optional<Pedido> buscarPorId(Long id) {
        return pedidoRepository.findById(id);
    }

    public Pedido salvar(Pedido pedido) {
        double precoCardapio = pedido.getCardapio().getPreco();
        int quantidade = pedido.getQuantidade();
        double totalAPagar = precoCardapio * quantidade;
        pedido.setTotalAPagar(totalAPagar);
        return pedidoRepository.save(pedido);
    }

    public Pedido atualizar(Long id, Pedido pedidoAtualizado) {
        Optional<Pedido> pedidoExistente = pedidoRepository.findById(id);
        if (pedidoExistente.isPresent()) {
            Pedido pedido = pedidoExistente.get();
            pedido.setDataPedido(pedidoAtualizado.getDataPedido());
            pedido.setUsuario(pedidoAtualizado.getUsuario());
            pedido.setCardapio(pedidoAtualizado.getCardapio());
            pedido.setQuantidade(pedidoAtualizado.getQuantidade());
            pedido.setEntrega(pedidoAtualizado.isEntrega());

            // Verifique se o cardápio do pedido atualizado não é nulo
            if (pedidoAtualizado.getCardapio() != null) {
                // Recupere o preço do cardápio selecionado no pedido atualizado
                double precoCardapio = pedidoAtualizado.getCardapio().getPreco();
                // Calcule o total a pagar com base no preço do cardápio e na quantidade
                int quantidade = pedido.getQuantidade();
                double totalAPagar = precoCardapio * quantidade;
                // Atualize o total a pagar no pedido
                pedido.setTotalAPagar(totalAPagar);
            }

            return pedidoRepository.save(pedido);
        } else {
            return null;
        }
    }


    public void deletarPorId(Long id) {
        pedidoRepository.deleteById(id);
    }
}
