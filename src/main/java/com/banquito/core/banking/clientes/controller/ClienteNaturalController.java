package com.banquito.core.banking.clientes.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banquito.core.banking.clientes.domain.ClienteNatural;
import com.banquito.core.banking.clientes.service.ClienteNaturalService;

@RestController
@RequestMapping("/cliente-natural")
public class ClienteNaturalController {

    private final ClienteNaturalService clienteNaturalService;

    public ClienteNaturalController(ClienteNaturalService clienteNaturalService) {
        this.clienteNaturalService = clienteNaturalService;
    }

    @GetMapping("/listar")
    public List<ClienteNatural> buscarTodas(){
        return clienteNaturalService.listarTodo();
    }

}
