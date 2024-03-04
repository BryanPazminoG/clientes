package com.banquito.core.clientes.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.banquito.core.clientes.dao.ClienteRepository;
import com.banquito.core.clientes.domain.Cliente;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<Cliente> listarTodo() {
        //log.info("Se va a obtener todos los clientes naturales");
        List<Cliente> dtos = new ArrayList<>();
        for (Cliente cliente : this.clienteRepository.findAll()) {
            if ("ACT".equals(cliente.getEstado())) {
            dtos.add(cliente);
            }
        }
        return dtos;
    }

    public Cliente obtenerPorId(String id) {
        //log.info("Se va a obtener el cliente con ID: {}", id);
        Cliente cliente = this.clienteRepository.findByIdCliente(id);
        if (cliente != null) {
            if ("ACT".equals(cliente.getEstado())) {
                //log.debug("Cliente obtenido: {}", cliente);
                return cliente;
            } 
             else {
                 throw new RuntimeException("Cliente con ID: " + id + " no se encuentra activo");
            }
        } else {
            throw new RuntimeException("No existe el cliente con el ID: " + id);
        }
    }
    
}
