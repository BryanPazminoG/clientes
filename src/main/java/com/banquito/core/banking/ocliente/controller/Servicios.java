package com.banquito.core.banking.ocliente.controller;


import com.banquito.core.empresas.domain.Empresa;
import com.banquito.core.empresas.service.EmpresaService;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import com.banquito.core.clientes.domain.Cliente;
import com.banquito.core.clientes.service.ClienteService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;



@ComponentScan("com.banquito.core.clientes")
@ComponentScan("com.banquito.core.empresas")
@RestController
@RequestMapping("/api/v2/oclientes")
public class Servicios {

    private final ClienteService clienteService;
    private final EmpresaService empresaService;

    public Servicios(ClienteService clienteService, EmpresaService empresaService) {
        this.clienteService = clienteService;
        this.empresaService = empresaService;
    }

    @GetMapping("/clientes")
    public ResponseEntity<List<Cliente>> listarClientes() {
        return ResponseEntity.ok(this.clienteService.listarTodo());
    }

    @GetMapping("/empresas")
    public ResponseEntity<List<Empresa>> listarEmpresas() {
        return ResponseEntity.ok(this.empresaService.listarTodo());
    }

}
