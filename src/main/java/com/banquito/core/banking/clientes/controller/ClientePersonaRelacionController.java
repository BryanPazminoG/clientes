package com.banquito.core.banking.clientes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banquito.core.banking.clientes.domain.ClientePersonaRelacion;
import com.banquito.core.banking.clientes.service.ClientePersonaRelacionService;

@RestController
@RequestMapping
public class ClientePersonaRelacionController {
    @Autowired
    private ClientePersonaRelacionService clientePersonaRelacionService;

    @GetMapping("/clienterelacion")
    public List<ClientePersonaRelacion> getall(){
        return clientePersonaRelacionService.getAll();
    }
}
