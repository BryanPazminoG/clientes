package com.banquito.core.banking.clientes.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banquito.core.banking.clientes.dao.ClienteRepository;
import com.banquito.core.banking.clientes.domain.Cliente;
import com.banquito.core.banking.clientes.service.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService{
    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public List<Cliente> getAll() { 
        return (List<Cliente>) clienteRepository.findAll();
    }
    
}
