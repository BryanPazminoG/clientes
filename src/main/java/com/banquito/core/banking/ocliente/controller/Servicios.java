package com.banquito.core.banking.ocliente.controller;

import com.banquito.core.clientes.dao.ClienteRepository;
import com.banquito.core.clientes.domain.Cliente;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v2/clientes")
public class Servicios {

    //private final ClienteRepository clienteRepository;

    //public Servicios(ClienteRepository clienteRepository) {
    //    this.clienteRepository = clienteRepository;
    //}

    @GetMapping
    public String holaMundo() {
        return "Hola Mundo";
    }
}
