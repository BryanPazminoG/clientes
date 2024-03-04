package com.banquito.core.banking.ocliente.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.banquito.core.clientes.dao.ClienteRepository;
import com.banquito.core.clientes.domain.Cliente;
import com.banquito.core.clientes.service.ClienteService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.banquito.core.clientes")
@RestController
@RequestMapping("/api/v2/oclientes")
public class Servicios {

    private final ClienteService clienteService;

    public Servicios(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> listarClientes() {
        return ResponseEntity.ok(this.clienteService.listarTodo());
    }

}
