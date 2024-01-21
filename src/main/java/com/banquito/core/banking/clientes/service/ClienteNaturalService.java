package com.banquito.core.banking.clientes.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.banquito.core.banking.clientes.dao.ClienteNaturalRepository;
import com.banquito.core.banking.clientes.domain.ClienteNatural;

@Service
public class ClienteNaturalService {

    private final ClienteNaturalRepository clienteNaturalRepository;

    public ClienteNaturalService(ClienteNaturalRepository clienteNaturalRepository) {
        this.clienteNaturalRepository = clienteNaturalRepository;
    }

    public List<ClienteNatural> listarTodo(){
        return clienteNaturalRepository.findAll(); 
    }

}
