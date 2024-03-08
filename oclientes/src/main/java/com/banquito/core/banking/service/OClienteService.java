package com.banquito.core.banking.service;

import org.springframework.stereotype.Service;

import com.banquito.core.banking.external.client.ClienteService;

@Service
public class OClienteService {

    private final ClienteService clienteService;

    public OClienteService(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    public String prueba(){
        return this.clienteService.pruebas();
    }


}
