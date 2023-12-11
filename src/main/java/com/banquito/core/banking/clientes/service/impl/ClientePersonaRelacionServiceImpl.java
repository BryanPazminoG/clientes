package com.banquito.core.banking.clientes.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banquito.core.banking.clientes.dao.ClientePersonaRelacionRepository;
import com.banquito.core.banking.clientes.domain.ClientePersonaRelacion;
import com.banquito.core.banking.clientes.service.ClientePersonaRelacionService;

@Service
public class ClientePersonaRelacionServiceImpl implements ClientePersonaRelacionService {

    @Autowired
    private ClientePersonaRelacionRepository clientePersonaRelacionRepository;

    @Override
    public List<ClientePersonaRelacion> getAll() {
        return (List<ClientePersonaRelacion>) clientePersonaRelacionRepository.findAll();
    }
}
