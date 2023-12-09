package com.banquito.core.banking.clientes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banquito.core.banking.clientes.domain.Cliente;
import com.banquito.core.banking.clientes.service.ClienteService;

@RestController
@RequestMapping
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/getall")
    public List<Cliente> getall(){
        return clienteService.getAll();
    }
}
