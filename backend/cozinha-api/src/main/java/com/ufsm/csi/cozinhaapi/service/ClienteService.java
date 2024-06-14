package com.ufsm.csi.cozinhaapi.service;

import com.ufsm.csi.cozinhaapi.model.Cliente;
import com.ufsm.csi.cozinhaapi.repo.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente criarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> buscarClientePorId(Long id) {
        return clienteRepository.findById(id);
    }

    public Cliente atualizarCliente(Long id, Cliente clienteAtualizado) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        if (cliente.isPresent()) {
            clienteAtualizado.setId(id);
            return clienteRepository.save(clienteAtualizado);
        } else {
            return null;
        }
    }

    public void deletarCliente(Long id) {
        clienteRepository.deleteById(id);
    }
}

